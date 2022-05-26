package com.framework.core.jdbc.query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年4月25日 上午10:52:14
 */
@Setter
@Getter
@ToString
@SuppressWarnings("serial")
public abstract class JdbcQueryObject implements Serializable {

	/**
	 * 把分页的数据封装到查询对象中;
	 */
	private Integer currentPage = 1;//当前页
	private Integer pageSize = 5;//页面大小

	//MYSQL分页查询的第一个参数
	public Integer getBeginIndex() {
		return (this.currentPage - 1) * this.pageSize;
	}

	/**
	 * 条件
	 */
	private List<String> conditions = new ArrayList<>();
	/**
	 * 参数
	 */
	private List<Object> params = new ArrayList<>();

	/**
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年4月25日 上午10:51:03
	 * @return
	 */
	public String getConditions() {
		conditions.clear();
		params.clear();
		//调用方法;
		customzied();

		StringBuilder sb = new StringBuilder();
		if (conditions.size() > 0) {
			sb.append(" WHERE ");
			sb.append(StringUtils.join(conditions, " AND "));
		}
		return sb.toString();
	}

	/**
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年4月25日 上午10:50:57
	 * @param condition
	 * @param param
	 */
	protected void add(String condition, Object... param) {
		conditions.add(condition);
		params.addAll(Arrays.asList(param));
	}

	/**
	 * 定制的方法,通用用于子类覆盖
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年4月25日 上午10:50:24
	 */
	protected void customzied() {
	}
}
