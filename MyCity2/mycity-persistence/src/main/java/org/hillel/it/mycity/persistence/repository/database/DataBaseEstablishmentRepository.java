package org.hillel.it.mycity.persistence.repository.database;

import java.sql.SQLException;
import java.util.List;

import org.hillel.it.mycity.model.entity.Cinema;
import org.hillel.it.mycity.model.entity.NightClub;
import org.hillel.it.mycity.model.entity.Restaurant;
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
		// TODO Auto-generated method stub
		return null;
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
	
}
