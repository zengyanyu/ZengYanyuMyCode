package com.framework.core.jdbc.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import org.junit.Test;

import com.framework.core.jdbc.dao.IDepartmentDao;
import com.framework.core.jdbc.dao.IEmployeeDao;
import com.framework.core.jdbc.dao.impl.DepartmentDaoImpl;
import com.framework.core.jdbc.dao.impl.EmployeeDaoImpl;
import com.framework.core.jdbc.domain.Department;
import com.framework.core.jdbc.domain.Employee;
import com.framework.core.jdbc.util.JdbcUtil;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年4月25日 下午12:22:54
 */
@SuppressWarnings("all")
public class BatchDaoTest {

	private IEmployeeDao empDao = new EmployeeDaoImpl();

	private IDepartmentDao deptDao = new DepartmentDaoImpl();

	/**
	 * 保存1万条数据10秒钟
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年4月25日 下午2:23:03
	 */
	@Test
	public void testSave() {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			Employee employee = new Employee("111", "222");
			empDao.save(employee);
		}
		long end = System.currentTimeMillis();
		print((end - start) + "ms");//10848ms
	}

	@Test
	public void employeeList() throws Exception {
		Long startDate = System.currentTimeMillis();
		List<Employee> employees = empDao.getAll();
		Long endDate = System.currentTimeMillis();
		print("查询MYSQL 的 'employee' --数据信息结束...");
		print("MYSQL的查询时间为  :  ====>   " + (endDate - startDate) + " 毫秒");
		print("----------------------------------------------");
		print("'employee'--往MYSQL中封装对象--'deptartmnet表'--中封装数据信息开始...");
		long mysqlStartDate = System.currentTimeMillis();
		for (Employee employee : employees) {
			//封装数据
			Department department = new Department(employee.getUsername(), employee.getPassword());
			//保存
			deptDao.save(department);
		}
		long mysqlEndDate = System.currentTimeMillis();
		print("employee'--往MYSQL中封装对象--'department表'--中封装数据信息结束...");
		print("MYSQL封装数据用时为=====>  " + ((mysqlEndDate - mysqlStartDate) / 1000) + "  秒");
	}

	/**
	* 保存10万条数据用时7~8秒
	* 优化之后的SQL语句,批量新增数据信息
	* 案例:没有设置不自动提交事务(默认自动提交事务)的操作:10条数据用时260秒左右;
	*     设置了不自动提交事务的操作:10万条数据用时7~8秒
	* 这个方法可能导致内存溢出(请看下面的方法)
	* @author ZengYanyu
	* @Description
	* @Date 2020年4月25日 下午12:11:55
	* @throws Exception
	*/
	@Test
	public void testBatchSave() throws Exception {
		Connection conn = null;
		PreparedStatement pst = null;
		Long startDate = System.currentTimeMillis();
		List<Employee> employees = empDao.getAll();
		Long endDate = System.currentTimeMillis();
		print("MYSQL的查询时间为  :  ====>   " + (endDate - startDate) + " 毫秒");
		long begin = System.currentTimeMillis();
		conn = JdbcUtil.getConn();
		String sql = "INSERT INTO department(username,password) VALUES(?,?)";
		pst = conn.prepareStatement(sql);
		long startDate2 = System.currentTimeMillis();
		//设置为不自动提交事务
		conn.setAutoCommit(false);
		for (int i = 0; i < employees.size(); i++) {
			pst.setObject(1, employees.get(i).getUsername());
			pst.setObject(2, employees.get(i).getPassword());
			//
			pst.executeUpdate();//addBatch();
		}
		//当pst循环中使用pst.executeUpdate()方法的时候,pst,executeBatch()的方法可以不写
		//pst.executeBatch();
		//提交事务
		conn.commit();
		long end = System.currentTimeMillis();
		print(end - begin);
		//-----------------------------------------------------------
		long endDate2 = System.currentTimeMillis();
		print("批量保存时间为  =======>>  " + (endDate2 - startDate2) + "ms");
		JdbcUtil.close(conn, pst, null);
	}

	/**
	 * 不会出现内存溢出的情况(对上面的方法进行优化操作)
	 * 优化之后:保存100万条数据用时为4~6秒钟
	 * 号称是世界上最快的批量保存方法
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年5月5日 下午3:32:33
	 */
	@Test
	public void testBatchSave1() throws Exception {
		Connection conn = null;
		PreparedStatement pst = null;
		Long startDate = System.currentTimeMillis();
		List<Employee> employees = empDao.getAll();
		print(employees.size());
		Long endDate = System.currentTimeMillis();
		print("MYSQL的查询时间为  :  ====>   " + (endDate - startDate) + " 毫秒");
		print("======================================");
		long begin = System.currentTimeMillis();
		conn = JdbcUtil.getConn();
		String sql = "INSERT INTO department(username,password) VALUES(?,?)";
		pst = conn.prepareStatement(sql);
		long startDate2 = System.currentTimeMillis();
		//设置为不自动提交事务
		conn.setAutoCommit(false);
		for (int i = 0; i < employees.size(); i++) {
			pst.setObject(1, employees.get(i).getUsername());
			pst.setObject(2, employees.get(i).getPassword());
			//
			//pst.executeUpdate();//addBatch();
			//积攒SQL
			pst.addBatch();
			if ((i + 1) % 300 == 0) {//批量保存一次
				pst.executeBatch();//执行一次批量保存的数据
				pst.clearBatch();//清空积攒的SQL
			}
		}
		//当pst循环中使用pst.executeUpdate()方法的时候,pst,executeBatch()的方法可以不写
		//pst.executeBatch();
		//若总条数不是批量数值的整数倍，则还需要再额外的执行一次
		if (100000 % 300 != 0) {
			pst.executeBatch();//执行一次批量保存的数据
			pst.clearBatch();//清空积攒的SQL
		}
		//提交事务
		conn.commit();
		long end = System.currentTimeMillis();
		print(end - begin);
		//-----------------------------------------------------------
		long endDate2 = System.currentTimeMillis();
		print("批量保存时间为  =======>>  " + (endDate2 - startDate2) + "ms");
		JdbcUtil.close(conn, pst, null);
	}

	public static void print(Object obj) {
		System.out.println(obj);
	}

}
