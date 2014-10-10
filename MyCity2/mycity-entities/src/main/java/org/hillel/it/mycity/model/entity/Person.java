package org.hillel.it.mycity.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.validator.routines.EmailValidator;
import org.hillel.it.mycity.helper.CryptoHelper;

@Entity
@Table(name="person")
@AttributeOverride(name="id", column=@Column(name="person_id", insertable=false, updatable=false))
public class Person extends BaseEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String username;
	private String email;
	private String password;
	private Group group;
	private boolean emailVerified; //прошел ли email проверку на подлинность, можно использовать
	//при вызове методов добавления и удаление файлов.
	//private boolean deleted - есть пользователь удалил аккаунт, сообщения остаются, если их не удаляют
	//в ручную, но удаляются оценки.
	
	public Person(String email, String password) {
		setEmail(email);
		setPassword(password);
		emailVerified = false;
		setUsername("user" + (getId()+256));
	}
	public Person() {
	
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@Column(name="firstname")
	public String getFirstName() {
		return firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Column(name="lastname")
	public String getLastName() {
		return lastName;
	}
	public void setUsername(String username) {
		if(!checkUserName(username)) {
			return;
		}
		this.username = username;
	}
	@Column(name="username")
	public String getUsername() {
		return username;
	}
	public void setEmail(String email) {
		checkEmail(email);
		this.email = email;
	}
	@Column(name="email", unique=true, nullable=false)
	public String getEmail() {
		return email;
	}
	public void setPassword(String password) {
		this.password = CryptoHelper.shaOne(password);
	}
	@Column(name="password", nullable=false)
	public String getPassword() {
		return password;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	@Column(name="group")
	public Group getGroup() {
		return group;
	}
	@Column(name="verified")
	public boolean isEmailVerified() {
		return emailVerified;
	}
	public void setEmailVerified(boolean emailVerified) {
		this.emailVerified = emailVerified;
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
	 * This method create Comment.
	 * @param userComment String type comment that received from user
	 * @return null if User that create comment is do not exist in Repository or return Comment.
	 */
	public Comment createComment(String userComment) {
		checkId(getId());
		Comment comment = new Comment();
		comment.setCreatedBy(this);
		comment.setComment(userComment);
		return comment;
	}
	/**
	 * 
	 * @param comment
	 * @param userComment
	 * @return
	 */
	public void changeComment(Comment comment, String userComment) {
		checkUserData(comment);
		comment.setComment(userComment);
		comment.setModifiedBy(this);
		comment.setModifiedDate(new Date());
	}
	public Assessment createAssessment(int userAssessment) {
		checkId(getId());
		Assessment assessment = new Assessment();
		assessment.setCreatedBy(this);
		assessment.setAssessment(userAssessment);
		return assessment;
	}
	public void changeAssessment(Assessment assessment, int userAssessment) {
		checkUserData(assessment);
		assessment.setAssessment(userAssessment);
		assessment.setModifiedBy(this);
		assessment.setModifiedDate(new Date());
	}
	/**
	 * Method that return group of class Person (Administrator, Moderator, User)
	 * @param group It is eNum of groups
	 * @return true if group of the person equals to group in argument otherwise return false  
	 */
	public boolean inGroup(Group group){
		return group == this.group;
	}
	/**
	 * Method <code>checkUserName</code> return true if <code>userName</code> is pass all checks. False if
	 * <code>userName</code> length less than 4 symbols or <code>userName</code> is Empty (== null)
	 * or if <code>userName</code> starts with space symbol.
	 * @param userName
	 * @return
	 */
	private boolean checkUserName(String username) {
		if(username.length() <= 3 || username.isEmpty()) {
			System.out.println("Username cat not be less than 3 symbols");
			return false;
		}
		Pattern pattern = Pattern.compile("^\\S\\w");
		Matcher matcher = pattern.matcher(username);
		if(!matcher.find()) {
			System.out.println("Username can not contains space symbol as a first character.");
			return false;
		}
		return true;
		
	}
	/**
	 * Check user data for change different type of parameter. For class Comment this check
	 * pass successful if creater of this Comment is this user or user with aministrator rights
	 * For class Assessment check pass successful if creater of this Assessment is this user.
	 * @param t one of classes Comment or Assessment
	 */
	public <T extends BaseEntity> void checkUserData(T t) {
		if(t.getClass() == Comment.class) {
			if(t.getCreatedBy() != this && !inGroup(Group.Administrator)) {
				throw new RuntimeException("This user cannot change this comment");
			}
		} else if(t.getClass() == Assessment.class) {
			if(t.getCreatedBy() != this) {
				throw new RuntimeException("This user can not change this comment");
			}
		}
	}
	/**
	 * Check email on validation with help of Apache Commons Validator - EmailValidator.
	 * @param email 
	 */
	public void checkEmail(String email) {
		if(!EmailValidator.getInstance().isValid(email)) {
			throw new RuntimeException("Incorrect email");
		}
	}
}
