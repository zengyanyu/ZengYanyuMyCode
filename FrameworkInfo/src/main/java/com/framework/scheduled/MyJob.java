package com.framework.scheduled;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.framework.constant.DateFormatConstant;

/**
 * 调度任务
 * @author ZengYanyu
 * @Description
 * @Date 2020年5月29日 下午9:58:32
 */
public class MyJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// context.getTrigger().getName()
		String format = DateFormatConstant.DATE_FORMAT.format(new Date());
		System.out.println(/*context.getTrigger().getName() +*/ "====>" + format);
	}

}
