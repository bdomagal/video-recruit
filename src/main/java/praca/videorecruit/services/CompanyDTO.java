package praca.videorecruit.services;

import org.springframework.web.multipart.MultipartFile;
import praca.videorecruit.datamodel.Account;
import praca.videorecruit.datamodel.Company;
import praca.videorecruit.datamodel.Offer;

import java.util.List;

public class CompanyDTO {

    private Integer accountId;
    private String name;
    private String address;
    private String description;
    private String homePage;
    private String imageUrl;
    private MultipartFile image;
    private Account accountByAccountId;
    private List<Offer> offersByAccountId;

    public CompanyDTO() {
    }

    public CompanyDTO(Company company){
        this.accountByAccountId=company.getAccountByAccountId();
        this.accountId=company.getAccountId();
        this.address=company.getAddress();
        this.description=company.getDescription();
        this.homePage = company.getHomePage();
        this.imageUrl=company.getImageUrl();
        this.name = company.getName();
    }
    public Integer getAccountId() {
        return accountId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public Account getAccountByAccountId() {
        return accountByAccountId;
    }

    public void setAccountByAccountId(Account accountByAccountId) {
        this.accountByAccountId = accountByAccountId;
    }

    public List<Offer> getOffersByAccountId() {
        return offersByAccountId;
    }

    public void setOffersByAccountId(List<Offer> offersByAccountId) {
        this.offersByAccountId = offersByAccountId;
    }
}
