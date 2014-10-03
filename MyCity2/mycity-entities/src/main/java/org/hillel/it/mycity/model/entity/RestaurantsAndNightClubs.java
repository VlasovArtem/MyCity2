package org.hillel.it.mycity.model.entity;

import java.time.Duration;
import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class RestaurantsAndNightClubs extends Establishment{
	//protected AdditionalService additionalService; //я думаю, что можно создать класс с дополнительными услугами.
	private LocalTime timeOpen;
	private LocalTime timeClose;
	private int averageCheck;
	private boolean twentyFourHour;
	
	public void setAverageCheck(int averageCheck) {
		this.averageCheck = averageCheck;
	}
	
	public int getAverageCheck() {
		return averageCheck;
	}
	
	public void setTimeOpen(int hour, int minute) {	
		timeCheck(hour, minute);
		timeOpen = LocalTime.of(hour, minute);
		if(timeOpen.equals(timeClose)) {
			twentyFourHour = true;
		} else if(Duration.between(timeOpen, timeClose).isNegative()) {
			throw new RuntimeException("TimeOpen cannot be less than TimeClose");
		}
	}
	
	public LocalTime getTimeOpen() {
		return timeOpen;
	}
	
	public void setTimeClose(int hour, int minute) {
		timeCheck(hour, minute);
		timeClose = LocalTime.of(hour, minute);
		if(timeOpen.equals(timeClose)) {
			twentyFourHour = true;
		} else if(Duration.between(timeOpen, timeClose).isNegative()) {
			throw new RuntimeException("TimeClose cannot be greater than TimeClose");
		}
	}
	
	public LocalTime getTimeClose() {	
		return timeClose;
	}
	private boolean timeCheck(int hour, int minute) {
		if(hour > 23 || hour < 0 || minute > 59 || hour < 0) {
			throw new RuntimeException("Incorrect time. Example 23:59 or 00:01");
		}
		return true;
	}
}
