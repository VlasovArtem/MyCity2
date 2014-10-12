package org.hillel.it.mycity.service;


import java.util.List;

import org.hillel.it.mycity.model.entity.Assessment;
import org.hillel.it.mycity.model.entity.Cinema;
import org.hillel.it.mycity.model.entity.Comment;
import org.hillel.it.mycity.model.entity.Establishment;
import org.hillel.it.mycity.model.entity.NightClub;
import org.hillel.it.mycity.model.entity.User;
import org.hillel.it.mycity.model.entity.Restaurant;

public interface ServiceMyCity {
	//Assessment
	public void addAssessment(Assessment assessment);
	public void deleteAssessment(int id);
	public void deleteAssessment(User user);
	public void deleteAssessment(Establishment establishment);
	public Assessment getAssessment(int id);
	public List<Assessment> getAssessments(User user);
	public List<Assessment> getAssessments(Establishment establishment);
	public List<Assessment> getAssessments();
	public void updateAssessment(Assessment assessment);
	
	//Comment
	public void addComment(Comment comment);
	public void deleteComment(int id);
	public void deleteComments(User user);
	public void deleteComments(Establishment establishment);
	public void deleteComments(Establishment establishment, User user);
	public Comment getComment(int id);
	public List<Comment> getComments(User user);
	public List<Comment> getComments(Establishment establishment);
	public List<Comment> getComments(Establishment establishment, User user);
	public List<Comment> getComments();
	public void updateComment(Comment comment);
	
	//Establishment
	public void addCinema(Cinema cinema);
	public void addNightClub(NightClub nightClub);
	public void addRestaurant(Restaurant restaurant);
	public Cinema getCinema(int id);
	public Restaurant getRestaurant(int id);
	public NightClub getNightClub(int id);
	public List<Cinema> getCinemas();
	public List<NightClub> getNightClubs();
	public List<Restaurant> getRestaurants();
	public void deleteCinemas();
	public void deleteNightClubs();
	public void deleteRestaurants();
	public void deleteCinema(int id);
	public void deleteRestaurant(int id);
	public void deleteNightClub(int id);
	public <T> void updateEstablishment(T t);
	
	//User
	public void addUser(User user);
	public User getUser(int id);
	public List<User> getUsers();
	public void deleteUsers();
	public void deleteUser(int id);
	public void updateUser(User user);
}
