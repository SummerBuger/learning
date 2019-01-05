package com.springdemo.liam.scheduler;

import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SimpleScheduler {

  private static final Logger LOGGER = LoggerFactory.getLogger(SimpleScheduler.class);

//  @Resource(name = "poolTaskScheduler")
//  private ThreadPoolTaskScheduler poolTaskScheduler;

  @Scheduled(cron = "0/10 * * * * ?")
//  @Scheduled(fixedRate = 10000)
  public void doTask1() throws InterruptedException {
    LOGGER.info("start do task 1 ......");
//    LOGGER
//      .info("ThreadPoolTaskScheduler pool size:{}, active count:{}", poolTaskScheduler.getPoolSize(),
//        poolTaskScheduler.getActiveCount());
    TimeUnit.SECONDS.sleep(65L);
    LOGGER.info("finish do task 1 ......");
  }

  @Scheduled(cron = "0/10 * * * * ?")
//  @Scheduled(fixedRate = 10000)
  public void doTask2() throws InterruptedException {
    LOGGER.info("start do task 2 ......");
    TimeUnit.SECONDS.sleep(2L);
    LOGGER.info("finish do task 2 ......");
  }

}
