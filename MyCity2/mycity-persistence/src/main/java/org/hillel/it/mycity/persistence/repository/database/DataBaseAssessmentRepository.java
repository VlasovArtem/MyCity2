package org.hillel.it.mycity.persistence.repository.database;

import java.util.List;

import org.hillel.it.mycity.model.entity.Assessment;
import org.hillel.it.mycity.model.entity.Establishment;
import org.hillel.it.mycity.model.entity.Person;
import org.hillel.it.mycity.persistence.repository.AssessmentRepository;
import org.springframework.stereotype.Repository;

@Repository
public class DataBaseAssessmentRepository implements AssessmentRepository{

	@Override
	public void addAssessment(Assessment assessment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAssessment(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAssessment(Person user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAssessment(Establishment establishment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Assessment getAssessment(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Assessment> getAssessments(Person user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Assessment> getAssessments(Establishment establishment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Assessment> getAssessments() {
		// TODO Auto-generated method stub
		return null;
	}

}
