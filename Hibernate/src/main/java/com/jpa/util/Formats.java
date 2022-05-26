package com.jpa.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

public class Formats {
	public static final String FULL_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss:SSS";
	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static final String SHORT_DATE_PATTERN = "yyyy-MM-dd";
	public static final String DIAGONAL_DATE_PATTERN = "dd/MM/yyyy";
	public static final String COMMON_DATE_PATTERN = "yyyyMMdd";
	static final Set<String> DATA_PATTERNS = new HashSet<>();
	public static final String DEFAULT_NUMBER_PATTERN = "#.##";

	public static DateFormat createDateFormat(String pattern) {
		return new SimpleDateFormat(pattern);
	}

	static {
		DATA_PATTERNS.add("yyyy-MM-dd HH:mm:ss:SSS");
		DATA_PATTERNS.add("yyyy-MM-dd HH:mm:ss");
		DATA_PATTERNS.add("yyyy-MM-dd");
		DATA_PATTERNS.add("dd/MM/yyyy");
		DATA_PATTERNS.add("yyyyMMdd");
		DATA_PATTERNS.add("yyyyMMdd");
	}
}
