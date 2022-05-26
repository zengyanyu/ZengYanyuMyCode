package com.springboot.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class WsdApplicationException extends RuntimeException {

	/**
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年6月20日 下午4:32:00
	 */
	private static final long serialVersionUID = 6905492619243282593L;

	public static final Class<?> DEFAULT_OWNER_CLASS = Object.class;

	protected String msgKey;

	protected Class<?> ownerClass = DEFAULT_OWNER_CLASS;

	protected String[] propertyNames;

	protected String[] propertyNameDescs;

	protected Object[] propertyValues;

	protected String[] msgParams;

	protected String msgDetails;

	protected Exception exception;

	public WsdApplicationException() {
		super();
		this.msgKey = getMsgKey();
	}

	public WsdApplicationException(Class<?> ownerClass, Exception exception) {
		this();
		this.ownerClass = ownerClass;
		this.exception = exception;
	}

	public WsdApplicationException(Class<?> ownerClass, Exception exception, String[] propertyNames,
			Object[] propertyValues) {
		this(ownerClass, exception, propertyNames, propertyValues, null);
	}

	public WsdApplicationException(Class<?> ownerClass, Exception exception, String[] msgParams) {
		this(ownerClass, exception, null, null, msgParams);
	}

	public WsdApplicationException(Class<?> ownerClass, Exception exception, String[] propertyNames,
			Object[] propertyValues, String[] msgParams) {
		this();
		this.ownerClass = ownerClass;
		this.exception = exception;
		this.propertyNames = propertyNames;
		this.propertyValues = propertyValues;
		this.msgParams = msgParams;
	}

	public WsdApplicationException(String msgKey, Class<?> ownerClass, Exception exception, String[] propertyNames,
			Object[] propertyValues, String[] msgParams, String msgDetails) {
		this();
		this.msgKey = msgKey;
		this.ownerClass = ownerClass;
		this.exception = exception;
		this.propertyNames = propertyNames;
		this.propertyValues = propertyValues;
		this.msgParams = msgParams;
		this.msgDetails = msgDetails;
	}

}
