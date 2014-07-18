package org.hillel.it.mycity.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Establishment extends BaseEntity{
	private int baseEntityId;
	private String name;
	private String address;
	private String phone; //формат (0482) 34-56-15, можно добавить форматирование по
	private String description;
	private List<Comment> commentsOfEstablishment;
	private List<Assessment> assessmentsOfEstablishment;
	
	
	
	public int getBaseEntityId() {
		return baseEntityId;
	}

	public void setBaseEntityId(int baseEntityId) {
		this.baseEntityId = baseEntityId;
	}

	public Establishment() {
		commentsOfEstablishment = new ArrayList<>();
		assessmentsOfEstablishment = new ArrayList<>();
	}
	
	public void setName(String nameOfEstablishment){
		this.name = nameOfEstablishment;
	}

	public String getName() {
		return name;
	}
	
	public void setAddress(String addressOfEstablishment) {
		this.address = addressOfEstablishment;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setPhone(String telephoneOfEstablishment) throws ClassNotFoundException {
		if(!checkTelephoneOfEstablishment(telephoneOfEstablishment)) {
			System.out.println("Incorrect format");
			return;
		}
		this.phone = telephoneOfEstablishment;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setDescription(String descriptionOfEstablishment) {
		this.description = descriptionOfEstablishment;
	}
	
	public String getDescription() {
		return description;
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
	
	/**
	 * Check insert telephone number on regex. Standart format 0939580099. Check Telephones for 
	 * Odessa Region.
	 * @param telephoneOfEstablishment
	 * @return true if telephone number is in standart format
	 */
	private boolean checkTelephoneOfEstablishment(String telephoneOfEstablishment) throws ClassNotFoundException{
		Pattern telephonePattern = Pattern.compile("(^0([6][3678]|[9][1-9]|39|48|50))\\d{7}");
		Matcher telephoneMatcher = telephonePattern.matcher(telephoneOfEstablishment);
		return telephoneMatcher.find();
	}
	
	private void checkAddComment(Comment comment) {
		try {
			comment.checkEstablishment(this);
			comment.checkId(comment.getId());
		} catch (RuntimeException e) {
			System.out.println("Problem with comment: " + e);
			throw new RuntimeException();
		}
	}
	
	private void checkAddAssessment(Assessment assessment) {
		try {
			assessment.checkEstablishment(this);
			assessment.checkId(assessment.getId());
		} catch (RuntimeException e) {
			System.out.println("Problem with Assessment: " + e);
			throw new RuntimeException();
		}
	}
}
