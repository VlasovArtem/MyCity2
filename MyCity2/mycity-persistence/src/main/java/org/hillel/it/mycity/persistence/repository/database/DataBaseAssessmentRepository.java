package org.hillel.it.mycity.persistence.repository.database;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hillel.it.mycity.model.entity.Assessment;
import org.hillel.it.mycity.model.entity.Establishment;
import org.hillel.it.mycity.model.entity.User;
import org.hillel.it.mycity.persistence.repository.AssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DataBaseAssessmentRepository implements AssessmentRepository {
	@PersistenceContext
	private EntityManager em;
	// think about search method.
	@Override
	public void addAssessment(Assessment assessment) {
		em.persist(assessment);
	}

	@Override
	public void deleteAssessment(int id) {
		Session session = em.getCurrentSession();
		Query query = session.createQuery(Assessment.DELETE_ASSESSMENT);
		query.setParameter("id", id);
	}

	@Override
	public void deleteAssessment(User user) {
		Session session = em.getCurrentSession();
		Query query = session
				.createQuery(Assessment.DELETE_ASSESSMENT_BY_USER_ID);
		query.setParameter("id", user.getId());
	}

	@Override
	public void deleteAssessment(Establishment establishment) {
		Session session = em.getCurrentSession();
		Query query = session
				.createQuery(Assessment.DELETE_ASSESSMENT_BY_ESTABLISHMENT_ID);
		query.setParameter("id", establishment.getId());
	}

	@Override
	public Assessment getAssessment(int id) {
		Session session = em.getCurrentSession();
		return (Assessment) session.get(Assessment.class, id);
	}

	@Override
	public List<Assessment> getAssessments(User user) {
		Session session = em.getCurrentSession();
		Query query = session
				.createQuery(Assessment.GET_ASSESSMENTS_BY_USER_ID);
		query.setParameter("id", user.getId());
		return query.list();
	}

	@Override
	public List<Assessment> getAssessments(Establishment establishment) {
		Session session = em.getCurrentSession();
		Query query = session
				.createQuery(Assessment.GET_ASSESSMENTS_BY_ESTABLISHMENT_ID);
		query.setParameter("id", establishment.getId());
		return query.list();
	}

	@Override
	public List<Assessment> getAssessments() {
		Session session = em.getCurrentSession();
		return session.createQuery(Assessment.GET_ASSESSMENTS).list();
	}

	@Override
	public void updateAssessment(Assessment assessment) {
		Session session = em.getCurrentSession();
		session.saveOrUpdate(assessment);
	}
}
