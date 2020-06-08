package com.geektrust.traffic.constants;

public enum OrbitType {

	ORBIT1("ORBIT1"),
	ORBIT2("ORBIT2");
	
	private String type;
	
	private OrbitType(String name) {
		this.type = name;
	}
	
	@Override
    public String toString() {
        return this.type;
    }
	
	
}
