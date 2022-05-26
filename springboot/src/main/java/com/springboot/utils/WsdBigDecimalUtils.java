package com.springboot.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.springboot.util.WsdStringUtils;

public class WsdBigDecimalUtils {

	private static int precision = 10;

	public static BigDecimal init(BigDecimal bigDecimal) {
		BigDecimal init = (bigDecimal == null ? new BigDecimal(0) : bigDecimal);
		return init;
	}

	/**
	 * 两数相加
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年10月4日 下午8:06:39
	 * @param b1
	 * @param b2
	 * @return
	 */
	public static BigDecimal add(BigDecimal b1, BigDecimal b2) {
		if (b1 == null) {
			b1 = new BigDecimal(0);
		}
		if (b2 == null) {
			b2 = new BigDecimal(0);
		}
		return b1.add(b2);
	}

	/**
	 * 两数相减
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年10月4日 下午8:06:57
	 * @param b1
	 * @param b2
	 * @return
	 */
	public static BigDecimal subtract(BigDecimal b1, BigDecimal b2) {
		if (b1 == null) {
			b1 = new BigDecimal(0);
		}
		if (b2 == null) {
			b2 = new BigDecimal(0);
		}
		return b1.subtract(b2);
	}

	/**
	 * 两数相乘
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年10月4日 下午8:09:45
	 * @param b1
	 * @param b2
	 * @return
	 */
	public static BigDecimal multiply(BigDecimal b1, BigDecimal b2) {
		if (b1 == null) {
			b1 = new BigDecimal(0);
		}
		if (b2 == null) {
			b2 = new BigDecimal(0);
		}
		return b1.multiply(b2).setScale(precision, RoundingMode.HALF_UP);
	}

	/**
	 * 两数相乘
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年10月4日 下午8:12:19
	 * @param b1
	 * @param b2
	 * @param scale  保存的小数位
	 * @return
	 */
	public static BigDecimal multiply(BigDecimal b1, BigDecimal b2, int scale) {
		if (b1 == null) {
			b1 = new BigDecimal(0);
		}
		if (b2 == null) {
			b2 = new BigDecimal(0);
		}
		return b1.multiply(b2).setScale(scale, RoundingMode.HALF_UP);
	}

	/**
	 * 比较数的大小
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年10月4日 下午8:13:09
	 * @param b1
	 * @param b2
	 * @return  b1比b2大于等于返回,返回true,否则,返回false
	 */
	public static boolean greaterThan(BigDecimal b1, BigDecimal b2) {
		if (b1 == null) {
			b1 = new BigDecimal(0);
		}
		if (b2 == null) {
			b2 = new BigDecimal(0);
		}
		return b1.compareTo(b2) > 0;
	}

	/**
	 * 比较数的大小
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年10月4日 下午8:16:16
	 * @param b1
	 * @param b2
	 * @return   b1比b2大于等于返回,返回true,否则,返回false
	 */
	public static boolean greaterOrEquals(BigDecimal b1, BigDecimal b2) {
		if (b1 == null) {
			b1 = new BigDecimal(0);
		}
		if (b2 == null) {
			b2 = new BigDecimal(0);
		}
		return b1.compareTo(b2) >= 0;
	}

	/**
	 * 比较数的大小
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年10月4日 下午8:17:10
	 * @param b1
	 * @param b2
	 * @return
	 */
	public static boolean lessThan(BigDecimal b1, BigDecimal b2) {
		if (b1 == null) {
			b1 = new BigDecimal(0);
		}
		if (b2 == null) {
			b2 = new BigDecimal(0);
		}
		return b1.compareTo(b2) < 0;
	}

	/**
	 * 比较数的大小
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年10月4日 下午8:17:15
	 * @param b1
	 * @param b2
	 * @return
	 */
	public static boolean lessOrEquals(BigDecimal b1, BigDecimal b2) {
		if (b1 == null) {
			b1 = new BigDecimal(0);
		}
		if (b2 == null) {
			b2 = new BigDecimal(0);
		}
		return b1.compareTo(b2) <= 0;
	}

	/**
	 * 判断两数相等
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年10月4日 下午8:17:23
	 * @param b1
	 * @param b2
	 * @return
	 */
	public static boolean equals(BigDecimal b1, BigDecimal b2) {
		if (b1 == null) {
			b1 = new BigDecimal(0);
		}
		if (b2 == null) {
			b2 = new BigDecimal(0);
		}
		return b1.compareTo(b2) == 0;
	}

	/**
	 * 两数相除
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年10月4日 下午8:25:03
	 * @param b1
	 * @param b2
	 * @return
	 */
	public static BigDecimal divide(BigDecimal b1, BigDecimal b2) {
		if (b1 == null) {
			return new BigDecimal(0);
		}
		if (b2 == null || equals(b2, new BigDecimal(0))) {
			throw new ArithmeticException("b2 is zero ! ");
		}
		return b1.divide(b2, precision, RoundingMode.HALF_UP);
	}

	/**
	 * 两数相除
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年10月4日 下午8:24:54
	 * @param b1
	 * @param b2
	 * @param scale
	 * @return
	 */
	public static BigDecimal divide(BigDecimal b1, BigDecimal b2, int scale) {
		if (b1 == null) {
			return new BigDecimal(0);
		}
		if (b2 == null || equals(b2, new BigDecimal(0))) {
			throw new ArithmeticException("b2 is zero ! ");
		}
		return b1.divide(b2, scale, RoundingMode.HALF_UP);
	}

	/**
	 * 数字取反
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年10月4日 下午8:25:57
	 * @param b1
	 * @return
	 */
	public static BigDecimal negate(BigDecimal b1) {
		if (b1 != null) {
			BigDecimal negate = b1.negate();
			return negate;
		}
		return BigDecimal.ZERO;
	}

	/**
	 * 字符串转换成BigDecimal
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年10月4日 下午8:26:58
	 * @param val
	 * @return
	 */
	public static BigDecimal strConvertDecimal(String val) {
		if (WsdStringUtils.isNotBlank(val)) {
			val = val.replaceAll("\\,", "");
			return new BigDecimal(val);
		}
		return null;
	}

	public static void main(String[] args) {
		BigDecimal bigDecimal = new BigDecimal(1000);
		BigDecimal bigDecimal2 = new BigDecimal(2000);
		bigDecimal2 = divide(bigDecimal, bigDecimal2, 2);
		System.out.println(bigDecimal2);
		System.out.println(strConvertDecimal("2000"));
	}

}
