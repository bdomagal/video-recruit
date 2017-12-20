package praca.videorecruit;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.test.context.junit4.SpringRunner;
import praca.videorecruit.datamodel.Account;
import praca.videorecruit.datamodel.Company;
import praca.videorecruit.datamodel.Offer;
import praca.videorecruit.repositories.AccountRepository;
import praca.videorecruit.repositories.CompanyRepository;
import praca.videorecruit.repositories.FieldOfBusinessRepository;
import praca.videorecruit.repositories.OfferRepository;
import praca.videorecruit.services.ApplicationService;
import praca.videorecruit.services.OfferDTO;
import praca.videorecruit.services.OfferService;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
public class OfferServiceTest {

    @TestConfiguration
    static class OfferServiceTestContextConfiguration{
        @Bean
        public OfferService offerService(){
            return new OfferService();
        }
    }

    @Autowired
    OfferService offerService;
    @MockBean
    FieldOfBusinessRepository fieldOfBusinessRepository;
    @MockBean
    OfferRepository offerRepository;
    @MockBean
    ApplicationService applicationService;
    @MockBean
    CompanyRepository companyRepository;
    @MockBean
    AccountRepository accountRepository;

    @Before
    public void setUp(){
        Account a =  new Account();
        a.setEmail("test@test.com");
        a.setPassword("123");
        a.setStatus("active");
        a.setAccountId(1);
        Mockito.when(accountRepository.findByEmail("test@test.com")).thenReturn(a);
        Company c = new Company();
        c.setAccountByAccountId(a);
        c.setName("TestCompany");
        c.setAccountId(1);
        c.setDescription("This is a test Company");
        c.setShortDesc("Tis is Test");
        Mockito.when(companyRepository.findByAccountByAccountId_Email("test@test.com")).thenReturn(c);
        Account a1 =  new Account();
        a1.setEmail("test1@test.com");
        a1.setPassword("123");
        a1.setStatus("active");
        a1.setAccountId(2);
        Mockito.when(accountRepository.findByEmail("test1@test.com")).thenReturn(a1);
        Company c1 = new Company();
        c1.setAccountByAccountId(a);
        c1.setName("TestCompany1");
        c1.setAccountId(2);
        c1.setDescription("This is a test Company1");
        c1.setShortDesc("Tis is Test1");
        Mockito.when(companyRepository.findByAccountByAccountId_Email("test1@test.com")).thenReturn(c1);
        Offer o = new Offer();
        o.setName("Test Offer");
        o.setDescription("This is a test offer");
        o.setCountry("Polska");
        o.setCity("Wrocław");
        o.setCompany(c);
        o.setOfferId(1);
        ArrayList<Offer> ofs =  new ArrayList<>();
        ofs.add(o);
        Mockito.when(offerRepository.findByCompany_AccountByAccountId_Email("test@test.com")).thenReturn(ofs);
        Mockito.when(offerRepository.findOne(1)).thenReturn(o);

    }
    @Test
    public void ifIdIsValid_OfferIsFound(){
        assert offerService.retrieveOffer(1)!=null;
    }

    @Test
    public void ifIdIsValid_OfferDtoIsCreated(){
        OfferDTO offerDTO = offerService.retrieveOfferDTO(1);
        assert offerDTO!=null;
    }

    @Test
    public void ifEmailIsValid_OffersAreFound(){
        List<Offer> offers = offerService.getOffersByLogin("test@test.com");
        assert offers.size()>0;
    }
    @Test
    public void ifEmailIsValidButNoOffers_EmptyListIsFound(){
        List<Offer> offers = offerService.getOffersByLogin("test1@test.com");
        assert offers.size()==0;
    }

    @Test(expected = AccessDeniedException.class)
    public void deleteOffer() throws MySQLIntegrityConstraintViolationException {
        offerService.deleteOffer(1, "test1@test.com");
    }
    @Test
    public void OfferDtoAndOfferHaveSameValues(){
        Offer o = offerService.retrieveOffer(1);
        OfferDTO od = offerService.retrieveOfferDTO(1);
        boolean b =
                o.getOfferId()==od.getOfferId()&&
                        o.getCountry()==od.getCountry()&&
                        o.getName()==od.getName()&&
                        o.getCity()==od.getCity()&&
                        o.getDescription()==od.getDescription()&&
                        o.getJobTitle()==od.getJobTitle();
        assert b;
    }
}
