package com.springboot.common;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;

import com.springboot.util.WsdBeanUtils;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年11月23日 下午10:09:30
 * @see com.springboot.common.WsdToolDao.java
 */
@SuppressWarnings("all")
public class WsdToolDao {

	protected WsdTemplateDao wsdTemplateDao;

	private int mod = 500;

	protected final Log logger = LogFactory.getLog(getClass());

	public static final String DEFAULT_ENTITY_ALIAS = "entity";

	public void setWsdTemplateDao(WsdTemplateDao wsdTemplateDao) {
		this.wsdTemplateDao = wsdTemplateDao;
	}

	public static String genId() {
		return UUID.randomUUID().toString();
	}

	public List<?> list(String hql, final Object[] values) {
		return this.wsdTemplateDao.find(hql, values);
	}

	public List<?> list(String hql) {
		List<?> list = this.wsdTemplateDao.find(hql, new Object[] {});
		return list;
	}

	public <T> T getById(Class<T> entityClass, final String id) {
		if (StringUtils.isBlank(id)) {
			return null;
		}
		return /*(T)*/ this.wsdTemplateDao.get(entityClass, id);
	}

	public void create(Object entity) {
		List<Object> list = new ArrayList<Object>();
		list.add(entity);
		createBatch(list);
	}

	public void createBatch(final List<?> entities) {
		Session session = getCurrentSession();
		for (int i = 0; i < entities.size(); i++) {
			try {
				genUuid(entities.get(i));
			} catch (Exception e) {
				logger.error(String.format("Entity %s lacks of property id", entities.get(i).getClass()), e);
			}
			session.save(entities.get(i));
			if ((i != 0 && i % 50 == 0) || i == entities.size() - 1) {
				session.flush();
				// session.clear();
			}
		}
	}

	public static void genUuid(Object entity) throws Exception {
		String uuid = UUID.randomUUID().toString();
		String id = (String) WsdBeanUtils.getProperty(entity, "id");
		if (null != entity && (StringUtils.isBlank(id) || "-1".equals(id))) {
			WsdBeanUtils.setProperty(entity, "id", uuid);
		}
	}

	public Session getCurrentSession() {
		return wsdTemplateDao.getCurrentSession();
	};

	public void update(Object entity) {
		List<Object> list = new ArrayList<Object>();
		list.add(entity);
		updateBatch(list);
	}

	public void updateBatch(final List<?> entities) {
		Session session = getCurrentSession();
		for (int i = 0; i < entities.size(); i++) {
			session.update(entities.get(i));
			if ((i != 0 && i % 50 == 0) || i == entities.size() - 1) {
				session.flush();
			}
		}
	}

	public void remove(Object entity) {
		List<Object> list = new ArrayList<Object>();
		list.add(entity);
		removeBatch(list);
	}

	public void removeBatch(final List<?> entities) {
		Session session = getCurrentSession();
		for (int i = 0; i < entities.size(); i++) {
			session.refresh(entities.get(i));
			session.delete(entities.get(i));
			if ((i != 0 && i % 50 == 0) || i == entities.size() - 1) {
				session.flush();
			}
		}
	}

	public Object getPropertyById(String entityClassName, String id, String propertyName) {
		String hql = String.format("select entity.%s from %s entity where entity.id='%s' ", propertyName,
				entityClassName, id);
		List<?> list = list(hql);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Deprecated
	public Session openSession() {
		return wsdTemplateDao.openSession();
	}

}
