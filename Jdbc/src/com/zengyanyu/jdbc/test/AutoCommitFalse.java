package com.zengyanyu.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.junit.Test;

import com.zengyanyu.jdbc.util.JdbcUtil;

public class AutoCommitFalse {

	private String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC&rewriteBatchedStatements=true";
	private String user = "root";
	private String password = "admin";

	/**
	 * 可能导致内存溢出
	 * 保存10万条数据7~8秒钟:  时间:7458
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年4月25日 下午2:09:44
	 */
	@Test
	public void Test() {
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			String sql = "INSERT INTO employee(username,password) values(?,?)";
			pstm = conn.prepareStatement(sql);
			Long startTime = System.currentTimeMillis();
			conn.setAutoCommit(false);
			//List<Employee> employees = new ArrayList<Employee>();
			for (int i = 1; i <= 100000; i++) {
				//封装数据
				//Employee employee = employees.get(i);
				pstm.setObject(1, i);
				pstm.setObject(2, i);
				pstm.executeUpdate();
			}
			conn.commit();
			Long endTime = System.currentTimeMillis();
			System.out.println("用时：" + (endTime - startTime));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn, pstm, null);
		}
	}

	/**
	 * 不会出现内存溢出的情况
	 * 保存10万条数据用时500毫秒左右
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年5月5日 下午3:23:43
	 */
	@Test
	public void test1() {
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			String sql = "INSERT INTO employee(username,password) values(?,?)";
			pstm = conn.prepareStatement(sql);
			Long startTime = System.currentTimeMillis();
			conn.setAutoCommit(false);
			//List<Employee> employees = new ArrayList<Employee>();
			for (int i = 1; i <= 100000; i++) {
				//封装数据
				//Employee employee = employees.get(i);
				pstm.setObject(1, i);
				pstm.setObject(2, i);
				//积攒SQL
				pstm.addBatch();
				if ((i + 1) % 300 == 0) {//批量保存一次
					pstm.executeBatch();//执行一次批量保存的数据
					pstm.clearBatch();//清空积攒的SQL
				}
				//pstm.executeUpdate();
			}
			//若总条数不是批量数值的整数倍，则还需要再额外的执行一次
			if (100000 % 300 != 0) {
				pstm.executeBatch();//执行一次批量保存的数据
				pstm.clearBatch();//清空积攒的SQL
			}
			conn.commit();
			Long endTime = System.currentTimeMillis();
			System.out.println("用时：" + (endTime - startTime));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn, pstm, null);
		}
	}

}
