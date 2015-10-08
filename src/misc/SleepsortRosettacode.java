package misc;

import java.util.concurrent.CountDownLatch;

/**
 * Sleep sort很简单. 在Princeton_algs里面的Trie也在开头提到了. 什么意思呢: 就是num[i]是几就sleep多久再print.
 * 这样每个num[i]都是一个thread. 但是thread/process处理实际上也要花时间. 在Google面试的时候和OS scheduling联合起来考.
 * 
 * @author tzhang
 *
 */
public class SleepsortRosettacode {

  /**
   * 
   * @param nums
   */
  public static void sleepSortAndPrint(int[] nums) {
    final CountDownLatch doneSignal = new CountDownLatch(nums.length);
    for (final int num : nums) {
      new Thread(new Runnable() {
        public void run() {
          doneSignal.countDown();
          try {
            doneSignal.await();

            // using straight milliseconds produces unpredictable
            // results with small numbers
            // using 1000 here gives a nifty demonstration
            Thread.sleep(num * 100);
            System.out.println(num);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }).start();
    }
  }

  public static void main(String[] args) {
    int[] nums = {5, 1, 3, 2, 11, 6, 4}; // new int[args.length];
    // for (int i = 0; i < args.length; i++)
    // nums[i] = Integer.parseInt(args[i]);
    sleepSortAndPrint(nums);
  }
}
