package Easy;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * http://www.lintcode.com/en/problem/ugly-number/
 * Created at 5:30 PM on 11/17/15.
 */
public class UglyNum {
  public static void main(String[] args) {
    UglyNum un = new UglyNum();
    long ans = 0;
    ans = un.kthPrimeNumber(4);
    System.out.println(ans);
  }

  /**
   * 9chap.
   * http://www.cnblogs.com/grandyang/p/4743837.html
   * @param k
   * @return
   */
  public long kthPrimeNumber(int k) {
    PriorityQueue<Long> minPQ = new PriorityQueue<>();
    Map<Long, Boolean> map = new HashMap<>();
    Long[] primes = new Long[3];
    primes[0] = Long.valueOf(3);
    primes[1] = Long.valueOf(5);
    primes[2] = Long.valueOf(7);
    for (int i = 0; i < 3; ++i) {
      minPQ.offer(primes[i]);
      map.put(primes[i], true);
    }
    for (int i = 0; i < k-1; ++i) {
      Long number = minPQ.poll();
      for (int j = 0; j < 3; ++j) {
        Long xin = primes[j] * number;
        if (!map.containsKey(xin)) {
          minPQ.offer(xin);
          map.put(xin, true);
        }
      }
    }
    return minPQ.peek();
  }

  /**
   * TLE on k = 599
   * http://www.cnblogs.com/grandyang/p/4741934.html
   */
  public long kthPrimeNumberTLE(int k) {
    //PriorityQueue<Integer> maxPQ = new PriorityQueue<>(new Comparator<Integer>() {
    //  @Override public int compare(Integer o1, Integer o2) {
    //    return o2 - o1;
    //  }
    //});
    int i = 3, count = 0;
    while (count <= k) {
      if (isUgly(i++)) {
        count++;
      }
    }
    return i;
  }

  boolean isUgly(int num) {
    while (num >= 2) {
      if (num % 3 == 0)  num /= 3;
      else if (num % 5 == 0)  num /= 5;
      else if (num % 7 == 0)  num /= 7;
      else return false;
    }
    return num == 1;
  }

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
