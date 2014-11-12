
package org.hillel.it.resource;

import java.util.Date;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.grizzly.http.server.HttpServer;
import org.hillel.it.mycity.model.entity.Assessment;
import org.hillel.it.mycity.model.entity.User;
import org.hillel.it.mycity.model.entity.UserGroup;
import org.hillel.it.rest.Grizzly;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class AssessmentResourceTest {
	private static HttpServer httpServer;
	private WebTarget webTarget;
	private Assessment assessment;
	private Client client;
	@Before
	public void setup() throws Exception {
		client = ClientBuilder.newClient();
	}
	@BeforeClass
	public static void serverStart() throws Exception {
		httpServer = Grizzly.startServer();
	}
	@AfterClass
	public static void tearDown() throws Exception {
		httpServer.shutdown();
	}
	@Before
	public void setupAssessment() {
		User user = new User();
		user.setCreatedDate(new Date());
		user.setEmail("vlasovartem21@gmail.com");
		user.setFirstName("Artem");
		user.setGroup(UserGroup.ADMINISTRATOR);
		user.setLastName("Vlasov");
		user.setModifiedDate(new Date());	
		user.setPassword("422617");
		user.setUsername("vlasovartem");
		Assessment assessment = new Assessment();
		assessment.setAssessment(10);
		assessment.setCreatedBy(user);
		assessment.setCreatedDate(new Date());
		assessment.setModifiedDate(new Date());
		this.assessment = assessment;
	}
	@Test
	public void testAddAssessment() {
		webTarget = client.target("http://localhost:8080/webapi/");
		String string = webTarget.path("hello").request(MediaType.TEXT_PLAIN).get(String.class);
		assertEquals("Hello, World!", string);
		/*em.getTransaction().begin();
		TypedQuery<Assessment> query = em.createQuery("FROM Assessment a WHERE a.id = (SELECT MAX(id) FROM Assessment)", Assessment.class);
		Assessment assessment = query.getSingleResult();
		em.getTransaction().commit();
		assertNotEquals(this.assessment, assessment);*/
	}
}
