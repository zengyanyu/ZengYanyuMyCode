package com.hibernate.config;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年11月23日 下午9:12:04
 * @see com.hibernate.config.SimpleInterpreter.java
 */
public class SimpleInterpreter implements Interpreter<InterpreteContext, SimpleExpression> {

	/**
	 * 对表达式进行解析，形成可以用于实际查询的上下文.
	 * @param context      解析上下文。
	 * @param expression 需要解析的对象，包括表达式、Order等等。
	 */
	@Override
	public void interprete(InterpreteContext context, SimpleExpression expression) {
		context.append(expression.getFullName());
		context.append(expression.getOperation());
		context.append(QueryConstants.INTERROGATION_POINT);
		context.addPrepraredValue(expression.getValue());
	}

}
