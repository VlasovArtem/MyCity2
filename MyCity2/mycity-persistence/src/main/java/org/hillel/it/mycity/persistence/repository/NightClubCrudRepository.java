package org.hillel.it.mycity.persistence.repository;

import java.time.LocalTime;
import java.util.List;
import org.hillel.it.mycity.model.entity.NightClub;
import org.springframework.data.repository.CrudRepository;

public interface NightClubCrudRepository extends CrudRepository<NightClub, Integer>{
	public List<NightClub> removeByName(String name);
	public List<NightClub> removeByAddress(String address);
	public List<NightClub> removeByNameAndAddress(String name, String address);
	public List<NightClub> findByName(String name);
	public List<NightClub> findByAddress(String address);
	public List<NightClub> findByNameAndAddress(String name, String address);
	public List<NightClub> findByTimeOpen(LocalTime timeOpen);
	public List<NightClub> findByTimeClose(LocalTime timeClose);
	public List<NightClub> findByAverageCheckOrderByAverageCheckAsc(int averageCheck);
}
