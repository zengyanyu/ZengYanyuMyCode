package com.framework.scheduled;

import java.text.ParseException;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年5月29日 下午9:56:36
 */
public class MyQuartzScheduling {

	public static void main(String[] args) throws SchedulerException, ParseException {
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = schedulerFactory.getScheduler();

		JobDetail jobDetail = new JobDetail("MyJob", Scheduler.DEFAULT_GROUP, MyJob.class);
		//=======================================
		//第一种方式
		/*SimpleTrigger simpleTrigger = new SimpleTrigger("simpleTrigger", Scheduler.DEFAULT_GROUP);
		simpleTrigger.setStartTime(new Date(System.currentTimeMillis()));
		//表示5秒钟执行一次
		simpleTrigger.setRepeatInterval(5000);
		//表示重复总数(负数的时候,默认执行无数次)
		simpleTrigger.setRepeatCount(-1);
		scheduler.scheduleJob(jobDetail, simpleTrigger);*/
		//=======================================
		//第二种方式
		String cronExpression = "0/1 * * * * ?"; // 每分钟的30s起，每5s触发任务 
		CronTrigger cronTrigger = new CronTrigger("cronTrigger", Scheduler.DEFAULT_GROUP, cronExpression);
		scheduler.scheduleJob(jobDetail, cronTrigger);
		//=======================================

		scheduler.start();
	}

}
