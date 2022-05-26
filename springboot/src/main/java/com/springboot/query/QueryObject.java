package com.springboot.query;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@SuppressWarnings("serial")
public abstract class QueryObject implements Serializable {

	private Integer currentPage = 1;//当前页,默认值为1

	private Integer pageSize = 10;//页面大小,默认值为10

	//开始时间
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date beginDate;

	//结束时间
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;

	//MYSQL分页查询的第一个参数  LIMIT #{beginIndex},#{pageSize}
	public Integer getBeginIndex() {
		return (this.currentPage - 1) * this.pageSize;
	}

}
