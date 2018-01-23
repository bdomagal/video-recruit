package praca.videorecruit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import praca.videorecruit.datamodel.Person;
import praca.videorecruit.repositories.FieldOfBusinessRepository;
import praca.videorecruit.repositories.OfferRepository;
import praca.videorecruit.repositories.PersonRepository;
import praca.videorecruit.services.OfferService;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    OfferRepository offerRepository;

    @GetMapping("/")
    public String hello(Model model){
        model.addAttribute("offers", offerRepository.findTop10ByOrderByPostedOn());
        return "hello";
    }

}
