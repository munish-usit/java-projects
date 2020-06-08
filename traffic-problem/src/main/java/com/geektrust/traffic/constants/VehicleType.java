package com.geektrust.traffic.constants;

public enum VehicleType {

	CAR("CAR"),
	TUKTUK("TUKTUK"),
	BIKE("BIKE")
	;
	private String type;
	VehicleType(String type) {
		this.type = type;
	}
	@Override
    public String toString() {
        return this.type;
    }
	
}
