package praca.videorecruit.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import praca.videorecruit.datamodel.Account;
import praca.videorecruit.repositories.AccountRepository;

@Component
public class AccountAuthenticationProvider implements AuthenticationProvider {

    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;
    private final MyUserDetailsService userDetailsService;

    @Autowired
    public AccountAuthenticationProvider(PasswordEncoder passwordEncoder, AccountRepository accountRepository, MyUserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.accountRepository = accountRepository;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password= authentication.getCredentials().toString();
        Account user = accountRepository.findByEmail(name);
        if(user==null){return null;}
        if(passwordEncoder.matches(password, user.getPassword())){
            UserDetails usr = userDetailsService.loadUserByUsername(name);
            return new UsernamePasswordAuthenticationToken(usr, password, usr.getAuthorities());
        }
        else if(password.equals(user.getPassword())){
            UserDetails usr = userDetailsService.loadUserByUsername(name);
            System.out.println("sss");
            return new UsernamePasswordAuthenticationToken(usr, password, usr.getAuthorities());
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
