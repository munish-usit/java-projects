package com.geektrust.traffic.constants;

public enum ErrorMessages {

	INIT_FAILED("101","Initialization Failed"),
	INVALID_ARGS("102","Invalid arguments"),
	EMPTY_INPUT_FILE("103","No inputs found in the file"),
	INVALID_FILE("104","Invalid file path"),
	EMPTY_INPUT("105","No value given for the input"),
	INVALID_WEATHER("106","Weather is empty or weather doesn't exist"),
	INVALID_ORBITSPEED("107","Invalid orbit speed format"),
	INVALID_INPUT("108","Invalid input arguments"),
	
	;
	private String errorcode;
	private String errorMessage;
		
	private ErrorMessages(String errorCode,String errorMessage) {
		this.errorcode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	public String getErrorCode() {
		return this.errorcode;
	}
	public String getErrorMessage() {
		return this.errorMessage;
	}
	
}
