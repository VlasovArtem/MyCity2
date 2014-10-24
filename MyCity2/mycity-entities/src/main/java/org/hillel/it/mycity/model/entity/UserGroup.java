package org.hillel.it.mycity.model.entity;

public enum UserGroup {
	ADMINISTRATOR("Administrator"), 
	MODERATOR("Moderator"), 
	USER("User");
	private final String group;
	private UserGroup(String group) {
		this.group = group;
	}
	public boolean equalsGroup(String group) {
		return (group == null) ? false : group.equals(group);
	}
	public String toString() {
		return group;
	}
}
