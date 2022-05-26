package com.hibernate.config;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年11月22日 下午8:13:17
 * @see com.hibernate.config.PropertyInterpreter.java
 */
public class PropertyInterpreter implements Interpreter<InterpreteContext, PropertyExpression> {

	/**
	 * 对表达式进行解析，形成可以用于实际查询的上下文.
	 * @param context      解析上下文。
	 * @param expression 需要解析的对象，包括表达式、Order等等。
	 */
	@Override
	public void interprete(InterpreteContext context, PropertyExpression expression) {
		context.append(expression.getFullName());
		context.append(expression.getOperation());
		//多表关联，使用占位符
		context.append(expression.getValue());
	}

}