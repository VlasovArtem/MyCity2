package org.hillel.it.mycity.persistence.repository.inmemory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.hillel.it.mycity.model.entity.Cinema;
import org.hillel.it.mycity.model.entity.Establishment;
import org.hillel.it.mycity.model.entity.NightClub;
import org.hillel.it.mycity.model.entity.Restaurant;
import org.hillel.it.mycity.persistence.repository.EstablishmentRepository;
import org.springframework.stereotype.Repository;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

public class InMemoryEstablishmentRepository implements EstablishmentRepository{
	protected List<Cinema> cinemas;
	protected List<NightClub> nightClubs;
	protected List<Restaurant> restaurants;
	private Map<Integer, String> estbalishmentMap;
	protected int maxId;
	
	public InMemoryEstablishmentRepository() {
		cinemas = ImmutableList.of();
		nightClubs = ImmutableList.of();
		restaurants = ImmutableList.of();
		estbalishmentMap = ImmutableMap.of();
		maxId = 1;
	}
	
	@Override
	public void addCinema(Cinema cinema) {
		validEstablishment(cinema);
		estbalishmentMap.put(maxId, "Cinema");
		cinemas.add(Objects.requireNonNull(cinema, "This object does not cointains any information"));
		try {
			flushCinema(cinema);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void addNightClub(NightClub nightClub) {
		validEstablishment(nightClub);
		estbalishmentMap.put(maxId, "NightClub");
		nightClubs.add(Objects.requireNonNull(nightClub, "This object does not cointains any information"));
		try {
			flushNightClub(nightClub);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void addRestaurant(Restaurant restaurant) {
		validEstablishment(restaurant);
		estbalishmentMap.put(maxId, "Restaurant");
		restaurants.add(Objects.requireNonNull(restaurant, "This object does not cointains any information"));
		try {
			flushRestaurant(restaurant);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Cinema> getCinemas() {
		return Collections.unmodifiableList(cinemas);
	}

	@Override
	public List<NightClub> getNightClubs() {
		return Collections.unmodifiableList(nightClubs);
	}

	@Override
	public List<Restaurant> getRestaurants() {
		return Collections.unmodifiableList(restaurants);
	}
	
	@Override
	public void deleteEstablishment(int id) {
		validId(id);
		switch (estbalishmentMap.get(id)) {
		case "Cinema":
			Iterator<Cinema> iteratorCinemas = cinemas.iterator();
			while (iteratorCinemas.hasNext()) {
				if(iteratorCinemas.next().getId() == id) {
					iteratorCinemas.remove();
					estbalishmentMap.remove(id);
					break;
				}
			}
			break;
		case "NightClub":
			Iterator<NightClub> iteratorNightClubs = nightClubs.iterator();
			while (iteratorNightClubs.hasNext()) {
				if(iteratorNightClubs.next().getId() == id) {
					iteratorNightClubs.remove();
					estbalishmentMap.remove(id);
					return;
				}
			}
			break;
		case "Restaurant":
			Iterator<Restaurant> iteratorRestaurants = restaurants.iterator();
			while (iteratorRestaurants.hasNext()) {
				if(iteratorRestaurants.next().getId() == id) {
					iteratorRestaurants.remove();
					estbalishmentMap.remove(id);
					return;
				}
			}
			break;
		}
		
	}

	@Override
	public void deleteEstablishments() {
		cinemas.clear();
		nightClubs.clear();
		restaurants.clear();
	}

	@Override
	public Cinema getCinema(int id) {
		validId(id);
		for(Cinema cinema : cinemas) {
			if(cinema.getId() == id) {
				return cinema;
			}
		}
		return null;
	}

	@Override
	public Restaurant getRestaurant(int id) {
		validId(id);
		for(Restaurant restaurant : restaurants) {
			if(restaurant.getId() == id) {
				return restaurant;
			}
		}
		return null;
	}

	@Override
	public NightClub getNightClub(int id) {
		validId(id);
		for(NightClub nightClub : nightClubs) {
			if(nightClub.getId() == id) {
				return nightClub;
			}
		}
		return null;
	}

	@Override
	public void deleteCinemas() {
		cinemas.clear();
	}

	@Override
	public void deleteNightClubs() {
		nightClubs.clear();
	}

	@Override
	public void deleteRestaurants() {
		restaurants.clear();
	}
	
	private void validEstablishment(Establishment establishment) {
		if(establishment.getId() > 0) {
			throw new RuntimeException("This establishment is already exist in memory");
		}
	}
	
	private void validId(int id) {
		if(id < 1 && id > maxId) {
			throw new RuntimeException("Incorrect id");
		} else if(!estbalishmentMap.containsKey(id)) {
			throw new RuntimeException("No such id");
		}
	}
	
	public int getMaxId() {
		int id = maxId;
		return id;
	}
	
	protected void flushCinema(Cinema cinema) throws SQLException{}
	protected void flushNightClub(NightClub nightClub) throws SQLException{}
	protected void flushRestaurant(Restaurant restaurant) throws SQLException{}
	public void updateDataBase() throws SQLException{}
}
