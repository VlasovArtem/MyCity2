package org.hillel.it.mycity.service;


import java.util.List;

import org.hillel.it.mycity.model.entity.Administrator;
import org.hillel.it.mycity.model.entity.Assessment;
import org.hillel.it.mycity.model.entity.Cinema;
import org.hillel.it.mycity.model.entity.Comment;
import org.hillel.it.mycity.model.entity.Establishment;
import org.hillel.it.mycity.model.entity.Moderator;
import org.hillel.it.mycity.model.entity.NightClub;
import org.hillel.it.mycity.model.entity.Person;
import org.hillel.it.mycity.model.entity.Restaurant;
import org.hillel.it.mycity.model.entity.User;

public interface ServiceMyCity {
	
	public void addRestaurant(Restaurant restaurant);
	public void addNightClub(NightClub nightClub);
	public void addCinema(Cinema cinema);
	
	// READ Establishment
	public List<Cinema> getCinemas();
	public List<NightClub> getNightClubs();
	public List<Restaurant> getRestaurants();
	
	public Cinema getCinema(int id);
	public NightClub getNightClub(int id);
	public Restaurant getRestaurant(int id);

	// DELETE Establishment
	public void deleteEstablishments();
	public void deleteEstablishment(int id);
	
	// Person repository
	// CREATE Person
	public void addAdministrator(Administrator administrator);
	public void addModerator(Moderator moderator);
	public void addUser(User user);

	// READ Person
	public List<Administrator> getAdministrators();
	public List<Moderator> getModerators();
	public List<User> getUsers();

	public Administrator getAdministrator(int id);
	public Moderator getModerator(int id);
	public User getUser(int id);

	// DELETE Person
	public void deletePersons();
	public void deletePerson(int id);
	
	//DeserializeData
	public void deserializeUserData();
	
	//CommentRepository
	//CREATE Comment
	public void addComment(Comment comment);
	
	//DELETE Comment
	public void deleteComment(int id);
	public void deleteComments(Person user);
	public void deleteComments(Establishment establishment);
	public void deleteComments(Establishment establishment, Person user);

	//READ Comment
	public Comment getComment(int id);
	public List<Comment> getComments(Person user);
	public List<Comment> getComments(Establishment establishment);
	public List<Comment> getComments(Establishment establishment, Person user);
	public List<Comment> getComments();

	//AssessmentRepository
	//CREATE Assessment
	public void addAssessment(Assessment assessment);
	
	//DELETE Assessment
	public void deleteAssessment(int id);
	public void deleteAssessment(Person user);
	public void deleteAssessment(Establishment establishment);
	
	//READ Assessment
	public Assessment getAssessment(int id);
	public List<Assessment> getAssessments(Person user);
	public List<Assessment> getAssessments(Establishment establishment);
	public List<Assessment> getAssessments();
	
	public void setLoggedUser(Person user);
}
