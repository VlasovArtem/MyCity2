package org.hillel.it.mycity.persistence.repository;

import java.util.List;

import org.hillel.it.mycity.model.entity.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true)
public interface CommentCrudRepository extends CrudRepository<Comment, Integer>{
	public void removeByUser(String username);
	public void removeByEstablishment(String name);
	public void removeByCreatedByAndEstablishment(String username, String name);
	public List<Comment> findByCreatedBy(String username);
	public List<Comment> findByEstablishment(String name);
	public List<Comment> findByCreatedByAndEstablishment(String username, String name);
}
