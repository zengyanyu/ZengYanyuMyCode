package com.springboot.config;

import java.sql.Connection;
import java.sql.SQLException;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.springboot.controller.ExcelController;

public class C3P0Test {

	private static final Log LOG = LogFactory.getLog(C3P0Test.class);

	private static ComboPooledDataSource dataSource = new ComboPooledDataSource("test");

	/**
	 * 获取Connection连接
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void main(String[] args) throws Exception {
		Connection connection = getConnection();
		LOG.info(connection.toString());
	}

}
