package com.liam.learn.demo;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by chaochun.ccc on 2017-07-09.
 */
public class SimpleJob implements Job {

  private static final Logger LOGGER = LoggerFactory.getLogger(SimpleJob.class);

  public SimpleJob() {}

  public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    LOGGER.info("the simple job has been executed");
    try {
      JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
      SimpleService simpleService = (SimpleService) jobDataMap.get("simpleService");
      LOGGER.warn("simpleServie.test: {}", simpleService.test());
      TimeUnit.SECONDS.sleep(4);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    LOGGER.info("the simple job finished");
  }
}
