package org.hillel.it.mycity.persistence.repository;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.hillel.it.mycity.connectionpool.ReuseableConnectionPool;
import org.hillel.it.mycity.model.entity.NightClub;

public class NightClubDao {
	private Connection connection;
	
	public NightClubDao(){
		ReuseableConnectionPool rcp = new ReuseableConnectionPool("jdbc:mysql://localhost:3306/mycity",1); 
		connection = rcp.getConnection();

	}
	
	public int addNightClub(NightClub nightClub){
		
		return 0; 
		
	}
	
	public void updateNightClub(int id){
		
	}
	
	public void deleteNightClub(int id){
		
	}
	
	public List<NightClub> getAllNightClubs(){
		List<NightClub> baseEntities = new ArrayList<>();
		
		return baseEntities;
	}
	
	public NightClub getNightClubById(int id){
		return null;
	}
	

}
