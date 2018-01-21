package praca.videorecruit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import praca.videorecruit.datamodel.Company;
import praca.videorecruit.datamodel.Person;

import java.util.ArrayList;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    Company findByName(String name);
    Company findByAccountByAccountId_Email(String name);
    Company findByAccountId(int id);
}
