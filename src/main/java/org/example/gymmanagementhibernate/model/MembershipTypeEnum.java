package org.example.gymmanagementhibernate.model;

public enum MembershipTypeEnum {
	BASIC(100), PREMIUM(199), MORNING(120), PERSONAL_TRAINING(499);
	
	private double price;
	
	private MembershipTypeEnum(double price) {
		this.price = price;
	}

}
