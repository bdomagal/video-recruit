package praca.videorecruit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import praca.videorecruit.datamodel.Account;
import praca.videorecruit.datamodel.Privilege;
import praca.videorecruit.datamodel.Role;
import praca.videorecruit.repositories.PrivilegeRepository;
import praca.videorecruit.repositories.RoleRepository;
import praca.videorecruit.repositories.AccountRepository;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Component
public class InitialDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {
 
    boolean alreadySetup = false;
 
    @Autowired
    private AccountRepository userRepository;
  
    @Autowired
    private RoleRepository roleRepository;
  
    @Autowired
    private PrivilegeRepository privilegeRepository;
  
    @Autowired
    private PasswordEncoder passwordEncoder;
  
    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
  
        if (alreadySetup)
            return;
        Privilege readPrivilege
          = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege
          = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
  
        List<Privilege> adminPrivileges = Arrays.asList(
          readPrivilege, writePrivilege);        
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));
 
        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        Account user = userRepository.findByEmail("admin");
        if(user == null) {
            user = new Account();
            user.setPassword(passwordEncoder.encode("admin"));
            user.setEmail("admin");
            user.setRoles(Arrays.asList(adminRole));
            user.setStatus("ADMIN");
            userRepository.save(user);
        }
        alreadySetup = true;
    }

    private Privilege createPrivilegeIfNotFound(String name) {
  
        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    private Role createRoleIfNotFound(
      String name, List<Privilege> privileges) {
  
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }
}