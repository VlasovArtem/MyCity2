package org.hillel.it.mycity.model.entity;
 
public class Administrator extends Person {
	
	public Administrator(String email, String password) {
		super(email, password);
	}
	
	public Cinema createEstablishmentCinema() {
		Cinema cinema = new Cinema();
		cinema.setCreatedBy(this);
		return cinema;
	}
	
	public Restaurant createEstablishmentRestaurant() {
		Restaurant restaurant = new Restaurant();
		restaurant.setCreatedBy(this);
		return restaurant;
	}
	
	public NightClub createEstablishmentNightClub() {
		NightClub nightClub = new NightClub();
		nightClub.setCreatedBy(this);
		return nightClub;
	}
	
	public void deleteEstablishment(int id) {
		
	}
	
	public void deleteComment(int id) {
	
	}
	
	public void editComment(String editComment, int id) {
		
	}

	@Override
	public String toString() {
		return "Administrator [firstName=" + getFirstName() + ", lastName="
				+ getLastName() + ", username=" + getUsername() + ", password="
				+ getPassword() + "]";
	}
	
	
}
