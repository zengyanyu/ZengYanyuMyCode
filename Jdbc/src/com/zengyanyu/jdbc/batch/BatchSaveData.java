package com.zengyanyu.jdbc.batch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.zengyanyu.jdbc.util.JdbcUtil;

@SuppressWarnings("all")
public class BatchSaveData {

	public static void main(String[] args) throws SQLException {
		test1();

		//testBatch();
	}

	private static void test1() throws SQLException {
		Connection conn = JdbcUtil.getConn();
		long begin = System.currentTimeMillis();
		for (int i = 0; i < 3000; i++) {
			String sql = "INSERT INTO department(username,password) VALUES(?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "AA");
			ps.setString(2, i + "");
			ps.executeUpdate();
		}
		System.out.println(System.currentTimeMillis() - begin + "ms");
	}

	private static void testBatch() throws SQLException {
		Connection conn = JdbcUtil.getConn();
		long begin = System.currentTimeMillis();
		String sql = "INSERT INTO department(username,password) VALUES(?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		for (int i = 0; i < 3000; i++) {
			//设置占位符参数
			ps.setString(1, "AA");
			ps.setString(2, i + "");
			ps.addBatch();
			if (i % 200 == 0) {
				ps.executeBatch();//执行批量操作
				ps.clearBatch();//清楚缓存
				ps.clearParameters();//清除参数
			}
		}
		System.out.println(System.currentTimeMillis() - begin + "ms");
		JdbcUtil.close(conn, ps, null);
	}

}
