package org.hillel.it.mycity.persistence.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hillel.it.mycity.connectionpool.ReuseableConnectionPool;
import org.hillel.it.mycity.helper.SqlHelper;
import org.hillel.it.mycity.model.entity.Establishment;

public class EstablishmentDao {
	private Connection connection;
	
	public EstablishmentDao(){
		ReuseableConnectionPool rcp = new ReuseableConnectionPool("jdbc:mysql://localhost:3306/mycity",1); 
		connection = rcp.getConnection();
		
	}
	
	public int addEstablishment(Establishment establishment){
		PreparedStatement preparedStatement;
		int establishmentId = 0;
		try {
			preparedStatement = connection.prepareStatement(""
					+ "INSERT INTO establishments"
					+ "(base_entity_id,name,address,phone,description)"
					+ "VALUES (?,?,?,?,?)");
			preparedStatement.setInt(1, establishment.getBaseEntityId());
			preparedStatement.setString(2, establishment.getName());
			preparedStatement.setString(3, establishment.getAddress());
			preparedStatement.setString(4, establishment.getPhone());
			preparedStatement.setString(5, establishment.getDescription());
			preparedStatement.executeUpdate();
			establishmentId = SqlHelper.getLastInsertedId(preparedStatement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return establishmentId; 
		
	}
	
	public void updateEstablishment(int id){
		
	}
	
	public void deleteEstablishment(int id){
		
	}
	
	public List<Establishment> getAllEstablishments(){
		List<Establishment> list = new ArrayList<>();
		
		return list;
	}
	
	public Establishment getEstablishmentById(int id){
		return null;
	}
	

}
