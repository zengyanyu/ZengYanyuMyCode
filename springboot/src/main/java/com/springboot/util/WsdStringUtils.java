package com.springboot.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.springboot.utils.WsdArrayUtils;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年6月18日 下午10:01:28
 * @see com.springboot.util.WsdStringUtils.java
 */
@SuppressWarnings("all")
public class WsdStringUtils extends StringUtils {

	private static final Log LOGGER = LogFactory.getLog(WsdStringUtils.class);

	public static String joinToString(final String[] src, String separator) {
		if (src == null)
			return "";
		if (separator == null)
			separator = ",";
		StringBuffer dest = new StringBuffer();
		for (String s : src) {
			dest.append(s).append(separator).append(" ");
		}
		dest.delete(dest.lastIndexOf(separator), dest.length());
		return dest.toString();
	}

	public static String joinToString(final Object[] keys, Object[] values, String entrySeparator, String kvSeparator) {
		if (WsdArrayUtils.isEmpty(keys))
			return "";

		if (WsdArrayUtils.isEmpty(values)) {
			values = new Object[keys.length];
		} else if (values.length < keys.length) {
			Object[] temp = new Object[keys.length];
			System.arraycopy(values, 0, temp, 0, values.length);
			values = temp;
		}

		if (kvSeparator == null)
			kvSeparator = "=";
		if (entrySeparator == null)
			entrySeparator = ",";

		StringBuffer dest = new StringBuffer();
		dest.append(keys[0]).append(kvSeparator).append(values[0]);
		for (int i = 1; i < keys.length; i++) {
			dest.append(entrySeparator).append(keys[i]).append(kvSeparator).append(values[i]);
		}
		return dest.toString();
	}

}
