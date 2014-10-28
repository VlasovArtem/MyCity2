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
	@NamedQuery(name = Restaurant.REMOVE_BY_NAME, query = "DELETE FROM Restaurant r "
			+ "WHERE r.name = ?1"),
	@NamedQuery(name = Restaurant.REMOVE_BY_ADDRESS, query = "DELETE FROM Restaurant r "
			+ "WHERE r.address = ?1"),
	@NamedQuery(name = Restaurant.REMOVE_BY_NAME_AND_ADDRESS, query = "DELETE FROM Restaurant r "
			+ "WHERE r.name = ?1 AND r.address = ?2"),
	@NamedQuery(name = Restaurant.FIND_BY_NAME, query = "SELECT * FROM Restaurant r "
			+ "WHERE r.name = ?1"),
	@NamedQuery(name = Restaurant.FIND_BY_ADDRESS, query = "SELECT * FROM Restaurant r "
			+ "WHERE r.address = ?1"),
	@NamedQuery(name = Restaurant.FIND_BY_NAME_AND_ADDRESS, query = "SELECT * FROM Restaurant r "
			+ "WHERE r.name = ?1 AND r.address = ?2"),
	@NamedQuery(name = Restaurant.FIND_BY_TIME_OPEN, query = "SELECT * FROM Restaurant r "
			+ "WHERE r.time_open = ?1"),
	@NamedQuery(name = Restaurant.FIND_BY_TIME_CLOSE, query = "SELECT * FROM Restaurant r "
			+ "WHERE r.time_close = ?1"),
	@NamedQuery(name = Restaurant.FIND_BY_AVERAGE_CHECK, query = "SELECT * FROM Restaurant r "
			+ "WHERE r.average_check ORDER BY average_check")})
public class Restaurant extends Establishment{
	public static final String REMOVE_BY_NAME = "removeByName";
	public static final String REMOVE_BY_ADDRESS = "removeByAddress";
	public static final String REMOVE_BY_NAME_AND_ADDRESS = "removeByNameAndAddress";
	public static final String FIND_BY_NAME = "findByName";
	public static final String FIND_BY_ADDRESS = "findByAddress";
	public static final String FIND_BY_NAME_AND_ADDRESS = "findByNameAndAddress";
	public static final String FIND_BY_TIME_OPEN = "findByTimeOpen";
	public static final String FIND_BY_TIME_CLOSE = "findByTimeClose";
	public static final String FIND_BY_AVERAGE_CHECK = "findByAverageCheck";
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
