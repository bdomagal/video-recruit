package praca.videorecruit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import praca.videorecruit.datamodel.Account;
import praca.videorecruit.datamodel.FieldOfBusiness;

@Repository
public interface FieldOfBusinessRepository extends JpaRepository<FieldOfBusiness, String> {
}
