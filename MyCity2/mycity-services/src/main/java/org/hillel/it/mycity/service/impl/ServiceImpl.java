package org.hillel.it.mycity.service.impl;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hillel.it.mycity.model.entity.Administrator;
import org.hillel.it.mycity.model.entity.Assessment;
import org.hillel.it.mycity.model.entity.BaseEntity;
import org.hillel.it.mycity.model.entity.Cinema;
import org.hillel.it.mycity.model.entity.Comment;
import org.hillel.it.mycity.model.entity.Establishment;
import org.hillel.it.mycity.model.entity.PersonGroup;
import org.hillel.it.mycity.model.entity.Moderator;
import org.hillel.it.mycity.model.entity.NightClub;
import org.hillel.it.mycity.model.entity.Person;
import org.hillel.it.mycity.model.entity.Restaurant;
import org.hillel.it.mycity.model.entity.User;
import org.hillel.it.mycity.persistence.repository.AssessmentRepository;
import org.hillel.it.mycity.persistence.repository.CommentRepository;
import org.hillel.it.mycity.persistence.repository.EstablishmentRepository;
import org.hillel.it.mycity.persistence.repository.UserRepository;
import org.hillel.it.mycity.service.ServiceMyCity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServiceImpl implements ServiceMyCity {
	@Autowired
	private EstablishmentRepository establishmentRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private AssessmentRepository assessmentRepository;
	private Person loggedUser;

//	public ServiceImpl() {
//	}
//	public ServiceImpl(EstablishmentRepository establishmentRepository, UserRepository userRepository, CommentRepository commentRepository, AssessmentRepository assessmentRepository) {
//		this.establishmentRepository = establishmentRepository;
//		this.userRepository = userRepository;
//		this.commentRepository = commentRepository;
//		this.assessmentRepository = assessmentRepository;
//	}
	// CREATE Establishment
	public void addRestaurant(Restaurant restaurant) {
		checkUser(restaurant);
		try {
			establishmentRepository.addRestaurant(restaurant);
		} catch (RuntimeException e) {
			throw new RuntimeException(e);
		}	
	}
	public void addNightClub(NightClub nightClub) {
		checkUser(nightClub);
		establishmentRepository.addNightClub(nightClub);
	}
	public void addCinema(Cinema cinema) {
		checkUser(cinema);
		establishmentRepository.addCinema(cinema);
	}
	// DELETE Establishment
	public void deleteEstablishments() {
		establishmentRepository.deleteEstablishments();
	}

	public void deleteEstablishment(int id) {
		try {
			establishmentRepository.deleteEstablishment(id);
		} catch (NullPointerException e) {
			System.out.println(e);
			throw new NullPointerException();
		} catch (RuntimeException e) {
			System.out.println(e);
			throw new RuntimeException();
		}
	}
	// READ Establishment
	public List<Cinema> getCinemas(){
		return establishmentRepository.getCinemas();
	}
	public List<NightClub> getNightClubs() {
		return establishmentRepository.getNightClubs();
	}
	public List<Restaurant> getRestaurants() {
		return establishmentRepository.getRestaurants();
	}
	public Cinema getCinema(int id) {
		try {
			return establishmentRepository.getCinema(id);
		} catch (NullPointerException e) {
			throw new NullPointerException("Incorrect id");
		}
	}
	public NightClub getNightClub(int id) {
		try {
			return establishmentRepository.getNightClub(id);
		} catch (NullPointerException e) {
			throw new NullPointerException("Incorrect id");
		}
	}
	public Restaurant getRestaurant(int id) {
		try {
			return establishmentRepository.getRestaurant(id);
		} catch (NullPointerException e) {
			throw new NullPointerException("Incorrect id");
		}
	}
	// CREATE Person
	public void addAdministrator(Administrator administrator) {
		try {
			userRepository.addAdministrator(administrator);
		} catch (RuntimeException e) {
			throw new RuntimeException("This user is already exist");
		}
	}
	public void addModerator(Moderator moderator) {
		try {
			userRepository.addModerator(moderator);
		} catch (RuntimeException e) {
			throw new RuntimeException("This user is already exist");
		}
		
	}
	public void addUser(User user) {
		try {
			userRepository.addUser(user);
		} catch (RuntimeException e) {
			throw new RuntimeException("This user is already exist");
		}
		
	}
	//READ Person
	public List<Administrator> getAdministrators() {
		return userRepository.getAdministrators();
	}
	public List<Moderator> getModerators() {
		return userRepository.getModerators();
	}
	public List<User> getUsers() {
		return userRepository.getUsers();
	}
	public Administrator getAdministrator(int id) {
		return userRepository.getAdministrator(id);
	}
	public Moderator getModerator(int id) {
		return userRepository.getModerator(id);
	}
	public User getUser(int id) {
		return userRepository.getUser(id);
	}
	//DELETE Person
	public void deletePersons() {
		userRepository.deletePersons();
	}
	public void deletePerson(int id) {
		userRepository.deletePerson(id);
	}
	//CREATE Comment
	public void addComment(Comment comment) {
		checkUser(comment);
		commentRepository.addComment(comment);
	}
	//DELETE Comment
	public void deleteComment(int id) {
		checkUser(getComment(id));
		commentRepository.deleteComment(id);
	}
	public void deleteComments(Person user) {
		checkUser(loggedUser);
		commentRepository.deleteComments(user);
	}
	public void deleteComments(Establishment establishment) {
		checkUser(loggedUser);
		commentRepository.deleteComments(establishment);
	}
	public void deleteComments(Establishment establishment, Person user) {
		checkUser(loggedUser);
		commentRepository.deleteComments(establishment,user);
	}
	//READ Comment
	public Comment getComment(int id) {
		return commentRepository.getComment(id);
	}
	public List<Comment> getComments(Person user) {
		return commentRepository.getComments(user);
	}
	public List<Comment> getComments(Establishment establishment) {
		return commentRepository.getComments(establishment);
	}
	public List<Comment> getComments(Establishment establishment, Person user) {
		return commentRepository.getComments(establishment, user);
	}
	public List<Comment> getComments() {
		return commentRepository.getComments();
	}
	//CREATE Assessment
	public void addAssessment(Assessment assessment) {
		checkUser(assessment);
		assessmentRepository.addAssessment(assessment);
	}
	//DELETE Assessment
	public void deleteAssessment(int id) {
		checkUser(loggedUser);
		assessmentRepository.deleteAssessment(id);
	}
	public void deleteAssessment(Person user) {
		checkUser(loggedUser);
		assessmentRepository.deleteAssessment(user);
	}
	public void deleteAssessment(Establishment establishment) {
		checkUser(loggedUser);
		assessmentRepository.deleteAssessment(establishment);
	}
	//READ Assessment
	public Assessment getAssessment(int id) {
		try {
			checkUser(loggedUser);
			return assessmentRepository.getAssessment(id);
		} catch (RuntimeException e) {
			System.out.println(e);
			throw new RuntimeException();
		}
	}
	public List<Assessment> getAssessments(Person user) {
		try {
			checkUser(loggedUser);
			return assessmentRepository.getAssessments(user);
		} catch (RuntimeException e) {
			System.out.println(e);
			throw new RuntimeException();
		}
	}
	public List<Assessment> getAssessments(Establishment establishment) {
		try {
			checkUser(loggedUser);
			return assessmentRepository.getAssessments(establishment);
		} catch (RuntimeException e) {
			System.out.println(e);
			throw new RuntimeException();
		}
	}
	public List<Assessment> getAssessments() {
		return assessmentRepository.getAssessments();
	}
	public void setLoggedUser(Person user) {
		loggedUser = user;
	}
	/**
	 * Throw RuntimeException if loggerUser is not in Administrator Group and t is not belongs
	 * to Assessment and Comment classes.
	 * Throw RuntimeException if CreatedBy user is not add to the repository and if Logged User
	 * and CreateBy user is not the same.
	 * @param t
	 */
	private <T extends BaseEntity>void checkUser(T t) {
		if(loggedUser.inGroup(PersonGroup.Administrator) == false && !(t.getClass() == Assessment.class) && !(t.getClass() == Comment.class)) {
			throw new RuntimeException("Administrator has no login");
		} else if(t.getCreatedBy().getId() == 0 || !loggedUser.equals(t.getCreatedBy())) {
			throw new RuntimeException("This user cannot create/delete object");
		} else if(loggedUser == null) {
			throw new RuntimeException("Users has no login into the system. Call setLoggedUser method");
		}
	}
	public void deserializeUserData() {
		userRepository.deserializeData();
	}
	public void setEstablishmentRepository(
			EstablishmentRepository establishmentRepository) {
		this.establishmentRepository = establishmentRepository;
	}
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	public void setCommentRepository(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}
	public void setAssessmentRepository(AssessmentRepository assessmentRepository) {
		this.assessmentRepository = assessmentRepository;
	}
}
