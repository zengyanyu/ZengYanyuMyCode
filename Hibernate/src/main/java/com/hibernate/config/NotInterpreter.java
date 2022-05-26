package com.hibernate.config;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年11月22日 下午6:59:05
 * @see com.hibernate.config.NotInterpreter.java
 */
public class NotInterpreter implements Interpreter<InterpreteContext, NotExpression> {

	/**
	 * 对表达式进行解析，形成可以用于实际查询的上下文。
	 * @param context       解析上下文。
	 * @param expression    需要解析的对象，包括表达式、Order等等。
	 */
	@Override
	public void interprete(InterpreteContext context, NotExpression expression) {
		context.append(QueryConstants.NOT);
		context.append(QueryConstants.LEFT_PARENTHESIS);
		WsdCriterionUtil.inteprete(InterpreterFactory.TYPE_SQL, context, expression.getCriterion());
		context.append(QueryConstants.RIGHT_PARENTHESIS);
	}

}
