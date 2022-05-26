package com.springboot.exception;

public class WsdSystemException extends WsdApplicationException {

	/**
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年6月20日 下午4:38:27
	 */
	private static final long serialVersionUID = 8862591552670096643L;

	public WsdSystemException() {
		super();
	}

	public WsdSystemException(String msgDetails) {
		super();
		super.msgDetails = msgDetails;
	}

	public WsdSystemException(Exception cause) {
		super();
		super.exception = cause;
	}

	public WsdSystemException(Exception cause, String msgDetails) {
		this(cause);
		super.msgDetails = msgDetails;
	}

	public WsdSystemException(Class<?> ownerClass, Exception cause) {
		super(ownerClass, cause);
	}

	public WsdSystemException(Class<?> ownerClass, Exception cause, String msgDetails) {
		this(ownerClass, cause);
		super.msgDetails = msgDetails;
	}
}
