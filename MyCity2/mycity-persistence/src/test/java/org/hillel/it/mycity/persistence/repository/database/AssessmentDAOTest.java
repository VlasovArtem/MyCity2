package org.hillel.it.mycity.persistence.repository.database;

import java.util.Date;

import org.hillel.it.mycity.model.entity.Assessment;
import org.hillel.it.mycity.model.entity.Establishment;
import org.hillel.it.mycity.model.entity.User;
import org.hillel.it.mycity.model.entity.UserGroup;
import org.hillel.it.mycity.persistence.AppConfig;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class AssessmentDAOTest {
	@Autowired
	AssessmentDAO assessmentDAO;
	private static User user;
	private static Establishment establishment;
	@BeforeClass
	public static void setup() {
		user = new User();
		user.setGroup(UserGroup.Administrator);
		user.setEmail("vlasovartem21@gmail.com");
		user.setUsername("vlasovartem");
		user.setPassword("helloworld");
		user.setCreatedDate(new Date());
		user.setId(1);
		establishment = new Establishment();
		establishment.setCreatedDate(new Date());
		establishment.setAddress("Hello, man");
		establishment.setTelephone("0637472072");
		establishment.setName("MyCityCafe");
		establishment.setId(1);
	}
	@Test
	public void addAssessmentTest() {
		Assessment assessment = user.createAssessment(10);
		assessment.setCreatedDate(new Date());
		establishment.setAssessment(assessment);
		assessmentDAO.addAssessment(assessment);
	}
}
