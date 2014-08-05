package org.hillel.it.mycity.persistence.repository.inmemory;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hillel.it.mycity.model.entity.Administrator;
import org.hillel.it.mycity.model.entity.Group;
import org.hillel.it.mycity.model.entity.Moderator;
import org.hillel.it.mycity.model.entity.Person;
import org.hillel.it.mycity.model.entity.User;
import org.hillel.it.mycity.persistence.repository.UserRepository;

public class InMemoryUserRepository implements UserRepository, Serializable{
	
	private static final long serialVersionUID = 2L;
	protected List<Administrator> administrators;
	protected List<Moderator> moderators;
	protected List<User> users;
	protected Map<Integer, Group> userMap;
	protected Set<String> emailSet;
	protected int maxId;
	
	public InMemoryUserRepository() {
		administrators = new ArrayList<>();
		moderators = new ArrayList<>();
		users = new ArrayList<>();
		userMap = new HashMap<Integer, Group>();
		emailSet = new HashSet<String>();
		maxId = 1;
	}

	@Override
	public void addUser(User user) {
		validUser(user);
		validEmail(user.getEmail());
		user.setGroup(Group.User);
		userMap.put(maxId, Group.User);
		user.setId(maxId++);
		users.add(user);
		flush(users, Group.User);
		try {
			flushDB(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void addModerator(Moderator moderator) {
		validUser(moderator);
		validEmail(moderator.getEmail());
		moderator.setGroup(Group.Moderator);
		userMap.put(maxId, Group.Moderator);
		moderator.setId(maxId++);
		moderators.add(moderator);
		flush(moderators, Group.Moderator);
		try {
			flushDB(moderator);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void addAdministrator(Administrator administrator) {
		validUser(administrator);
		validEmail(administrator.getEmail());
		administrator.setGroup(Group.Administrator);
		userMap.put(maxId, Group.Administrator);
		administrator.setId(maxId++);
		administrators.add(administrator);
		flush(administrators, Group.Administrator);
		try {
			flushDB(administrator);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deletePerson(int id) {
		validId(id);

		switch (userMap.get(id)) {
		case User:
			Iterator<User> iteratorUsers = users.iterator();
			while(iteratorUsers.hasNext()) {
				if(iteratorUsers.next().getId() == id) {
					iteratorUsers.remove();
					return;
				}
			}
			break;
		case Moderator:
			Iterator<Moderator> iteratorModerators = moderators.iterator();
			while (iteratorModerators.hasNext()) {
				if(iteratorModerators.next().getId() == id) {
					iteratorModerators.remove();
					return;
				}
			}
			break;
		case Administrator:
			Iterator<Administrator> iteratorAdministrators = administrators.iterator();
			while (iteratorAdministrators.hasNext()) {
				if(iteratorAdministrators.next().getId() == id) {
					iteratorAdministrators.remove();
					return;
				}
			}
			break;
		}
	}

	@Override
	public List<Administrator> getAdministrators() {
		return Collections.unmodifiableList(administrators);
	}

	@Override
	public List<Moderator> getModerators() {
		return Collections.unmodifiableList(moderators);
	}

	@Override
	public List<User> getUsers() {
		return Collections.unmodifiableList(users);
	}

	@Override
	public void deletePersons() {
		administrators.clear();
		moderators.clear();
		users.clear();
	}

	@Override
	public User getUser(int id) {
		if(!checkGroup(id, Group.User)) {
			return null;
		}
		for(User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	@Override
	public Moderator getModerator(int id) {
		if(!checkGroup(id, Group.Moderator)) {
			return null;
		}
		for(Moderator moderator : moderators) {
			if (moderator.getId() == id) {
				return moderator;
			}
		}
		return null;
	}

	@Override
	public Administrator getAdministrator(int id) {
		if(!checkGroup(id, Group.Administrator)) {
			return null;
		}
		for(Administrator administrator : administrators) {
			if (administrator.getId() == id) {
				return administrator;
			}
		}
		return null;
	}
	
	@Override
	public void deserializeData(){}
	
	public int getMaxId() {
		return maxId;
	}
	
	private <T extends Person>void validUser(T t) {
		if(t.getId() < 1 && userMap.containsKey(t.getId())) {
			throw new RuntimeException("User is already exist or he have incorrect id");
		}
	}
	
	private void validId(int id) {
		if(id < 1 && !userMap.containsKey(id)) {
			throw new RuntimeException("Incorrect Id");
		}
	}
	
	private boolean checkGroup(int id, Group group) {
		if(userMap.get(id) != group) {
			System.out.println("This is no such " + group + " object by this ID");
			return false;
		}
		return true;
	}
	
	private void validEmail(String email) {
		if(emailSet.contains(email)) {
			throw new RuntimeException("User with this email is already exist");
		}
		emailSet.add(email);
	}
	
	public <T>void flush(T t, Group group){}
	
	@Override
	public boolean checkUserInDB(String email, String password){ return false;}
	
	protected void flushDB(Person person) throws SQLException{}
}
