package org.hillel.it.resource;

import javax.ws.rs.Path;

import org.hillel.it.mycity.service.ServiceMyCity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Path("/assessment")
@Component
public class AssessmentResource {
	@Autowired
	private ServiceMyCity serviceMyCity;
}
