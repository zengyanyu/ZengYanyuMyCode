package com.hibernate.config;

import java.util.List;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年11月22日 下午8:25:36
 * @see com.hibernate.config.GroupInterpreter.java
 */
public class GroupInterpreter implements Interpreter<InterpreteContext, Group> {

	/**
	 * 对表达式进行解析，形成可以用于实际查询的上下文。
	 * @param context 解析上下文。
	 * @param group     需要解析的对象。
	 */
	@Override
	public void interprete(InterpreteContext context, Group group) {
		context.append(QueryConstants.BLANK);
		context.append(QueryConstants.GROUP_BY);
		context.append(QueryConstants.BLANK);

		List<String> entityNames = group.getEntityNames();
		List<String> propNames = group.getPropNames();

		for (int i = 0; i < entityNames.size(); i++) {
			String entityName = entityNames.get(i);
			String propName = propNames.get(i);
			if (i > 0) {
				context.append(QueryConstants.COMMA);
				context.append(QueryConstants.BLANK);
			}
			context.append(group.getFullName(entityName, propName));
		}
	}

}
