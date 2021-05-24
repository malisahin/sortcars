package main.java.com.sahin.sortcars.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author malisahin
 * @since 24-May-21
 */

public class ThreadPool {
  private Queue tasks = new Queue();
  private int numberOfThreads;
  private List<String> threadNameList;

  public ThreadPool(int numberOfThreads) {
    this.numberOfThreads = numberOfThreads;
    startAllThreads();
  }

  private void startAllThreads() {
    threadNameList = new ArrayList<>();
    for (int i = 0; i < numberOfThreads; i++) {
      final String threadName = "Worker#" + i;
      threadNameList.add(threadName);
      Worker worker = new Worker(tasks, threadName);
      worker.start();
    }
  }

  public void submitTask(Runnable runnable) {
    tasks.enqueue(runnable);
  }
}
