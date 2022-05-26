package com.framework.core.jdbc.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.framework.core.jdbc.handler.IResultSetHandler;

/**
 * Java数据库连接(JDBC)模板对象
 * @author ZengYanyu
 * @Description
 * @Date 2020年4月25日 上午10:42:47
 */
public class JdbcTemplate {

	private final static Log LOG = LogFactory.getLog(JdbcTemplate.class);

	/**
	 * DML操作模板
	 * 增/删/改通用的方法
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年4月25日 上午10:42:51
	 * @param sql      SQL语句
	 * @param params   SQL对应的参数
	 */
	public static void update(String sql, Object... params) {
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = JdbcUtil.getConn();
			pstm = conn.prepareStatement(sql);
			/**
			 * 自动提交事务的操作默认值为true,使用自动提交事务的操作,严重影响性能问题
			 * 所有需要把自动提交事务设置false,然后在PreparedStatement语句执行
			 * 完之后,再设置conn中的提交事务操作conn.commit();
			 */
			//设置为不自动提交事务
			conn.setAutoCommit(false);
			for (int i = 0; i < params.length; i++) {
				pstm.setObject(i + 1, params[i]);
			}
			pstm.executeUpdate();
			//提交事务
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("jdbcTemplate update method execute error : ", e);
		} finally {
			JdbcUtil.close(conn, pstm, null);
		}
	}

	//优化(实现自动关闭资源)
	public static void update1(String sql, Object... params) {
		try (Connection conn = JdbcUtil.getConn(); //
				PreparedStatement pstm = conn.prepareStatement(sql)) {
			LOG.info("Implements AutoCloseable resources method");
			//设置为不自动提交事务
			conn.setAutoCommit(false);
			for (int i = 0; i < params.length; i++) {
				pstm.setObject(i + 1, params[i]);
			}
			pstm.executeUpdate();
			//提交事务
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("jdbcTemplate update method execute error : ", e);
		}
	}

	/**
	 * DQL操作模板
	 * 查询通用的方法
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年4月25日 上午10:42:57
	 * @param sql     SQL语句
	 * @param rsh     结果集处理器
	 * @param params  SQL对应的参数
	 * @return
	 */
	public static <T> T query(String sql, IResultSetHandler<T> rsh, Object... params) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConn();
			pstm = conn.prepareStatement(sql);
			//设置为不自动提交事务
			conn.setAutoCommit(false);
			for (int i = 0; i < params.length; i++) {
				pstm.setObject(i + 1, params[i]);
			}
			rs = pstm.executeQuery();
			//提交事务
			conn.commit();
			return rsh.handle(rs);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("jdbcTemplate query method execute error : ", e);
			return null;
		} finally {
			JdbcUtil.close(conn, pstm, rs);
		}
	}

	//优化(实现自动关闭资源)
	public static <T> T query1(String sql, IResultSetHandler<T> rsh, Object... params) {
		try (Connection conn = JdbcUtil.getConn(); //
				PreparedStatement pstm = conn.prepareStatement(sql)) {
			LOG.info("Implements AutoCloseable resources method");
			//设置为不自动提交事务
			conn.setAutoCommit(false);
			for (int i = 0; i < params.length; i++) {
				pstm.setObject(i + 1, params[i]);
			}
			try (ResultSet rs = pstm.executeQuery()) {
				//提交事务
				conn.commit();
				return rsh.handle(rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
