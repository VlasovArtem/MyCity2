package org.hillel.it.mycity.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="CINEMA")
public class Cinema extends Establishment{
	@Column(name="number_of_halls")
	private int numberOfHalls;
	@Column(name="number_of_seats_in_hall")
	private int numberOfSeatsInHall;
	@Column(name="cinema_technology")
	private String cinemaTechnology;
	
	public Cinema() {
		super();
	}
	
	public void setNumberOfHalls(int numberOfHalls) {
		this.numberOfHalls = numberOfHalls;
	}
	
	public int getNumberOfHalls() {
		return numberOfHalls;
	}
	
	public void setNumberOfSeatsInHall(int numberOfSeatsInHall) {
		this.numberOfSeatsInHall = numberOfSeatsInHall;
	}
	
	public int getNumberOfSeatsInHall() {
		return numberOfSeatsInHall;
	}
	
	public void setCinemaTechnology(String cinemaTechnology) {
		this.cinemaTechnology = cinemaTechnology;
	}
	
	public String getCinemaTechnology() {	
		return cinemaTechnology;
	}
	
}
