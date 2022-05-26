package com.framework.core.page;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 分页结果集对象
 * @author ZengYanyu
 * @Description
 * @Date 2020年4月19日 下午1:59:04
 * @param <T>
 */
@Getter
@Setter
@ToString
public class PageResult implements Serializable {

	private static final long serialVersionUID = -1442148055106017624L;

	//计算出来的
	private List<?> listData;//结果集合
	private Integer totalCount;//总条数

	//用户传递的
	private Integer currentPage;//当前页
	private Integer pageSize;//页面大小

	//计算出来的
	private Integer prevPage;//上一页
	private Integer nextPage;//下一页
	private Integer totalPage;//总页数

	/**
	 * 使用创建者模式,计算总页数,上一页,下一页
	 * @param listData     结果集
	 * @param totalCount   总条数
	 * @param currentPage  当前页
	 * @param pageSize     页面大小
	 */
	public PageResult(List<?> listData, Integer totalCount, Integer currentPage, Integer pageSize) {
		this.listData = listData;
		this.totalCount = totalCount;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		//总页数
		this.totalPage = this.totalCount % this.pageSize == 0 ? this.totalCount / this.pageSize
				: this.totalCount / this.pageSize + 1;
		//上一页
		this.prevPage = this.currentPage - 1 >= 1 ? this.currentPage - 1 : 1;
		//下一页
		this.nextPage = this.currentPage + 1 <= this.totalPage ? this.currentPage + 1 : this.totalPage;
	}

	/**
	 * 返回空的结果集对象信息
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年4月19日 下午2:09:37
	 * @param pageSize
	 * @return
	 */
	public static PageResult EMPTY_LIST(Integer pageSize) {
		return new PageResult(Collections.EMPTY_LIST, 0, 1, pageSize);
	}

}
