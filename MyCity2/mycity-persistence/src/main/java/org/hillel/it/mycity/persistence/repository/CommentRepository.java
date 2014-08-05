package org.hillel.it.mycity.persistence.repository;

import java.util.List;

import org.hillel.it.mycity.model.entity.Comment;
import org.hillel.it.mycity.model.entity.Establishment;
import org.hillel.it.mycity.model.entity.Person;

public interface CommentRepository {
	public void addComment(Comment comment);
	public void deleteComment(int id);
	public void deleteComments(Person user);
	public void deleteComments(Establishment establishment);
	public void deleteComments(Establishment establishment, Person user);
	public Comment getComment(int id);
	public List<Comment> getComments(Person user);
	public List<Comment> getComments(Establishment establishment);
	public List<Comment> getComments(Establishment establishment, Person user);
	public List<Comment> getComments();
}
