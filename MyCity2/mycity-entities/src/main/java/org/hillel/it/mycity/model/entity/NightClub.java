package org.hillel.it.mycity.model.entity;

import java.time.Duration;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="nightclub")
@PrimaryKeyJoinColumn(name="nightclub_id")
public class NightClub extends Establishment{
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
	private boolean timeCheck(int hour, int minute) {
		if(hour > 23 || hour < 0 || minute > 59 || hour < 0) {
			throw new RuntimeException("Incorrect time. Example 23:59 or 00:01");
		}
		return true;
	}
	public boolean isTwentyFourHour() {
		return twentyFourHour;
	}
	public void setTwentyFourHour(boolean twentyFourHour) {
		this.twentyFourHour = twentyFourHour;
	}
}
