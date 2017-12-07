package praca.videorecruit.services;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import praca.videorecruit.datamodel.Application;
import praca.videorecruit.repositories.ApplicationRepository;

import java.util.Collection;

@Service
public class ApplicationService {

    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    FileService fileService;

    public void delete(int offerId, String user) throws MySQLIntegrityConstraintViolationException{
        Application application = applicationRepository.findByOffer_OfferIdAndPerson_AccountByAccountId_Email(offerId, user);
        if(application!=null){
            applicationRepository.delete(application);
            fileService.deleteFolder(application.getVideoUrl());
        }
    }

    public void deleteAll(Collection<Application> applications) throws MySQLIntegrityConstraintViolationException{
        for (Application application : applications) {
            if(application!=null){
                applicationRepository.delete(application);
                fileService.deleteFolder(application.getVideoUrl());
            }
        }
    }
}
