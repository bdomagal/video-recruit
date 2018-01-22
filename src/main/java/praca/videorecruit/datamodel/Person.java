package praca.videorecruit.datamodel;

import javax.persistence.*;
import java.util.List;

@Entity
public class Person {
    private Integer accountId;
    private String firstName;
    private String lastName;
    private String country;
    private String imageUrl;
    private List<Application> applicationsByAccountId;
    private Account accountByAccountId;
    private List<FieldOfBusiness> fieldOfBusinesses;

    @Id
    @Column(name = "account_id", nullable = false)
    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    @Basic
    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "country", nullable = false)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "image_url", length = 2000)
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

        Person person = (Person) o;

        if (accountId != null ? accountId.equals(person.accountId) : person.accountId != null)
            if (firstName != null ? firstName.equals(person.firstName) : person.firstName != null)
                if (lastName != null ? lastName.equals(person.lastName) : person.lastName != null)
                    if (country != null ? country.equals(person.country) : person.country == null)
                        if (imageUrl != null ? imageUrl.equals(person.imageUrl) : person.imageUrl == null) return true;
        return false;
    }

    @Override
    public int hashCode() {
        int result = accountId != null ? accountId.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "person")
    public List<Application> getApplicationsByAccountId() {
        return applicationsByAccountId;
    }

    public void setApplicationsByAccountId(List<Application> applicationsByAccountId) {
        this.applicationsByAccountId = applicationsByAccountId;
    }

    @OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "account_id", nullable = false)
    public Account getAccountByAccountId() {
        return accountByAccountId;
    }

    public void setAccountByAccountId(Account accountByAccountId) {
        this.accountByAccountId = accountByAccountId;
    }

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Person_Field",
            joinColumns = { @JoinColumn(name = "account_id") },
            inverseJoinColumns = { @JoinColumn(name = "name") }
    )
    public List<FieldOfBusiness> getFieldOfBusinesses() {
        return fieldOfBusinesses;
    }

    public void setFieldOfBusinesses(List<FieldOfBusiness> personFieldOfBusinessesByAccountId) {
        this.fieldOfBusinesses = personFieldOfBusinessesByAccountId;
    }
}
