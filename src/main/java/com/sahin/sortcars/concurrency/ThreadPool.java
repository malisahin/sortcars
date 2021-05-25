package main.java.com.sahin.sortcars.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author malisahin
 * @since 24-May-21
 */

public class ThreadPool {

  public static final String THREAD_PREFIX = "Worker#";
  private final Queue tasks  = new Queue();
  private int numberOfThreads;
  private List<Worker> workerList;

  public ThreadPool(int numberOfThreads) {
    this.numberOfThreads = numberOfThreads;
    workerList = new ArrayList<>(numberOfThreads);
    startAllThreads();

  }

  private void startAllThreads() {

    for (int i = 0; i < numberOfThreads; i++) {
      final String threadName = THREAD_PREFIX + i;
      Worker worker = new Worker(tasks, threadName);
      workerList.add(worker);
      worker.start();
    }
  }

  public void submitTask(Runnable runnable) {
    tasks.enqueue(runnable);
  }

  public synchronized void stop() {
    for (Worker worker : workerList) {
      worker.doStop();
    }
  }

  public synchronized void waitUntilAllTasksFinished() {
    while (true) {
      try {
        Thread.sleep(1);
        final boolean hasActive = Thread.getAllStackTraces().keySet()
            .stream()
            .filter(thread -> thread.getName().startsWith(THREAD_PREFIX))
            .anyMatch(thread -> !thread.getState().equals(Thread.State.WAITING));

        if (this.tasks.isEmpty() && !hasActive) {
          break;
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }



}
