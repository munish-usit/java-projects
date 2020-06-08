package com.geektrust.traffic.models;

import java.io.Serializable;

/**
 * Velocity Bean.
 */
public class Velocity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int speed; // Default unit is megamiles/hour.
	private String  unit;

	public Velocity() {
		// Default constructor
	}
	
	public Velocity(int speed, String unit) {
		super();
		this.speed = speed;
		this.unit = unit;
	}
	
	public int getSpeed() {
		return this.speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	@Override
	public String toString() {
		StringBuilder velocity = new StringBuilder("Velocity");
		velocity.append(": {")
			.append("speed=").append(speed)
			.append(", unit=").append(unit)
			.append("}");
		return velocity.toString();
	}
}
