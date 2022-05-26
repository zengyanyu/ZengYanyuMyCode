package com.hibernate.config;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年11月22日 下午6:36:12
 * @see com.hibernate.config.BetweenInterpreter.java
 */
public class BetweenInterpreter implements Interpreter<InterpreteContext, BetweenExpression> {

	/**
	* 对表达式进行解析，形成可以用于实际查询的上下文。
	* @param context       解析上下文。
	* @param expression    需要解析的表达式。
	*/
	@Override
	public void interprete(InterpreteContext context, BetweenExpression expression) {
		context.append(expression.getFullName());
		context.append(QueryConstants.BLANK);
		context.append(QueryConstants.BETWEEN);
		context.append(QueryConstants.BLANK);
		context.append(QueryConstants.INTERROGATION_POINT);
		context.append(QueryConstants.BLANK);
		context.append(QueryConstants.AND);
		context.append(QueryConstants.BLANK);
		context.append(QueryConstants.INTERROGATION_POINT);
		context.append(QueryConstants.BLANK);
		context.addPrepraredValue(expression.getLow());
		context.addPrepraredValue(expression.getHigh());
	}

}
