package main.java.com.sahin.sortcars.concurrency;

import java.util.LinkedList;

/**
 * @author malisahin
 * @since 24-May-21
 */

public class Queue {

  private final LinkedList<Runnable> tasks = new LinkedList<>();

  public synchronized void enqueue(Runnable runnable) {
    tasks.addLast(runnable);
    notifyAll();
  }

  public synchronized Runnable dequeue() {
    Runnable runnable = null;

    while (tasks.isEmpty()) {
      try {
        wait();
      } catch (InterruptedException e) {
        return runnable;
      }
    }
    runnable = tasks.remove();
    return runnable;
  }
}
