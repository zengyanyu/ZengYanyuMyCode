package com.sso.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;

import com.sso.util.SSOClientUtil;

public class LoginFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void doFilter(ServletRequest req, ServletResponse resp, //
			FilterChain chain) throws IOException, ServletException {
		//类型转换
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		//判断局部回话,看是否有登录状态
		Object isLogin = request.getSession().getAttribute("isLogin");
		if (isLogin != null && (Boolean) isLogin) {
			//直接放行
			chain.doFilter(request, response);
			return;
		}

		//========================
		//判断参数是否有token,
		Object token = request.getParameter("token");
		if (!StringUtils.isEmpty(token)) {
			//如果有,则进行校验操作
			boolean result = SSOClientUtil.verify((String) token, //
					SSOClientUtil.getClientLogOutUrl(request), request.getSession().getId());
			if (result) {
				//创建局部回话
				request.getSession().setAttribute("isLogin", true);
				//直接放行
				chain.doFilter(request, response);
				return;
			}
		}
		//========================

		//跳转到认证中心进行认证.(看是否有其他系统登录过)
		//www.server.com/checkLogin
		//通过http请求来访问认证中心
		SSOClientUtil.redirectToSSOURL(request, response);
	}

	public void destroy() {
	}

}
