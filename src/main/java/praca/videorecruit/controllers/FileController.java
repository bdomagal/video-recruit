package praca.videorecruit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import praca.videorecruit.datamodel.Application;
import praca.videorecruit.datamodel.Offer;
import praca.videorecruit.repositories.ApplicationRepository;
import praca.videorecruit.repositories.OfferRepository;
import praca.videorecruit.repositories.PersonRepository;
import praca.videorecruit.services.FileService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@RestController
public class FileController {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private FileService fileService;

    @GetMapping(value = "/movie/{applicationId}")
    @ResponseBody
    public FileSystemResource plain(Authentication authentication, HttpServletResponse response, @PathVariable int applicationId) {

        return null;
    }


    @GetMapping(value = "/getFile/{personId}/{offer}/{fileName:.+}")
    @ResponseBody
    public Resource getFile(Authentication authentication, HttpServletResponse response,
                                      @PathVariable("offer") int offerId, @PathVariable("fileName") String filename,
                            @PathVariable("personId") int personId) {
        Offer offer = offerRepository.findOne(offerId);
        if(personRepository.findOne(personId).getAccountByAccountId().getEmail()==authentication.getName()
                || offer.getCompany().getAccountByAccountId().getEmail()==authentication.getName()){

        }
        try {
            return fileService.loadAsResource(personId+"/"+offerId+"/"+filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
