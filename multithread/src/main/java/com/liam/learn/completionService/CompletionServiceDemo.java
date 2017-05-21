package com.liam.learn.completionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * Created by chaochun.ccc on 2017-03-12.
 */
public class CompletionServiceDemo {

  private static final Logger LOGGER = LoggerFactory.getLogger(CompletionServiceDemo.class);


  private static ThreadPoolExecutor executorService = new ThreadPoolExecutor(4, 10, 0,
      TimeUnit.SECONDS, new LinkedBlockingQueue(100), new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
          CompletionTask completionTask = (CompletionTask) r;
          LOGGER.error("the completion task has been rejected! {}", completionTask.getName());
        }
      });

  public static void main(String[] args) {
    waitForAllSubThread();
  }

  public static void waitForAllSubThread() {
    CompletionService<Integer> completionService =
            new ExecutorCompletionService<Integer>(executorService);

    int threadCount = 100;
    long begin = System.currentTimeMillis();
    for (int i = 0; i < threadCount; i++) {
      completionService.submit(new CompletionTask("test-completion-service-" + i, i));
    }
    LOGGER.info("===================== the completion task begin =====================");
    for (int i = 0; i < threadCount; i++) {
      try {
        Integer ir = completionService.take().get();
        LOGGER.info("the completion task get {}", ir);
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      }
    }
    LOGGER.info("logging the using time of completion task: {}",
            System.currentTimeMillis() - begin);
  }


  private static class CompletionTask implements Callable<Integer> {

    private String name;
    private Integer idx;

    public CompletionTask(String name, Integer idx) {
      this.name = name;
      this.idx = idx;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public Integer getIdx() {
      return idx;
    }

    public void setIdx(Integer idx) {
      this.idx = idx;
    }

    public Integer call() throws Exception {
      LOGGER.info("begin the callable service!!!! {}", name);
      int nextInt = ThreadLocalRandom.current().nextInt(5);
      if (idx == 3) {
        nextInt = 100;
      }
      TimeUnit.SECONDS.sleep(nextInt);
      LOGGER.info("the completion task {} sleep {} s", name, nextInt);
      return nextInt;
    }
  }

}


