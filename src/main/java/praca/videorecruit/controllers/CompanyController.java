package praca.videorecruit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import praca.videorecruit.datamodel.FieldOfBusiness;
import praca.videorecruit.repositories.CompanyRepository;
import praca.videorecruit.repositories.FieldOfBusinessRepository;
import praca.videorecruit.services.OfferDTO;
import praca.videorecruit.services.OfferService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class CompanyController {

    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    OfferService offerService;

    @GetMapping("/myProfile/company")
    public String companyProfile(Model model, Authentication authentication){
        model.addAttribute(companyRepository.findByAccountByAccountId_Email(authentication.getName()));
        return "companyProfile";
    }

    @GetMapping("/myProfile/company/edit")
    public String companyEdit(Model model, Authentication authentication){
        model.addAttribute(companyRepository.findByAccountByAccountId_Email(authentication.getName()));
        return "companyEditForm";
    }

    @GetMapping("/createOffer")
    public String printOfferForm(Model model){
        model.addAttribute("offer", new OfferDTO());
        model.addAttribute("fields", offerService.getAllFields());
        return "offerForm";
    }

    @PostMapping("/createOffer")
    public String createOffer(Model model, @ModelAttribute("offer") @Valid OfferDTO offerDTO, BindingResult result,
                              Authentication authentication, WebRequest request, Errors errors){
        offerService.saveOffer(offerDTO, companyRepository.findByAccountByAccountId_Email(authentication.getName()));
        model.addAttribute("message", "Utworzono nową ofertę");
        return "offerForm";
    }

    @GetMapping("/{id}/createOffer")
    public String printOfferEdit(Model model, @PathVariable("id") int id){
        model.addAttribute("offer", offerService.retrieveOffer(id));
        model.addAttribute("fields", offerService.getAllFields());
        return "offerForm";
    }

    @PostMapping("/{id}/createOffer")
    public String editOffer(Model model, @ModelAttribute("offer") @Valid OfferDTO offerDTO, BindingResult result,
                              Authentication authentication, WebRequest request, Errors errors){
        offerService.saveOffer(offerDTO, companyRepository.findByAccountByAccountId_Email(authentication.getName()));
        model.addAttribute("message", "Zmodyfikowano ofertę");
        return "offerForm";
    }
}
