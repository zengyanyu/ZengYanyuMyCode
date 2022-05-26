package com.framework.web;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Collection;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.common.util.ReflectionUtil;
import org.apache.cxf.helpers.CastUtils;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.FrameworkServlet;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年11月4日 下午4:26:09
 * @see com.framework.web.CXFServletExtend.java
 */
public class CXFServletExtend extends CXFServlet {
	private static final long serialVersionUID = 1L;

	private static final String BUS_PARAMETER = "bus";

	private boolean busCreated;
	private XmlWebApplicationContext createdContext;

	@Override
	protected void loadBus(ServletConfig servletConfig) {
		//使用spring监听器启动项目,获取ApplicationContext的方式
		//ApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(servletConfig.getServletContext());

		//使用springmvc的servlet启动项目,获取ApplicationContext的方式
		ApplicationContext wac = (ApplicationContext) servletConfig.getServletContext()
				.getAttribute(FrameworkServlet.SERVLET_CONTEXT_PREFIX + "dispatcherServlet");

		if (wac instanceof AbstractApplicationContext) {
			addListener((AbstractApplicationContext) wac);
		}

		String configLocation = servletConfig.getInitParameter("config-location");
		if (configLocation == null) {
			try {
				InputStream is = servletConfig.getServletContext().getResourceAsStream("/WEB-INF/cxf-servlet.xml");
				if (is != null && is.available() > 0) {
					is.close();
					configLocation = "/WEB-INF/cxf-servlet.xml";
				}
			} catch (Exception ex) {
			}
		}
		if (configLocation != null) {
			wac = createSpringContext(wac, servletConfig, configLocation);
		}
		if (wac != null) {
			String busParam = servletConfig.getInitParameter(BUS_PARAMETER);
			String busName = busParam == null ? "cxf" : busParam.trim();

			setBus(wac.getBean(busName, Bus.class));
		} else {
			busCreated = true;
			setBus(BusFactory.newInstance().createBus());
		}
	}

	@Override
	protected void addListener(AbstractApplicationContext wac) {
		try {
			Method m = wac.getClass().getMethod("getApplicationListeners");
			Collection<Object> c = CastUtils.cast((Collection<?>) ReflectionUtil.setAccessible(m).invoke(wac));
			c.add(this);
		} catch (Throwable t) {
		}
	}

	private ApplicationContext createSpringContext(ApplicationContext ctx, ServletConfig servletConfig,
			String location) {
		XmlWebApplicationContext ctx2 = new XmlWebApplicationContext();
		createdContext = ctx2;

		ctx2.setServletConfig(servletConfig);
		Resource r = ctx2.getResource(location);
		try {
			InputStream in = r.getInputStream();
			in.close();
		} catch (IOException e) {
			r = ctx2.getResource("classpath:" + location);
			try {
				r.getInputStream().close();
			} catch (IOException e2) {
				r = null;
			}
		}
		try {
			if (r != null) {
				location = r.getURL().toExternalForm();
			}
		} catch (IOException e) {
		}
		if (ctx != null) {
			ctx2.setParent(ctx);
			String names[] = ctx.getBeanNamesForType(Bus.class);
			if (names == null || names.length == 0) {
				ctx2.setConfigLocations(new String[] { "classpath:/META-INF/cxf/cxf.xml", location });
			} else {
				ctx2.setConfigLocations(new String[] { location });
			}
		} else {
			ctx2.setConfigLocations(new String[] { "classpath:/META-INF/cxf/cxf.xml", location });
			createdContext = ctx2;
		}
		ctx2.refresh();
		return ctx2;
	}

	@Override
	public void destroyBus() {
		if (busCreated) {
			getBus().shutdown(true);
			setBus(null);
		}
		if (createdContext != null) {
			createdContext.close();
		}
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		destroy();
		setBus(null);
		try {
			init(getServletConfig());
		} catch (ServletException e) {
			throw new RuntimeException("Unable to reinitialize the CXFServlet", e);
		}
	}

}
