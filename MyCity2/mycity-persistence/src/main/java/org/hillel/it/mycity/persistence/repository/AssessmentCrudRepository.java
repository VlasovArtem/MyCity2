package org.hillel.it.mycity.persistence.repository;

import java.util.List;

import org.hillel.it.mycity.model.entity.Assessment;
import org.hillel.it.mycity.model.entity.Establishment;
import org.hillel.it.mycity.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface AssessmentCrudRepository extends CrudRepository<Assessment, Integer>{
	public List<Assessment> removeByCreatedBy(User user);
	public List<Assessment> removeByEstablishment(Establishment establishment);
	public List<Assessment> findByCreatedBy(User user);
	public List<Assessment> findByEstablishment(Establishment establishment);
}
