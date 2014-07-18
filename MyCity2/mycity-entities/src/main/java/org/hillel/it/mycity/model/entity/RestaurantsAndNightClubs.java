package org.hillel.it.mycity.model.entity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class RestaurantsAndNightClubs extends Establishment{
	//protected AdditionalService additionalService; //я думаю, что можно создать класс с дополнительными услугами.
	private String timeOpen;
	private String timeClose;
	private int averageCheck;
	
	public void setAverageCheck(int averageCheck) {
		this.averageCheck = averageCheck;
	}
	
	public int getAverageCheck() {
		return averageCheck;
	}
	
	public void setTimeOpen(String timeOpen) {	
		Pattern pattern = Pattern.compile("\\d{2}:\\d{2}");
		Matcher matcher = pattern.matcher(timeOpen);
		if(matcher.matches()) {
			this.timeOpen = timeOpen;
		} else {
			System.out.println("Incorrect time format (Example: 22:00)");
		}
	}
	
	public String getTimeOpen() {
		return timeOpen;
	}
	
	public void setTimeClose(String timeClose) {
		Pattern pattern = Pattern.compile("\\d{2}:\\d{2}");
		Matcher matcher = pattern.matcher(timeClose);
		if(matcher.matches()) {
			this.timeClose = timeClose;
		} else {
			System.out.println("Incorrect time format (Example: 22:00)");
		}
	}
	
	public String getTimeClose() {	
		return timeClose;
	}
	
}
