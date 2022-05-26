package com.hibernate.config;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年11月22日 下午6:20:06
 * @see com.hibernate.config.WsdCriteria.java
 */
@SuppressWarnings("serial")
public class WsdCriteria implements WsdCriterion {

	protected List<WsdCriterion> criterions = new ArrayList<WsdCriterion>();
	protected List<Order> orders = new ArrayList<Order>();
	protected List<QueryEntity> entities = new ArrayList<QueryEntity>();
	protected Group group;
	protected Having having;
	protected boolean distinct = false;

	/**
	 * Add a restriction to constrain the results to be retrieved。
	 *
	 * @param criterion The criterion object representing the restriction to be applied.
	 * @return this (for method chaining)
	 */
	public WsdCriteria add(WsdCriterion criterion) {
		criterions.add(criterion);
		return this;
	}

	/**
	 * Add a restriction to constrain the results to be retrieved.
	 *
	 * @param order The criterion object representing the restriction to be applied.
	 * @return this (for method chaining)
	 */
	public WsdCriteria addOrder(Order order) {
		orders.add(order);
		return this;
	}

	/**
	 * 返回所有的条件列表。
	 *
	 * @return 条件列表。
	 */
	public List<WsdCriterion> getCriterions() {
		return criterions;
	}

	/**
	 * 返回所有的排序列表。
	 *
	 * @return 所有的排序列表。
	 */
	public List<Order> getOrders() {
		return orders;
	}

	/**
	 * 以注入的方式支持多表关联查询.
	 *
	 * @param className     实体类的SimpleName
	 * @param entityName    entityName，即别名
	 */
	public void addEntity(String className, String entityName) {
		entities.add(new QueryEntity(className, entityName));
	}

	/**
	 * 获取注入的实体/表名称.
	 *
	 * @return 获取注入的实体/表名称
	 */
	public List<QueryEntity> getEntities() {
		return entities;
	}

	/**
	 * 获取Group By语句对象.
	 *
	 * @return Group By对象
	 */
	public Group getGroup() {
		return group;
	}

	/**
	 * 设置Group By语句对象.
	 *
	 * @param group Group By语句对象
	 */
	public void setGroup(Group group) {
		this.group = group;
	}

	/**
	 * 获取Having语句对象.
	 *
	 * @return Having语句对象
	 */
	public Having getHaving() {
		return having;
	}

	/**
	 * 设置Having语句对象.
	 *
	 * @param having    Having语句对象
	 */
	public void setHaving(Having having) {
		this.having = having;
	}

	/**
	 * 获取distinct属性.
	 *
	 * @return distinct属性
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * 设置distinct属性.
	 *
	 * @param distinct distinct属性
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * 对查询条件进行解析。例如生成SQL语句，Hibernate的Conditions对象等等。
	 *
	 * @param context 解析上下文.
	 */
	public void interprete(Object context) {
		WsdCriterionUtil.inteprete(context, this);
	}

	/**
	 * 对查询条件进行解析。例如生成SQL语句，Hibernate的Conditions对象等等。
	 *
	 * @param type    解析类型。
	 * @param context 解析上下文.
	 */
	public void interprete(String type, Object context) {
		WsdCriterionUtil.inteprete(type, context, this);
	}
}
