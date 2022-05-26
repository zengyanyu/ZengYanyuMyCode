package com.springboot.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年6月14日 下午1:40:37
 * @see com.wisdom.springboot.web.filter.MyFilter.java
 */
//对所有的资源进行拦截
//@WebFilter(urlPatterns = "/*")
public class TestFilter implements Filter {

	/**
	 * 销毁
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年8月7日 下午7:43:55
	 */
	@Override
	public void destroy() {
		System.out.println("销毁顾虑器...");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//类型转换
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		System.out.println("testFilter");
		//放行所有请求和响应(放行转换之后的类型)
		chain.doFilter(req, resp);
	}

	/**
	 * 初始化
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年8月7日 下午7:44:02
	 * @param config
	 */
	@Override
	public void init(FilterConfig config) throws ServletException {
		System.out.println("初始化过滤器....");
	}

}
