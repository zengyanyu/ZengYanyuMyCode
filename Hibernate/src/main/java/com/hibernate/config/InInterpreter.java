package com.hibernate.config;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年11月22日 下午6:38:58
 * @see com.hibernate.config.InInterpreter.java
 */
public class InInterpreter implements Interpreter<InterpreteContext, InExpression> {

	/**
	* 对表达式进行解析，形成可以用于实际查询的上下文。
	* 解析成形如：entity.id in (1,2,3)的语句
	* 如果in语句中的值超过QueryConstants.MAX_EXPS_IN_LIST，则拆分成形如：
	* (entity.id in (1) or entity.id in (2) or entity.id in (3))的语句
	* 如果in语句中的值个数0，则返回形如：entity.id in ('')，避免语法错误
	* @param context       解析上下文
	* @param expression    IN表达式
	*/
	@Override
	public void interprete(InterpreteContext context, InExpression expression) {
		Object[] objs = expression.getValues();
		if (objs == null || objs.length == 0) {
			context.append(expression.getFullName());
			context.append(QueryConstants.BLANK);
			context.append(QueryConstants.IN);
			context.append(QueryConstants.LEFT_PARENTHESIS);
			context.append("''");
			context.append(QueryConstants.RIGHT_PARENTHESIS);
		} else if (objs.length <= QueryConstants.MAX_EXPS_IN_LIST) {
			context.append(expression.getFullName());
			context.append(QueryConstants.BLANK);
			context.append(QueryConstants.IN);
			context.append(QueryConstants.LEFT_PARENTHESIS);
			for (int i = 0; i < objs.length; i++) {
				if (i > 0) {
					context.append(QueryConstants.COMMA);
					context.append(QueryConstants.BLANK);
				}
				context.append(QueryConstants.INTERROGATION_POINT);
				context.addPrepraredValue(objs[i]);
			}
			context.append(QueryConstants.RIGHT_PARENTHESIS);
		} else {
			// 如果in语句中的值超过QueryConstants.MAX_EXPS_IN_LIST，则拆分成形如：
			// (entity.id in (1) or entity.id in (2) or entity.id in (3))的语句
			context.append(QueryConstants.LEFT_PARENTHESIS); //左括号
			//拆分IN语句的个数
			int inExpCount = objs.length / QueryConstants.MAX_EXPS_IN_LIST;
			if (objs.length % QueryConstants.MAX_EXPS_IN_LIST > 0) {
				inExpCount = inExpCount + 1;
			}
			for (int i = 1; i <= inExpCount; i++) {
				context.append(expression.getFullName());
				context.append(QueryConstants.BLANK);
				context.append(QueryConstants.IN);
				context.append(QueryConstants.LEFT_PARENTHESIS);
				for (int j = 0; j < QueryConstants.MAX_EXPS_IN_LIST; j++) {
					int k = (i - 1) * QueryConstants.MAX_EXPS_IN_LIST + j;
					if (k >= objs.length) {
						break;
					}
					if (j > 0 && k < objs.length) {
						context.append(QueryConstants.COMMA);

						context.append(QueryConstants.BLANK);
					}
					context.append(QueryConstants.INTERROGATION_POINT);
					context.addPrepraredValue(objs[k]);
				}
				context.append(QueryConstants.RIGHT_PARENTHESIS);
				if (i < inExpCount) {
					context.append(QueryConstants.BLANK);
					context.append(QueryConstants.OR);
					context.append(QueryConstants.BLANK);
				}
			}
			context.append(QueryConstants.RIGHT_PARENTHESIS);//右括号
		}
	}

}
