package org.hillel.it.mycity.model.entity;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
@Entity
@Table(name="RESTAURANTS")
@PrimaryKeyJoinColumn(name="establishment_id")
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
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
