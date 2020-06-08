/**
 * Business exception to represent exception that occurs when business logic is not executed as per expectation
 */
package com.geektrust.traffic.exception;

import com.geektrust.traffic.constants.ErrorMessages;


public class BusinessException extends Exception {

	
	private static final long serialVersionUID = 1L;
	
	public static final String EMPTY = "";

	private final String mErrorCode;

   
    public BusinessException() {
        super();
        this.mErrorCode = EMPTY;
    }

   
    public BusinessException(final String errorMsg) {
        super(errorMsg);
        this.mErrorCode = EMPTY;
    }

    
    public BusinessException(final String errorCode, final String errorMsg) {
        super(errorMsg);
        this.mErrorCode = errorCode;
    }
    
   
    public BusinessException(ErrorMessages errorMessage) {
        super(errorMessage.getErrorMessage());
        this.mErrorCode = errorMessage.getErrorCode();
    }

   
    public BusinessException(Throwable cause) {
        super(cause);
        this.mErrorCode = EMPTY;
    }

   
    public BusinessException(final String errorMsg, final Throwable cause) {
        super(errorMsg, cause);
        this.mErrorCode = EMPTY;
    }

   
    public BusinessException(final String errorCode, final String errorMsg, final Throwable cause) {
        super(errorMsg, cause);
        this.mErrorCode = errorCode;
    }

    public String getErrorCode() {
        return mErrorCode;
    }
}