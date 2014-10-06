package org.hillel.it.mycity.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Assessment")
public class Assessment extends BaseEntity{
	@Column(name="assessment")
	private int assessment;
	private Establishment establishment;
	
	public void setAssessment(int assessment) {
		this.assessment = assessment;
	}
	
	public int getAssessment() {
		return assessment;
	}
	
	public void setEstablishment(Establishment establishment) {
		checkDataIsNotNull(this.establishment, "You cannot add another Establishment");
		this.establishment = establishment;
	}
	
	public boolean checkEstablishment(Establishment establishment) {
		if(!this.establishment.equals(establishment)) {
			System.out.println("You can not add additional Establishment to this Assessment");
			return false;
		}
		return true;
	}
}
