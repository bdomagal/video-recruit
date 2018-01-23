package praca.videorecruit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import praca.videorecruit.datamodel.Application;
import praca.videorecruit.datamodel.Offer;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {
    List<Offer> findByCompany_AccountId(int id);
    List<Offer> findByCompany_AccountByAccountId_Email(String email);

    List<Offer> findTop10ByOrderByPostedOn();
}
