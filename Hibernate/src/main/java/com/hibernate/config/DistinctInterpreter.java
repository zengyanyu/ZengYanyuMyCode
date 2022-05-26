package com.hibernate.config;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年11月22日 下午8:29:15
 * @see com.hibernate.config.DistinctInterpreter.java
 */
public class DistinctInterpreter implements Interpreter<SqlInterpreteContext, Boolean> {

	@Override
	public void interprete(SqlInterpreteContext context, Boolean distinct) {
		String sql = context.getContextString();
		boolean flag = sql.toUpperCase().trim().startsWith(QueryConstants.SELECT);
		if (flag) {
			//有select的处理
			int index = sql.toUpperCase().indexOf(QueryConstants.SELECT);
			String suffix = sql.substring(index + QueryConstants.SELECT.length());
			context.getNativeString().delete(index + QueryConstants.SELECT.length(), sql.length());
			injectSQL(context);
			context.append(suffix);
		} else {
			//无select的处理, todo
		}
	}

	/**
	 * 注入distinct sql语句.
	 * @param context   解释器上下文
	 */
	private void injectSQL(InterpreteContext context) {
		context.append(QueryConstants.BLANK);
		context.append(QueryConstants.DISTINCT);
		context.append(QueryConstants.BLANK);
	}

}
