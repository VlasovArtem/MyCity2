package org.hillel.it.mycity.persistence.repository;

import java.util.List;

import org.hillel.it.mycity.model.entity.User;
import org.hillel.it.mycity.model.entity.UserGroup;
import org.springframework.data.repository.CrudRepository;

public interface UserCrudRepistory extends CrudRepository<User, Integer> {
	public List<User> removeByEmail(String email);
	public List<User> removeByUsername(String username);
	public List<User> removeByGroup(UserGroup group);
	public List<User> removeByCreatedBy(User user);
	public List<User> findByEmail(String email);
	public List<User> findByUsername(String username);
	public List<User> findByGroup(UserGroup group);
	public List<User> findByCreatedBy(User user);
}
