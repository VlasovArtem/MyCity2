package org.hillel.it.mycity.persistence.repository;

import java.util.List;

import org.hillel.it.mycity.model.entity.Assessment;
import org.hillel.it.mycity.model.entity.Establishment;
import org.hillel.it.mycity.model.entity.Person;

public interface AssessmentRepository {
	public void addAssessment(Assessment assessment);
	public void deleteAssessment(int id);
	public void deleteAssessment(Person user);
	public void deleteAssessment(Establishment establishment);
	public Assessment getAssessment(int id);
	public List<Assessment> getAssessments(Person user);
	public List<Assessment> getAssessments(Establishment establishment);
	public List<Assessment> getAssessments();
}
