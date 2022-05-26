package com.hibernate.config;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年11月22日 下午6:48:02
 * @see com.hibernate.config.LikeInterpreter.java
 */
public class LikeInterpreter implements Interpreter<InterpreteContext, LikeExpression> {
	/**
	 * 默认的转义字符串
	 */
	public static final String[] defaultChars = new String[] { "%", "_", "％" };

	/**
	 * 配置文件获取的key
	 */
	public static final String ESCAPE_CHARS = "sql.escapeChars";

	/**
	 * 对表达式进行解析，形成可以用于实际查询的上下文。
	 *
	 * @param context       解析上下文。
	 * @param expression    需要解析的对象，包括表达式、Order等等。
	 */
	@Override
	public void interprete(InterpreteContext context, LikeExpression expression) {
		context.append(expression.getFullName());
		context.append(QueryConstants.BLANK);
		context.append(QueryConstants.LIKE);
		context.append(QueryConstants.BLANK);
		context.append(QueryConstants.INTERROGATION_POINT);
		Character escapeChar = expression.getEscapeChar();
		if (escapeChar != null) {
			context.append(" escape \'" + escapeChar + "\'");
			context.addPrepraredValue(expression.getMatchMode().toMatchString(expression.getValue().toString()));
		} else if (expression.isEsc()) {
			escapeChar = '^';
			Object value = expression.getValue();
			if (value instanceof String) {
				String[] escapeChars = defaultChars;
				value = escapeChars((String) value, escapeChar, escapeChars);
				//                value = ((String) value).replaceAll("%", escapeChar + "%");
				//                value = ((String) value).replaceAll("_", escapeChar + "_");
				//                value = ((String) value).replaceAll("％", escapeChar + "_");
				context.append(" escape \'" + escapeChar + "\'");
				context.addPrepraredValue(expression.getMatchMode().toMatchString((String) value));
			}
		}
	}

	/**
	 * 转义特殊字符
	 * @param value 需要转义的字符串
	 * @param chars 转义字符数字
	 * @param escapeChar 转义标识符
	 * @return 转义后的字符串
	 */
	protected String escapeChars(String value, Character escapeChar, String... chars) {
		for (int i = 0; chars != null && i < chars.length; i++) {
			value = value.replaceAll(chars[i], escapeChar + chars[i]);
		}
		return value;
	}

}
