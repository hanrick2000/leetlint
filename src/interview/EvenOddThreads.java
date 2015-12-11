package interview;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * http://www.careercup.com/question?id=5652784707796992
 * Created at 8:54 PM on 11/29/15.
 */

public class EvenOddThreads {

  public static void demoUsingJUC() {
    final BlockingQueue<Boolean> queue = new SynchronousQueue<>();

    Thread t1 = new Thread(new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i < 10; i += 2) {
          try {
            if (i > 0 && queue.take()) {
              System.out.println(i);
            } else {
              System.out.println(i);
            }
            queue.put(true);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    });

    Thread t2 = new Thread(new Runnable() {
      @Override
      public void run() {
        for (int i = 1; i < 10; i += 2) {
          try {
            if (queue.take()) {
              System.out.println(i);
              queue.put(true);
            }
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    });

    t1.start();
    t2.start();
  }

  public static void main(String[] args) {
    demoUsingJUC();
  }

}