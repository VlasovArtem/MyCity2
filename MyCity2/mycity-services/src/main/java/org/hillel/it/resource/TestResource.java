package org.hillel.it.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hillel.it.mycity.model.entity.UserGroup;
import org.hillel.it.mycity.model.entity.User;
import org.hillel.it.mycity.service.ServiceMyCity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Path("/testresource")
@Component
public class TestResource {
	@Autowired
	private ServiceMyCity serviceMyCity;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getTest() {
		return "Hello, World!";
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getTest2() {
		User user = new User("test@mail.com", "testpassword");
		user.setId(1);
		user.setGroup(UserGroup.Administrator);
		serviceMyCity.addUser(user);
		return "Create user: emain - " + user.getEmail() + ", password - " + user.getPassword() + ", createdDate - " + user.getCreatedDate().toString();
	}
}
