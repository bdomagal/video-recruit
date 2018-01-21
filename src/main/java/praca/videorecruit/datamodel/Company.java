package praca.videorecruit.datamodel;

import javax.persistence.*;
import java.util.List;

@Entity
public class Company {
    private Integer accountId;
    private String name;
    private String address;
    private String description;
    private String homePage;
    private String imageUrl;
    private Account accountByAccountId;
    private List<Offer> offersByAccountId;

    @Id
    @Column(name = "accountId", nullable = false)
    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
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
    @Column(name = "address", nullable = false, length = 1000)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 6000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "homePage", nullable = true, length = 2000)
    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String shortDesc) {
        this.homePage = shortDesc;
    }

    @Basic
    @Column(name = "imageUrl", nullable = true, length = 2000)
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        if (accountId != null ? !accountId.equals(company.accountId) : company.accountId != null) return false;
        if (name != null ? !name.equals(company.name) : company.name != null) return false;
        if (address != null ? !address.equals(company.address) : company.address != null) return false;
        if (description != null ? !description.equals(company.description) : company.description != null) return false;
        if (homePage != null ? !homePage.equals(company.homePage) : company.homePage != null) return false;
        if (imageUrl != null ? !imageUrl.equals(company.imageUrl) : company.imageUrl != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = accountId != null ? accountId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (homePage != null ? homePage.hashCode() : 0);
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "accountId", referencedColumnName = "accountId", nullable = false)
    public Account getAccountByAccountId() {
        return accountByAccountId;
    }

    public void setAccountByAccountId(Account accountByAccountId) {
        this.accountByAccountId = accountByAccountId;
    }

    @OneToMany(mappedBy = "company")
    public List<Offer> getOffersByAccountId() {
        return offersByAccountId;
    }

    public void setOffersByAccountId(List<Offer> offersByAccountId) {
        this.offersByAccountId = offersByAccountId;
    }
}
