package com.geektrust.traffic.constants;


/**
 * WeatherType Enum.
  */
public enum WeatherType {

	
	SUNNY("SUNNY"),
		
	RAINY("RAINY"),
	
	WINDY("WINDY");

	private String type;

    private WeatherType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }
}
