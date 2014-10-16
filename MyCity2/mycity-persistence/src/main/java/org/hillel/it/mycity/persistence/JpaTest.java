package org.hillel.it.mycity.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hillel.it.mycity.model.entity.Cinema;

public class JpaTest {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("school");
		EntityManager em = emf.createEntityManager();
		try {
			Cinema cinema = new Cinema();
			cinema.setAddress("Hello");
			em.getTransaction().begin();
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
