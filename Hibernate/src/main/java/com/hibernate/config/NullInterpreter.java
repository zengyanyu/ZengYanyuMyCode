package com.hibernate.config;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年11月22日 下午8:04:50
 * @see com.hibernate.config.NullInterpreter.java
 */
public class NullInterpreter implements Interpreter<InterpreteContext, NullExpression> {
	/**
	 * 对表达式进行解析，形成可以用于实际查询的上下文。
	 *
	 * @param context       解析上下文。
	 * @param expression    需要解析的对象，包括表达式、Order等等。
	 */
	@Override
	public void interprete(InterpreteContext context, NullExpression expression) {
		context.append(expression.getFullName());
		context.append(QueryConstants.BLANK);
		context.append(QueryConstants.IS);
		context.append(QueryConstants.BLANK);
		context.append(QueryConstants.NULL);
	}
}
