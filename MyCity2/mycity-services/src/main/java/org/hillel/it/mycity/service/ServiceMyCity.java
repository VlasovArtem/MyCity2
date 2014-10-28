package org.hillel.it.mycity.service;

import java.time.LocalTime;
import java.util.List;

import org.hillel.it.mycity.model.entity.Assessment;
import org.hillel.it.mycity.model.entity.Cinema;
import org.hillel.it.mycity.model.entity.Comment;
import org.hillel.it.mycity.model.entity.NightClub;
import org.hillel.it.mycity.model.entity.User;
import org.hillel.it.mycity.model.entity.Restaurant;
import org.hillel.it.mycity.model.entity.UserGroup;

public interface ServiceMyCity {
	//Assessment
	public void addAssessment(Assessment assessment);
	public void deleteAssessment(int id);
	public void deleteAssessmentByUser(String username);
	public void deleteAssessmentByEstablishment(String establishmentName);
	public Assessment getAssessment(int id);
	public List<Assessment> getAssessmentsByUser(String username);
	public List<Assessment> getAssessmentsByEstablishment(String establishmentName);
	public List<Assessment> getAssessments();
	
	//Comment
	public void addComment(Comment comment);
	public void deleteComment(int id);
	public void deleteCommentsByUser(String username);
	public void deleteCommentsByEstablishment(String name);
	public void deleteCommentsByUserAndEstablishment(String username, String name);
	public Comment getComment(int id);
	public List<Comment> getCommentsByUser(String username);
	public List<Comment> getCommentsByEstablishment(String name);
	public List<Comment> getCommentsByUserAndEstablishment(String username, String name);
	public List<Comment> getComments();
	
	//Cinema
	public void addCinema(Cinema cinema);
	public Cinema getCinema(int id);
	public List<Cinema> getCinemas();
	public List<Cinema> getCinemaByName(String name);
	public List<Cinema> getCinemaByAddress(String address);
	public List<Cinema> getCinemaByCinemaTechnology(String cinemaTechnology);
	public List<Cinema> getCinemaByNameAndAddress(String name, String address);
	public void deleteCinemas();
	public void deleteCinema(int id);
	public void deleteCinemaByName(String name);
	public void deleteCinemaByAddress(String address);
	public void deleteCinemaByNameAndAddress(String name, String address);

	//Restaurant
	public void addRestaurant(Restaurant restaurant);
	public Restaurant getRestaurant(int id);
	public List<Restaurant> getRestaurants();
	public List<Restaurant> getRestaurantByName(String name);
	public List<Restaurant> getRestaurantByAddress(String address);
	public List<Restaurant> getRestaurantByNameAndAddress(String name, String address);
	public List<Restaurant> getRestaurantByTimeOpen(LocalTime timeOpen);
	public List<Restaurant> getRestaurantByTimeClose(LocalTime timeClose);
	public List<Restaurant> getRestaurantByAverageCheck(int averageCheck);
	public void deleteRestaurants();
	public void deleteRestaurant(int id);
	public void deleteRestaurantByName(String name);
	public void deleteRestaurantByAddress(String address);
	public void deleteRestaurantByNameAndAddress(String name, String address);
	
	//NightClub
	public void addNightClub(NightClub nightClub);
	public NightClub getNightClub(int id);
	public List<NightClub> getNightClubs();
	public List<NightClub> getNightClubByName(String name);
	public List<NightClub> getNightClubByAddress(String address);
	public List<NightClub> getNightClubByNameAndAddress(String name, String address);
	public List<NightClub> getNightClubByTimeOpen(LocalTime timeOpen);
	public List<NightClub> getNightClubByTimeClose(LocalTime timeClose);
	public List<NightClub> getNightClubByAverageCheck(int averageCheck);
	public void deleteNightClubs();
	public void deleteNightClub(int id);
	public void deleteNightClubByName(String name);
	public void deleteNightClubByAddress(String address);
	public void deleteNightClubByNameAndAddress(String name, String address);
	
	//User
	public void addUser(User user);
	public User getUser(int id);
	public List<User> getUsers();
	public List<User> getUserByEmail(String email);
	public List<User> getUserByUsername(String username);
	public List<User> getUserByGroup(UserGroup group);
	public void deleteUsers();
	public void deleteUser(int id);
	public void deleteUserByEmail(String email);
	public void deleteUserByUsername(String username);
	public void deleteUserByGroup(UserGroup group);
}
