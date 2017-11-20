package praca.videorecruit.controllers;

import com.sun.org.apache.xml.internal.security.keys.storage.StorageResolverException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import praca.videorecruit.repositories.AccountRepository;
import praca.videorecruit.services.ApplicationDTO;
import praca.videorecruit.services.FileService;

@Controller
public class JobApplicationController {

    @Autowired
    FileService fileService;
    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/apply/{id}/new")
    public String printApplicationForm(Model model){
        model.addAttribute("job", new ApplicationDTO());
        return "applicationForm";
    }

    @PostMapping("/apply/{id}/new")
    public String saveJobAppl(Authentication authentication,
            Model model, @PathVariable("id") int id, @ModelAttribute("job") ApplicationDTO applicationDTO) throws StorageResolverException {
        int accountId = accountRepository.findByEmail(authentication.getName()).getAccountId();
        fileService.store(applicationDTO.getVideo(), accountId+"/"+id+"/", "video");
        fileService.store(applicationDTO.getCv(), accountId+"/"+id+"/", "cv");
        return "applicationForm";
    }
}
