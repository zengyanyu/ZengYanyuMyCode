package com.hibernate.config;

import java.util.Collection;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年11月23日 下午9:12:55
 * @see com.hibernate.config.ZyyTemplateDao.java
 */
@SuppressWarnings("all")
public class ZyyTemplateDao extends HibernateTemplate {

	public Session getCurrentSession() {
		return this.getSessionFactory().getCurrentSession();
	}

	public Session openSession() {
		return this.getSessionFactory().openSession();
	}

	public void saveOrUpdateAll(final Collection entities) throws DataAccessException {
		executeWithNativeSession(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				checkWriteOperationAllowed(session);
				for (Iterator it = entities.iterator(); it.hasNext();) {
					session.saveOrUpdate(it.next());
				}
				return null;
			}
		});
	}
}
