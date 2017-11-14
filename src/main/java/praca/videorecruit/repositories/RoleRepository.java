package praca.videorecruit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import praca.videorecruit.datamodel.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
    Role findByName(String name);
}
