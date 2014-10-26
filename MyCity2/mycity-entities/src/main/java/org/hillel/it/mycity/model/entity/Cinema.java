package org.hillel.it.mycity.model.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
@Entity
@Table(name="CINEMAS")
@PrimaryKeyJoinColumn(name="establishment_id")
@NamedQueries({@NamedQuery(name="getCinemas", query="from Cinema"), 
	@NamedQuery(name="deleteCinemas", query="delete Cinema"), 
	@NamedQuery(name="deleteCinema", query="delete Cinema where cinema_id = :id")})
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Cinema extends Establishment{
	public static final String GET_CINEMAS = "getCinemas";
	public static final String DELETE_CINEMAS = "deleteCinemas";
	public static final String DELETE_CINEMA = "deleteCinema";
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
