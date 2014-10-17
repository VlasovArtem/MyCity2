package org.hillel.it.mycity.persistence.repository;

import java.util.List;

import org.hillel.it.mycity.model.entity.User;
import org.hillel.it.mycity.model.entity.UserGroup;

public interface UserRepository {
	public void addUser(User user);
	public User getUser(int id);
	public List<User> getUsers();
	public <T> List<T> getUsersByGroup(Class<T> resultClass, UserGroup group);
	public void deleteUsers();
	public void deleteUser(int id);
}
