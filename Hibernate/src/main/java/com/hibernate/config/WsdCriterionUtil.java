package com.hibernate.config;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年11月22日 下午5:29:22
 * @see com.hibernate.config.WsdCriterionUtil.java
 */
public class WsdCriterionUtil {

	static final String pattern_block = "(^.*FROM.*WHERE$)|(.*FROM.*AND$)|(.*FROM.*OR$)|(.*FROM.*NOT$)";
	static final String pattern_permit = "(^SELECT.*FROM)|(^DELETE.*FROM)|(^UPDATE)";

	private WsdCriterionUtil() {
	}

	public static void inteprete(Object context, Object obj) {
		inteprete(InterpreterFactory.TYPE_SQL, context, obj);
	}

	public static void inteprete(String type, Object context, Object obj) {
		InterpreterFactory factory = getFactory(type);
		Interpreter<Object, Object> interpreter = (Interpreter<Object, Object>) factory.getInterpreter(obj);
		interpreter.interprete(context, obj);
	}

	private static InterpreterFactory getFactory(String type) {
		return InterpreterFactoryImpl.getInstance();
	}

	/**
	 * Simple Validator.
	 *
	 * @param request   to be validated sql
	 * @return success or failure
	 */
	public static boolean validate(String request) {
		String sql = request.toUpperCase();
		StringBuilder sb = new StringBuilder();
		char pre = sql.charAt(0);
		char current;
		if (pre != ' ') {
			sb.append(pre);
		}
		for (int i = 1; i < sql.length(); i++) {
			current = sql.charAt(i);
			if (current == ' ' && pre == ' ') {
				pre = current;
				continue;
			}
			sb.append(current);
		}

		String stardard = sb.toString();

		Pattern p = Pattern.compile(pattern_block);
		Matcher m = p.matcher(stardard);
		boolean flag = m.find();
		if (flag) {
			return false;
		}
		p = Pattern.compile(pattern_permit);
		m = p.matcher(stardard);
		flag = m.find();
		return flag;
	}

	/**
	 * 获取统计的结果数count.
	 *
	 * @param sql        SQL字符串
	 * @param distinct  是否允许重复，默认为false
	 * @return 统计结果的sql
	 */
	public static String getCountSqlString(String sql, boolean distinct) {
		if (distinct) {
			StringBuilder buf = new StringBuilder("SELECT COUNT(*) FROM (");

			String temp = sql.toUpperCase();
			int begin = temp.indexOf("FROM");
			int end = temp.lastIndexOf("ORDER BY");
			//如果有多表，从order by 截断会导致语法错误
			//	            if (temp.lastIndexOf("FROM") > begin) {
			//	                end = -1;
			//	            }
			if (begin > -1 && end > begin) {
				buf.append(sql.substring(0, end));
			} else {
				buf.append(sql);
			}

			buf.append(")");
			return buf.toString();
		} else {
			StringBuilder buf = new StringBuilder("SELECT ");
			buf.append("COUNT(*) ");
			String temp = sql.toUpperCase();
			int begin = temp.indexOf("FROM");
			int end = temp.lastIndexOf("ORDER BY");
			//如果有多表，从order by 截断会导致语法错误
			//	            if (temp.lastIndexOf("FROM") > begin) {
			//	                end = -1;
			//	            }
			if (begin > -1 && end > begin) {
				buf.append(sql.substring(begin, end));
			} else if (begin > -1) {
				buf.append(sql.substring(begin));
			}
			return buf.toString();
		}
	}

	/**
	 * 获取统计的结果数count，不区分重复记录.
	 *
	 * @param sql        SQL字符串
	 * @return 统计结果的sql
	 */
	public static String getCountSqlString(String sql) {
		return getCountSqlString(sql, false);
	}

}
