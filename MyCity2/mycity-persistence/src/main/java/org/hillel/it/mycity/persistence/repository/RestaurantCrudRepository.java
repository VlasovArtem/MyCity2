package org.hillel.it.mycity.persistence.repository;

import java.time.LocalTime;
import java.util.List;

import org.hillel.it.mycity.model.entity.Restaurant;
import org.springframework.data.repository.CrudRepository;

public interface RestaurantCrudRepository extends CrudRepository<Restaurant, Integer>{
	public List<Restaurant> removeByName(String name);
	public List<Restaurant> removeByAddress(String address);
	public List<Restaurant> removeByNameAndAddress(String name, String address);
	public List<Restaurant> findByName(String name);
	public List<Restaurant> findByAddress(String address);
	public List<Restaurant> findByNameAndAddress(String name, String address);
	public List<Restaurant> findByTimeOpen(LocalTime timeOpen);
	public List<Restaurant> findByTimeClose(LocalTime timeClose);
	public List<Restaurant> findByAverageCheckOrderByAverageCheckAsc(int averageCheck);
}
