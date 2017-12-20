package praca.videorecruit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import praca.videorecruit.repositories.AccountRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class VideorecruitApplicationTests {

	@Autowired
	AccountRepository accountRepository;
	@Test
	public void contextLoads() {
	}


}
