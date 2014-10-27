package org.hillel.it.mycity.model.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
@Entity
@Table(name="CINEMAS")
@PrimaryKeyJoinColumn(name="establishment_id")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
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
