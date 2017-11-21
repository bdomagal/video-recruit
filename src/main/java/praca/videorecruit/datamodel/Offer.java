package praca.videorecruit.datamodel;

import javax.persistence.*;
import java.util.List;

@Entity
public class Offer {
    private Integer offerId;
    private String name;
    private String description;
    private String jobTitle;
    private String city;
    private String country;
    private List<Application> applicationsByOfferId;
    private Company company;
    private List<FieldOfBusiness> positionTypes;



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "offerId", nullable = false)
    public Integer getOfferId() {
        return offerId;
    }

    public void setOfferId(Integer offerId) {
        this.offerId = offerId;
    }


    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description", nullable = false, length = 6000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "jobTitle", nullable = true, length = 255)
    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Basic
    @Column(name = "city", nullable = true, length = 255)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "country", nullable = false, length = 255)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Offer offer = (Offer) o;

        if (offerId != null ? !offerId.equals(offer.offerId) : offer.offerId != null) return false;
        if (name != null ? !name.equals(offer.name) : offer.name != null) return false;
        if (description != null ? !description.equals(offer.description) : offer.description != null) return false;
        if (jobTitle != null ? !jobTitle.equals(offer.jobTitle) : offer.jobTitle != null) return false;
        if (city != null ? !city.equals(offer.city) : offer.city != null) return false;
        if (country != null ? !country.equals(offer.country) : offer.country != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = offerId != null ? offerId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (jobTitle != null ? jobTitle.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "offer")
    public List<Application> getApplicationsByOfferId() {
        return applicationsByOfferId;
    }

    public void setApplicationsByOfferId(List<Application> applicationsByOfferId) {
        this.applicationsByOfferId = applicationsByOfferId;
    }

    @ManyToOne
    @JoinColumn(name = "companyId", referencedColumnName = "accountId", nullable = false)
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company companyByCompanyId) {
        this.company = companyByCompanyId;
    }


    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Offer_Field",
            joinColumns = { @JoinColumn(name = "offerId") },
            inverseJoinColumns = { @JoinColumn(name = "name") }
    )
    public List<FieldOfBusiness> getPositionTypes() {
        return positionTypes;
    }

    public void setPositionTypes(List<FieldOfBusiness> positionTypesByOfferId) {
        this.positionTypes = positionTypesByOfferId;
    }
}
