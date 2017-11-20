package praca.videorecruit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import praca.videorecruit.datamodel.Account;
import praca.videorecruit.datamodel.Application;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    List<Application> findByPerson_AccountByAccountId_Email(String email);
    List<Application> findByOffer_OfferId(int id);
}
