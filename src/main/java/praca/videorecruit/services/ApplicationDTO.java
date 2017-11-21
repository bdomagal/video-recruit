package praca.videorecruit.services;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import praca.videorecruit.datamodel.Application;
import praca.videorecruit.datamodel.Offer;
import praca.videorecruit.datamodel.Person;

@Component
public class ApplicationDTO {
    private Integer applicationId;
    private String status;
    private String cvUrl;
    private MultipartFile video;
    private MultipartFile cv;
    private String videoUrl;
    private Offer offer;
    private Person person;

    public ApplicationDTO(Application app) {
        if(app==null){return;}
        this.applicationId = app.getApplicationId();
        this.status = app.getStatus();
        this.cvUrl = app.getCvUrl();
        this.videoUrl = app.getVideoUrl();
        this.offer = app.getOffer();
        this.person = app.getPerson();
    }

    public ApplicationDTO() {

    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCvUrl() {
        return cvUrl;
    }

    public void setCvUrl(String cvUrl) {
        this.cvUrl = cvUrl;
    }

    public MultipartFile getVideo() {
        return video;
    }

    public void setVideo(MultipartFile video) {
        this.video = video;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public MultipartFile getCv() {
        return cv;
    }

    public void setCv(MultipartFile cv) {
        this.cv = cv;
    }
}
