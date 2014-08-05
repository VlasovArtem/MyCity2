package org.hillel.it.mycity.persistence.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hillel.it.mycity.connectionpool.ReuseableConnectionPool;
import org.hillel.it.mycity.helper.SqlHelper;
import org.hillel.it.mycity.model.entity.BaseEntity;
import org.hillel.it.mycity.model.entity.Establishment;
import org.hillel.it.mycity.model.entity.Person;
import org.hillel.it.mycity.model.entity.Restaurant;

public class RestaurantDao {
	
	private Connection connection;
	
	public RestaurantDao(){
		ReuseableConnectionPool rcp = new ReuseableConnectionPool("jdbc:mysql://localhost:3306/mycity",1); 
		connection = rcp.getConnection();
	}
	
	public void addRestaurant(Restaurant restaurnant){
		try {	        
	        BaseEntity baseEntity = new BaseEntity();
	        Person createdBy = new Person(); // нужно будет взять пользователя из текущей сессии
	        baseEntity.setCreatedBy(createdBy);
	        BaseEntityDao beDao = new BaseEntityDao();
	        int baseEntityId = beDao.addBaseEntity(baseEntity);
	        
	        Establishment establishment = restaurnant;
	        establishment.setBaseEntityId(baseEntityId);
	        EstablishmentDao estabDao = new EstablishmentDao();
	        int establishmentId = estabDao.addEstablishment(establishment);

	        PreparedStatement preparedStatement = connection.prepareStatement(""
					+ "INSERT INTO Restaurant"
					+ "(establishment_id, time_open, time_close, average_check)"
					+ "VALUES (?,?,?,?)");
			preparedStatement.setInt(1, establishmentId);
			preparedStatement.setString(2, restaurnant.getTimeOpen().toString());
			preparedStatement.setString(3, restaurnant.getTimeClose().toString());
			preparedStatement.setInt(4, restaurnant.getAverageCheck());
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteRestaurant(int id){
		
	}

	public Restaurant getRestaurantById(
			int restaurantId) {
		return null;
	}

	public void updateRestaurant(Restaurant restaurant) {
		// TODO Auto-generated method stub
		
	}

	public Object getAllRestaurants() {
		// TODO Auto-generated method stub
		return null;
	}

}
