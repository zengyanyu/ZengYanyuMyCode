package com.jpa.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.springframework.util.Assert;

public class NumberUtils {
	public static boolean isNumber(String text) {
		try {
			Double.valueOf(text);
			return true;
		} catch (NumberFormatException var2) {
			return false;
		}
	}

	public static Number scale(Number number, int scale, int roundingMode) {
		Assert.notNull(number);
		return scale(number, scale, RoundingMode.valueOf(roundingMode));
	}

	public static Number scale(Number number, int scale, RoundingMode roundingMode) {
		Assert.notNull(number);
		return Formats.createNumberFormat(new NumberConfig(scale, roundingMode)).scale(number);
	}

	public static Number precision(Number number, MathContext mathContext) {
		Assert.notNull(number);
		return new BigDecimal(number.doubleValue(), mathContext);
	}

	public static String format(Number number, String pattern, RoundingMode roundingMode) {
		Assert.notNull(number);
		DecimalFormat df = new DecimalFormat(pattern);
		df.setRoundingMode(roundingMode);
		return df.format(number);
	}
}
