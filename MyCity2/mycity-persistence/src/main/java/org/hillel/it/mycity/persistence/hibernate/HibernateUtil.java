package org.hillel.it.mycity.persistence.hibernate;

import java.lang.annotation.Annotation;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hillel.it.mycity.model.entity.*;

public class HibernateUtil {
	private final static SessionFactory INSTANCE = buildSessionFactory();
	public static SessionFactory getSessionFactory() {
		return INSTANCE;
	}
	public static SessionFactory buildSessionFactory() {
		try {
			Configuration configuration = new Configuration()
				.addAnnotatedClass(Administrator.class)
				.addAnnotatedClass(Assessment.class)
				.addAnnotatedClass(Cinema.class)
				.addAnnotatedClass(Comment.class)
				.addAnnotatedClass(Moderator.class)
				.addAnnotatedClass(NightClub.class)
				.addAnnotatedClass(Restaurant.class)
				.addAnnotatedClass(User.class)
				.configure();
			ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			return configuration.buildSessionFactory(registry);
		} catch(HibernateException ex) {
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}
}
