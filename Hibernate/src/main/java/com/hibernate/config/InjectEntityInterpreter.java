package com.hibernate.config;

public class InjectEntityInterpreter implements Interpreter<SqlInterpreteContext, QueryEntity> {

	/**
	 * 对表达式进行解析，形成可以用于实际查询的上下文。
	 * context中先用于保存sql查询语句.
	 *
	 * @param context      解析上下文。
	 * @param entity     需要解析的对象，只包括Entity列表对象等等。
	 */
	public void interprete(SqlInterpreteContext context, QueryEntity entity) {
		String prefix = context.getContextString();
		int index = prefix.toUpperCase().indexOf(QueryConstants.WHERE) - 1;
		if (index > 0) {
			String suffix = prefix.substring(index);
			context.getNativeString().delete(index, prefix.length());
			injectSQL(context, entity);
			context.append(suffix);
		} else if (index == -1) {
			injectSQL(context, entity);
			context.append(QueryConstants.BLANK);
		}
	}

	/**
	 * 注入entity sql语句.
	 *
	 * @param context   解释器上下文
	 * @param entity    注入的entity实体
	 */
	private void injectSQL(SqlInterpreteContext context, QueryEntity entity) {
		context.append(QueryConstants.COMMA);
		context.append(QueryConstants.BLANK);
		context.append(entity.getEntityName());
		context.append(QueryConstants.BLANK);
		context.append(entity.getAlias());
	}
}
