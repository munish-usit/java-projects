package org.analytics.covidtracker.constants;

public enum RolesType {

	ADMIN("ADMIN"),
	USER("USER");
	
	private String type;
	
	private RolesType(String name) {
		this.type = name;
	}
	
	@Override
    public String toString() {
        return this.type;
    }
	
	
}
