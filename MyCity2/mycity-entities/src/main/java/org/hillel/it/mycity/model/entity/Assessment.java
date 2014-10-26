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
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@AttributeOverride(name="id", column = @Column(name="assessment_id", insertable=false, updatable=false))
@NamedQueries({@NamedQuery(name="getAssessments", query="from Assessment"),
	@NamedQuery(name="getAssessmentsByUserId", query="from Assessment"),
	@NamedQuery(name="getAssessmentsByEstablishmentId", query="from Assessment"),
	@NamedQuery(name="deleteAssessments", query="delete Assessment"), 
	@NamedQuery(name="deleteAssessment", query="delete Assessment where assessment_id = :id"),
	@NamedQuery(name="deleteAssessmentByUserId", query="delete Assessment where user_id = :id"),
	@NamedQuery(name="deleteAssessmentByEstablishmentId", query="delete Assessment where establishment_id = :id")})
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Assessment extends BaseEntity{
	public static final String GET_ASSESSMENTS = "getAssessments";
	public static final String GET_ASSESSMENTS_BY_USER_ID = "getAssessmentsByUserId";
	public static final String GET_ASSESSMENTS_BY_ESTABLISHMENT_ID = "getAssessmentsByEstablishmentId";
	public static final String DELETE_ASSESSMENTS = "deleteAssessments";
	public static final String DELETE_ASSESSMENT = "deleteAssessment";
	public static final String DELETE_ASSESSMENT_BY_USER_ID = "deleteAssessmentByUserId";
	public static final String DELETE_ASSESSMENT_BY_ESTABLISHMENT_ID = "deleteAssessmentByEstablishmentId";
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
	@JoinColumn(name="establishmentId", referencedColumnName="establishment_id")
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
