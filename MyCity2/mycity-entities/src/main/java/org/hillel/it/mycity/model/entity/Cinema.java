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
@NamedQueries({
	@NamedQuery(name = Cinema.REMOVE_BY_NAME, query = "DELETE FROM Cinema c "
			+ "WHERE c.name = ?1"),
	@NamedQuery(name = Cinema.REMOVE_BY_ADDRESS, query = "DELETE FROM Cinema c "
			+ "WHERE c.address = ?1"),
	@NamedQuery(name = Cinema.REMOVE_BY_NAME_AND_ADDRESS, query = "DELETE FROM Cinema c "
			+ "WHERE c.name = ?1 AND c.address = ?2"),
	@NamedQuery(name = Cinema.FIND_BY_NAME, query = "SELECT * FROM Cinema c "
			+ "WHERE c.name = ?1"),
	@NamedQuery(name = Cinema.FIND_BY_ADDRESS, query = "SELECT * FROM Cinema c "
			+ "WHERE c.address = ?1"),
	@NamedQuery(name = Cinema.FIND_BY_NAME_AND_ADDRESS, query = "SELECT * FROM Cinema c "
			+ "WHERE c.name = ?1 AND c.address = ?2"),
	@NamedQuery(name = Cinema.FIND_BY_CINEMATECHNOLOGY, query = "SELECT * FROM Cinema c "
			+ "c.cinema_technology = ?1")})
public class Cinema extends Establishment{
	public static final String REMOVE_BY_NAME = "removeByName";
	public static final String REMOVE_BY_ADDRESS = "removeByAddress";
	public static final String REMOVE_BY_NAME_AND_ADDRESS = "removeByNameAndAddress";
	public static final String FIND_BY_NAME = "findByName";
	public static final String FIND_BY_ADDRESS = "findByAddress";
	public static final String FIND_BY_NAME_AND_ADDRESS = "findByNameAndAddress";
	public static final String FIND_BY_CINEMATECHNOLOGY = "findByCinemaTechnology";
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
