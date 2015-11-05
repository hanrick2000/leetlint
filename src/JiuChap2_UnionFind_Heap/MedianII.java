package JiuChap2_UnionFind_Heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * http://www.lintcode.com/en/problem/data-stream-median/
 * Created this class in JiuChap2_UnionFind_Heap at 10:05 PM, 11/4/2015.
 */
public class MedianII {
  public static void main(String[] args) {
    int[] input = new int[] {4, 5, 1, 3, 2, 6, 0}; // {1,2,3,4,5}; //
    MedianII m2 = new MedianII();
    System.out.println(Arrays.toString(m2.medianII(input)));
  }
  /**
   * @param nums: A list of integers.
   * @return: the median of numbers
   */
  public int[] medianII(int[] nums) {
    // write your code here
    if (nums == null || nums.length == 0) {
      return null;
    }
    List<Integer> res = new ArrayList<>();
    PriorityQueue<Integer> lmaxQ = new PriorityQueue<>(new Comparator<Integer>() {
      @Override public int compare(Integer o1, Integer o2) {
        return o2 - o1;
      }
    });
    PriorityQueue<Integer> rminQ = new PriorityQueue<>();
    int median = nums[0];
    res.add(median);

    for (int i = 1; i < nums.length; ++i) {
      if (nums[i] < median) {
        lmaxQ.add(nums[i]);
      }
      else {
        rminQ.add(nums[i]);
      }

      int lsize = lmaxQ.size();
      int rsize = rminQ.size();
      if (lsize > rsize) {
        rminQ.add(median);
        median = lmaxQ.poll();
      }
      else if (lsize + 1 < rsize) {
        lmaxQ.add(median);
        median = rminQ.poll();
      }
      res.add(median);
    }

    int[] ans = new int[res.size()];
    for (int i = 0; i < res.size(); ++i) {
      ans[i] = res.get(i);
    }
    return ans;
  }
}
