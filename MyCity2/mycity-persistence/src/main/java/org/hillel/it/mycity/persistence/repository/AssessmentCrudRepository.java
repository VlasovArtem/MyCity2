package org.hillel.it.mycity.persistence.repository;

import java.util.List;

import org.hillel.it.mycity.model.entity.Assessment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface AssessmentCrudRepository extends CrudRepository<Assessment, Integer>{
	public void removeByUser(String username);
	public void removeByEstablishment(String establishmentName);
	public List<Assessment> findByUser(String username);
	public List<Assessment> findByEstablishment(String establishmentName);
}
