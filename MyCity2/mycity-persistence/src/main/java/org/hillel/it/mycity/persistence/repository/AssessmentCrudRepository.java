package org.hillel.it.mycity.persistence.repository;

import java.util.List;

import org.hillel.it.mycity.model.entity.Assessment;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface AssessmentCrudRepository extends CrudRepository<Assessment, Integer>{
	@Modifying
	@Query(name=Assessment.REMOVE_BY_USER)
	public void removeByUser(String username);
	@Modifying
	@Query(name=Assessment.REMOVE_BY_ESTABLISHMENT)
	public void removeByEstablishment(String establishmentName);
	@Modifying
	@Query(name=Assessment.FIND_BY_USER)
	public List<Assessment> findByUser(String username);
	@Modifying
	@Query(name=Assessment.FIND_BY_ESTABLISHMENT)
	public List<Assessment> findByEstablishment(String establishmentName);
}
