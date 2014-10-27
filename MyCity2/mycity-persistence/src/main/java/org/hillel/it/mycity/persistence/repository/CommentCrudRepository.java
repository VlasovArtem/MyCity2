package org.hillel.it.mycity.persistence.repository;

import java.util.List;

import org.hillel.it.mycity.model.entity.Comment;
import org.hillel.it.mycity.model.entity.Establishment;
import org.hillel.it.mycity.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true)
public interface CommentCrudRepository extends CrudRepository<Comment, Integer>{
	public List<Comment> removeByCreatedBy(User user);
	public List<Comment> removeByEstablishment(Establishment establishment);
	public List<Comment> removeByCreatedByAndEstablishment(User user, Establishment establishment);
	public List<Comment> findByCreatedBy(User user);
	public List<Comment> findByEstablishment(Establishment establishment);
	public List<Comment> findByCreatedByAndEstablishment(User user, Establishment establishment);
}
