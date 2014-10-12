package org.hillel.it.mycity.persistence.repository;


import java.util.List;

import org.hillel.it.mycity.model.entity.User;

public interface UserRepository {
	public void addUser(User user);
	public User getUser(int id);
	public List<User> getUsers();
	public void deleteUsers();
	public void deleteUser(int id);
	public void updateUser(User user);
}
