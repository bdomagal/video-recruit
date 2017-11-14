package praca.videorecruit.datamodel;

import javax.persistence.*;
import java.util.List;

@Entity
public class Application {
    private Integer applicationId;
    private String status;
    private String cvUrl;
    private String videoUrl;
    private Offer offer;
    private Person person;
    private List<File> files;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "applicationId", nullable = false)
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
    @Column(name = "cvURL", nullable = true, length = 1000)
    public String getCvUrl() {
        return cvUrl;
    }

    public void setCvUrl(String cvUrl) {
        this.cvUrl = cvUrl;
    }

    @Basic
    @Column(name = "videoURL", nullable = true, length = 1000)
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

        if (applicationId != null ? !applicationId.equals(that.applicationId) : that.applicationId != null)
            return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (cvUrl != null ? !cvUrl.equals(that.cvUrl) : that.cvUrl != null) return false;
        if (videoUrl != null ? !videoUrl.equals(that.videoUrl) : that.videoUrl != null) return false;

        return true;
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
    @JoinColumn(name = "offerId", referencedColumnName = "offerId", nullable = false)
    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offerByOfferId) {
        this.offer = offerByOfferId;
    }

    @ManyToOne
    @JoinColumn(name = "personId", referencedColumnName = "accountId", nullable = false)
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person personByPersonId) {
        this.person = personByPersonId;
    }

    @OneToMany(mappedBy = "application")
    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> filesByApplicationId) {
        this.files = filesByApplicationId;
    }
}
