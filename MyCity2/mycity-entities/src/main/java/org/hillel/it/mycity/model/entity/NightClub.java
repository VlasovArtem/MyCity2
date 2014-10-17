package org.hillel.it.mycity.model.entity;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="NIGHTCLUBS")
@PrimaryKeyJoinColumn(name="establishment_id")
@NamedQueries({@NamedQuery(name="getNightClubs", query="from NightClub"), 
	@NamedQuery(name="deleteNightClubs", query="delete NightClub"), 
	@NamedQuery(name="deleteNightClub", query="delete NightClub where nightclub_id = :id")})
public class NightClub extends Establishment{
	public static final String GET_NIGTHCLUBS = "getNightClubs";
	public static final String DELETE_NIGHTCLUBS = "deleteNightClubs";
	public static final String DELETE_NIGHTCLUB = "deleteNightClub";
	private LocalTime timeOpen;
	private LocalTime timeClose;
	private int averageCheck;
	private boolean twentyFourHour;
	public NightClub() {
		super();
	}
 	public void setAverageCheck(int averageCheck) {
		this.averageCheck = averageCheck;
	}
	@Column(name="average_check")
	public int getAverageCheck() {
		return averageCheck;
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
	public boolean isTwentyFourHour() {
		return twentyFourHour;
	}
	public void setTwentyFourHour(boolean twentyFourHour) {
		this.twentyFourHour = twentyFourHour;
	}
}
