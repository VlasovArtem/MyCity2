package org.hillel.it.mycity.persistence.repository;

import java.time.LocalTime;
import java.util.List;

import org.hillel.it.mycity.model.entity.NightClub;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface NightClubCrudRepository extends CrudRepository<NightClub, Integer>{
	@Modifying
	@Query(name=NightClub.REMOVE_BY_NAME)
	public void removeByName(String name);
	@Modifying
	@Query(name=NightClub.REMOVE_BY_ADDRESS)
	public void removeByAddress(String address);
	@Modifying
	@Query(name=NightClub.REMOVE_BY_NAME_AND_ADDRESS)
	public void removeByNameAndAddress(String name, String address);
	@Modifying
	@Query(name=NightClub.FIND_BY_NAME)
	public List<NightClub> findByName(String name);
	@Modifying
	@Query(name=NightClub.FIND_BY_ADDRESS)
	public List<NightClub> findByAddress(String address);
	@Modifying
	@Query(name=NightClub.FIND_BY_NAME_AND_ADDRESS)
	public List<NightClub> findByNameAndAddress(String name, String address);
	@Modifying
	@Query(name=NightClub.FIND_BY_TIME_OPEN)
	public List<NightClub> findByTimeOpen(LocalTime timeOpen);
	@Modifying
	@Query(name=NightClub.FIND_BY_TIME_CLOSE)
	public List<NightClub> findByTimeClose(LocalTime timeClose);
	@Modifying
	@Query(name=NightClub.FIND_BY_AVERAGE_CHECK)
	public List<NightClub> findByAverageCheck(int averageCheck);
}
