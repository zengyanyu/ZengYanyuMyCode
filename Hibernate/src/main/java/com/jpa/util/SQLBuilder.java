package com.jpa.util;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2021年7月4日 下午9:09:48
 */
public interface SQLBuilder {

	SQLBuilder eq(String field);

	SQLBuilder ne(String field);

	SQLBuilder like(String field);

	SQLBuilder notLike(String field);

	SQLBuilder lt(String field);

	SQLBuilder le(String field);

	SQLBuilder gt(String field);

	SQLBuilder ge(String field);

	SQLBuilder in(String field);

	SQLBuilder notIn(String field);

	SQLBuilder isNull(String field);

	SQLBuilder notNull(String field);

	SQLBuilder isTrue(String field);

	SQLBuilder isFalse(String field);

	String build();

}
