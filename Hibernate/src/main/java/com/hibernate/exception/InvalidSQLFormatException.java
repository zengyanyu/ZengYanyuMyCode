package com.hibernate.exception;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年11月22日 下午8:24:13
 * @see com.hibernate.exception.InvalidSQLFormatException.java
 */
@SuppressWarnings("serial")
public class InvalidSQLFormatException extends RuntimeException {
	/**
	 * 构造
	 * @param message 消息
	 */
	public InvalidSQLFormatException(String message) {
		super(message);
	}

	/**
	 * 构造一个嵌套的异常
	 * @param message 异常消息
	 * @param cause   异常成因
	 */
	public InvalidSQLFormatException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 构造一个嵌套的异常
	 * @param message 异常消息
	 * @param cause   异常成因
	 * @param args      参数
	 */
	public InvalidSQLFormatException(String message, Throwable cause, Object... args) {
		//    TODO 该异常需要优化
		super(message + args, cause);
	}

}
