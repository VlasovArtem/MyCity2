package org.hillel.it.mycity.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="assessment")
public class Assessment extends BaseEntity{
	private int assessment;
	private Establishment establishment;
	public void setAssessment(int assessment) {
		this.assessment = assessment;
	}
	@Column(name="assessment")
	public int getAssessment() {
		return assessment;
	}
	public void setEstablishment(Establishment establishment) {
		checkDataIsNotNull(this.establishment, "You cannot add another Establishment");
		this.establishment = establishment;
	}
	@ManyToOne(fetch=FetchType.EAGER, optional=false, cascade={})
	@JoinColumn(name="establishment_id")
	public Establishment getEstablishment() {
		return establishment;
	}
	@Override
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="assessment_id")
	public int getId() {
		return id;
	}
	public boolean checkEstablishment(Establishment establishment) {
		if(this.establishment == null) {
			return true;
		}
		else if(!this.establishment.equals(establishment)) {
			System.out.println("You can not add additional Establishment to this Assessment");
			return false;
		}
		return true;
	}
	
}
