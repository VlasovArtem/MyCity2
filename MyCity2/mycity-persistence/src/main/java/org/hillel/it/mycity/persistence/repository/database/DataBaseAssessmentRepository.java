package org.hillel.it.mycity.persistence.repository.database;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hillel.it.mycity.model.entity.Assessment;
import org.hillel.it.mycity.model.entity.Establishment;
import org.hillel.it.mycity.model.entity.User;
import org.hillel.it.mycity.persistence.repository.AssessmentRepository;
import org.springframework.stereotype.Repository;

@Repository
public class DataBaseAssessmentRepository implements AssessmentRepository {
	@PersistenceContext
	private EntityManager em;
	@Override
	public void addAssessment(Assessment assessment) {
		em.persist(assessment);
	}

	@Override
	public void deleteAssessment(int id) {
		Query query	= em.createNamedQuery(Assessment.DELETE_ASSESSMENT);
		query.setParameter("id", id);
	}

	@Override
	public void deleteAssessment(User user) {
		Query query = em
				.createNamedQuery(Assessment.DELETE_ASSESSMENT_BY_USER_ID);
		query.setParameter("id", user.getId());
	}

	@Override
	public void deleteAssessment(Establishment establishment) {
		
		Query query = em
				.createNamedQuery(Assessment.DELETE_ASSESSMENT_BY_ESTABLISHMENT_ID);
		query.setParameter("id", establishment.getId());
	}

	@Override
	public Assessment getAssessment(int id) {
		
		return em.find(Assessment.class, id);
	}

	@Override
	public List<Assessment> getAssessments(User user) {
		
		TypedQuery<Assessment> query = em
				.createNamedQuery(Assessment.GET_ASSESSMENTS_BY_USER_ID, Assessment.class);
		query.setParameter("id", user.getId());
		return query.getResultList();
	}

	@Override
	public List<Assessment> getAssessments(Establishment establishment) {
		
		TypedQuery<Assessment> query = em
				.createQuery(Assessment.GET_ASSESSMENTS_BY_ESTABLISHMENT_ID, Assessment.class);
		query.setParameter("id", establishment.getId());
		return query.getResultList();
	}

	@Override
	public List<Assessment> getAssessments() {
		
		return em.createQuery(Assessment.GET_ASSESSMENTS, Assessment.class).getResultList();
	}

}
