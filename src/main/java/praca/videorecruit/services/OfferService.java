package praca.videorecruit.services;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import praca.videorecruit.datamodel.Application;
import praca.videorecruit.datamodel.Company;
import praca.videorecruit.datamodel.FieldOfBusiness;
import praca.videorecruit.datamodel.Offer;
import praca.videorecruit.repositories.ApplicationRepository;
import praca.videorecruit.repositories.CompanyRepository;
import praca.videorecruit.repositories.FieldOfBusinessRepository;
import praca.videorecruit.repositories.OfferRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class OfferService {

    @Autowired
    FieldOfBusinessRepository fieldOfBusinessRepository;

    @Autowired
    OfferRepository offerRepository;
    @Autowired
    ApplicationService applicationService;
    @Autowired
    CompanyRepository companyRepository;

    public OfferDTO retrieveOfferDTO(int id){
        Offer offer = offerRepository.findOne(id);
        return new OfferDTO(offer);
    }

    public Offer retrieveOffer(int id){
        return offerRepository.findOne(id);
    }
    @Transactional
    public void saveOffer(OfferDTO dto, Company company){
        Offer offer = null;
        if(dto.getOfferId()!=null) {
            offer = offerRepository.findOne(dto.getOfferId());
        }
        if(offer==null){
            offer=new Offer();
            offer.setCompany(company);
        }
        offer.setCity(dto.getCity());
        offer.setCountry(dto.getCountry());
        offer.setDescription(dto.getDescription());
        offer.setJobTitle(dto.getJobTitle());
        offer.setName(dto.getName());
        offer.setPositionTypes(retrievePositions(dto.getPositionTypes()));
        offerRepository.save(offer);
    }
    @Transactional
    List<FieldOfBusiness> retrievePositions(List<String> positionTypes) {
        List<FieldOfBusiness> result = new ArrayList<>();
        for (String positionType : positionTypes) {
            FieldOfBusiness f = fieldOfBusinessRepository.findOne(positionType);
            if(f==null){
                f= new FieldOfBusiness();
                f.setName(positionType);
                f = fieldOfBusinessRepository.save(f);
            }
            result.add(f);
        }
        return result;
    }
    public List<FieldOfBusiness> getAllFields(){
        return fieldOfBusinessRepository.findAll();
    }

    @Transactional
    @Cacheable
    public List<Offer> getOffers() {
        return offerRepository.findAll();
    }

    @Transactional
    public List<Offer> getOffersOfCompany(Company company){
        return offerRepository.findByCompany_AccountId(company.getAccountId());
    }
    @Transactional
    public List<Offer> getOffersByLogin(String company){
        return offerRepository.findByCompany_AccountByAccountId_Email(company);
    }

    public void deleteOffer(int id, String name) throws MySQLIntegrityConstraintViolationException {
        Offer offer = offerRepository.findOne(id);
        if(offer == null){
            return;
        }
        if(offer.getCompany().getAccountByAccountId().getEmail().equals(name)){
            applicationService.deleteAll(offer.getApplicationsByOfferId());
            offerRepository.delete(id);
        }
        else{
            throw new AccessDeniedException("Nie możesz usuwać cudzych ofert.");
        }
    }


    @Transactional
    public boolean updateStatus(Offer offer){
        applicationService.updateStatus(offer.getApplicationsByOfferId(), offer.getOfferId());
        return true;
    }
}
