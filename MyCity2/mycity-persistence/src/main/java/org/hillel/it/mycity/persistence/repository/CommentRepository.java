package org.hillel.it.mycity.persistence.repository;

import java.util.List;

import org.hillel.it.mycity.model.entity.Comment;
import org.hillel.it.mycity.model.entity.Establishment;
import org.hillel.it.mycity.model.entity.User;

public interface CommentRepository {
	public void addComment(Comment comment);
	public void deleteComment(int id);
	public void deleteComments(User user);
	public void deleteComments(Establishment establishment);
	public void deleteComments(Establishment establishment, User user);
	public Comment getComment(int id);
	public List<Comment> getComments(User user);
	public List<Comment> getComments(Establishment establishment);
	public List<Comment> getComments(Establishment establishment, User user);
	public List<Comment> getComments();
}
