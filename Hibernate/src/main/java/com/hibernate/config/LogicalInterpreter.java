package com.hibernate.config;

public class LogicalInterpreter implements Interpreter<InterpreteContext, LogicalExpression> {

	/**
	 * 对表达式进行解析，形成可以用于实际查询的上下文。
	 * @param context       解析上下文。
	 * @param expression    需要解析的表达式。
	 */
	@Override
	public void interprete(InterpreteContext context, LogicalExpression expression) {
		WsdCriterion lhs = expression.getLhs();
		WsdCriterion rhs = expression.getRhs();
		context.append(QueryConstants.LEFT_PARENTHESIS);
		WsdCriterionUtil.inteprete(InterpreterFactory.TYPE_SQL, context, lhs);
		context.append(QueryConstants.BLANK);
		context.append(expression.getOperation());
		context.append(QueryConstants.BLANK);
		WsdCriterionUtil.inteprete(InterpreterFactory.TYPE_SQL, context, rhs);
		context.append(QueryConstants.RIGHT_PARENTHESIS);
	}

}
