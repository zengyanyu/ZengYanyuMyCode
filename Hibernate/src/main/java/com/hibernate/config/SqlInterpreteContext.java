package com.hibernate.config;

import java.util.List;

import com.hibernate.exception.InvalidSQLFormatException;

/**
 * SQL解析上下文，用于生成组装的SQL语句以及记录参数列表.
 * @author ZengYanyu
 * @Description
 * @Date 2020年11月22日 下午8:22:59
 * @see com.hibernate.config.SqlInterpreteContext.java
 */
public class SqlInterpreteContext extends CommonInterpreteContext {

	/**
	 * Constructor.
	 */
	public SqlInterpreteContext() {
	}

	/**
	 * Constructor.
	 *  sql为普通语句，要求为一完整的语句，如：
	 *      select contact from ContactInfo contact
	 *      select contact from ContactInfo contact WHERE contact.id=1
	 *  以下语句不合法：
	 *      select contact from ContactInfo contact WHERE
	 *      select contact from ContactInfo contact WHERE contact.id=1 and
	 *
	 * @param sql               sql语句
	 */
	public SqlInterpreteContext(String sql) {
		if (sql == null) {
			throw new InvalidSQLFormatException(QueryConstants.INVALID_SQL_FORMAT, null, sql);
		}
		sb.append(sql);
		if (sql.length() > 0 && !WsdCriterionUtil.validate(sql)) {
			throw new InvalidSQLFormatException(QueryConstants.INVALID_SQL_FORMAT, null, sql);
		}
	}

	/**
	 * 添加上下文语句.
	 * @param obj   上下文语句
	 */
	@Override
	public void append(Object obj) {
		sb.append(obj);
	}

	/**
	 * 获取上下文语句.
	 * @return 上下文语句
	 */
	@Override
	public String getContextString() {
		return sb.toString();
	}

	protected StringBuilder getNativeString() {
		return sb;
	}

	/**
	 * 获取统计的结果数count.
	 * @param distinct  是否允许重复
	 * @return 统计结果的sql
	 */
	//    protected String getCountSqlString(boolean distinct)
	//    {
	//        StringBuilder buf = new StringBuilder("SELECT ");
	//        if (distinct) {
	//            buf.append("DISTINCT ");
	//        }
	//        buf.append("COUNT(*) ");
	//        String temp = sb.toString().toUpperCase();
	//        int begin = temp.indexOf("FROM");
	//        int end = temp.indexOf("ORDER BY");
	//        if (begin > -1 && end > begin) {
	//            buf.append(sb.substring(begin, end));
	//        }
	//        else if (begin > -1) {
	//            buf.append(sb.substring(begin));
	//        }
	//        return buf.toString();
	//    }

	/**
	 * 添加上下文参数.
	 * @param obj   上下文参数
	 */
	@Override
	public void addPrepraredValue(Object obj) {
		preparedValues.add(obj);
	}

	/**
	 * 获取上下文参数.
	 * @return 上下文参数
	 */
	@Override
	public List getPreparedValues() {
		return preparedValues;
	}
}
