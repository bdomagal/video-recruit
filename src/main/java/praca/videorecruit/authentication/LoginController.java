package praca.videorecruit.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import praca.videorecruit.datamodel.Person;
import praca.videorecruit.repositories.AccountRepository;
import praca.videorecruit.repositories.CompanyRepository;
import praca.videorecruit.repositories.PersonRepository;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    UserService userService;



    @RequestMapping("/register")
    public String registerPage(Model model){
        model.addAttribute("user", new RegisterDTO());
        model.addAttribute("company", new RegisterCompanyDTO());
        return "register";
    }

    @RequestMapping("/perform_login")
    public String performLogin(Model model){
        model.addAttribute("user", new RegisterDTO());
        model.addAttribute("company", new RegisterCompanyDTO());
        return "register";
    }

    @PostMapping("/registerUser")
    public String registerUserAccount
            (@ModelAttribute("user") @Valid RegisterDTO user,
             BindingResult result, WebRequest request, Errors errors, Model model){

        if(userService.register(user, result)){
            return "redirect:/login?register=true";
        }
        model.addAttribute("user", user);
        model.addAttribute("company", new RegisterCompanyDTO());
        return "register";
    }

    @PostMapping("/registerComp")
    public String registerCompanyAccount
            (@ModelAttribute("company") @Valid RegisterCompanyDTO user,
             BindingResult result, WebRequest request, Errors errors, Model model){
        if(userService.register(user, result)){
            return "redirect:/login?register=true";
        }
        model.addAttribute("user", new RegisterDTO());
        model.addAttribute("company", user);
        return "register";
    }







    @Autowired
    AccountRepository accountRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    CompanyRepository companyRepository;
    @GetMapping("/clearUsers")
    public String test(Authentication authentication){
                accountRepository.deleteAll();
                companyRepository.deleteAll();
                personRepository.deleteAll();
                return "hello";
    }
}
