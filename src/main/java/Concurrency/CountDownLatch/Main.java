package Concurrency.CountDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Main {

  public static void main(String... args) throws InterruptedException {
    final int numberOfTasks = 3;
    final CountDownLatch tasksFinished = new CountDownLatch(numberOfTasks);

    for (int taskId = 1; taskId <= numberOfTasks; taskId++) {
      final int id = taskId;
      final Thread worker = new Thread(() -> processTask(id, tasksFinished), "worker-" + id);
      worker.start();
    }

    System.out.println("Main thread waiting for all tasks...");
    tasksFinished.await();
    System.out.println("All tasks finished. Main thread continues.");
  }

  private static void processTask(int id, CountDownLatch tasksFinished) {
    try {
      System.out.println("Task " + id + " started on " + Thread.currentThread().getName());
      TimeUnit.MILLISECONDS.sleep(id * 500L);
      System.out.println("Task " + id + " finished.");
    } catch (final InterruptedException e) {
      Thread.currentThread().interrupt();
      System.out.println("Task " + id + " was interrupted.");
    } finally {
      tasksFinished.countDown();
    }
  }
}

/*
 * CountDownLatch lets one thread wait until a fixed number of other tasks have finished.
 * Each worker calls countDown() in finally, so it signals completion even if interrupted.
 * The main thread's await() returns only after the counter reaches zero. A latch cannot be reset.
 */
