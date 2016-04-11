package com.entgroup.test.quartzdemo;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws SchedulerException {
		run();
	}

	private static void run() throws SchedulerException {
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		// define the job and tie it to our MyJob class
		JobDetail job = JobBuilder.newJob(MyJob.class)
				.withIdentity("job1", "group1").build();

		// Trigger the job to run now, and then repeat every 40 seconds
		Trigger trigger = TriggerBuilder
				.newTrigger()
				.withIdentity("trigger1", "group1")
				.startNow()
				.withSchedule(
						SimpleScheduleBuilder.simpleSchedule()
								.withIntervalInSeconds(40).repeatForever())
				.build();

		// Tell quartz to schedule the job using our trigger
		scheduler.scheduleJob(job, trigger);
		// and start it off
		scheduler.start();
	}
}
