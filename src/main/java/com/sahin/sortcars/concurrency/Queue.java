package main.java.com.sahin.sortcars.concurrency;

import java.util.LinkedList;

/**
 * @author malisahin
 * @since 24-May-21
 */

public class Queue {

  private final LinkedList<Runnable> tasks = new LinkedList<>();

  public Queue() {
  }

  public synchronized void enqueue(Runnable runnable) {
    tasks.addLast(runnable);

    this.notifyAll();
  }

  public synchronized Runnable dequeue() {
    while (tasks.isEmpty()) {
      try {
        wait();
      } catch (InterruptedException e) {
        return null;
      }
    }
    return tasks.poll();
  }

  public synchronized boolean isEmpty() {
    return tasks.isEmpty();
  }
}
