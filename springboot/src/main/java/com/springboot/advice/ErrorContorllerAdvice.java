package com.springboot.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;

/**
 * 统一异常处理
 * @author ZengYanyu
 * @Description
 * @Date 2020年6月14日 上午12:05:26
 * @see com.springboot.advice.ErrorContorllerAdvice.java
 */
@ControllerAdvice
public class ErrorContorllerAdvice {

	private final Log LOG = LogFactory.getLog(ErrorContorllerAdvice.class);

	/**
	 * 统一异常处理
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年6月14日 上午12:13:12
	 * @param ex
	 * @param model
	 * @param handlerMethod
	 */
	@ExceptionHandler({ Exception.class })
	public void handlerException(Exception ex, Model model, HandlerMethod handlerMethod) {
		LOG.info("统一异常处理");
		LOG.info("异常信息-Exception: " + ex);
		LOG.info(ex.toString());
		if (ex instanceof ArithmeticException) {
			//java.lang.ArithmeticException: / by zero
			LOG.info("===>>>ArithmeticException");
		}
		//异常信息
		LOG.info("异常信息 : " + ex.getMessage());
		//知道那个类下面的错误
		Class<? extends Object> class1 = handlerMethod.getBean().getClass();
		LOG.info("异常出现的类 : " + class1);
		//错误出现在那个方法
		String methodName = handlerMethod.getMethod().getName();
		LOG.info("异常出现的方法 : " + methodName);
		//=============================
		model.addAttribute("errMsg", ex.getMessage());
	}

}
