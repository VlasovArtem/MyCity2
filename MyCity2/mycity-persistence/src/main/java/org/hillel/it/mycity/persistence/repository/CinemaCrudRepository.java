package org.hillel.it.mycity.persistence.repository;

import java.util.List;

import org.hillel.it.mycity.model.entity.Cinema;
import org.springframework.data.repository.CrudRepository;

public interface CinemaCrudRepository extends CrudRepository<Cinema, Integer>{
	public void removeByName(String name);
	public void removeByAddress(String address);
	public void removeByNameAndAddress(String name, String address);
	public List<Cinema> findByName(String name);
	public List<Cinema> findByAddress(String address);
	public List<Cinema> findByNameAndAddress(String name, String address);
	public List<Cinema> findByCinemaTechnology(String cinemaTechnology);
}
