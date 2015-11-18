package Easy;

import java.util.PriorityQueue;

/**
 * http://www.lintcode.com/en/problem/ugly-number/
 * Created at 5:30 PM on 11/17/15.
 */
public class UglyNum {
  public static void main(String[] args) {
    UglyNum un = new UglyNum();
    long ans = 0;
    // ans = un.kthPrimeNumber(4);
    System.out.println(ans);
  }

  /**
   * @param k: The number k.
   * @return: The kth prime number as description.
   */
  public long kthPrimeNumberWRONG(int k) {
    // write your code here
    PriorityQueue<Integer> minPQ = new PriorityQueue<>();
    int count = 0;
    int i = 3;
    while (count < k) {
      if (i % 3 == 0 || i % 5 == 0 || i % 7 == 0) {
        minPQ.offer(i);
        count++;
      }
      i++;
    }
    return minPQ.peek();
  }
}
