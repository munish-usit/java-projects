package com.geektrust.traffic.models;

import java.io.Serializable;
import java.util.List;

/**
 * TraverseDetail Bean. 
 * 
 * Here list of orbits that contains sequence of routes/orbits between source and final destinations, 
 * to visit via other destinations/suburbs.
 * 
 * Note: When there is single destination, each orbit-sequence contains only one orbit, i.e. orbit between any source and destination.
 */

public class TraverseDetail implements Serializable,Comparable<TraverseDetail> {
	
	private static final long serialVersionUID = 1L;

	// Contains traverse time for all the orbits. Default unit is minutes.
	private int traverseTime; 
	private List<Orbit> orbits; 
	private Vehicle vehicle;
	
	public TraverseDetail() {
		// Default constructor
	}
	
	public TraverseDetail(int traverseTime, List<Orbit> orbits, Vehicle vehicle) {
		super();
		this.traverseTime = traverseTime; // Default unit is minutes.
		this.orbits = orbits;
		this.vehicle = vehicle; 
	}
	
	public int getTraverseTime() {
		return traverseTime;
	}

	public void setTraverseTime(int pTraverseTime) {
		traverseTime = pTraverseTime;
	}

	public List<Orbit> getOrbits() {
		return orbits;
	}

	public void setOrbit(List<Orbit> pOrbits) {
		orbits = pOrbits;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle pVehicle) {
		vehicle = pVehicle;
	}

	@Override
	public String toString() {
		StringBuilder traverseDetail = new StringBuilder("TraverseDetail");
		traverseDetail.append(": {")
			.append("traverseTime=").append(traverseTime)
			.append(", orbits=").append(orbits)
			.append(", vehicle=").append(vehicle)
			.append("}");
		return traverseDetail.toString();
	}

	@Override
	public int compareTo(TraverseDetail o) {
		if(this.getTraverseTime() < o.getTraverseTime()) return -1;
		else if(this.getTraverseTime() > o.getTraverseTime()) return 1;
		else return 0;
	}

	
}
