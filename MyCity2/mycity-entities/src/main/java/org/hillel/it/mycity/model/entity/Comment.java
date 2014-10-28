package org.hillel.it.mycity.model.entity;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
@Entity
@Table(name="COMMENTS")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@AttributeOverride(name="id", column = @Column(name="comment_id", insertable=false, updatable=false))
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@NamedQueries({
	@NamedQuery(name = Comment.REMOVE_BY_USER, query = "DELETE FROM Comment c "
			+ "INNER JOIN User u ON c.created_by = u.user_id "
			+ "WHERE u.username = ?1"),
	@NamedQuery(name = Comment.REMOVE_BY_ESTABLISHMENT, query = "DELETE FROM Comment c "
			+ "INNER JOIN Establishment e ON c.establishment_id = e.establishment_id "
			+ "WHERE e.name = ?1"),
	@NamedQuery(name = Comment.REMOVE_BY_USER_AND_ESTABLISHMENT, query = "DELETE FROM Comment c "
			+ "INNER JOIN User u ON c.created_by = u.user_id "
			+ "INNER JOIN Establishment e ON c.establishment_id = e.establishment_id"
			+ "WHERE u.username = ?1"),
	@NamedQuery(name = Comment.FIND_BY_USER, query = "SELECT * FROM Comment c "
			+ "INNER JOIN User u ON c.created_by = u.user_id "
			+ "WHERE u.username = ?1"),
	@NamedQuery(name = Comment.FIND_BY_ESTABLISHMENT, query = "SELECT * FROM Comment c "
			+ "INNER JOIN Establishment e ON c.establishment_id = e.establishment_id "
			+ "WHERE e.name = ?1"),
	@NamedQuery(name = Comment.FIND_BY_USER_AND_ESTABLISHMENT, query = "SELECT * FROM Comment c "
			+ "INNER JOIN User u ON c.created_by = u.user_id "
			+ "INNER JOIN Establishment e ON c.establishment_id = e.establishment_id"
			+ "WHERE u.username = ?1") })
public class Comment extends BaseEntity{
	public static final String REMOVE_BY_USER = "removeByUser";
	public static final String REMOVE_BY_ESTABLISHMENT = "removeByEstablishment";
	public static final String REMOVE_BY_USER_AND_ESTABLISHMENT = "removeByUserAndEstablishment";
	public static final String FIND_BY_USER = "findByUser";
	public static final String FIND_BY_ESTABLISHMENT = "findByEstablishment";
	public static final String FIND_BY_USER_AND_ESTABLISHMENT = "findByUserAndEstablishment";
	private int commentAssessment;
	private String userComment;
	private boolean needToModerate;
	private Establishment establishment;
	public Comment() {
		commentAssessment = 0;
		needToModerate = false;
	}
	public void setComment(String comment) {
		this.userComment = comment;
	}
	@Column(name="comment")
	public String getComment() {
		return userComment;
	}
	public void setCommentPositiveAssessment() {
		++commentAssessment;
	}
	public void setCommentNegativeAssessment() {
		--commentAssessment;
	}
	@Column(name="comment_assessment")
	public int getCommentAssessment() {
		return commentAssessment;
	}
	public void setCommentAssessment(int commentAssessment) {
		this.commentAssessment = commentAssessment;
	}
	public void setCommentToModerate(User user) {
		checkUserForComment(user);
		needToModerate = true;
	}
	@Override
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="establishmentId", referencedColumnName="establishment_id")
	public Establishment getEstablishment() {
		return establishment;
	}
	/**
	 * This method add Establishment object to the Comment object. Check is this comment establishment 
	 * is not empty.
	 * @param establishment
	 */
	public void setEstablishment(Establishment establishment) {
		this.establishment = establishment;
	}
	public boolean isNeedToModerate() {
		return needToModerate;
	}
	public void setNeedToModerate(boolean needToModerate) {
		this.needToModerate = needToModerate;
	}
	public boolean checkCommentForModeration() {
		return needToModerate;
	}
	/**
	 * Method that return true if <code>Establishment</code> of this Comment object is equals
	 * to Establishment object that get by argument.
	 * @param establishment
	 */
	public boolean checkEstablishment(Establishment establishment) {
		if(this.establishment == null) {
			return true;
		}
		return this.establishment.equals(establishment);
	}
	public void checkUserForComment(User user) {
		if(!user.inGroup(UserGroup.MODERATOR) && !user.inGroup(UserGroup.ADMINISTRATOR)) {
			throw new RuntimeException("This user can`t mark comment to moderate");
		}
	}
}
