package org.hillel.it.mycity.service.impl;

import java.util.List;

import org.hillel.it.mycity.model.entity.Assessment;
import org.hillel.it.mycity.model.entity.BaseEntity;
import org.hillel.it.mycity.model.entity.Cinema;
import org.hillel.it.mycity.model.entity.Comment;
import org.hillel.it.mycity.model.entity.Establishment;
import org.hillel.it.mycity.model.entity.UserGroup;
import org.hillel.it.mycity.model.entity.NightClub;
import org.hillel.it.mycity.model.entity.User;
import org.hillel.it.mycity.model.entity.Restaurant;
import org.hillel.it.mycity.persistence.repository.AssessmentRepository;
import org.hillel.it.mycity.persistence.repository.CommentRepository;
import org.hillel.it.mycity.persistence.repository.EstablishmentRepository;
import org.hillel.it.mycity.persistence.repository.UserRepository;
import org.hillel.it.mycity.service.ServiceMyCity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ServiceImpl implements ServiceMyCity {
	@Autowired
	private EstablishmentRepository establishmentRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private AssessmentRepository assessmentRepository;
	private User loggedUser;
	
	@Override
	public void addAssessment(Assessment assessment) {
		assessmentRepository.addAssessment(assessment);
	}
	@Override
	public void deleteAssessment(int id) {
		assessmentRepository.deleteAssessment(id);
	}
	@Override
	public void deleteAssessment(User user) {
		assessmentRepository.deleteAssessment(user);
	}
	@Override
	public void deleteAssessment(Establishment establishment) {
		assessmentRepository.deleteAssessment(establishment);
	}
	@Override
	public Assessment getAssessment(int id) {
		return assessmentRepository.getAssessment(id);
	}
	@Override
	public List<Assessment> getAssessments(User user) {
		return assessmentRepository.getAssessments(user);
	}
	@Override
	public List<Assessment> getAssessments(Establishment establishment) {
		return assessmentRepository.getAssessments(establishment);
	}
	@Override
	public List<Assessment> getAssessments() {
		return assessmentRepository.getAssessments();
	}
	@Override
	public void addComment(Comment comment) {
		commentRepository.addComment(comment);
	}
	@Override
	public void deleteComment(int id) {
		commentRepository.deleteComment(id);
	}
	@Override
	public void deleteComments(User user) {
		commentRepository.deleteComments(user);
	}
	@Override
	public void deleteComments(Establishment establishment) {
		commentRepository.deleteComments(establishment);
	}
	@Override
	public void deleteComments(Establishment establishment, User user) {
		commentRepository.deleteComments(establishment, user);
	}
	@Override
	public Comment getComment(int id) {
		return commentRepository.getComment(id);
	}
	@Override
	public List<Comment> getComments(User user) {
		return commentRepository.getComments(user);
	}
	@Override
	public List<Comment> getComments(Establishment establishment) {
		return commentRepository.getComments(establishment);
	}
	@Override
	public List<Comment> getComments(Establishment establishment, User user) {
		return commentRepository.getComments(establishment, user);
	}
	@Override
	public List<Comment> getComments() {
		return commentRepository.getComments();
	}
	@Override
	public void addCinema(Cinema cinema) {
		establishmentRepository.addCinema(cinema);
	}
	@Override
	public void addNightClub(NightClub nightClub) {
		establishmentRepository.addNightClub(nightClub);
	}
	@Override
	public void addRestaurant(Restaurant restaurant) {
		establishmentRepository.addRestaurant(restaurant);
	}
	@Override
	public Cinema getCinema(int id) {
		return establishmentRepository.getCinema(id);
	}
	@Override
	public Restaurant getRestaurant(int id) {
		return establishmentRepository.getRestaurant(id);
	}
	@Override
	public NightClub getNightClub(int id) {
		return establishmentRepository.getNightClub(id);
	}
	@Override
	public List<Cinema> getCinemas() {
		return establishmentRepository.getCinemas();
	}
	@Override
	public List<NightClub> getNightClubs() {
		return establishmentRepository.getNightClubs();
	}
	@Override
	public List<Restaurant> getRestaurants() {
		return establishmentRepository.getRestaurants();
	}
	@Override
	public void deleteCinemas() {
		establishmentRepository.deleteCinemas();
	}
	@Override
	public void deleteNightClubs() {
		establishmentRepository.deleteNightClubs();
	}
	@Override
	public void deleteRestaurants() {
		establishmentRepository.deleteRestaurants();
	}
	@Override
	public void deleteCinema(int id) {
		establishmentRepository.deleteCinema(id);
	}
	@Override
	public void deleteRestaurant(int id) {
		establishmentRepository.deleteRestaurant(id);
	}
	@Override
	public void deleteNightClub(int id) {
		establishmentRepository.deleteNightClub(id);
	}
	@Override
	public void addUser(User user) {
		userRepository.addUser(user);
	}
	@Override
	public User getUser(int id) {
		return userRepository.getUser(id);
	}
	@Override
	public List<User> getUsers() {
		return userRepository.getUsers();
	}
	@Override
	public void deleteUsers() {
		userRepository.deleteUsers();
	}
	@Override
	public void deleteUser(int id) {
		userRepository.deleteUser(id);
	}
	/**
	 * Throw RuntimeException if loggerUser is not in Administrator Group and t is not belongs
	 * to Assessment and Comment classes.
	 * Throw RuntimeException if CreatedBy user is not add to the repository and if Logged User
	 * and CreateBy user is not the same.
	 * @param t
	 */
	private <T extends BaseEntity>void checkUser(T t) {
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
	}
}
