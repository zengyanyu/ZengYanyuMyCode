package com.jpa.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class JpaDaoImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>implements JpaDao<T, ID> {

	public JpaDaoImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
	}

	@Override
	public List<T> findAll(Map<String, Object> params, String... sorts) {

		return null;
	}

	@Override
	public List<T> findAll(Map<String, Object> params) {

		return null;
	}

	@Override
	public T findFirst(Map<String, Object> params, String... sorts) {

		return null;
	}

	@Override
	public T findFirst(Map<String, Object> params, LockModeType lockModeType, String... sorts) {

		return null;
	}

	@Override
	public List<T> findAllWithoutAssociation(Map<String, Object> params, String... sorts) {

		return null;
	}

	@Override
	public List<T> findAllWithoutAssociation(Map<String, Object> params) {

		return null;
	}

	@Override
	public Page<T> findAll(Page<T> page, Map<String, Object> params) {

		return null;
	}

	@Override
	public Page<T> findAllWithoutAssociation(Page<T> page, Map<String, Object> params) {

		return null;
	}

	@Override
	public List<T> findAll(List<String> columns, Map<String, Object> params, String... sorts) {

		return null;
	}

	@Override
	public <S> List<S> findAll(Class<S> resultType, Map<String, Object> params, String... sorts) {

		return null;
	}

	@Override
	public <S> List<S> findAll(List<String> columns, Class<S> resultType, Map<String, Object> params, String... sorts) {

		return null;
	}

	@Override
	public List<Map<String, Object>> findAll(Map<String, String> mapper, Map<String, Object> params, String... sorts) {

		return null;
	}

	@Override
	public Page<T> findAll(Page<T> page, List<String> columns, Map<String, Object> params) {

		return null;
	}

	@Override
	public <S> Page<S> findAll(Page<S> page, Class<S> resultType, Map<String, Object> params) {

		return null;
	}

	@Override
	public <S> Page<S> findAll(Page<S> page, List<String> columns, Class<S> resultType, Map<String, Object> params) {

		return null;
	}

	@Override
	public Page<Map<String, Object>> findAll(Page<Map<String, Object>> page, Map<String, Object> mapper,
			Map<String, Object> params) {

		return null;
	}

	@Override
	public List<T> findAllWillfully() {

		return null;
	}

	@Override
	public T getById(ID id) {

		return null;
	}

	@Override
	public T findById(ID id, LockModeType lockModeType) {

		return null;
	}

	@Override
	public T findByIdWithoutAssociation(ID id) {

		return null;
	}

	@Override
	public T findOne(Map<String, Object> params) {

		return null;
	}

	@Override
	public T findOne(Map<String, Object> params, LockModeType lockModeType) {

		return null;
	}

	@Override
	public T findOne(Map<String, Object> params, Map<String, String> mapper, LockModeType lockModeType) {

		return null;
	}

	@Override
	public T findOne(Map<String, Object> params, Map<String, String> mapper, String... sorts) {

		return null;
	}

	@Override
	public T findOne(Map<String, Object> params, List<String> fields, String... sorts) {

		return null;
	}

	@Override
	public T findOneWithoutAssociation(Map<String, Object> params) {

		return null;
	}

	@Override
	public <S> S findOneColumn(String column, Class<S> resultType, Map<String, Object> params) {

		return null;
	}

	@Override
	public <S> List<S> findOneColumnList(String column, Class<S> resultType, Map<String, Object> params,
			String... sorts) {

		return null;
	}

	@Override
	public long count(Map<String, Object> params) {

		return 0;
	}

	@Override
	public T updatePartially(T entity) {

		return null;
	}

	@Override
	public List<T> saveInBatch(Iterable<T> entities) {

		return null;
	}

	@Override
	public List<T> saveInBatch(Iterable<T> entities, int batchSize) {

		return null;
	}

	@Override
	public <S> List<S> executeQuery(String hql, Object... params) {

		return null;
	}

	@Override
	public <S> List<S> executeQuery(String hql, Class<S> resultType, Object... params) {

		return null;
	}

	@Override
	public List<Object[]> executeQueryArray(String hql, Object... params) {

		return null;
	}

	@Override
	public <S> S executeQueryOne(String jpql, Object... params) {

		return null;
	}

	@Override
	public <S> S executeQueryOne(String jpql, Map<String, Object> params) {

		return null;
	}

	@Override
	public int executeUpdate(String jpql, Object... params) {

		return 0;
	}

	@Override
	public int executeUpdate(String jpql, Map<String, Object> params) {

		return 0;
	}

	@Override
	public List<T> executeSqlQuery(String sql, Object... params) {

		return null;
	}

	@Override
	public List<Object[]> executeSqlQueryArray(String sql, Object... args) {

		return null;
	}

	@Override
	public List<Object[]> executeSqlQueryArray(String sql, Map<String, Object> params) {

		return null;
	}

	@Override
	public <S> List<S> executeSqlQuery(Class<S> resultType, String sql, Object... params) {

		return null;
	}

	@Override
	public List<T> executeSqlQueryWithAssociation(String sql, Object... params) {

		return null;
	}

	@Override
	public List<T> executeSqlQuery(List<String> columns, String sql, Object... params) {

		return null;
	}

	@Override
	public <S> List<S> executeSqlQuery(List<String> columns, Class<S> resultType, String sql, Object... params) {

		return null;
	}

	@Override
	public Page<T> executeSqlQuery(Page<T> page, String sql, Object... params) {

		return null;
	}

	@Override
	public Page<T> executeSqlQuery(Page<T> page, List<String> columns, String sql, Object... params) {

		return null;
	}

	@Override
	public <S> Page<S> executeSqlQuery(Page<S> page, List<String> columns, Class<S> resultType, String sql,
			Object... params) {

		return null;
	}

	@Override
	public List<T> executeSqlQueryWithAssociation(String sql, Map<String, Object> params) {

		return null;
	}

	@Override
	public List<T> executeSqlQuery(String sql, Map<String, Object> params) {

		return null;
	}

	@Override
	public List<T> executeSqlQuery(List<String> columns, String sql, Map<String, Object> params) {

		return null;
	}

	@Override
	public <S> List<S> executeSqlQuery(List<String> columns, Class<S> resultType, String sql,
			Map<String, Object> params) {

		return null;
	}

	@Override
	public Page<T> executeSqlQuery(Page<T> page, String sql, Map<String, Object> params) {

		return null;
	}

	@Override
	public Page<T> executeSqlQuery(Page<T> page, List<String> columns, String sql, Map<String, Object> params) {

		return null;
	}

	@Override
	public <S> Page<S> executeSqlQuery(Page<S> page, List<String> columns, Class<S> resultType, String sql,
			Map<String, Object> params) {

		return null;
	}

	@Override
	public void deleteById(Iterable<ID> ids) {

	}

	@Override
	public int executeSqlUpdate(String sql, Object... params) {

		return 0;
	}

	@Override
	public boolean exists(Map<String, Object> params) {

		return false;
	}

	@Override
	public boolean isNew(T entity) {

		return false;
	}

	@Override
	public ID getEntityId(T entity) {

		return null;
	}

	@Override
	public Class<?> getEntityClass() {

		return null;
	}

	@Override
	public EntityManager getEm() {

		return null;
	}

	@Override
	public String getIdName(Class<?> entityClass) {

		return null;
	}

	@Override
	public String getIdName(String entityName) {

		return null;
	}

	@Override
	public ID forceGetEntityId(Object entity) {

		return null;
	}

}
