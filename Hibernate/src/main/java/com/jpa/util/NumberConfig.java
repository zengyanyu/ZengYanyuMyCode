package com.jpa.util;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class NumberConfig {
	private static final String PATTERN = "#.##";
	public static final NumberConfig HALF_UP_2;
	public static final NumberConfig HALF_UP_4;
	public static final NumberConfig HALF_UP_6;
	public final String pattern;
	public final int scale;
	public final RoundingMode roundingMode;
	public final int maximumIntegerDigits;
	public final int minimumIntegerDigits;
	public final int maximumFractionDigits;
	public final int minimumFractionDigits;

	public NumberConfig(int scale, RoundingMode roundingMode) {
		this("#.##", scale, roundingMode, -1, -1, -1, -1);
	}

	public NumberConfig(String pattern, int scale, RoundingMode roundingMode) {
		this(pattern, scale, roundingMode, -1, -1, -1, -1);
	}

	public NumberConfig(String pattern, RoundingMode roundingMode, int maximumIntegerDigits,
			int maximumFractionDigits) {
		this.pattern = pattern;
		this.roundingMode = roundingMode;
		this.scale = maximumFractionDigits;
		this.maximumIntegerDigits = maximumIntegerDigits;
		this.maximumFractionDigits = maximumFractionDigits;
		this.minimumIntegerDigits = -1;
		this.minimumFractionDigits = -1;
	}

	public NumberConfig(String pattern, int scale, RoundingMode roundingMode, int minimumIntegerDigits,
			int maximumIntegerDigits, int maximumFractionDigits, int minimumFractionDigits) {
		this.pattern = pattern;
		this.scale = scale;
		this.roundingMode = roundingMode;
		this.minimumIntegerDigits = minimumIntegerDigits;
		this.maximumIntegerDigits = maximumIntegerDigits;
		this.maximumFractionDigits = maximumFractionDigits;
		this.minimumFractionDigits = minimumFractionDigits;
	}

	public NumberFormat create() {
		DecimalFormat df = new DecimalFormat(this.pattern);
		this.config(df);
		return df;
	}

	public void config(NumberFormat numberFactory) {
		numberFactory.setRoundingMode(this.roundingMode);
		if (this.maximumFractionDigits > 0) {
			numberFactory.setMaximumFractionDigits(this.maximumFractionDigits);
		}

		if (this.minimumFractionDigits > 0) {
			numberFactory.setMinimumFractionDigits(this.minimumFractionDigits);
		}

		if (this.maximumFractionDigits < 0 && this.minimumFractionDigits < 0) {
			numberFactory.setMinimumFractionDigits(this.scale);
			numberFactory.setMaximumFractionDigits(this.scale);
		}

		if (this.maximumIntegerDigits > 0) {
			numberFactory.setMaximumIntegerDigits(this.maximumIntegerDigits);
		}

		if (this.minimumIntegerDigits > 0) {
			numberFactory.setMinimumIntegerDigits(this.minimumIntegerDigits);
		}
	}

	static {
		HALF_UP_2 = new NumberConfig("#.##", 2, RoundingMode.HALF_UP);
		HALF_UP_4 = new NumberConfig("#.##", 4, RoundingMode.HALF_UP);
		HALF_UP_6 = new NumberConfig("#.##", 6, RoundingMode.HALF_UP);
	}
}
