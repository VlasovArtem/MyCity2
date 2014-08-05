package org.hillel.it.mycity.persistence.repository;


import java.util.List;

import org.hillel.it.mycity.model.entity.Administrator;
import org.hillel.it.mycity.model.entity.Moderator;
import org.hillel.it.mycity.model.entity.User;

public interface UserRepository {
	
	public void addUser(User user);
	public void addModerator(Moderator	moderator);
	public void addAdministrator(Administrator administrator);
	public void deletePerson(int id);
	public User getUser(int id);
	public Administrator getAdministrator(int id);
	public Moderator getModerator(int id);
	public List<Administrator> getAdministrators();
	public List<Moderator> getModerators();
	public List<User> getUsers();
	public void deletePersons();
	public void deserializeData();
	public boolean checkUserInDB(String email, String password);
}
