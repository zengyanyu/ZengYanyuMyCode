package com.hibernate.config;

public interface QueryConstants {
	static final String INVALID_SQL_FORMAT = "err.invalid_sql";

	static final String BLANK = " ";
	static final String COMMA = ",";
	static final String SELECT = "SELECT";
	static final String FROM = "FROM";
	static final String WHERE = "WHERE";
	static final String AND = "AND";
	static final String OR = "OR";
	static final String NOT = "NOT";
	static final String BETWEEN = "BETWEEN";
	static final String ORDER_BY = "ORDER BY";
	static final String GROUP_BY = "GROUP BY";
	static final String HAVING = "HAVING";
	static final String DISTINCT = "DISTINCT";

	static final String ASC = "ASC";
	static final String DESC = "DESC";

	static final String NULL = "NULL";
	static final String IS = "IS";
	static final String LIKE = "LIKE";
	static final String IN = "IN";
	static final String NOT_IN = "NOT IN";
	static final String INTERROGATION_POINT = "?";

	static final String LEFT_PARENTHESIS = "(";
	static final String RIGHT_PARENTHESIS = ")";

	static final String EQUALITY_SIGN = "=";
	static final String INEQUALITY_SIGN = "<>";
	static final String LESS_EQUALITY_SIGN = "<=";
	static final String LESS_SIGN = "<";
	static final String GREAT_EQUALITY_SIGN = ">=";
	static final String GREAT_SIGN = ">";

	/*
	 * 用于拆分in/not in语句，避免出现
	 * ORA-01795 maximum number of expressions in a list is 1000
	 * 在DB2,SQL Server中无此限制
	 */
	int MAX_EXPS_IN_LIST = 1000;

}
