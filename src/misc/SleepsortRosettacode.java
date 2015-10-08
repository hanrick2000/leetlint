package misc;

import java.util.concurrent.CountDownLatch;

/**
 * Sleep sort�ܼ�. ��Princeton_algs�����TrieҲ�ڿ�ͷ�ᵽ��. ʲô��˼��: ����num[i]�Ǽ���sleep�����print.
 * ����ÿ��num[i]����һ��thread. ����thread/process����ʵ����ҲҪ��ʱ��. ��Google���Ե�ʱ���OS scheduling����������.
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
