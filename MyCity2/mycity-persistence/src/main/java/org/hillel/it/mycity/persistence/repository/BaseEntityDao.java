package org.hillel.it.mycity.persistence.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hillel.it.mycity.connectionpool.ReuseableConnectionPool;
import org.hillel.it.mycity.helper.SqlHelper;
import org.hillel.it.mycity.model.entity.BaseEntity;

public class BaseEntityDao {
	private Connection connection;
	
	public BaseEntityDao(){
		ReuseableConnectionPool rcp = new ReuseableConnectionPool("jdbc:mysql://localhost:3306/mycity",1); 
		connection = rcp.getConnection();
		
	}
	
	public int addBaseEntity(BaseEntity baseEntity){
		PreparedStatement preparedStatement;
		int baseEntityId = 0;
		try {
			preparedStatement = connection.prepareStatement(""
					+ "INSERT INTO base_entities"
					+ "(created_date,created_by)"
					+ "VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			preparedStatement.setString(1, sdf.format(baseEntity.getCreatedDate() ));
			preparedStatement.setInt(2, baseEntity.getCreatedBy().getId());
			preparedStatement.executeUpdate();
			baseEntityId = SqlHelper.getLastInsertedId(preparedStatement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return baseEntityId; 
		
	}
	
	public void updateBaseEntity(int id){
		
	}
	
	public void deleteBaseEntity(int id){
		
	}
	
	public List<BaseEntity> getAllBaseEntities(){
		List<BaseEntity> list = new ArrayList<>();
		
		return list;
	}
	
	public BaseEntity getBaseEntityById(int id){
		return null;
	}
	

}
