package org.hillel.it.mycity.persistence.repository;

import java.util.List;

import org.hillel.it.mycity.model.entity.User;
import org.hillel.it.mycity.model.entity.UserGroup;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserCrudRepistory extends CrudRepository<User, Integer> {
	@Modifying
	@Query(name=User.REMOVE_BY_EMAIL)
	public void removeByEmail(String email);
	@Modifying
	@Query(name=User.REMOVE_BY_USERNAME)
	public void removeByUsername(String username);
	@Modifying
	@Query(name=User.REMOVE_BY_GROUP)
	public void removeByGroup(UserGroup group);
	@Modifying
	@Query(name=User.FIND_BY_EMAIL)
	public List<User> findByEmail(String email);
	@Modifying
	@Query(name=User.FIND_BY_USERNAME)
	public List<User> findByUsername(String username);
	@Modifying
	@Query(name=User.FIND_BY_GROUP)
	public List<User> findByGroup(UserGroup group);
}
