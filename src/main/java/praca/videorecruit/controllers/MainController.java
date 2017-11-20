package praca.videorecruit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import praca.videorecruit.datamodel.Person;
import praca.videorecruit.repositories.FieldOfBusinessRepository;
import praca.videorecruit.repositories.PersonRepository;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private PersonRepository personRepository;
    @GetMapping("/")
    public String hello(){
        List<Person> p = personRepository.findByFirstNameAndLastName("Bartosz", "Domagała");
        p.stream().forEach(g -> System.out.println(g.getFirstName()));
        return "hello";
    }

}
