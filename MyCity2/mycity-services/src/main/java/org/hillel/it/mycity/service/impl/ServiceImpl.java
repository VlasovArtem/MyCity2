package org.hillel.it.mycity.service.impl;

import java.time.LocalTime;
import java.util.List;

import org.hillel.it.mycity.model.entity.Assessment;
import org.hillel.it.mycity.model.entity.Cinema;
import org.hillel.it.mycity.model.entity.Comment;
import org.hillel.it.mycity.model.entity.Establishment;
import org.hillel.it.mycity.model.entity.NightClub;
import org.hillel.it.mycity.model.entity.User;
import org.hillel.it.mycity.model.entity.Restaurant;
import org.hillel.it.mycity.model.entity.UserGroup;
import org.hillel.it.mycity.persistence.repository.AssessmentCrudRepository;
import org.hillel.it.mycity.persistence.repository.CinemaCrudRepository;
import org.hillel.it.mycity.persistence.repository.CommentCrudRepository;
import org.hillel.it.mycity.persistence.repository.NightClubCrudRepository;
import org.hillel.it.mycity.persistence.repository.RestaurantCrudRepository;
import org.hillel.it.mycity.persistence.repository.UserCrudRepistory;
import org.hillel.it.mycity.service.ServiceMyCity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceImpl implements ServiceMyCity {
	@Autowired
	private CinemaCrudRepository cinemaCrudRepository;
	@Autowired
	private RestaurantCrudRepository restaurantCrudRepository;
	@Autowired
	private NightClubCrudRepository nightClubCrudRepository;
	@Autowired
	private CommentCrudRepository commentCrudRepository;
	@Autowired
	private AssessmentCrudRepository assessmentCrudRepository;
	@Autowired
	private UserCrudRepistory userCrudRepistory;
	
	@Override
	public void addAssessment(Assessment assessment) {
		assessmentCrudRepository.save(assessment);
	}
	@Override
	public void deleteAssessment(int id) {
		assessmentCrudRepository.delete(id);
	}
	@Override
	public void deleteAssessmentByUser(String username) {
//		assessmentCrudRepository.removeByUser(username);
	}
	@Override
	public void deleteAssessmentByEstablishment(String establishmentName) {
//		assessmentCrudRepository.removeByEstablishment(establishmentName);
	}
	@Override
	public Assessment getAssessment(int id) {
		return assessmentCrudRepository.findOne(id);
	}
	@Override
	public List<Assessment> getAssessmentsByUser(String username) {
//		return assessmentCrudRepository.findByUser(username);
		return null;
	}
	@Override
	public List<Assessment> getAssessmentsByEstablishment(String establishmentName) {
//		return assessmentCrudRepository.findByEstablishment(establishmentName);
		return null;
	}
	@Override
	public List<Assessment> getAssessments() {
		return (List<Assessment>) assessmentCrudRepository.findAll();
	}
	@Override
	public void addComment(Comment comment) {
		commentCrudRepository.save(comment);
	}
	@Override
	public void deleteComment(int id) {
		commentCrudRepository.delete(id);
	}
	@Override
	public void deleteCommentsByUser(String username) {
//		commentCrudRepository.removeByUser(username);
	}
	@Override
	public void deleteCommentsByEstablishment(String name) {
//		commentCrudRepository.removeByEstablishment(name);
	}
	@Override
	public void deleteCommentsByUserAndEstablishment(String username, String name) {
//		commentCrudRepository.removeByCreatedByAndEstablishment(username, name);
	}
	@Override
	public Comment getComment(int id) {
		return commentCrudRepository.findOne(id);
	}
	@Override
	public List<Comment> getCommentsByUser(String username) {
//		return commentCrudRepository.findByCreatedBy(username);
		return null;
	}
	@Override
	public List<Comment> getCommentsByEstablishment(String name) {
//		return commentCrudRepository.findByEstablishment(name);
		return null;
	}
	@Override
	public List<Comment> getCommentsByUserAndEstablishment(String username, String name) {
//		return commentCrudRepository.findByCreatedByAndEstablishment(username, name);
		return null;
	}
	@Override
	public List<Comment> getComments() {
		return (List<Comment>) commentCrudRepository.findAll();
	}
	@Override
	public void addCinema(Cinema cinema) {
		cinemaCrudRepository.save(cinema);
	}
	@Override
	public Cinema getCinema(int id) {
		return cinemaCrudRepository.findOne(id);
	}
	@Override
	public List<Cinema> getCinemas() {
		return (List<Cinema>) cinemaCrudRepository.findAll();
	}
	@Override
	public List<Cinema> getCinemaByName(String name) {
//		return cinemaCrudRepository.findByName(name);
		return null;
	}
	@Override
	public List<Cinema> getCinemaByAddress(String address) {
//		return cinemaCrudRepository.findByAddress(address);
		return null;
	}
	@Override
	public List<Cinema> getCinemaByCinemaTechnology(String cinemaTechnology) {
//		return cinemaCrudRepository.findByCinemaTechnology(cinemaTechnology);
		return null;
	}
	@Override
	public List<Cinema> getCinemaByNameAndAddress(String name, String address) {
//		return cinemaCrudRepository.findByNameAndAddress(name, address);
		return null;
	}
	@Override
	public void deleteCinemas() {
		cinemaCrudRepository.deleteAll();
	}
	@Override
	public void deleteCinema(int id) {
		cinemaCrudRepository.delete(id);
	}
	@Override
	public void deleteCinemaByName(String name) {
//		cinemaCrudRepository.removeByName(name);
	}
	@Override
	public void deleteCinemaByAddress(String address) {
//		cinemaCrudRepository.removeByAddress(address);
	}
	@Override
	public void deleteCinemaByNameAndAddress(String name, String address) {
//		cinemaCrudRepository.removeByNameAndAddress(name, address);
	}
	@Override
	public void addRestaurant(Restaurant restaurant) {
		restaurantCrudRepository.save(restaurant);
	}
	@Override
	public Restaurant getRestaurant(int id) {
		return restaurantCrudRepository.findOne(id);
	}
	@Override
	public List<Restaurant> getRestaurants() {
		return (List<Restaurant>) restaurantCrudRepository.findAll();
	}
	@Override
	public List<Restaurant> getRestaurantByName(String name) {
//		return restaurantCrudRepository.findByName(name);
		return null;
	}
	@Override
	public List<Restaurant> getRestaurantByAddress(String address) {
//		return restaurantCrudRepository.findByAddress(address);
		return null;
	}
	@Override
	public List<Restaurant> getRestaurantByNameAndAddress(String name,
			String address) {
//		return restaurantCrudRepository.findByNameAndAddress(name, address);
		return null;
	}
	@Override
	public List<Restaurant> getRestaurantByTimeOpen(LocalTime timeOpen) {
//		return restaurantCrudRepository.findByTimeOpen(timeOpen);
		return null;
	}
	@Override
	public List<Restaurant> getRestaurantByTimeClose(LocalTime timeClose) {
//		return restaurantCrudRepository.findByTimeClose(timeClose);
		return null;
	}
	@Override
	public List<Restaurant> getRestaurantByAverageCheck(
			int averageCheck) {
//		return restaurantCrudRepository.findByAverageCheck(averageCheck);
		return null;
	}
	@Override
	public void deleteRestaurants() {
		restaurantCrudRepository.deleteAll();
	}
	@Override
	public void deleteRestaurant(int id) {
		restaurantCrudRepository.delete(id);
	}
	@Override
	public void deleteRestaurantByName(String name) {
//		restaurantCrudRepository.removeByName(name);
	}
	@Override
	public void deleteRestaurantByAddress(String address) {
//		restaurantCrudRepository.removeByAddress(address);
	}
	@Override
	public void deleteRestaurantByNameAndAddress(String name,
			String address) {
//		restaurantCrudRepository.removeByNameAndAddress(name, address);
	}
	@Override
	public void addNightClub(NightClub nightClub) {
		nightClubCrudRepository.save(nightClub);
	}
	@Override
	public NightClub getNightClub(int id) {
		return nightClubCrudRepository.findOne(id);
	}
	@Override
	public List<NightClub> getNightClubs() {
		return (List<NightClub>) nightClubCrudRepository.findAll();
	}
	@Override
	public List<NightClub> getNightClubByName(String name) {
//		return nightClubCrudRepository.findByName(name);
		return null;
	}
	@Override
	public List<NightClub> getNightClubByAddress(String address) {
//		return nightClubCrudRepository.findByAddress(address);
		return null;
	}
	@Override
	public List<NightClub> getNightClubByNameAndAddress(String name,
			String address) {
//		return nightClubCrudRepository.findByNameAndAddress(name, address);
		return null;
	}
	@Override
	public List<NightClub> getNightClubByTimeOpen(LocalTime timeOpen) {
//		return nightClubCrudRepository.findByTimeOpen(timeOpen);
		return null;
	}
	@Override
	public List<NightClub> getNightClubByTimeClose(LocalTime timeClose) {
//		return nightClubCrudRepository.findByTimeClose(timeClose);
		return null;
	}
	@Override
	public List<NightClub> getNightClubByAverageCheck(
			int averageCheck) {
//		return nightClubCrudRepository.findByAverageCheck(averageCheck);
		return null;
	}
	@Override
	public void deleteNightClubs() {
		nightClubCrudRepository.deleteAll();
	}
	@Override
	public void deleteNightClub(int id) {
		nightClubCrudRepository.delete(id);
	}
	@Override
	public void deleteNightClubByName(String name) {
//		nightClubCrudRepository.removeByName(name);
	}
	@Override
	public void deleteNightClubByAddress(String address) {
//		nightClubCrudRepository.removeByAddress(address);
	}
	@Override
	public void deleteNightClubByNameAndAddress(String name,
			String address) {
//		nightClubCrudRepository.removeByNameAndAddress(name, address);
	}
	@Override
	public void addUser(User user) {
		userCrudRepistory.save(user);
	}
	@Override
	public User getUser(int id) {
		return userCrudRepistory.findOne(id);
	}
	@Override
	public List<User> getUsers() {
		return (List<User>) userCrudRepistory.findAll();
	}
	@Override
	public List<User> getUserByEmail(String email) {
//		return userCrudRepistory.findByEmail(email);
		return null;
	}
	@Override
	public List<User> getUserByUsername(String username) {
//		return userCrudRepistory.findByUsername(username);
		return null;
	}
	@Override
	public List<User> getUserByGroup(UserGroup group) {
//		return userCrudRepistory.findByGroup(group);
		return null;
	}
	@Override
	public void deleteUsers() {
		userCrudRepistory.deleteAll();
	}
	@Override
	public void deleteUser(int id) {
		userCrudRepistory.delete(id);
	}
	@Override
	public void deleteUserByEmail(String email) {
//		userCrudRepistory.removeByEmail(email);
	}
	@Override
	public void deleteUserByUsername(String username) {
//		userCrudRepistory.removeByUsername(username);
	}
	@Override
	public void deleteUserByGroup(UserGroup group) {
//		userCrudRepistory.removeByGroup(group);
	}
	
	/**
	 * Throw RuntimeException if loggerUser is not in Administrator Group and t is not belongs
	 * to Assessment and Comment classes.
	 * Throw RuntimeException if CreatedBy user is not add to the repository and if Logged User
	 * and CreateBy user is not the same.
	 * @param t
	 */
	/*private <T extends BaseEntity>void checkUser(T t) {
		if(loggedUser.inGroup(UserGroup.Administrator) == false && !(t.getClass() == Assessment.class) && !(t.getClass() == Comment.class)) {
			throw new RuntimeException("Administrator has no login");
		} else if(t.getCreatedBy().getId() == 0 || !loggedUser.equals(t.getCreatedBy())) {
			throw new RuntimeException("This user cannot create/delete object");
		} else if(loggedUser == null) {
			throw new RuntimeException("Users has no login into the system. Call setLoggedUser method");
		}
	}
	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
	}
	private void checkLoggedUser() {
		if(loggedUser == null) {
			throw new RuntimeException("There is no logged user for Service, you should run setLoggedUser() method");
		}
	}
	private void checkUserForEstablishmentAndPerson() {
		if(!loggedUser.inGroup(UserGroup.Administrator)) {
			throw new RuntimeException("Only Administrators can make manipulations with Establishment repository and User repository");
		}
	}
	private void checkUserForAssessment(Assessment assessment) {
		if((loggedUser != assessment.getCreatedBy() && assessment.getCreatedBy().getId() == 0) || !loggedUser.inGroup(UserGroup.Administrator)) {
			throw new RuntimeException("Only user how create Assessment and Administator can manipulating with Assessment repository");
		}
	}*/
}
