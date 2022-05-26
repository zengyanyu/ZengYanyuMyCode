package com.zengyanyu.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;

/**
 * 普通类
 * @author ZengYanyu
 * @Description
 * @Date 2020年4月25日 下午3:42:45
 */
public class Ordinary {

	private String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC";
	private String user = "root";
	private String password = "admin";

	/**
	 * 普通插入方式,也就是连接中使用默认自动提交事务的方式
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年4月25日 下午2:34:07
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
			for (int i = 1; i <= 100000; i++) {
				pstm.setObject(1, i);
				pstm.setObject(2, i);
				pstm.executeUpdate();
			}
			Long endTime = System.currentTimeMillis();
			System.out.println("用时：" + (endTime - startTime));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
		}
	}

}
