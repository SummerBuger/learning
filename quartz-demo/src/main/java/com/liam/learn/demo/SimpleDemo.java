package com.liam.learn.demo;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.core.jmx.JobDataMapSupport;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chaochun.ccc on 2017-07-09.
 */
public class SimpleDemo {
  private static final Logger LOGGER = LoggerFactory.getLogger(SimpleDemo.class);

  public static void main(String[] args) {

    try {
      SchedulerFactory schedulerFactory = new StdSchedulerFactory();
      Scheduler scheduler;
      scheduler = schedulerFactory.getScheduler();
      scheduler.start();
      Map<String, Object> serviceMap = new HashMap<String, Object>();
      serviceMap.put("simpleService", new SimpleService());
      JobDataMap jobDataMap = JobDataMapSupport.newJobDataMap(serviceMap);
      JobDetail jobDetail = JobBuilder.newJob(SimpleJob.class)
          .withIdentity("simple job", "learn-demo").usingJobData(jobDataMap).build();
      SimpleTrigger trigger =
          TriggerBuilder.newTrigger().withIdentity("simple job", "learn-demo").startNow()
              .withSchedule(
                  SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).repeatForever())
              .build();
      scheduler.scheduleJob(jobDetail, trigger);
    } catch (SchedulerException e) {
      e.printStackTrace();
    }
  }
}
