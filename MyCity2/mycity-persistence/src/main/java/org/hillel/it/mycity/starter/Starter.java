package org.hillel.it.mycity.starter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hillel.it.mycity.model.entity.Administrator;
import org.hillel.it.mycity.model.entity.Assessment;
import org.hillel.it.mycity.model.entity.Cinema;
import org.hillel.it.mycity.model.entity.Comment;
import org.hillel.it.mycity.persistence.hibernate.HibernateUtil;
import org.hillel.it.mycity.persistence.repository.EstablishmentRepository;
import org.hillel.it.mycity.persistence.repository.database.DataBaseEstablishmentRepository;

public class Starter {
	public static void main(String[] args) {
		Administrator administrator = new Administrator("test@mail.com", "mypassword");
		administrator.setId(1);
		Cinema cinema = administrator.createEstablishmentCinema();
		Assessment assessment = administrator.createAssessment(5);
		assessment.setId(1);
		cinema.setAssessment(assessment);
		Comment comment = administrator.createComment("Hello man");
		comment.setId(1);
		cinema.setComment(comment);
		cinema.setAddress("My Adress");
		SessionFactory sessionFactory = null;
		Session session = null;
		try {
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.saveOrUpdate(cinema);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		}
	}
}
