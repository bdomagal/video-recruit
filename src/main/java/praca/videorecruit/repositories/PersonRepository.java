package praca.videorecruit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import praca.videorecruit.datamodel.Account;
import praca.videorecruit.datamodel.Person;

import java.util.ArrayList;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    @Query("SELECT p FROM Person p INNER JOIN p.fieldOfBusinesses fields WHERE  ?1 IN (fields)")
    ArrayList<Person> findByFieldOfBusinesses_Name(String name);

    ArrayList<Person> findByFirstNameAndLastName(String firstName, String lastName);

    Person findByAccountByAccountId_Email(String email);
}
