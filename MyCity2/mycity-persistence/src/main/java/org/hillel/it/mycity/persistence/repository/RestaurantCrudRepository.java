package org.hillel.it.mycity.persistence.repository;

import java.time.LocalTime;
import java.util.List;

import org.hillel.it.mycity.model.entity.NightClub;
import org.hillel.it.mycity.model.entity.Restaurant;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RestaurantCrudRepository extends CrudRepository<Restaurant, Integer>{
//	public void removeByName(String name);
//	public void removeByAddress(String address);
//	public void removeByNameAndAddress(String name, String address);
//	public List<Restaurant> findByName(String name);
//	public List<Restaurant> findByAddress(String address);
//	public List<Restaurant> findByNameAndAddress(String name, String address);
//	public List<Restaurant> findByTimeOpen(LocalTime timeOpen);
//	public List<Restaurant> findByTimeClose(LocalTime timeClose);
//	public List<Restaurant> findByAverageCheck(int averageCheck);
}
