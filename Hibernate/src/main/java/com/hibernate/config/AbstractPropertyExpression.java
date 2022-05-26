package com.hibernate.config;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年11月22日 下午6:23:12
 * @see com.hibernate.config.AbstractPropertyExpression.java
 */
@SuppressWarnings("serial")
public class AbstractPropertyExpression implements WsdCriterion {
	/**
	* entity实体名称
	*/
	protected final String entityName;

	/**
	 * prop属性名称
	 */
	protected final String propName;

	/**
	 * 构造函数.
	 *
	 * @param entityName 实体名称
	 * @param propName 属性名称
	 */
	protected AbstractPropertyExpression(String entityName, String propName) {
		this.entityName = entityName;
		this.propName = propName;
	}

	/**
	 * 构造函数.
	 *
	 * @param propName 属性名称
	 */
	protected AbstractPropertyExpression(String propName) {
		this.entityName = null;
		this.propName = propName;
	}

	/**
	 * 获取属性名称.
	 *
	 * @return 属性名称
	 */
	public String getPropName() {
		return propName;
	}

	/**
	 * 获取实体名称.
	 *
	 * @return 属性名称
	 */
	public String getEntityName() {
		return entityName;
	}

	/**
	 * 获取组装后的名称.
	 *
	 * @return 组装后的名称
	 */
	public String getFullName() {
		if (entityName == null) {
			return propName;
		} else {
			return entityName + "." + propName;
		}
	}

	/**
	 * 是否存在多表字段关联.
	 *
	 * @return 多表字段关联
	 */
	//    public boolean isConjunction()
	//    {
	//        return conjunction;
	//    }
}
