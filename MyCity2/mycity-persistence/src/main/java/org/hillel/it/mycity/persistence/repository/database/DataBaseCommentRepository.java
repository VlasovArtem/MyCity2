package org.hillel.it.mycity.persistence.repository.database;

import java.util.List;

import org.hillel.it.mycity.model.entity.Comment;
import org.hillel.it.mycity.model.entity.Establishment;
import org.hillel.it.mycity.model.entity.Person;
import org.hillel.it.mycity.persistence.repository.CommentRepository;
import org.springframework.stereotype.Repository;

@Repository
public class DataBaseCommentRepository implements CommentRepository{

	@Override
	public void addComment(Comment comment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteComment(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteComments(Person user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteComments(Establishment establishment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteComments(Establishment establishment, Person user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Comment getComment(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> getComments(Person user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> getComments(Establishment establishment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> getComments(Establishment establishment, Person user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> getComments() {
		// TODO Auto-generated method stub
		return null;
	}

}
