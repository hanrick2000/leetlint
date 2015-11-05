package nineChap8_DSA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MedianII {
  PriorityQueue<Integer> RminPQ;
  PriorityQueue<Integer> LmaxPQ;
  Integer med = null;

  public static void main(String[] args) {
    int[] input = new int[] {1,2,3,4,5}; // {4, 5, 1, 3, 2, 6, 0};
    MedianII m2 = new MedianII();
    System.out.println(Arrays.toString(m2.median2(input)));
  }

  public MedianII() {
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

  public int[] median2(int[] nums) {
    List<Integer> res = new ArrayList<>();
    for (int ip : nums) {
      put(ip);
      res.add(med);
    }
    // http://stackoverflow.com/a/23945015/3984911
    int[] output = res.stream().mapToInt(i->i).toArray();  // Fucking cool Stream API!
    return output;
  }
}
