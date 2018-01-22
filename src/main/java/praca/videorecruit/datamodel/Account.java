package praca.videorecruit.datamodel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Account {
    private Integer accountId;
    private String email;
    private String password;
    private String status;
    private String phone;
    private Company companyByAccountId;
    private Person personByAccountId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id", nullable = false)
    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    @Basic
    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "status", nullable = false, length = 10)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "phone", length = 12)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (accountId != null ? accountId.equals(account.accountId) : account.accountId == null)
            if (email != null ? email.equals(account.email) : account.email == null)
                if (password != null ? password.equals(account.password) : account.password == null)
                    if (status != null ? status.equals(account.status) : account.status == null)
                        if (phone != null ? phone.equals(account.phone) : account.phone == null) return true;
        return false;
    }

    @Override
    public int hashCode() {
        int result = accountId != null ? accountId.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }

    @OneToOne(mappedBy = "accountByAccountId")
    public Company getCompanyByAccountId() {
        return companyByAccountId;
    }

    public void setCompanyByAccountId(Company companyByAccountId) {
        this.companyByAccountId = companyByAccountId;
    }

    @OneToOne(mappedBy = "accountByAccountId")
    public Person getPersonByAccountId() {
        return personByAccountId;
    }

    public void setPersonByAccountId(Person personByAccountId) {
        this.personByAccountId = personByAccountId;
    }

    private List<Role> roles = new ArrayList<>();


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "account_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
