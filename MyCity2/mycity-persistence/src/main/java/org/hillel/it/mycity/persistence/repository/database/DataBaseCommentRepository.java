package org.hillel.it.mycity.persistence.repository.database;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.loader.custom.Return;
import org.hillel.it.mycity.model.entity.Comment;
import org.hillel.it.mycity.model.entity.Establishment;
import org.hillel.it.mycity.model.entity.User;
import org.hillel.it.mycity.persistence.hibernate.HibernateUtil;
import org.hillel.it.mycity.persistence.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DataBaseCommentRepository implements CommentRepository{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addComment(Comment comment) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(comment);
	}

	@Override
	public void deleteComment(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(Comment.DELETE_COMMENT_BY_ID);
		query.setParameter("id", id);
	}

	@Override
	public void deleteComments(User user) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(Comment.DELETE_COMMENT_BY_USER_ID);
		query.setParameter("id", user.getId());
	}

	@Override
	public void deleteComments(Establishment establishment) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(Comment.DELETE_COMMENT_BY_ESTABLISHMENT_ID);
		query.setParameter("id", establishment.getId());
	}

	@Override
	public void deleteComments(Establishment establishment, User user) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(Comment.DELETE_COMMENT_BY_ESTABLISHMENT_AND_USER_ID);
		query.setParameter("establishment_id", establishment.getId());
		query.setParameter("person_id", user.getId());
	}

	@Override
	public Comment getComment(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (Comment) session.get(Comment.class, id);
	}

	@Override
	public List<Comment> getComments(User user) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from comment where person_id = :id");
		query.setParameter("id", user.getId());
		return (List<Comment>) query.list();
	}

	@Override
	public List<Comment> getComments(Establishment establishment) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from comment where establishment_id = :id");
		query.setParameter("id", establishment.getId());
		return (List<Comment>) query.list();
	}

	@Override
	public List<Comment> getComments(Establishment establishment, User user) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from comment where person_id = :pid and establishment_id = :eid");
		query.setParameter("pid", user.getId());
		query.setParameter("eid", establishment.getId());
		return (List<Comment>) query.list();
	}

	@Override
	public List<Comment> getComments() {
		Session session = sessionFactory.getCurrentSession();
		return (List<Comment>) session.createQuery("from comment").list();
	}
	
	@Override
	public void updateComment(Comment comment) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(comment);
	}
}
