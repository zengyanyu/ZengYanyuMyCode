package com.framework.core.jdbc.handler;

import java.sql.ResultSet;

/**
 * 结果集处理器通用接口
 * @author ZengYanyu
 * @Description
 * @Date 2020年4月25日 上午11:14:29
 * @param <T>
 */
public interface IResultSetHandler<T> {

	/**
	 * 处理结果集(只针对查询方法处理,增/删/改是没有结果集的)
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年4月25日 上午11:14:48
	 * @param rs  结果集
	 * @return
	 * @throws Exception
	 */
	T handle(ResultSet rs) throws Exception;
}
