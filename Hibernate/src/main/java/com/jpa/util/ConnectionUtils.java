package com.jpa.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.jpa.context.IeepApplicationContext;
import com.zaxxer.hikari.HikariDataSource;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2021年7月3日 上午1:25:04
 */
public class ConnectionUtils {

	public static Connection getConnection() {
		try {
			return getDataSource().getConnection();
		} catch (SQLException var1) {
			var1.printStackTrace();
			return null;
		}
	}

	public static Connection getConnection(DataSource dataSource) {
		try {
			return dataSource.getConnection();
		} catch (SQLException var2) {
			var2.printStackTrace();
			return null;
		}
	}

	public static DataSource getDataSource() {
		DataSource dataSource = null;

		try {
			dataSource = (DataSource) IeepApplicationContext.getBean(HikariDataSource.class);
		} catch (Exception var2) {
			;
		}

		if (dataSource == null) {
			dataSource = (DataSource) IeepApplicationContext.getBean(DataSource.class);
		}

		return dataSource;
	}

	public static String getDatabaseProductName() {
		Connection connection = null;

		try {
			connection = getConnection();
			String name = connection.getMetaData().getDatabaseProductName();
			String var2 = name;
			return var2;
		} catch (SQLException var6) {
			var6.printStackTrace();
		} finally {
			close(connection);
		}

		return null;
	}

	public static void close(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException var2) {
				var2.printStackTrace();
			}
		}

	}

}
