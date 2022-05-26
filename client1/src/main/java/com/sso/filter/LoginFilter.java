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
		//����ת��
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		//�жϾֲ��ػ�,���Ƿ��е�¼״̬
		Object isLogin = request.getSession().getAttribute("isLogin");
		if (isLogin != null && (Boolean) isLogin) {
			//ֱ�ӷ���
			chain.doFilter(request, response);
			return;
		}

		//========================
		//�жϲ����Ƿ���token,
		Object token = request.getParameter("token");
		if (!StringUtils.isEmpty(token)) {
			//�����,�����У�����
			boolean result = SSOClientUtil.verify((String) token, //
					SSOClientUtil.getClientLogOutUrl(request), request.getSession().getId());
			if (result) {
				//�����ֲ��ػ�
				request.getSession().setAttribute("isLogin", true);
				//ֱ�ӷ���
				chain.doFilter(request, response);
				return;
			}
		}
		//========================

		//��ת����֤���Ľ�����֤.(���Ƿ�������ϵͳ��¼��)
		//www.server.com/checkLogin
		//ͨ��http������������֤����
		SSOClientUtil.redirectToSSOURL(request, response);
	}

	public void destroy() {
	}

}
