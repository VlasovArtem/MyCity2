package org.hillel.it.mycity.persistence.repository;

import java.util.List;

import org.hillel.it.mycity.model.entity.User;
import org.hillel.it.mycity.model.entity.UserGroup;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserCrudRepistory extends CrudRepository<User, Integer> {
//	public void removeByEmail(String email);
//	public void removeByUsername(String username);
//	public void removeByGroup(UserGroup group);
//	public List<User> findByEmail(String email);
//	public List<User> findByUsername(String username);
//	public List<User> findByGroup(UserGroup group);
}
