package com.hibernate.config;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.engine.spi.TypedValue;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年11月23日 下午7:43:26
 * @see com.hibernate.config.Criterion.java
 */
public interface Criterion extends Serializable {
	String toSqlString(Criteria criteria, CriteriaQuery //
			criteriaQuery) throws HibernateException;

	TypedValue[] getTypedValues(Criteria criteria, CriteriaQuery//
			criteriaQuery) throws HibernateException;
}
