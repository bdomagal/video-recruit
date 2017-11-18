package praca.videorecruit.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import praca.videorecruit.datamodel.Account;
import praca.videorecruit.datamodel.Company;
import praca.videorecruit.datamodel.Person;
import praca.videorecruit.repositories.AccountRepository;
import praca.videorecruit.repositories.CompanyRepository;
import praca.videorecruit.repositories.PersonRepository;
import praca.videorecruit.repositories.RoleRepository;

import javax.transaction.Transactional;

@Component
public class UserService {

    final
    AccountRepository accountRepository;
    final
    PersonRepository personRepository;
    final
    CompanyRepository companyRepository;
    final
    RoleRepository roleRepository;
    final
    PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(AccountRepository accountRepository, PersonRepository personRepository, CompanyRepository companyRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.personRepository = personRepository;
        this.companyRepository = companyRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public boolean register(RegisterDTO user, BindingResult result){
        boolean isValid = true;
        if(!checkEmail(user.getEmail())){
            isValid=false;
            result.rejectValue("email", "message.alreadyRegistered");
        }
        if(user.getPassword().length()<8){
            isValid=false;
            result.rejectValue("password", "message.tooShort");
        }
        if(!user.getPassword().equals(user.getPassword2())){
            isValid=false;
            result.rejectValue("password", "message.differentPassword");
        }
        if(isValid){
            Account account = new Account();
            account.setEmail(user.getEmail());
            account.setPassword(passwordEncoder.encode(user.getPassword()));
            account.setStatus("nActive");
            account.getRoles().add(roleRepository.findByName("ROLE_USER"));
            accountRepository.save(account);
            Person p= new Person();
            p.setAccountByAccountId(accountRepository.findByEmail(user.getEmail()));
            p.setFirstName(user.getFirstName());
            p.setLastName(user.getLastName());
            p.setAccountId(account.getAccountId());
            p.setCountry(user.getCountry());
            personRepository.save(p);
        }
        return isValid;
    }

    private boolean checkEmail(String email){
        return accountRepository.findByEmail(email)==null;
    }

    @Transactional
    public boolean register(RegisterCompanyDTO user, BindingResult result){
        boolean isValid = true;
        if(!checkEmail(user.getEmail())){
            isValid=false;
            result.rejectValue("email", "message.alreadyRegistered");
        }
        if(!isNameAvailable(user.getName())){
            isValid=false;
            result.rejectValue("name", "message.usedName");
            }
        if(user.getPasswordComp().length()<8){
            isValid=false;
            result.rejectValue("passwordComp", "message.tooShort");
        }
        if(!user.getPasswordComp().equals(user.getPasswordComp2())){
            isValid=false;
            result.rejectValue("passwordComp", "message.differentPassword");
        }
        if(isValid){
            Account account = new Account();
            account.setEmail(user.getEmail());
            account.setPassword(passwordEncoder.encode(user.getPasswordComp()));
            account.setStatus("nActive");
            account.getRoles().add(roleRepository.findByName("ROLE_COMPANY"));
            accountRepository.save(account);
            Company p= new Company();
            p.setAccountByAccountId(accountRepository.findByEmail(user.getEmail()));
            p.setName(user.getName());
            p.setAddress(user.getAddress());
            p.setAccountId(account.getAccountId());
            companyRepository.save(p);
        }
        return isValid;
    }

    private boolean isNameAvailable(String name) {
        return companyRepository.findByName(name)==null;
    }
}
