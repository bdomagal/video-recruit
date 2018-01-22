package praca.videorecruit.datamodel;

import javax.persistence.*;

@Entity
public class Application {
    private Integer applicationId;
    private String status;
    private String cvUrl;
    private String videoUrl;
    private Offer offer;
    private Person person;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id", nullable = false)
    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }


    @Basic
    @Column(name = "status", nullable = false, length = 20)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "cvURL", length = 1000)
    public String getCvUrl() {
        return cvUrl;
    }

    public void setCvUrl(String cvUrl) {
        this.cvUrl = cvUrl;
    }

    @Basic
    @Column(name = "videoURL", length = 1000)
    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Application that = (Application) o;

        if (applicationId != null ? applicationId.equals(that.applicationId) : that.applicationId == null)
            if (status != null ? status.equals(that.status) : that.status == null)
                if (cvUrl != null ? cvUrl.equals(that.cvUrl) : that.cvUrl == null)
                    if (videoUrl != null ? videoUrl.equals(that.videoUrl) : that.videoUrl == null) return true;
        return false;
    }

    @Override
    public int hashCode() {
        int result = applicationId != null ? applicationId.hashCode() : 0;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (cvUrl != null ? cvUrl.hashCode() : 0);
        result = 31 * result + (videoUrl != null ? videoUrl.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "offer_id", referencedColumnName = "offer_id", nullable = false)
    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offerByOfferId) {
        this.offer = offerByOfferId;
    }

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "account_id", nullable = false)
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person personByPersonId) {
        this.person = personByPersonId;
    }

}
