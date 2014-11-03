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
@Table(name="NIGHTCLUBS")
@PrimaryKeyJoinColumn(name="establishment_id")
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@NamedQueries({
	@NamedQuery(name = "NightClub.removeByName", query = "DELETE FROM NightClub n "
			+ "WHERE n.name = ?1"),
	@NamedQuery(name = "NightClub.removeByAddress", query = "DELETE FROM NightClub n "
			+ "WHERE n.address = ?1"),
	@NamedQuery(name = "NightClub.removeByNameAndAddress", query = "DELETE FROM NightClub n "
			+ "WHERE n.name = ?1 AND n.address = ?2"),
	@NamedQuery(name = "NightClub.findByName", query = "FROM NightClub n "
			+ "WHERE n.name = ?1"),
	@NamedQuery(name = "NightClub.findByAddress", query = "FROM NightClub n "
			+ "WHERE n.address = ?1"),
	@NamedQuery(name = "NightClub.findByNameAndAddress", query = "FROM NightClub n "
			+ "WHERE n.name = ?1 AND n.address = ?2"),
	@NamedQuery(name = "NightClub.findByTimeOpen", query = "FROM NightClub n "
			+ "WHERE n.timeOpen = ?1"),
	@NamedQuery(name = "NightClub.findByTimeClose", query = "FROM NightClub n "
			+ "WHERE n.timeClose = ?1"),
	@NamedQuery(name = "NightClub.findByAverageCheck", query = "FROM NightClub n "
			+ "WHERE n.averageCheck = ?1 ORDER BY n.averageCheck ASC")})
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
	public boolean isTwentyFourHour() {
		return twentyFourHour;
	}
	public void setTwentyFourHour(boolean twentyFourHour) {
		this.twentyFourHour = twentyFourHour;
	}
}
