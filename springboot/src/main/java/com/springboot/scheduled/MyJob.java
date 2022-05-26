package com.springboot.scheduled;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.springboot.constant.DateFormatConstant;

/**
 * 调度任务
 * @author ZengYanyu
 * @Description
 * @Date 2020年5月29日 下午9:58:32
 */
public class MyJob implements Job {

	private final static Log LOG = LogFactory.getLog(MyJob.class);

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// context.getTrigger().getName()
		String format = DateFormatConstant.DATE_FORMAT2.format(new Date());
		LOG.info("MyJob scheduled : ====>>" + format);
		LOG.info("====>" + format);
	}

}
