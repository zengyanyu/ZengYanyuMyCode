package com.jpa.util;

import java.util.Map;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2021年7月3日 上午1:26:58
 */
public class MySQLSQLBuilder extends AbstractSQLBuilder {
	public MySQLSQLBuilder(String sql) {
		super(sql);
	}

	public MySQLSQLBuilder(String sql, Map<String, Object> params) {
		super(sql, params);
	}
}
