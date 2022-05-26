package com.hibernate.config;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年11月23日 下午7:44:14
 * @see com.hibernate.config.HavingInterpreter.java
 */
public class HavingInterpreter implements Interpreter<SqlInterpreteContext, Having> {

	/**
	 * 对表达式进行解析，形成可以用于实际查询的上下文。
	 *
	 * @param context 解析上下文。
	 * @param having     需要解析的对象，包括表达式、Order等等。
	 */
	@Override
	public void interprete(SqlInterpreteContext context, Having having) {
		context.append(QueryConstants.BLANK);
		context.append(QueryConstants.HAVING);
		context.append(QueryConstants.BLANK);
		WsdCriterion criterion = having.getCriterion();
		WsdCriterionUtil.inteprete(InterpreterFactory.TYPE_SQL, context, criterion);
	}
}