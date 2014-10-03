package org.hillel.it.mycity.persistence.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.hillel.it.mycity.model.entity.Comment;
import org.springframework.jdbc.core.RowMapper;

public class CommentMapper implements RowMapper<Comment>{

	@Override
	public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
		Comment comment = new Comment();
		comment.setComment(rs.getString("comment"));
		comment.setCommentAssessment(rs.getInt("comment_assessment"));
		return comment;
	}

}
