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
import praca.videorecruit.repositories.ApplicationRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@RestController
public class FileController {

    @Autowired
    private ApplicationRepository applicationRepository;
    private final static String BASE_PATH = "src/main/resources/userFiles/";

    @GetMapping(value = "/movie/{applicationId}")
    @ResponseBody
    public FileSystemResource plain(Authentication authentication, HttpServletResponse response, @PathVariable int applicationId) {
        Application application = applicationRepository.findOne(applicationId);
        String fileName = application.getVideoUrl();
        String user = ""+application.getPerson().getAccountId();
        File file = new File(BASE_PATH + user+"/"+fileName);
        System.out.println(file.length());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName()+"\"");
        return new FileSystemResource(file);
    }


}
