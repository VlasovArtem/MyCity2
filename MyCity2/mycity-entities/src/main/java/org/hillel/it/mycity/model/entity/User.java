package org.hillel.it.mycity.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="PERSON")
public class User extends Person{
	
	public User() {
		
	}
	public User(String email, String password) {
		super(email, password);
	}
}
