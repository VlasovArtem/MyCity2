package org.hillel.it.mycity.persistence.repository;

import java.util.List;

import org.hillel.it.mycity.model.entity.Comment;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true)
public interface CommentCrudRepository extends CrudRepository<Comment, Integer>{
	@Modifying
	@Query(name=Comment.REMOVE_BY_USER)
	public void removeByUser(String username);
	@Modifying
	@Query(name=Comment.REMOVE_BY_ESTABLISHMENT)
	public void removeByEstablishment(String name);
	@Modifying
	@Query(name=Comment.REMOVE_BY_USER_AND_ESTABLISHMENT)
	public void removeByCreatedByAndEstablishment(String username, String name);
	@Modifying
	@Query(name=Comment.FIND_BY_USER)
	public List<Comment> findByCreatedBy(String username);
	@Modifying
	@Query(name=Comment.FIND_BY_ESTABLISHMENT)
	public List<Comment> findByEstablishment(String name);
	@Modifying
	@Query(name=Comment.FIND_BY_USER_AND_ESTABLISHMENT)
	public List<Comment> findByCreatedByAndEstablishment(String username, String name);
}
