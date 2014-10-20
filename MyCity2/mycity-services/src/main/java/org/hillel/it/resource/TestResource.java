package org.hillel.it.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

@Path("/testresource")
@Component
public class TestResource {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getTest() {
		return "Hello, World!";
	}

}
