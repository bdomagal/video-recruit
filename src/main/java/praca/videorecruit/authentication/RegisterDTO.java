package praca.videorecruit.authentication;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class RegisterDTO {
    @NotNull(message = "{firstName.notnull}")
    @NotEmpty(message = "{firstName.notnull}")
    private String firstName;

    @NotNull(message = "{lastName.notnull}")
    @NotEmpty(message = "{lastName.notnull}")
    private String lastName;

    @NotNull(message = "{password.notnull}")
    @NotEmpty(message = "{password.notnull}")
    private String password;
    private String password2;

    private String country;
    @NotNull(message = "{email.notnull}")
    @NotEmpty(message = "{email.notnull}")
    private String email;

    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
