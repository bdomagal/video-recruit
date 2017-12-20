package praca.videorecruit.authentication;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class RegisterCompanyDTO {
    @NotNull(message = "{name.notnull}")
    @NotEmpty(message = "{name.notnull}")
    private String name;


    @NotNull(message = "{address.notnull}")
    @NotEmpty(message = "{address.notnull}")
    private String address;

    @NotNull(message = "{password.notnull}")
    @NotEmpty(message = "{password.notnull}")
    private String passwordComp;
    private String passwordComp2;

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
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswordComp() {
        return passwordComp;
    }

    public void setPasswordComp(String passwordComp) {
        this.passwordComp = passwordComp;
    }

    public String getPasswordComp2() {
        return passwordComp2;
    }

    public void setPasswordComp2(String passwordComp2) {
        this.passwordComp2 = passwordComp2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
