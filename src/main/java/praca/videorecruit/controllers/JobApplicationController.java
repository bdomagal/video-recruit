package praca.videorecruit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import praca.videorecruit.datamodel.Application;
import praca.videorecruit.datamodel.Offer;
import praca.videorecruit.repositories.ApplicationRepository;
import praca.videorecruit.repositories.PersonRepository;
import praca.videorecruit.services.ApplicationDTO;
import praca.videorecruit.services.FileService;
import praca.videorecruit.services.OfferService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class JobApplicationController {

    @Autowired
    FileService fileService;
    @Autowired
    OfferService offerService;
    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    PersonRepository personRepository;

    @GetMapping({"/apply/{id}/new", "/offer/{id}"})
    public String printApplicationForm(Model model, @PathVariable("id") int id, Authentication authentication){
        Application app = applicationRepository.findByOffer_OfferIdAndPerson_AccountByAccountId_Email(id, authentication.getName());
        model.addAttribute("offer", offerService.retrieveOffer(id));
        model.addAttribute("job", new ApplicationDTO(app));
        return "applicationForm";
    }

    @PostMapping({"/apply/{id}/new", "/offer/{id}"})
    public String saveJobAppl(Authentication authentication,
            @PathVariable("id") int id, @ModelAttribute("job") ApplicationDTO applicationDTO){
        int accountId = personRepository.findByAccountByAccountId_Email(authentication.getName()).getAccountId();
        try {
            Application app=applicationRepository.findByOffer_OfferIdAndPerson_AccountByAccountId_Email(id, authentication.getName());
            if(app==null){app= new Application();}
            String video = fileService.store(applicationDTO.getVideo(), accountId+"/"+id+"/", "video");
            String cv  = fileService.store(applicationDTO.getCv(), accountId+"/"+id+"/", "cv");
            String files = fileService.store(applicationDTO.getOtherFiles(), accountId+"/"+id+"/", "files");
            app.setCvUrl(accountId+"/"+id+"/"+ cv);
            app.setVideoUrl(accountId+"/"+id+"/"+video);
            app.setOtherFilesUrl(accountId+"/"+id+"/"+files);
            app.setOffer(offerService.retrieveOffer(id));
            app.setStatus("submitted");
            app.setPerson(personRepository.findByAccountByAccountId_Email(authentication.getName()));
            applicationRepository.save(app);
        } catch (Exception e) {

        }
        return "redirect:/apply/"+id+"/new?s=true";
    }

    @GetMapping("/offers")
    public String showJobs(Model model){
        model.addAttribute("offers", offerService.getOffers());
        return "offerList";
    }

    @PostMapping("/apply/{id}/delete")
    public String deleteApplication(Authentication authentication, @PathVariable("id") int offerId){
        Application application = applicationRepository.findByOffer_OfferIdAndPerson_AccountByAccountId_Email(offerId, authentication.getName());
        if(application!=null){
            applicationRepository.delete(application);
            fileService.deleteFolder(application.getVideoUrl());
        }
        return "redirect:/apply/"+offerId+"/new?s=true";
    }

    @GetMapping("/myProfile/applications")
    public String getMyApplications(Authentication authentication, Model model){
        List<Application> applicationList = applicationRepository.findByPerson_AccountByAccountId_Email(authentication.getName());
        model.addAttribute("applications", applicationList);
        return "applicationList";
    }
}
