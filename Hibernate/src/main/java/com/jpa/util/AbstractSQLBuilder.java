package com.jpa.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2021年7月3日 上午1:18:53
 */
public abstract class AbstractSQLBuilder implements SQLBuilder {

	protected boolean hasWhere;
	protected String sql;
	protected StringBuffer finalSql;
	protected StringBuffer causes;
	protected Map<String, Object> params;

	public AbstractSQLBuilder(String sql) {
		this(sql, (Map) null);
	}

	public AbstractSQLBuilder(String sql, Map<String, Object> params) {
		this.hasWhere = sql.contains("where") || sql.contains("WHERE") || sql.contains("Where");
		this.params = (params == null ? new HashMap() : params);
		this.sql = sql;
		this.finalSql = new StringBuffer(sql);
		this.causes = new StringBuffer();
	}

	public String build() {
		if (this.params.isEmpty()) {
			return this.sql.toString();
		} else {
			this.where();
			List<String> removedKeys = new ArrayList();
			Iterator var2 = this.params.keySet().iterator();

			String key;
			while (var2.hasNext()) {
				key = (String) var2.next();
				String opretor = key.substring(0, key.indexOf("_"));
				String field = key.substring(key.indexOf("_") + 1, key.length());
				Object value = this.params.get(key);
				if ("EQ".equals(opretor)) {
					this.eq(field);
				} else if ("LIKE".equals(opretor)) {
					this.like(field);
					this.params.put(key, this.likeValue(value));
				} else if ("NOTLIKE".equals(opretor)) {
					this.notLike(field);
					this.params.put(key, this.likeValue(value));
				} else if ("GT".equals(opretor)) {
					this.gt(field);
				} else if ("GE".equals(opretor)) {
					this.ge(field);
				} else if ("LT".equals(opretor)) {
					this.lt(field);
				} else if ("LE".equals(opretor)) {
					this.le(field);
				} else if ("NE".equals(opretor)) {
					this.ne(field);
				} else if ("IN".equals(opretor)) {
					this.in(field);
				} else if ("NOTIN".equals(opretor)) {
					this.notIn(field);
				} else if ("IS".equals(opretor) && value != null) {
					if ("NULL".equals(value.toString())) {
						this.isNull(field);
					} else if ("NOTNULL".equals(value.toString())) {
						this.notNull(field);
					} else if ("TRUE".equals(value.toString())) {
						this.isTrue(field);
					} else if ("FALSE".equals(value.toString())) {
						this.isFalse(field);
					}

					removedKeys.add(key);
				}
			}

			var2 = removedKeys.iterator();

			while (var2.hasNext()) {
				key = (String) var2.next();
				this.params.remove(key);
			}

			String causesStr = this.causes.toString();
			if (!this.hasWhere) {
				causesStr = causesStr.trim();
				causesStr = " " + causesStr.substring(3, causesStr.length());
			}

			this.finalSql.append(causesStr);
			return this.finalSql.toString();
		}
	}

	public String likeValue(Object y) {
		if (y instanceof String) {
			if (((String) y).indexOf("%") == -1) {
				y = "%" + y + "%";
			}

			return (String) y;
		} else {
			throw new IllegalArgumentException("like value is not string");
		}
	}

	private SQLBuilder where() {
		if (this.hasWhere) {
			return this;
		} else {
			this.finalSql.append(" where");
			return this;
		}
	}

	public SQLBuilder eq(String field) {
		this.causes.append(" and " + field + " = ?");
		return this;
	}

	public SQLBuilder ne(String field) {
		this.causes.append(" and " + field + " <> ?");
		return this;
	}

	public SQLBuilder like(String field) {
		this.causes.append(" and " + field + " like ?");
		return this;
	}

	public SQLBuilder notLike(String field) {
		this.causes.append(" and " + field + " not like ?");
		return this;
	}

	public SQLBuilder lt(String field) {
		this.causes.append(" and " + field + " < ?");
		return this;
	}

	public SQLBuilder le(String field) {
		this.causes.append(" and " + field + " <= ?");
		return this;
	}

	public SQLBuilder gt(String field) {
		this.causes.append(" and " + field + " > ?");
		return this;
	}

	public SQLBuilder ge(String field) {
		this.causes.append(" and " + field + " >= ?");
		return this;
	}

	public SQLBuilder in(String field) {
		this.causes.append(" and " + field + " in ?");
		return this;
	}

	public SQLBuilder notIn(String field) {
		this.causes.append(" and " + field + " in ?");
		return this;
	}

	public SQLBuilder isNull(String field) {
		this.causes.append(" and " + field + " is null");
		return this;
	}

	public SQLBuilder notNull(String field) {
		this.causes.append(" and " + field + " is not null");
		return this;
	}

	public SQLBuilder isTrue(String field) {
		this.causes.append(" and " + field + " is true");
		return this;
	}

	public SQLBuilder isFalse(String field) {
		this.causes.append(" and " + field + " is false");
		return this;
	}
	
}
