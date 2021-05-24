package main.java.com.sahin.sortcars.concurrency;

/**
 * @author malisahin
 * @since 24-May-21
 */

public class Worker extends Thread {

  private Queue tasks;

  public Worker(Queue tasks, String name) {
    super(name);
    this.tasks = tasks;
  }


  @Override
  public void run() {
    while (true) {
      try {
        tasks.dequeue().run();
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }

  }
}
