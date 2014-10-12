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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="COMMENTS")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@AttributeOverride(name="id", column = @Column(name="comment_id", insertable=false, updatable=false))
@NamedQueries({@NamedQuery(name="getComments", query="from comments"), 
	@NamedQuery(name="deleteComments", query="delete comments"), 
	@NamedQuery(name="deleteCommentById", query="delete comments where comment_id = :id"),
	@NamedQuery(name="deleteCommentsByUserId", query="delete comments where person_id = :id"),
	@NamedQuery(name="deleteCommentsByEstablishmentId", query="delete comments where establishment_id = :id"),
	@NamedQuery(name="deleteCommentsByEstablishmentAndUserId", query="delete comments where establishment_id = :establishment_id and person_id = :person_id")})
public class Comment extends BaseEntity{
	public static final String GET_COMMENTSS = "getComments";
	public static final String DELETE_COMMENTS = "deleteComments";
	public static final String DELETE_COMMENT_BY_ID = "deleteCommentById";
	public static final String DELETE_COMMENT_BY_USER_ID = "deleteCommentsByUserId";
	public static final String DELETE_COMMENT_BY_ESTABLISHMENT_ID = "deleteCommentsByEstablishmentId";
	public static final String DELETE_COMMENT_BY_ESTABLISHMENT_AND_USER_ID = "deleteCommentsByEstablishmentAndUserId";
	private int commentAssessment;
	private String comment;
	private boolean needToModerate;
	private Establishment establishment;
	
	public Comment() {
		commentAssessment = 0;
		needToModerate = false;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Column(name="comment")
	public String getComment() {
		return comment;
	}
	//наверно нужно добавить проверка, на количество вызовов метода одним пользователем. 
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
	@ManyToOne(fetch=FetchType.EAGER, optional=false, cascade={})
	@JoinColumn(name="establishment_id")
	public Establishment getEstablishment() {
		return establishment;
	}
	/**
	 * This method add Establishment object to the Comment object. Check is this comment establishment 
	 * is not empty.
	 * @param establishment
	 */
	public void setEstablishment(Establishment establishment) {
		checkDataIsNotNull(this.establishment, "You can not add additional Establishment to this Comment");
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
		if(!user.inGroup(UserGroup.Moderator) && !user.inGroup(UserGroup.Administrator)) {
			throw new RuntimeException("This user can`t mark comment to moderate");
		}
	}
}
