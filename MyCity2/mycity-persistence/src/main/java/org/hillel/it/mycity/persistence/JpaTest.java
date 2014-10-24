package org.hillel.it.mycity.persistence;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hillel.it.mycity.model.entity.Cinema;
import org.hillel.it.mycity.model.entity.User;
import org.hillel.it.mycity.model.entity.UserGroup;

public class JpaTest {
	public static void main(String[] args) throws ClassNotFoundException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mycity");
		EntityManager em = emf.createEntityManager();
		try {
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
			em.getTransaction().begin();
			em.persist(user);
			em.persist(cinema);
			em.getTransaction().commit();
		} finally {
			if(em != null) {
				em.close();
			}
			emf.close();
		}
	}
}
