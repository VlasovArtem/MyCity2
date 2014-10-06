package org.hillel.it.mycity.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="PERSON")
public class Moderator extends Person{
	
	public Moderator(String email, String password) {
		super(email, password);
	}
	
}
