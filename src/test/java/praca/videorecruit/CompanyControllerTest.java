package praca.videorecruit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import praca.videorecruit.authentication.AccountAuthenticationProvider;
import praca.videorecruit.authentication.MyUserDetailsService;
import praca.videorecruit.config.MvcConfig;
import praca.videorecruit.config.SecurityConfig;
import praca.videorecruit.datamodel.Account;
import praca.videorecruit.datamodel.Company;
import praca.videorecruit.repositories.CompanyRepository;

import javax.servlet.ServletContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by Bartek on 23.12.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class CompanyControllerTest {

    @Autowired
    private WebApplicationContext wac;
    @MockBean
    CompanyRepository companyRepository;

    @Before
    public void prepare(){Account a =  new Account();
        a.setEmail("test@test.com");
        a.setPassword("123");
        a.setStatus("active");
        a.setAccountId(1);
        Company c = new Company();
        c.setAccountByAccountId(a);
        c.setName("TestCompany");
        c.setAccountId(1);
        c.setDescription("This is a test Company");
        Mockito.when(companyRepository.findByAccountByAccountId_Email("test@test.com")).thenReturn(c);
    }

    private MockMvc mockMvc;
    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void givenWac_whenServletContext_thenItProvidesCompanyController() {
        ServletContext servletContext = wac.getServletContext();

        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(wac.getBean("companyController"));
    }

    @Test
    public void givenCreateOfferURI_whenMockMVC_thenReturnsCreateOfferViewName() throws Exception {
        this.mockMvc.perform(get("/createOffer")).andDo(print())

                .andExpect(view().name("offerForm"));
    }



}
