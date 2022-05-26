package com.jpa.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2021年7月2日 下午10:30:55
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface JpaDao<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

	List<T> findAll(Map<String, Object> params, String... sorts);

	List<T> findAll(Map<String, Object> params);

	T findFirst(Map<String, Object> params, String... sorts);

	T findFirst(Map<String, Object> params, LockModeType lockModeType, String... sorts);

	List<T> findAllWithoutAssociation(Map<String, Object> params, String... sorts);

	List<T> findAllWithoutAssociation(Map<String, Object> params);

	Page<T> findAll(Page<T> page, Map<String, Object> params);

	Page<T> findAllWithoutAssociation(Page<T> page, Map<String, Object> params);

	List<T> findAll(List<String> columns, Map<String, Object> params, String... sorts);

	<S> List<S> findAll(Class<S> resultType, Map<String, Object> params, String... sorts);

	<S> List<S> findAll(List<String> columns, Class<S> resultType, Map<String, Object> params, String... sorts);

	List<Map<String, Object>> findAll(Map<String, String> mapper, Map<String, Object> params, String... sorts);

	Page<T> findAll(Page<T> page, List<String> columns, Map<String, Object> params);

	<S> Page<S> findAll(Page<S> page, Class<S> resultType, Map<String, Object> params);

	<S> Page<S> findAll(Page<S> page, List<String> columns, Class<S> resultType, Map<String, Object> params);

	Page<Map<String, Object>> findAll(Page<Map<String, Object>> page, Map<String, Object> mapper,
			Map<String, Object> params);

	List<T> findAll();

	List<T> findAllWillfully();

	T getById(ID id);

	T findById(ID id, LockModeType lockModeType);

	T findByIdWithoutAssociation(ID id);

	T findOne(Map<String, Object> params);

	T findOne(Map<String, Object> params, LockModeType lockModeType);

	T findOne(Map<String, Object> params, Map<String, String> mapper, LockModeType lockModeType);

	T findOne(Map<String, Object> params, Map<String, String> mapper, String... sorts);

	T findOne(Map<String, Object> params, List<String> fields, String... sorts);

	T findOneWithoutAssociation(Map<String, Object> params);

	<S> S findOneColumn(String column, Class<S> resultType, Map<String, Object> params);

	<S> List<S> findOneColumnList(String column, Class<S> resultType, Map<String, Object> params, String... sorts);

	long count(Map<String, Object> params);

	T updatePartially(T entity);

	List<T> saveInBatch(Iterable<T> entities);

	List<T> saveInBatch(Iterable<T> entities, int batchSize);

	<S> List<S> executeQuery(String hql, Object... params);

	<S> List<S> executeQuery(String hql, Class<S> resultType, Object... params);

	List<Object[]> executeQueryArray(String hql, Object... params);

	<S> S executeQueryOne(String jpql, Object... params);

	<S> S executeQueryOne(String jpql, Map<String, Object> params);

	int executeUpdate(String jpql, Object... params);

	int executeUpdate(String jpql, Map<String, Object> params);

	List<T> executeSqlQuery(String sql, Object... params);

	List<Object[]> executeSqlQueryArray(String sql, Object... args);

	List<Object[]> executeSqlQueryArray(String sql, Map<String, Object> params);

	<S> List<S> executeSqlQuery(Class<S> resultType, String sql, Object... params);

	List<T> executeSqlQueryWithAssociation(String sql, Object... params);

	List<T> executeSqlQuery(List<String> columns, String sql, Object... params);

	<S> List<S> executeSqlQuery(List<String> columns, Class<S> resultType, String sql, Object... params);

	Page<T> executeSqlQuery(Page<T> page, String sql, Object... params);

	Page<T> executeSqlQuery(Page<T> page, List<String> columns, String sql, Object... params);

	<S> Page<S> executeSqlQuery(Page<S> page, List<String> columns, Class<S> resultType, String sql, Object... params);

	List<T> executeSqlQueryWithAssociation(String sql, Map<String, Object> params);

	List<T> executeSqlQuery(String sql, Map<String, Object> params);

	List<T> executeSqlQuery(List<String> columns, String sql, Map<String, Object> params);

	<S> List<S> executeSqlQuery(List<String> columns, Class<S> resultType, String sql, Map<String, Object> params);

	Page<T> executeSqlQuery(Page<T> page, String sql, Map<String, Object> params);

	Page<T> executeSqlQuery(Page<T> page, List<String> columns, String sql, Map<String, Object> params);

	<S> Page<S> executeSqlQuery(Page<S> page, List<String> columns, Class<S> resultType, String sql,
			Map<String, Object> params);

	void deleteById(Iterable<ID> ids);

	int executeSqlUpdate(String sql, Object... params);

	boolean exists(ID id);

	boolean exists(Map<String, Object> params);

	boolean isNew(T entity);

	ID getEntityId(T entity);

	Class<?> getEntityClass();

	EntityManager getEm();

	String getIdName(Class<?> entityClass);

	String getIdName(String entityName);

	ID forceGetEntityId(Object entity);
}
