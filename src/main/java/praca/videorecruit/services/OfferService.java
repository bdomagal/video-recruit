package praca.videorecruit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import praca.videorecruit.datamodel.Company;
import praca.videorecruit.datamodel.FieldOfBusiness;
import praca.videorecruit.datamodel.Offer;
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
}
