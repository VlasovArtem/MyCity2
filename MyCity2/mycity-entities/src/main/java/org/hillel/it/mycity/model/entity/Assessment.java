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
@NamedQueries({@NamedQuery(name=Assessment.REMOVE_BY_USER, 
		query="DELETE FROM Assessment a "
				+ "INNER JOIN User u ON a.created_by = u.user_id "
				+ "WHERE u.username = ?1"),
	@NamedQuery(name=Assessment.REMOVE_BY_ESTABLISHMENT, 
		query="DELETE FROM Assessment a "
				+ "INNER JOIN Establishment e ON a.establishment_id = e.establishment_id "
				+ "WHERE e.name = ?1"),
	@NamedQuery(name=Assessment.FIND_BY_USER, 
		query="SELECT * FROM Assessment a "
				+ "INNER JOIN User u ON a.created_by = u.user_id "
				+ "WHERE u.username = ?1"),
	@NamedQuery(name=Assessment.FIND_BY_ESTABLISHMENT, 
		query="SELECT * FROM Assessment a "
				+ "INNER JOIN Establishment e ON a.establishment_id = e.establishment_id "
				+ "WHERE e.name = ?1")})
public class Assessment extends BaseEntity{
	public static final String REMOVE_BY_USER = "removeByUser";
	public static final String REMOVE_BY_ESTABLISHMENT = "removeByEstablishment";
	public static final String FIND_BY_USER = "findByUser";
	public static final String FIND_BY_ESTABLISHMENT = "findByEstablishment";
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
