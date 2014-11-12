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
@Entity
@Table(name="CINEMAS")
@PrimaryKeyJoinColumn(name="establishment_id")
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@NamedQueries({
		@NamedQuery(name = "Cinema.removeByName", query = "DELETE FROM Cinema c "
				+ "WHERE c.name = ?1"),
		@NamedQuery(name = "Cinema.removeByAddress", query = "DELETE FROM Cinema c "
				+ "WHERE c.address = ?1"),
		@NamedQuery(name = "Cinema.removeByNameAndAddress", query = "DELETE FROM Cinema c "
				+ "WHERE c.name = ?1 AND c.address = ?2"),
		@NamedQuery(name = "Cinema.findByName", query = "FROM Cinema c "
				+ "WHERE c.name = ?1"),
		@NamedQuery(name = "Cinema.findByAddress", query = "FROM Cinema c "
				+ "WHERE c.address = ?1"),
		@NamedQuery(name = "Cinema.findByNameAndAddress", query = "FROM Cinema c "
				+ "WHERE c.name = ?1 AND c.address = ?2"),
		@NamedQuery(name = "Cinema.findByCinemaTechnology", query = "FROM Cinema c "
				+ "WHERE c.cinemaTechnology = ?1") })
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
