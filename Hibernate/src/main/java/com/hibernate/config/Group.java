package com.hibernate.config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年11月22日 下午8:26:06
 * @see com.hibernate.config.Group.java
 */
@SuppressWarnings("serial")
public class Group implements Serializable {
	/**
	* 实体类对象SimpleName的List
	*/
	protected List<String> entityNames = new ArrayList<String>();;

	/**
	 * 属性名称列表
	 */
	protected List<String> propNames = new ArrayList<String>();

	/**
	 * Constructor
	 */
	public Group() {
	}

	/**
	 * Constructor
	 * @param entityName 实体名称.
	 * @param propName   属性名
	 */
	public Group(String entityName, String propName) {
		entityNames.add(entityName);
		propNames.add(propName);
	}

	/**
	 * 新增Group by属性.
	 *
	 * @param entityName    实体类对象的SimpleName
	 * @param propName      属性名称
	 */
	public void addGroup(String entityName, String propName) {
		entityNames.add(entityName);
		propNames.add(propName);
	}

	public List<String> getEntityNames() {
		return entityNames;
	}

	public List<String> getPropNames() {
		return propNames;
	}

	/**
	 * 获取组装后的名称.
	 *
	 * @return 组装后的名称
	 */
	public String getFullName(String entityName, String propName) {
		if (entityName == null) {
			return propName;
		} else {
			return entityName + "." + propName;
		}
	}
}
