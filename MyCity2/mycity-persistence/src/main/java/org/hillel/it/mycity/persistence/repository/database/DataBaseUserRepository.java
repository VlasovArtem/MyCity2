package org.hillel.it.mycity.persistence.repository.database;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hillel.it.mycity.model.entity.User;
import org.hillel.it.mycity.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class DataBaseUserRepository implements UserRepository{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);
	}
	@Override
	public void deleteUser(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(User.DELETE_USER);
		query.setParameter("id", id);
	}

	@Override
	@Transactional(readOnly=true)
	public User getUser(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (User) session.get(User.class, id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<User> getUsers() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery(User.GET_USERS).list();
	}
	@Override
	public void deleteUsers() {
		Session session = sessionFactory.getCurrentSession();
		session.createQuery(User.DELETE_USERS);
	}
	@Override
	public void updateUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);
	}
}
