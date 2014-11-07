package org.hillel.it.mycity.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hillel.it.mycity.model.entity.Assessment;
import org.hillel.it.mycity.service.ServiceMyCity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Path("/assessment")
@Component
public class AssessmentResource {
	@Autowired
	private ServiceMyCity serviceMyCity;
	
	@PUT
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public void addAssessment(Assessment assessment) {
		serviceMyCity.addAssessment(assessment);
	}
	@DELETE
	@Path("/delete/byid/{id}")
	public void deleteAssessmentById(@PathParam("id") int id) {
		serviceMyCity.deleteAssessment(id);
	}
	@DELETE
	@Path("/delete/byuser/{username}")
	public void deleteAssessmentByUser(@PathParam("username") String username) {
		serviceMyCity.deleteAssessmentByUser(username);
	}
	@DELETE
	@Path("/delete/byestablishment/{establishment}")
	public void deleteAssessmentByEstablishment(@PathParam("establishment") String name) {
		serviceMyCity.deleteAssessmentByEstablishment(name);
	}
	@GET
	@Path("/get/byid/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Assessment getAssessment(@PathParam("id") int id) {
		return serviceMyCity.getAssessment(id);
	}
	@GET
	@Path("/get/byuser/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Assessment> getAssessmentsByUser(@PathParam("username") String username) {
		return serviceMyCity.getAssessmentsByUser(username);
	}
	@GET
	@Path("/get/byestablishment/{establishment}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Assessment> getAssessmentsByEstablishment(@PathParam("establishment") String establishmentName) {
		return serviceMyCity.getAssessmentsByEstablishment(establishmentName);
	}
	@GET
	@Path("/get/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Assessment> getAssessments() {
		return serviceMyCity.getAssessments();
	}
	
}
