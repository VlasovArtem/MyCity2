package org.hillel.it.mycity.persistence.repository;

import java.util.List;

import org.hillel.it.mycity.model.entity.Assessment;
import org.hillel.it.mycity.model.entity.Establishment;
import org.hillel.it.mycity.model.entity.User;

public interface AssessmentRepository {
	public void addAssessment(Assessment assessment);
	public void deleteAssessment(int id);
	public void deleteAssessment(User user);
	public void deleteAssessment(Establishment establishment);
	public Assessment getAssessment(int id);
	public List<Assessment> getAssessments(User user);
	public List<Assessment> getAssessments(Establishment establishment);
	public List<Assessment> getAssessments();
	public void updateAssessment(Assessment assessment);
}
