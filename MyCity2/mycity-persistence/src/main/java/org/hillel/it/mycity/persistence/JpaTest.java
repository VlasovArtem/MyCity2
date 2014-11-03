package org.hillel.it.mycity.persistence;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.hillel.it.mycity.model.entity.Assessment;
import org.hillel.it.mycity.model.entity.Cinema;
import org.hillel.it.mycity.model.entity.User;
import org.hillel.it.mycity.model.entity.UserGroup;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JpaTest {
	private static int i = 1;
	private static String username = "User" + i;
	public static void main(String[] args) throws ClassNotFoundException, JsonProcessingException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mycity");
		EntityManager em = emf.createEntityManager();
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			User user = new User();
			user.setGroup(UserGroup.ADMINISTRATOR);
			user.setEmail("vlasovartem21@gmail.com");
			user.setUsername("vlasovartem");
			user.setPassword("helloworld");
			user.setCreatedDate(new Date());
			Cinema cinema = new Cinema();
			cinema.setAddress("Hello");
			cinema.setName("MyFirstCinema");
			cinema.setTelephone("+38063745133");
			cinema.setCreatedDate(new Date());
			cinema.setCreatedBy(user);
			Cinema cinema2 = new Cinema();
			cinema2.setAddress("Hello2");
			cinema2.setName("MyFirstCinema2");
			cinema2.setTelephone("+380637395133");
			cinema2.setCreatedDate(new Date());
			cinema2.setCreatedBy(user);
			Assessment assessment = new Assessment();
			assessment.setCreatedDate(new Date());
			assessment.setAssessment(5);
			assessment.setEstablishment(cinema);
			Assessment assessment2 = new Assessment();
			assessment2.setCreatedDate(new Date());
			assessment2.setAssessment(10);
			assessment2.setEstablishment(cinema2);
			String jsonBeforPersist = objectMapper.writeValueAsString(cinema2);
			System.out.println(jsonBeforPersist);
			em.getTransaction().begin();
			em.persist(user);
			em.persist(assessment);
			em.persist(assessment2);
			em.persist(cinema);
			em.persist(cinema2);
			cinema.setAssessment(assessment);
			cinema2.setAssessment(assessment2);
			TypedQuery<Assessment> query = em.createQuery("select a from Assessment a join a.establishment e where e.name = 'MyFirstCinema2'", Assessment.class);
			List<Assessment> assessments = query.getResultList();
			for(Assessment tempAssessment : assessments) {
				System.out.println(tempAssessment.getAssessment());
			}
			TypedQuery<Cinema> query1 = em.createQuery("from Cinema", Cinema.class);
			List<Cinema> cinemas = query1.getResultList();
			for(Cinema cinema1 : cinemas) {
				System.out.println(cinema1.getName());
			}
			em.getTransaction().commit();
		} finally {
			if(em != null) {
				em.close();
			}
			emf.close();
		}
	}
	public User createUser(String email, String password) {
		User user = new User();
		user.setCreatedDate(new Date());
		user.setEmail(email);
		user.setFirstName("FirstName");
		user.setGroup(UserGroup.ADMINISTRATOR);
		user.setLastName("Lastname");
		user.setPassword(password);
		user.setUsername(username);
		return user;
	}
	public Cinema createCinema(String telephone) {
		Cinema cinema = new Cinema();
		cinema.setAddress("Address");
		return cinema;
	}
}
