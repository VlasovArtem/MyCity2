package org.hillel.it.grizzly;

import java.net.URI;
import java.util.Date;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.hillel.it.mycity.model.entity.Assessment;
import org.hillel.it.mycity.model.entity.User;
import org.hillel.it.mycity.model.entity.UserGroup;

public class GrizzlyTest {
	private static final String BASE_URI = "http://localhost:9090/AssessmentTest/";
	public static void main(String[] args) {
		/*User user = new User();
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
		assessment.setModifiedDate(new Date());*/
		HttpServer httpServer = null;
		try {
			ResourceConfig rc = new ResourceConfig().packages("org.hillel.it");
			URI baseUri = URI.create(BASE_URI);
			httpServer = GrizzlyHttpServerFactory.createHttpServer(baseUri, rc);
			httpServer.start();
			System.out.println(String.format("Jersey app started with WADL available at "
	                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
	        System.in.read();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(httpServer != null) {
				httpServer.shutdownNow();
			}
		}
		
		
	}
}
