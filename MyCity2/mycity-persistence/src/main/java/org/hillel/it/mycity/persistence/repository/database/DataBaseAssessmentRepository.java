package org.hillel.it.mycity.persistence.repository.database;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hillel.it.mycity.model.entity.Assessment;
import org.hillel.it.mycity.model.entity.Establishment;
import org.hillel.it.mycity.model.entity.User;
import org.hillel.it.mycity.persistence.hibernate.HibernateUtil;
import org.hillel.it.mycity.persistence.repository.AssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DataBaseAssessmentRepository implements AssessmentRepository {
	@Autowired
	private SessionFactory sessionFactory;
	// think about search method.
	@Override
	public void addAssessment(Assessment assessment) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(assessment);
	}

	@Override
	public void deleteAssessment(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(Assessment.DELETE_ASSESSMENT);
		query.setParameter("id", id);
	}

	@Override
	public void deleteAssessment(User user) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery(Assessment.DELETE_ASSESSMENT_BY_USER_ID);
		query.setParameter("id", user.getId());
	}

	@Override
	public void deleteAssessment(Establishment establishment) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery(Assessment.DELETE_ASSESSMENT_BY_ESTABLISHMENT_ID);
		query.setParameter("id", establishment.getId());
	}

	@Override
	public Assessment getAssessment(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (Assessment) session.get(Assessment.class, id);
	}

	@Override
	public List<Assessment> getAssessments(User user) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery(Assessment.GET_ASSESSMENTS_BY_USER_ID);
		query.setParameter("id", user.getId());
		return query.list();
	}

	@Override
	public List<Assessment> getAssessments(Establishment establishment) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery(Assessment.GET_ASSESSMENTS_BY_ESTABLISHMENT_ID);
		query.setParameter("id", establishment.getId());
		return query.list();
	}

	@Override
	public List<Assessment> getAssessments() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery(Assessment.GET_ASSESSMENTS).list();
	}

	@Override
	public void updateAssessment(Assessment assessment) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(assessment);
	}
}
