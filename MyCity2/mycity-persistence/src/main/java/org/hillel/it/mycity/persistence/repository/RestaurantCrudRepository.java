package org.hillel.it.mycity.persistence.repository;

import java.time.LocalTime;
import java.util.List;

import org.hillel.it.mycity.model.entity.NightClub;
import org.hillel.it.mycity.model.entity.Restaurant;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RestaurantCrudRepository extends CrudRepository<Restaurant, Integer>{
	@Modifying
	@Query(name=Restaurant.REMOVE_BY_NAME)
	public void removeByName(String name);
	@Modifying
	@Query(name=Restaurant.REMOVE_BY_ADDRESS)
	public void removeByAddress(String address);
	@Modifying
	@Query(name=Restaurant.REMOVE_BY_NAME_AND_ADDRESS)
	public void removeByNameAndAddress(String name, String address);
	@Modifying
	@Query(name=Restaurant.FIND_BY_NAME)
	public List<Restaurant> findByName(String name);
	@Modifying
	@Query(name=Restaurant.FIND_BY_ADDRESS)
	public List<Restaurant> findByAddress(String address);
	@Modifying
	@Query(name=Restaurant.FIND_BY_NAME_AND_ADDRESS)
	public List<Restaurant> findByNameAndAddress(String name, String address);
	@Modifying
	@Query(name=Restaurant.FIND_BY_TIME_OPEN)
	public List<Restaurant> findByTimeOpen(LocalTime timeOpen);
	@Modifying
	@Query(name=Restaurant.FIND_BY_TIME_CLOSE)
	public List<Restaurant> findByTimeClose(LocalTime timeClose);
	@Modifying
	@Query(name=Restaurant.FIND_BY_AVERAGE_CHECK)
	public List<Restaurant> findByAverageCheck(int averageCheck);
}
