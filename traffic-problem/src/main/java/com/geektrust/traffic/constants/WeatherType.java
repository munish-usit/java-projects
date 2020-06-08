package com.geektrust.traffic.constants;

import java.util.Arrays;

import com.geektrust.traffic.exception.BusinessException;

/**
 * WeatherType Enum.
  */
public enum WeatherType {

	
	SUNNY("SUNNY"),
		
	RAINY("RAINY"),
	
	WINDY("WINDY");

	private static final WeatherType[] ENUMS = WeatherType.values();

	private WeatherType() {
		// Restrict instantiation
	}

	 /**
     * Obtains an instance of {@code WeatherType} from an {@code int} value.
     *
     * @param weatherType  the weather type to represent, from 0 (Sunday) to 6 (Saturday)
     * @return the day-of-week singleton, not null
     * @throws BusinessException, if the day-of-week is invalid
     */
	public static WeatherType of(int weatherType) throws BusinessException {
		if (weatherType < 0) {
			throw new BusinessException("Invalid value for WeatherType: " + weatherType);
		}
		return ENUMS[weatherType];
	}
	
	public static boolean contains(String pWeatherType) {
		return Arrays.stream(ENUMS	)
				.anyMatch(weatherType -> weatherType.toString().equalsIgnoreCase(pWeatherType));
	}
	
	private String type;

    private WeatherType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }
}
