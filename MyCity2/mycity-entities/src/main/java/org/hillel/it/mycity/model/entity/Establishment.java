package org.hillel.it.mycity.model.entity;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="ESTABLISHMENTS")
@Inheritance(strategy=InheritanceType.JOINED)
@AttributeOverride(name="id", column = @Column(name="establishment_id", insertable=false, updatable=false))
public class Establishment extends BaseEntity{
	private String name;
	private String address;
	private String telephone;
	private String description;
	private List<Comment> commentsOfEstablishment;
	private List<Assessment> assessmentsOfEstablishment;
	public Establishment() {
		commentsOfEstablishment = new ArrayList<Comment>();
		assessmentsOfEstablishment = new ArrayList<Assessment>();
	}
	public void setName(String name){
		this.name = name;
	}
	@Column(name="name", nullable=false, length = 100)
	public String getName() {
		return name;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name = "address", nullable = false, length = 150)
	public String getAddress() {
		return address;
	}
	public void setTelephone(String telephone) throws ClassNotFoundException {
		checkTelephoneOfEstablishment(telephone);
		this.telephone = telephone;
	}
	@Column(name="telephone", nullable=false, unique=true, length = 13)
	public String getTelephone() {
		return telephone;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="description",columnDefinition="BLOB(500) DEFAULT NULL")
	public String getDescription() {
		return description;
	}
	public void setCommentsOfEstablishment(List<Comment> commentsOfEstablishment) {
		this.commentsOfEstablishment = commentsOfEstablishment;
	}
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="establishment", orphanRemoval=true)
	public List<Comment> getCommentsOfEstablishment() {
		return commentsOfEstablishment;
	}
	public void setAssessmentsOfEstablishment(
			List<Assessment> assessmentsOfEstablishment) {
		this.assessmentsOfEstablishment = assessmentsOfEstablishment;
	}
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="establishment", orphanRemoval=true)
	public List<Assessment> getAssessmentsOfEstablishment() {
		return assessmentsOfEstablishment;
	}
	
	/**
	 * Method that set comment for Establishment. checkEstablishment - check if this comment is belongs 
	 * to this Establishment (if it was changed). checkId - check if this comment is created in Repository
	 * or have correct id;
	 * @param comment
	 */
	public void setComment(Comment comment) {
		checkAddComment(comment);
		comment.setEstablishment(this);
		commentsOfEstablishment.add(comment);
	}
	public void setAssessment(Assessment assessment) {
		checkAddAssessment(assessment);
		assessment.setEstablishment(this);
		assessmentsOfEstablishment.add(assessment);
	}
	@Override
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	@Override
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * Check insert telephone number on regex. Standart format 0939580099. Check Telephones for 
	 * Odessa Region.
	 * @param telephoneOfEstablishment
	 * @return true if telephone number is in standart format
	 */
	private boolean checkTelephoneOfEstablishment(String telephone) throws ClassNotFoundException{
		Pattern telephonePattern = Pattern.compile("(^0([6][3678]|[9][1-9]|39|48|50))\\d{7}");
		Matcher telephoneMatcher = telephonePattern.matcher(telephone);
		return telephoneMatcher.find();
	}
	protected void checkAddComment(Comment comment) {
		try {
			comment.checkEstablishment(this);
			comment.checkId(comment.getId());
		} catch (RuntimeException e) {
			System.out.println("Problem with comment: " + e);
			throw new RuntimeException();
		}
	}
	protected void checkAddAssessment(Assessment assessment) {
		try {
			assessment.checkEstablishment(this);
			assessment.checkId(assessment.getId());
		} catch (RuntimeException e) {
			System.out.println("Problem with Assessment: " + e);
			throw new RuntimeException();
		}
	}
}
