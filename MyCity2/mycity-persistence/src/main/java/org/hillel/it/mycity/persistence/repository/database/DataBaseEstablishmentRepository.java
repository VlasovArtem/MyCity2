package org.hillel.it.mycity.persistence.repository.database;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hillel.it.mycity.model.entity.Cinema;
import org.hillel.it.mycity.model.entity.NightClub;
import org.hillel.it.mycity.model.entity.Restaurant;
import org.hillel.it.mycity.persistence.hibernate.HibernateUtil;
import org.hillel.it.mycity.persistence.repository.EstablishmentRepository;
import org.springframework.stereotype.Repository;

@Repository
public class DataBaseEstablishmentRepository implements EstablishmentRepository{

	@Override
	public void addCinema(Cinema cinema) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addNightClub(NightClub nightClub) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addRestaurant(Restaurant restaurant) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cinema getCinema(int id) {
		SessionFactory factory = null;
		Cinema cinema = null;
		try {
			factory = HibernateUtil.getSessionFactory();
			execute(factory, (session) -> {
				(Cinema) session.get(Cinema.class, id);
			});
		} catch (HibernateException ex) {
			ex.printStackTrace();
			throw new HibernateException(ex);
		}
		return cinema;
	}

	@Override
	public Restaurant getRestaurant(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NightClub getNightClub(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cinema> getCinemas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NightClub> getNightClubs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Restaurant> getRestaurants() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCinemas() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteNightClubs() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteRestaurants() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteEstablishments() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteEstablishment(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateDataBase() throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	public void execute(SessionFactory factory, Action runner) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		try {
			runner.run(session);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			if(session != null) {
				session.close();
			}
		}
	}
	public <T> T execute(SessionFactory factory, ReturnAction<T> runner) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		T t = null;
		try {
			t = runner.run(session);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			if(session != null) {
				session.close();
			}
		}
		return t;
	}
	interface Action {
		void run(Session session);
	}
	interface ReturnAction<T> {
		T run(Session session);
	}
}
