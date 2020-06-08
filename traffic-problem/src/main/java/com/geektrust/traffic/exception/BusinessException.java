package com.geektrust.traffic.exception;

import com.geektrust.traffic.constants.ErrorMessages;

/**
 * DESCRIPTION - This class is wrapper over Exception. 
 * 				 This is used wherever Business condition violation occurs.
 */
public class BusinessException extends Exception {

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String EMPTY = "";

	private final String mErrorCode;

    /**
     * Throws valid business exception
     */
    public BusinessException() {
        super();
        this.mErrorCode = EMPTY;
    }

    /**
     * Instantiates a new Business exception.
     * 
     * @param errorMsg the error msg
     */
    public BusinessException(final String errorMsg) {
        super(errorMsg);
        this.mErrorCode = EMPTY;
    }

    /**
     * Instantiates a new Business exception.
     * 
     * @param errorCode the error code
     * @param errorMsg the error msg
     */
    public BusinessException(final String errorCode, final String errorMsg) {
        super(errorMsg);
        this.mErrorCode = errorCode;
    }
    
    /**
     * 
     */
    public BusinessException(ErrorMessages errorMessage) {
        super(errorMessage.getErrorMessage());
        this.mErrorCode = errorMessage.getErrorCode();
    }

    /**
     * @param cause
     */
    public BusinessException(Throwable cause) {
        super(cause);
        this.mErrorCode = EMPTY;
    }

    /**
     * Instantiates a new Business exception.
     * 
     * @param errorMsg the error msg
     * @param cause the cause
     */
    public BusinessException(final String errorMsg, final Throwable cause) {
        super(errorMsg, cause);
        this.mErrorCode = EMPTY;
    }

    /**
     * Instantiates a new Business exception.
     * 
     * @param errorCode the error code
     * @param errorMsg the error msg
     * @param cause the cause
     */
    public BusinessException(final String errorCode, final String errorMsg, final Throwable cause) {
        super(errorMsg, cause);
        this.mErrorCode = errorCode;
    }

    public String getErrorCode() {
        return mErrorCode;
    }
}