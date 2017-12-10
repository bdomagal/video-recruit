package praca.videorecruit.services;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import praca.videorecruit.datamodel.Application;
import praca.videorecruit.repositories.ApplicationRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    FileService fileService;
    @Autowired
    EmailServiceImpl emailService;

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
    @Transactional
    public boolean updateStatus(Collection<Application> applications, int offerId){
        List<Application> apps = applicationRepository.findByOffer_OfferId(offerId);
        Iterator<Application> dst = apps.listIterator();
        Iterator<Application> src = applications.iterator();
        while(src.hasNext()&&dst.hasNext()){
            Application dst1 = dst.next();
            Application src1 = src.next();
            if(dst1.getApplicationId()==src1.getApplicationId()){
                if(src1.getStatus().equals("accepted")&&!src1.getStatus().equals(dst1.getStatus())){
                    emailService.sendSimpleMessage(dst1.getPerson().getAccountByAccountId().getEmail(), "Twoje podanie zostało zaakceptowane"
                    , "Twoje podanie zostało na stanowisko " + dst1.getOffer().getName()+ " zaakceptowane. Akceptacja nie jest wiążąca i niedaje pewności " +
                                    "zaproszenia na rozmowę.\n\n\n" +
                                    "Nie odpowiadaj na ten email. Wygenerowano automatycznie przez wideo-rekrut.pl");
                }
                dst1.setStatus(src1.getStatus());
            }
            else{
                dst = apps.listIterator();
                do{
                    dst1=dst.next();
                }while(dst.hasNext()&&dst1.getApplicationId()==src1.getApplicationId());
            }
        }
        applicationRepository.save(apps);
        return true;
    }
}
