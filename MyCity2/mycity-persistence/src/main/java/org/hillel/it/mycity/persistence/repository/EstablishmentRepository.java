package org.hillel.it.mycity.persistence.repository;

import java.util.List;

import org.hillel.it.mycity.model.entity.Cinema;
import org.hillel.it.mycity.model.entity.NightClub;
import org.hillel.it.mycity.model.entity.Restaurant;

public interface EstablishmentRepository {
	
	public void addCinema(Cinema cinema);
	public void addNightClub(NightClub nightClub);
	public void addRestaurant(Restaurant restaurant);
	public Cinema getCinema(int id);
	public Restaurant getRestaurant(int id);
	public NightClub getNightClub(int id);
	public List<Cinema> getCinemas();
	public List<NightClub> getNightClubs();
	public List<Restaurant> getRestaurants();
	public void deleteCinemas();
	public void deleteNightClubs();
	public void deleteRestaurants();
	public void deleteCinema(int id);
	public void deleteRestaurant(int id);
	public void deleteNightClub(int id);
	public <T> void updateEstablishment(T t); 
}
