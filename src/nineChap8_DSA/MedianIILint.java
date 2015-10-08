package nineChap8_DSA;

import java.util.*;

public class MedianIILint {
  Queue<Integer> RminPQ;
  Queue<Integer> LmaxPQ;
  Integer med = null;

  public MedianIILint() {
    RminPQ = new PriorityQueue<Integer>();
    LmaxPQ = new PriorityQueue<Integer>(new Comparator<Integer>() {
      @Override
      public int compare(Integer arg0, Integer arg1) {
        return arg1 - arg0;
      }
    });
  }

  public void put(int ip) {
    if (med == null) {
      med = ip;
      return;
    }
    int LSize = LmaxPQ.size();
    int RSize = RminPQ.size();
    int minMaxPQ, maxMinPQ;
    if (LSize != 0)
      minMaxPQ = LmaxPQ.peek();
    if (RSize != 0)
      maxMinPQ = RminPQ.peek();

    if (LSize == RSize) {
      if (ip < med) {
        LmaxPQ.offer(ip);
        RminPQ.offer(med);
        med = LmaxPQ.poll();
      } else {
        RminPQ.offer(ip);
      }
    }

    else {
      if (ip < med) {
        LmaxPQ.offer(ip);
      } else {
        LmaxPQ.offer(med);
        RminPQ.offer(ip);
        med = RminPQ.poll();
      }
    }
  }

  public int[] medianII(int[] nums) {
    List<Integer> res = new ArrayList<>();
    for (int ip : nums) {
      put(ip);
      res.add(med);
    }
    int[] output = new int[res.size()];
    for (int i = 0; i < res.size(); ++i) {
      output[i] = res.get(i);
    }
    return output;
  }

  public static void main(String[] args) {
    int[] input = new int[] {4, 5, 1, 3, 2, 6, 0};
    MedianIILint m2 = new MedianIILint();
    System.out.println(Arrays.toString(m2.medianII(input)));
  }
}
