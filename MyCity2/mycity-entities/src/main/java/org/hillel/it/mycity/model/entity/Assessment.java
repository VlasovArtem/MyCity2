package org.hillel.it.mycity.model.entity;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
@Entity
@Table(name="ASSESSMENTS")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@AttributeOverride(name="id", column = @Column(name="assessment_id", insertable=false, updatable=false))
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@NamedQueries({@NamedQuery(name="Assessment.removeByUser", 
		query="DELETE FROM Assessment a "
				+ "WHERE a.createdBy in "
				+ "(FROM User u WHERE u.username = ?1)"),
	@NamedQuery(name="Assessment.removeByEstablishment", 
		query="DELETE FROM Assessment a "
				+ "WHERE a.establishment in "
				+ "(FROM Establishment e WHERE e.name = ?1)"),
	@NamedQuery(name="Assessment.findByUser", 
		query="SELECT a FROM Assessment a "
				+ "JOIN a.createdBy cb "
				+ "WHERE cb.username = ?1"),
	@NamedQuery(name="Assessment.findByEstablishment", 
		query="SELECT a FROM Assessment a "
				+ "JOIN a.establishment e "
				+ "WHERE e.name = ?1")})
public class Assessment extends BaseEntity{
	private int establishmentAssessment;
	private Establishment establishment;
	public void setAssessment(int assessment) {
		this.establishmentAssessment = assessment;
	}
	@Column(name="establishmentAssessment")
	public int getAssessment() {
		return establishmentAssessment;
	}
	public void setEstablishment(Establishment establishment) {
		this.establishment = establishment;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="establishment_id", referencedColumnName="establishment_id")
	public Establishment getEstablishment() {
		return establishment;
	}
	@Override
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public boolean checkEstablishment(Establishment establishment) {
		if(this.establishment == null) {
			return true;
		} else if(!this.establishment.equals(establishment)) {
			System.out.println("You can not add additional Establishment to this Assessment");
			return false;
		}
		return true;
	}
	
}
