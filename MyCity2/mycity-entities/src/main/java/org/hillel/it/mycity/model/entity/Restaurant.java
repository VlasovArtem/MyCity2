package org.hillel.it.mycity.model.entity;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="RESTAURANTS")
@PrimaryKeyJoinColumn(name="establishment_id")
@NamedQueries({@NamedQuery(name="getRestaurants", query="from Restaurant"), 
	@NamedQuery(name="deleteRestaurants", query="delete Restaurant"), 
	@NamedQuery(name="deleteRestaurant", query="delete Restaurant where restaurant_id = :id")})
public class Restaurant extends Establishment{
	public static final String GET_RESTAURANTS = "getRestaurants";
	public static final String DELETE_RESTAURANTS = "deleteRestaurants";
	public static final String DELETE_RESTAURANT = "deleteRestaurant";
	private LocalTime timeOpen;
	private LocalTime timeClose;
	private int averageCheck;
	private boolean twentyFourHour;
	
	public void setAverageCheck(int averageCheck) {
		this.averageCheck = averageCheck;
	}
	@Column(name="average_check")
	public int getAverageCheck() {
		return averageCheck;
	}
	public boolean isTwentyFourHour() {
		return twentyFourHour;
	}
	public void setTwentyFourHour(boolean twentyFourHour) {
		this.twentyFourHour = twentyFourHour;
	}
	public void setTimeOpen(LocalTime timeOpen) {
		this.timeOpen = timeOpen;
	}
	@Column(name="time_open")
	public LocalTime getTimeOpen() {
		return timeOpen;
	}
	public void setTimeClose(LocalTime timeClose) {
		this.timeClose = timeClose;
	}
	@Column(name="time_close")
	public LocalTime getTimeClose() {	
		return timeClose;
	}
}
