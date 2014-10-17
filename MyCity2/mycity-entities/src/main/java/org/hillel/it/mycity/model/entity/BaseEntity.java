package org.hillel.it.mycity.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@MappedSuperclass
public abstract class BaseEntity{
	protected int id;
	private Date createdDate;
	private Date modifiedDate;
	private User createdBy;
	private User modifiedBy;

	public BaseEntity(){
	}
	
	public void setCreatedDate(Date createdDate){
		this.createdDate = createdDate;
	}
	@Column(name="created_date", nullable=false)
	public Date getCreatedDate(){
		return createdDate;
	}
	
	public void setModifiedDate(Date modifiedDate){
		this.modifiedDate = modifiedDate;
	}
	@Column(name="modified_date")
	public Date getModifiedDate(){
		return modifiedDate;
	}
	
	/**
	 * Add createdBy person to the object. Check if this object field createdBy is no empty
	 * @param createdBy
	 */
	public void setCreatedBy(User createdBy){
		//checkDataIsNotNull(this.createdBy, "CreatedBy user is alredy exist. You can not add another one.");
		this.createdBy = createdBy;
	}
	@OneToOne
	@JoinColumn(name="created_by", referencedColumnName="user_id")
	public User getCreatedBy(){
		return createdBy;
	}
	
	public void setModifiedBy(User modifiedBy){
		//checkUser(modifiedBy);
		this.modifiedBy = modifiedBy;
	}
	@OneToOne
	@JoinColumn(name="modified_by", referencedColumnName="user_id")
	public User getModifiedBy(){
		return modifiedBy;
	}
	
	public void setId(int id) {
		this.id = (this.id == 0 ? id : this.id);
	}
	public int getId() {
		return id;
	}
	
	//TODO нужно добавить проверка на maxId из нужного Repository
	public void checkId(int id) {
		if(id < 1) {
			throw new RuntimeException("Incorrect id");
		}
	}
	
	public <T>void checkDataIsNotNull(T t, String exceptionText){
		if(t != null) {
			throw new RuntimeException(exceptionText);
		}
	}
	
	public void checkUser(User modifiedBy) {
		if(modifiedBy != createdBy && !modifiedBy.inGroup(UserGroup.Administrator)) {
			throw new RuntimeException("This person can not modify information");
		}
	}
}
