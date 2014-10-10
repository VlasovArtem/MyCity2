package org.hillel.it.mycity.model.entity;

import java.time.Duration;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="restaurant")
@PrimaryKeyJoinColumn(name="restaurant_id")
public class Restaurant extends Establishment{
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
	public void setTimeOpen(int hour, int minute) {	
		timeCheck(hour, minute);
		timeOpen = LocalTime.of(hour, minute);
		if(timeOpen.equals(timeClose)) {
			twentyFourHour = true;
		} else if(Duration.between(timeOpen, timeClose).isNegative()) {
			throw new RuntimeException("TimeOpen cannot be less than TimeClose");
		}
	}
	@Column(name="time_open")
	@Temporal(TemporalType.TIMESTAMP)
	public LocalTime getTimeOpen() {
		return timeOpen;
	}
	public void setTimeClose(int hour, int minute) {
		timeCheck(hour, minute);
		timeClose = LocalTime.of(hour, minute);
		if(timeOpen.equals(timeClose)) {
			twentyFourHour = true;
		} else if(Duration.between(timeOpen, timeClose).isNegative()) {
			throw new RuntimeException("TimeClose cannot be greater than TimeClose");
		}
	}
	@Column(name="time_close")
	@Temporal(TemporalType.TIMESTAMP)
	public LocalTime getTimeClose() {	
		return timeClose;
	}
	private boolean timeCheck(int hour, int minute) {
		if(hour > 23 || hour < 0 || minute > 59 || hour < 0) {
			throw new RuntimeException("Incorrect time. Example 23:59 or 00:01");
		}
		return true;
	}
}
