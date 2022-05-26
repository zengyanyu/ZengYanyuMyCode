package com.hibernate.config;

/**
 * OrExpression解析器
 * @author ZengYanyu
 * @Description
 * @Date 2020年11月22日 下午8:46:11
 * @see com.hibernate.config.OrInterpreter.java
 */
@SuppressWarnings("rawtypes")
public class OrInterpreter implements Interpreter<InterpreteContext, OrExpression> {

	/**
	 * 对表达式进行解析，形成可以用于实际查询的上下文。
	 * @param context       解析上下文。
	 * @param expression    IN表达式。
	 */
	@Override
	public void interprete(InterpreteContext context, OrExpression expression) {
		WsdCriterion[] criterions = expression.getValues();
		for (int i = 0; i < criterions.length; i++) {
			context.append(QueryConstants.BLANK);
			if (i > 0) {
				context.append(QueryConstants.OR);
				context.append(QueryConstants.BLANK);
			}
			WsdCriterionUtil.inteprete(InterpreterFactory.TYPE_SQL, context, criterions[i]);
		}
	}

}
