package com.hibernate.config;

import java.io.Serializable;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年11月22日 下午6:10:51
 * @see com.hibernate.config.QueryEntity.java
 */
@SuppressWarnings("serial")
public class QueryEntity implements Serializable {

	/**
	* 实体类对象的SimpleName
	*/
	private final String entityName;

	/**
	 * 实体对象的别名
	 */
	private final String alias;

	/**
	 * Constructor
	 *
	 * @param entityName     实体类名
	 * @param alias    实体别名
	 */
	public QueryEntity(String entityName, String alias) {
		this.entityName = entityName;
		this.alias = alias;
	}

	/**
	 * 返回实体类对象的SimpleName.
	 *
	 * @return 实体类对象的SimpleName
	 */
	public String getEntityName() {
		return entityName;
	}

	/**
	 * 获取实体对象的别名.
	 *
	 * @return 实体对象的别名
	 */
	public String getAlias() {
		return alias;
	}

}
