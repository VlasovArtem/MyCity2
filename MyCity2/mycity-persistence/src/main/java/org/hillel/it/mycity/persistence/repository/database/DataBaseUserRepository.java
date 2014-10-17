package org.hillel.it.mycity.persistence.repository.database;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hillel.it.mycity.model.entity.User;
import org.hillel.it.mycity.model.entity.UserGroup;
import org.hillel.it.mycity.persistence.repository.UserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class DataBaseUserRepository implements UserRepository{
	@PersistenceContext
	private EntityManager em;
	@Override
	public void addUser(User user) {
		em.persist(user);
	}
	@Override
	public void deleteUser(int id) {
		Query query = em.createNamedQuery(User.DELETE_USER);
		query.setParameter("id", id);
	}
	@Override
	@Transactional(readOnly=true)
	public User getUser(int id) {
		return em.find(User.class, id);
	}
	@Override
	@Transactional(readOnly=true)
	public List<User> getUsers() {
		return em.createNamedQuery(User.GET_USERS, User.class).getResultList();
	}
	@Override
	public void deleteUsers() {
		em.createNamedQuery(User.DELETE_USERS);
	}
	@Override
	public <T> List<T> getUsersByGroup(Class<T> resultClass, UserGroup group) {
		TypedQuery<T> query = em.createNamedQuery(User.GET_USERS_BY_GROUP, resultClass);
		query.setParameter("usergroup", group.name());
		return query.getResultList();
	}
}
