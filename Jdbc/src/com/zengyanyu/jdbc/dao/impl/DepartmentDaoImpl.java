package com.zengyanyu.jdbc.dao.impl;

import java.sql.ResultSet;
import java.util.List;

import com.zengyanyu.jdbc.dao.IDepartmentDao;
import com.zengyanyu.jdbc.domain.Department;
import com.zengyanyu.jdbc.handler.IResultSetHandler;
import com.zengyanyu.jdbc.handler.impl.BeanHandler;
import com.zengyanyu.jdbc.handler.impl.BeanListHandler;
import com.zengyanyu.jdbc.page.PageResult;
import com.zengyanyu.jdbc.query.DepartmentQueryObject;
import com.zengyanyu.jdbc.util.JdbcTemplate;

public class DepartmentDaoImpl implements IDepartmentDao {

	@Override
	public void save(Department department) {
		String sql = "INSERT INTO department(username,password) VALUES(?,?)";
		JdbcTemplate.update(sql, department.getUsername(), department.getPassword());
	}

	@Override
	public void delete(Long id) {
		String sql = "DELETE FROM department WHERE id = ?";
		JdbcTemplate.update(sql, id);
	}

	@Override
	public void update(Department dept) {
		String sql = "UPDATE department SET username = ? ,password = ? WHERE id = ?";
		JdbcTemplate.update(sql, dept.getUsername(), dept.getPassword(), dept.getId());
	}

	@Override
	public Department get(Long id) {
		String sql = "select * from department where id = ?";
		return JdbcTemplate.query(sql, new BeanHandler<>(Department.class), id);
	}

	@Override
	public List<Department> getAll() {
		String sql = "SELECT * FROM department";
		return JdbcTemplate.query(sql, new BeanListHandler<>(Department.class));
	}

	@Override
	public PageResult query(DepartmentQueryObject qo) {
		//分页
		String sql = "SELECT * FROM department " + qo.getConditions() + " LIMIT ?,?";
		List<Object> params = qo.getParams();
		//设置分页查询的第一个参数
		params.add(qo.getBeginIndex());
		//设置分页查询的第二个参数
		params.add(qo.getPageSize());
		//查询结果集
		List<Department> listData = JdbcTemplate.query(sql, new BeanListHandler<>(Department.class), params.toArray());
		//查询总条数
		sql = "SELECT COUNT(id) FROM department " + qo.getConditions();
		Long totalCountLong = JdbcTemplate.query(sql, new IResultSetHandler<Long>() {

			@Override
			public Long handle(ResultSet rs) throws Exception {
				rs.next();
				return rs.getLong(1);
			}
		}, params.toArray());
		int totalCount = totalCountLong.intValue();
		return new PageResult(listData, totalCount, qo.getCurrentPage(), qo.getPageSize());
	}

	@Override
	public Long getTotalCount() {
		String sql = "SELECT COUNT(id) FROM DEPARTMENT";
		return JdbcTemplate.query(sql, new IResultSetHandler<Long>() {

			@Override
			public Long handle(ResultSet rs) throws Exception {
				rs.next();
				return rs.getLong(1);
			}
		});
	}

}