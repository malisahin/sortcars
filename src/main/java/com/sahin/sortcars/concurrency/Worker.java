package main.java.com.sahin.sortcars.concurrency;

/**
 * @author malisahin
 * @since 24-May-21
 */

public class Worker extends Thread {

  private boolean isStopped = false;
  private Thread thread = null;
  private final Queue tasks;

  public Worker(Queue tasks, String name) {
    super(name);
    this.tasks = tasks;
  }


  @Override
  public void run() {
    this.thread = Thread.currentThread();
    while (!isStopped()) {
      try {
        final Runnable runnable = tasks.dequeue();

        if (runnable != null)
          runnable.run();
      } catch (Exception e) {
        //e.printStackTrace();
        System.out.println(e.getMessage());
      }
    }

  }

  public synchronized void doStop() {
    isStopped = true;
    this.thread.interrupt();
  }

  public synchronized boolean isStopped() {
    return isStopped;
  }
}
