package com.hibernate.config;

import java.util.List;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年11月22日 下午6:37:54
 * @see com.hibernate.config.CriteriaInterpreter.java
 */
public class CriteriaInterpreter implements Interpreter<InterpreteContext, WsdCriteria> {
	/**
	 * 对表达式进行解析，形成可以用于实际查询的上下文。
	 *
	 * @param context  解析上下文。
	 * @param criteria 需要解析的对象，包括表达式、Order等等。
	 */
	@Override
	public void interprete(InterpreteContext context, WsdCriteria criteria) {
		List<WsdCriterion> criterions = criteria.getCriterions();
		List<Order> orders = criteria.getOrders();
		List<QueryEntity> entities = criteria.getEntities();
		Group group = criteria.getGroup();
		Having having = criteria.getHaving();

		String upperSql = context.getContextString().toUpperCase();
		int index = upperSql.indexOf(QueryConstants.WHERE);
		if (index == -1 && criterions.size() > 0 && upperSql.trim().length() > 0) {
			assembleContext(context, QueryConstants.WHERE);
		}

		intercepteEntities(context, entities);

		intercepteCriterions(context, criterions);

		intercepteOrders(context, orders);

		intercepteGroup(context, group);

		intercepteHaving(context, having);

		intercepteDistinct(context, criteria.isDistinct());
	}

	/**
	 * 解析条件.
	 *
	 * @param context       上下文
	 * @param criterions    条件集合
	 */
	protected void intercepteCriterions(InterpreteContext context, List<WsdCriterion> criterions) {
		String upperSql = context.getContextString().toUpperCase();
		int index = upperSql.indexOf(QueryConstants.WHERE);
		for (int i = 0; i < criterions.size(); i++) {
			if (i == 0 && index > 0) {
				if (index + QueryConstants.WHERE.length() + 4 < upperSql.length()) {
					//Need add AND
					assembleContext(context, QueryConstants.AND);
				}
			} else if (i > 0) {
				assembleContext(context, QueryConstants.AND);
			}
			WsdCriterionUtil.inteprete(InterpreterFactory.TYPE_SQL, context, criterions.get(i));
		}
	}

	/**
	 * 解析Entity.
	 *
	 * @param context   上下文
	 * @param entities  Entity集合
	 */
	protected void intercepteEntities(InterpreteContext context, List<QueryEntity> entities) {
		for (QueryEntity entity : entities) {
			WsdCriterionUtil.inteprete(InterpreterFactory.TYPE_SQL, context, entity);
		}
	}

	/**
	 * 解析排序.
	 *
	 * @param context   上下文
	 * @param orders    排序集合
	 */
	protected void intercepteOrders(InterpreteContext context, List<Order> orders) {
		if (!orders.isEmpty()) {
			assembleContext(context, QueryConstants.ORDER_BY);
		}

		for (int i = 0; i < orders.size(); i++) {
			if (i > 0) {
				context.append(QueryConstants.COMMA);
			}
			WsdCriterionUtil.inteprete(InterpreterFactory.TYPE_SQL, context, orders.get(i));
		}
	}

	/**
	 * 解析Having语句.
	 *
	 * @param context   上下文
	 * @param having   having对象集合
	 */
	protected void intercepteHaving(InterpreteContext context, Having having) {
		if (having != null) {
			WsdCriterionUtil.inteprete(InterpreterFactory.TYPE_SQL, context, having);
		}

	}

	/**
	 * 解析Group By.
	 *
	 * @param context   上下文
	 * @param group    group by对象集合
	 */
	protected void intercepteGroup(InterpreteContext context, Group group) {
		if (group != null) {
			WsdCriterionUtil.inteprete(InterpreterFactory.TYPE_SQL, context, group);
		}
	}

	/**
	 * 解析Group By.
	 *
	 * @param context   上下文
	 * @param distinct  distinct属性
	 */
	protected void intercepteDistinct(InterpreteContext context, boolean distinct) {
		if (!distinct) {
			return;
		}
		WsdCriterionUtil.inteprete(context, distinct);
	}

	/**
	 * 一种[" "+$+" "]的组装方法.
	 *
	 * @param context   上下文
	 * @param middle    中间的字符串
	 */
	protected void assembleContext(InterpreteContext context, String middle) {
		context.append(QueryConstants.BLANK);
		context.append(middle);
		context.append(QueryConstants.BLANK);
	}

}
