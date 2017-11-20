package praca.videorecruit.services;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;
import praca.videorecruit.datamodel.FieldOfBusiness;
import praca.videorecruit.datamodel.Offer;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Component
public class OfferDTO {
    private Integer offerId;

    @NotNull(message = "{required")
    @NotEmpty(message = "{required}")
    private String name;
    @NotNull(message = "{required")
    @NotEmpty(message = "{required}")
    private String description;
    private String jobTitle;
    private String city;
    @NotNull(message = "{required")
    @NotEmpty(message = "{required}")
    private String country;
    private List<String> positionTypes = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<String> getPositionTypes() {
        return positionTypes;
    }

    public void setPositionTypes(List<String> positionTypes) {
        this.positionTypes = positionTypes;
    }

    public void setPositionTypesByField(List<FieldOfBusiness> positionTypes){
        this.positionTypes = new ArrayList<>();
        for (FieldOfBusiness positionType : positionTypes) {
            this.positionTypes.add(positionType.getName());
        }
    }

    public Integer getOfferId() {
        return offerId;
    }

    public void setOfferId(Integer offerId) {
        this.offerId = offerId;
    }

    public OfferDTO(String name, String description, String jobTitle, String city, String country, List<FieldOfBusiness> positionTypes, Integer offerId) {
        this.name = name;
        this.description = description;
        this.jobTitle = jobTitle;
        this.city = city;
        this.country = country;
        this.offerId = offerId;

        this.positionTypes = new ArrayList<>();
        for (FieldOfBusiness positionType : positionTypes) {
            this.positionTypes.add(positionType.getName());
        }
    }
    public OfferDTO(Offer offer) {
        if(offer==null){return;}
        this.name = offer.getName();
        this.description = offer.getDescription();
        this.jobTitle = offer.getJobTitle();
        this.city = offer.getCity();
        this.country = offer.getCountry();
        this.offerId = offer.getOfferId();
        this.positionTypes = new ArrayList<>();
        for (FieldOfBusiness positionType : offer.getPositionTypes()) {
            this.positionTypes.add(positionType.getName());
        }
    }
    public OfferDTO() {
        positionTypes = new ArrayList<>();
    }
}
