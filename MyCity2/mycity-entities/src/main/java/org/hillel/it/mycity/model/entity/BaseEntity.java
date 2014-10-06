package org.hillel.it.mycity.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity implements Serializable{
	@Id
	@GeneratedValue
	private int id;
	@Column(name="created_date")
	private Date createdDate;
	@Column(name="modified_date")
	private Date modifiedDate;
	@Column(name="created_by")
	private Person createdBy;
	@Column(name="modified_by")
	private Person modifiedBy;

	public BaseEntity(){
		setCreateDate(new Date());	
	}
	
	public void setCreateDate(Date createdDate){
		this.createdDate = createdDate;
	}
	
	public Date getCreatedDate(){
		return createdDate;
	}
	
	public void setModifiedDate(Date modifiedDate){
		this.modifiedDate = modifiedDate;
	}
	
	public Date getModifiedDate(){
		return modifiedDate;
	}
	
	/**
	 * Add createdBy person to the object. Check if this object field createdBy is no empty
	 * @param createdBy
	 */
	public void setCreatedBy(Person createdBy){
		checkDataIsNotNull(this.createdBy, "CreatedBy user is alredy exist. You can not add another one.");
		this.createdBy = createdBy;
	}
	
	public Person getCreatedBy(){
		return createdBy;
	}
	
	public void setModifiedBy(Person modifiedBy){
		checkUser(modifiedBy);
		this.modifiedBy = modifiedBy;
	}
	
	public Person getModifiedBy(){
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
	
	public void checkUser(Person modifiedBy) {
		if(modifiedBy != createdBy && !modifiedBy.inGroup(Group.Administrator)) {
			throw new RuntimeException("This person can not modify information");
		}
	}
}
