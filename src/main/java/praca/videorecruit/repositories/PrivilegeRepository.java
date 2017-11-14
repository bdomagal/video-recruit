package praca.videorecruit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import praca.videorecruit.datamodel.Privilege;

import java.util.List;

public interface PrivilegeRepository extends JpaRepository<Privilege, Integer>{
    Privilege findByName(String name);
    List<Privilege> findByRoles_Name(String roleName);
}
