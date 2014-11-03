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
@NamedQueries({
	@NamedQuery(name = "Restaurant.removeByName", query = "DELETE FROM Restaurant r "
			+ "WHERE r.name = ?1"),
	@NamedQuery(name = "Restaurant.removeByAddress", query = "DELETE FROM Restaurant r "
			+ "WHERE r.address = ?1"),
	@NamedQuery(name = "Restaurant.removeByNameAndAddress", query = "DELETE FROM Restaurant r "
			+ "WHERE r.name = ?1 AND r.address = ?2"),
	@NamedQuery(name = "Restaurant.findByName", query = "FROM Restaurant r "
			+ "WHERE r.name = ?1"),
	@NamedQuery(name = "Restaurant.findByAddress", query = "FROM Restaurant r "
			+ "WHERE r.address = ?1"),
	@NamedQuery(name = "Restaurant.findByNameAndAddress", query = "FROM Restaurant r "
			+ "WHERE r.name = ?1 AND r.address = ?2"),
	@NamedQuery(name = "Restaurant.findByTimeOpen", query = "FROM Restaurant r "
			+ "WHERE r.timeOpen = ?1"),
	@NamedQuery(name = "Restaurant.findByTimeClose", query = "FROM Restaurant r "
			+ "WHERE r.timeClose = ?1"),
	@NamedQuery(name = "Restaurant.findByAverageCheck", query = "FROM Restaurant r "
			+ "WHERE r.averageCheck = ?1 ORDER BY r.averageCheck ASC")})
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
