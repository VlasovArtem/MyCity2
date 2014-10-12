package org.hillel.it.mycity.persistence.repository.database;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hillel.it.mycity.model.entity.Cinema;
import org.hillel.it.mycity.model.entity.NightClub;
import org.hillel.it.mycity.model.entity.Restaurant;
import org.hillel.it.mycity.persistence.repository.EstablishmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class DataBaseEstablishmentRepository implements EstablishmentRepository{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addCinema(Cinema cinema) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(cinema);
	}

	@Override
	public void addNightClub(NightClub nightClub) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(nightClub);
	}

	@Override
	public void addRestaurant(Restaurant restaurant) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(restaurant);
	}

	@Override
	@Transactional(readOnly=true)
	public Cinema getCinema(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (Cinema) session.get(Cinema.class, id);
	}

	@Override
	@Transactional(readOnly=true)
	public Restaurant getRestaurant(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (Restaurant) session.get(Restaurant.class, id);
	}

	@Override
	@Transactional(readOnly=true)
	public NightClub getNightClub(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (NightClub) session.get(NightClub.class, id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Cinema> getCinemas() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery(Cinema.GET_CINEMAS).list();

	}

	@Override
	@Transactional(readOnly=true)
	public List<NightClub> getNightClubs() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from nightclub").list();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Restaurant> getRestaurants() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from restaurant").list();
	}

	@Override
	public void deleteCinemas() {
		Session session = sessionFactory.getCurrentSession();
		session.createQuery(Cinema.DELETE_CINEMAS);
	}

	@Override
	public void deleteNightClubs() {
		Session session = sessionFactory.getCurrentSession();
		session.createQuery(NightClub.DELETE_NIGHTCLUBS);
	}

	@Override
	public void deleteRestaurants() {
		Session session = sessionFactory.getCurrentSession();
		session.createQuery(Restaurant.DELETE_RESTAURANTS);
	}

	@Override
	public void deleteCinema(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(Cinema.DELETE_CINEMA);
		query.setParameter("id", id);
	}

	@Override
	public void deleteRestaurant(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(Restaurant.DELETE_RESTAURANT);
		query.setParameter("id", id);
	}

	@Override
	public void deleteNightClub(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(NightClub.DELETE_NIGHTCLUB);
		query.setParameter("id", id);
	}

	@Override
	public <T> void updateEstablishment(T t) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(t);
	}
}
