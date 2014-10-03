package org.hillel.it.mycity.persistence.repository.database;

import java.util.List;

import org.hillel.it.mycity.model.entity.Administrator;
import org.hillel.it.mycity.model.entity.Moderator;
import org.hillel.it.mycity.model.entity.User;
import org.hillel.it.mycity.persistence.repository.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class DataBaseUserRepository implements UserRepository{

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addModerator(Moderator moderator) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addAdministrator(Administrator administrator) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePerson(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Administrator getAdministrator(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Moderator getModerator(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Administrator> getAdministrators() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Moderator> getModerators() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePersons() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deserializeData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean checkUserInDB(String email, String password) {
		// TODO Auto-generated method stub
		return false;
	}

}
