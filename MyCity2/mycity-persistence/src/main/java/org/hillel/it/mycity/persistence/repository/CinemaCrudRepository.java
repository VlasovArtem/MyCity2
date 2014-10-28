package org.hillel.it.mycity.persistence.repository;

import java.util.List;

import org.hillel.it.mycity.model.entity.Cinema;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CinemaCrudRepository extends CrudRepository<Cinema, Integer>{
	@Modifying
	@Query(name=Cinema.REMOVE_BY_NAME)
	public void removeByName(String name);
	@Modifying
	@Query(name=Cinema.REMOVE_BY_ADDRESS)
	public void removeByAddress(String address);
	@Modifying
	@Query(name=Cinema.REMOVE_BY_NAME_AND_ADDRESS)
	public void removeByNameAndAddress(String name, String address);
	@Modifying
	@Query(name=Cinema.FIND_BY_NAME)
	public List<Cinema> findByName(String name);
	@Modifying
	@Query(name=Cinema.FIND_BY_ADDRESS)
	public List<Cinema> findByAddress(String address);
	@Modifying
	@Query(name=Cinema.FIND_BY_NAME_AND_ADDRESS)
	public List<Cinema> findByNameAndAddress(String name, String address);
	@Modifying
	@Query(name=Cinema.FIND_BY_CINEMATECHNOLOGY)
	public List<Cinema> findByCinemaTechnology(String cinemaTechnology);
}
