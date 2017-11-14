package praca.videorecruit.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public String loginPage(
            @RequestParam(name = "error", required = false)boolean error,
            @RequestParam(name = "register", required = false) boolean register,
            Model model){
        if(register){model.addAttribute("error", "Rejestracja przebiegła pomyślnie, możesz się zalogować.");}
        if(error){model.addAttribute("error", "Niepoprawny email lub hasło");}
        return "login";
    }
    @RequestMapping("/register")
    public String registerPage(Model model){
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
}
