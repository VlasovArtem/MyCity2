package org.hillel.it.mycity.persistence.repository.database;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hillel.it.mycity.model.entity.Comment;
import org.hillel.it.mycity.model.entity.Establishment;
import org.hillel.it.mycity.model.entity.User;
import org.hillel.it.mycity.persistence.repository.CommentRepository;
import org.springframework.stereotype.Repository;

@Repository
public class DataBaseCommentRepository implements CommentRepository{
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void addComment(Comment comment) {
		em.persist(comment);
	}

	@Override
	public void deleteComment(int id) {
		Query query = em.createNamedQuery(Comment.DELETE_COMMENT_BY_ID);
		query.setParameter("id", id);
	}

	@Override
	public void deleteComments(User user) {
		Query query = em.createNamedQuery(Comment.DELETE_COMMENT_BY_USER_ID);
		query.setParameter("id", user.getId());
	}

	@Override
	public void deleteComments(Establishment establishment) {
		Query query = em.createNamedQuery(Comment.DELETE_COMMENT_BY_ESTABLISHMENT_ID);
		query.setParameter("id", establishment.getId());
	}

	@Override
	public void deleteComments(Establishment establishment, User user) {
		Query query = em.createNamedQuery(Comment.DELETE_COMMENT_BY_ESTABLISHMENT_AND_USER_ID);
		query.setParameter("establishment_id", establishment.getId());
		query.setParameter("person_id", user.getId());
	}

	@Override
	public Comment getComment(int id) {
		return em.find(Comment.class, id);
	}

	@Override
	public List<Comment> getComments(User user) {
		TypedQuery<Comment> query = em.createNamedQuery(Comment.GET_COMMENT_BY_USER_ID, Comment.class);
		query.setParameter("id", user.getId());
		return query.getResultList();
	}

	@Override
	public List<Comment> getComments(Establishment establishment) {
		TypedQuery<Comment> query = em.createNamedQuery(Comment.DELETE_COMMENT_BY_ESTABLISHMENT_ID, Comment.class);
		query.setParameter("id", establishment.getId());
		return query.getResultList();
	}

	@Override
	public List<Comment> getComments(Establishment establishment, User user) {
		TypedQuery<Comment> query = em.createNamedQuery(Comment.DELETE_COMMENT_BY_ESTABLISHMENT_AND_USER_ID, Comment.class);
		query.setParameter("pid", user.getId());
		query.setParameter("eid", establishment.getId());
		return query.getResultList();
	}

	@Override
	public List<Comment> getComments() {
		return em.createNamedQuery(Comment.GET_COMMENTS, Comment.class).getResultList();
	}
	
}
