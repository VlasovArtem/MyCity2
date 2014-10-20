package org.hillel.it.mycity.persistence.repository.database;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.persistence.Query;
import org.hillel.it.mycity.model.entity.Cinema;
import org.hillel.it.mycity.model.entity.NightClub;
import org.hillel.it.mycity.model.entity.Restaurant;
import org.hillel.it.mycity.persistence.repository.EstablishmentRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class EstablishmentDAO implements EstablishmentRepository{
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void addCinema(Cinema cinema) {
		em.persist(cinema);
	}

	@Override
	public void addNightClub(NightClub nightClub) {
		em.persist(nightClub);
	}

	@Override
	public void addRestaurant(Restaurant restaurant) {
		em.persist(restaurant);
	}

	@Override
	@Transactional(readOnly=true)
	public Cinema getCinema(int id) {
		return em.find(Cinema.class, id);
	}

	@Override
	@Transactional(readOnly=true)
	public Restaurant getRestaurant(int id) {
		return em.find(Restaurant.class, id);
	}

	@Override
	@Transactional(readOnly=true)
	public NightClub getNightClub(int id) {
		return em.find(NightClub.class, id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Cinema> getCinemas() {
		return em.createNamedQuery(Cinema.GET_CINEMAS, Cinema.class).getResultList();

	}

	@Override
	@Transactional(readOnly=true)
	public List<NightClub> getNightClubs() {
		return em.createNamedQuery(NightClub.GET_NIGTHCLUBS, NightClub.class).getResultList();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Restaurant> getRestaurants() {
		return em.createNamedQuery(Restaurant.GET_RESTAURANTS, Restaurant.class).getResultList();
	}

	@Override
	public void deleteCinemas() {
		em.createNamedQuery(Cinema.DELETE_CINEMAS);
	}

	@Override
	public void deleteNightClubs() {
		em.createNamedQuery(NightClub.DELETE_NIGHTCLUBS);
	}

	@Override
	public void deleteRestaurants() {
		em.createNamedQuery(Restaurant.DELETE_RESTAURANTS);
	}

	@Override
	public void deleteCinema(int id) {
		Query query = em.createNamedQuery(Cinema.DELETE_CINEMA);
		query.setParameter("id", id);
	}

	@Override
	public void deleteRestaurant(int id) {
		Query query = em.createNamedQuery(Restaurant.DELETE_RESTAURANT);
		query.setParameter("id", id);
	}

	@Override
	public void deleteNightClub(int id) {	
		Query query = em.createNamedQuery(NightClub.DELETE_NIGHTCLUB);
		query.setParameter("id", id);
	}
}
