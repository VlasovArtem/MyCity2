package org.hillel.it.mycity.persistence.repository;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.hillel.it.mycity.connectionpool.ReuseableConnectionPool;
import org.hillel.it.mycity.model.entity.Cinema;

public class CinemaDao {
	private Connection connection;
	
	public CinemaDao(){
		ReuseableConnectionPool rcp = new ReuseableConnectionPool("jdbc:mysql://localhost:3306/mycity",1); 
		connection = rcp.getConnection();
		
	}
	
	public int addCinema(Cinema cinema){
		
		return 0; 
		
	}
	
	public void updateCinema(int id){
		
	}
	
	public void deleteCinema(int id){
		
	}
	
	public List<Cinema> getAllCinemas(){
		List<Cinema> baseEntities = new ArrayList<>();
		
		return baseEntities;
	}
	
	public Cinema getCinemaById(int id){
		return null;
	}
	

}
