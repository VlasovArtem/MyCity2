package org.hillel.it.mycity.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="cinema")
@PrimaryKeyJoinColumn(name="cinema_id")
public class Cinema extends Establishment{
	private int numberOfHalls;
	private int numberOfSeatsInHall;
	private String cinemaTechnology;
	public Cinema() {
		super();
	}
	public void setNumberOfHalls(int numberOfHalls) {
		this.numberOfHalls = numberOfHalls;
	}
	@Column(name="number_of_halls")
	public int getNumberOfHalls() {
		return numberOfHalls;
	}
	public void setNumberOfSeatsInHall(int numberOfSeatsInHall) {
		this.numberOfSeatsInHall = numberOfSeatsInHall;
	}
	@Column(name="number_of_seats_in_hall")
	public int getNumberOfSeatsInHall() {
		return numberOfSeatsInHall;
	}
	public void setCinemaTechnology(String cinemaTechnology) {
		this.cinemaTechnology = cinemaTechnology;
	}
	@Column(name="cinema_technology")
	public String getCinemaTechnology() {	
		return cinemaTechnology;
	}
}
