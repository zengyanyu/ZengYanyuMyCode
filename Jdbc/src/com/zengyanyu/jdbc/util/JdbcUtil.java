package com.zengyanyu.jdbc.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;

/**
 * Java数据库连接(JDBC)工具类
 * @author ZengYanyu
 * @Description
 * @Date 2020年4月25日 上午10:53:42
 */
public class JdbcUtil {

	//constructor private
	private JdbcUtil() {
	}

	private final static Log LOG = LogFactory.getLog(JdbcUtil.class);

	private static DataSource dataSource = null;

	private static Properties porperties = new Properties();

	static {
		InputStream inStream = Thread.currentThread().getContextClassLoader()//
				.getResourceAsStream("db.properties");
		try {
			porperties.load(inStream);
			//使用德鲁伊连接池
			dataSource = DruidDataSourceFactory.createDataSource(porperties);
			LOG.info("JdbcUtil static class getter DruidDataSource[druid connection pool]"//
					+ " info successed,connection info [" + dataSource + "]");
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("JdbcUtil static class getter druid connection pool info error : ", e);
		}
	}

	/**
	 * 获取数据库连接
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年4月25日 上午10:53:49
	 * @return
	 */
	public static Connection getConn() {
		try {
			Connection connection = dataSource.getConnection();
			LOG.info("JdbcUtil getConn method getter connection info successed,"//
					+ " connection info [" + connection + "]");
			return connection;
		} catch (Exception e) {
			LOG.error("JdbcUtil getConn method getter connection error : ", e);
		}
		return null;
	}

	/**
	 * 关闭资源
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年4月25日 上午10:54:08
	 * @param conn  连接对象
	 * @param st    预编译语句对象(使用的是父类接口,可以传递子类对象PreparedStatement[面向接口编程])
	 * @param rs    结果集对象(增删改是没有结果集的,JdbcTemplate中的update方法[ 传递结果集对象的时候,传递的是null ])
	 */
	public static void close(Connection conn, Statement st, ResultSet rs) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			LOG.error("JdbcUtil close method close Connection sources error : ", e);
		} finally {
			try {
				if (st != null) {
					st.close();
				}
			} catch (Exception e) {
				LOG.error("JdbcUtil close method close Statement sources error : ", e);
			}

			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				LOG.error("JdbcUtil close method close ResultSet sources error : ", e);
			}
		}
		LOG.info("JdbcUtil close method close java.sql.Connection and java.sql.Statement "//
				+ "and java.sql.ResultSet resources successed");
	}

}
