package com.framework.core.jdbc.dao.impl;

import java.sql.ResultSet;
import java.util.List;

import com.framework.core.jdbc.dao.IEmployeeDao;
import com.framework.core.jdbc.domain.Employee;
import com.framework.core.jdbc.handler.IResultSetHandler;
import com.framework.core.jdbc.handler.impl.BeanHandler;
import com.framework.core.jdbc.handler.impl.BeanListHandler;
import com.framework.core.jdbc.query.EmployeeQueryObject;
import com.framework.core.jdbc.util.JdbcTemplate;
import com.framework.core.page.PageResult;

public class EmployeeDaoImpl implements IEmployeeDao {

	@Override
	public void save(Employee employee) {
		String sql = "INSERT INTO employee(username,password) VALUES(?,?)";
		JdbcTemplate.update(sql, employee.getUsername(), employee.getPassword());
	}

	@Override
	public void delete(Long id) {
		String sql = "DELETE FROM employee WHERE id = ?";
		JdbcTemplate.update(sql, id);
	}

	@Override
	public void update(Employee emp) {
		String sql = "UPDATE employee SET username = ? ,password = ? WHERE id = ?";
		JdbcTemplate.update(sql, emp.getUsername(), emp.getPassword(), emp.getId());
	}

	@Override
	public Employee get(Long id) {
		String sql = "select * from employee where id = ?";
		return JdbcTemplate.query(sql, new BeanHandler<>(Employee.class), id);
	}

	@Override
	public List<Employee> getAll() {
		String sql = "SELECT * FROM employee";
		return JdbcTemplate.query(sql, new BeanListHandler<>(Employee.class));
	}

	@Override
	public PageResult query(EmployeeQueryObject qo) {
		//分页
		String sql = "SELECT * FROM employee " + qo.getConditions() + " LIMIT ?,?";
		List<Object> params = qo.getParams();
		//设置分页查询的第一个参数
		params.add(qo.getBeginIndex());
		//设置分页查询的第二个参数
		params.add(qo.getPageSize());
		//查询结果集
		List<Employee> listData = JdbcTemplate.query(sql, new BeanListHandler<>(Employee.class), params.toArray());
		//查询总条数
		sql = "SELECT COUNT(id) FROM employee " + qo.getConditions();
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
		String sql = "SELECT COUNT(id) FROM employee";
		return JdbcTemplate.query(sql, new IResultSetHandler<Long>() {

			@Override
			public Long handle(ResultSet rs) throws Exception {
				rs.next();
				return rs.getLong(1);
			}
		});
	}

}
