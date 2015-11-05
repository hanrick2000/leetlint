package JiuChap2_UnionFind_Heap;

import java.util.ArrayList;
import misc.HashHeap;

/**
 * Created this class in JiuChap2_UnionFind_Heap at 10:57 PM, 11/4/2015.
 */
public class SlideWindowMedian {

  public static void main(String[] args) {
    int[] data = new int[]{1,2,7,8,5};
    int k = 2;
    ArrayList<Integer> ans = new SlideWindowMedian().medianSlidingWindow(data, k);
    System.out.println(ans);
  }
  /**
   * @param nums: A list of integers.
   * @return: The median of the element inside the window at each moving.
   */
  public ArrayList<Integer> medianSlidingWindow(int[] nums, int k) {
    // write your code here
    if (nums == null || nums.length == 0) {
      return null;
    }
    ArrayList<Integer> result = new ArrayList<>();
    HashHeap lmax = new HashHeap("max");
    HashHeap rmin = new HashHeap("min");

    int median = nums[0];
    for (int i = 1; i < nums.length; ++i) {
      if (lmax.size() + rmin.size() + 1 == k) {
        if (lmax.size() != 0)  lmax.pop();
      }

      if (nums[i] < median) {
        lmax.add(nums[i]);
      }
      else {
        rmin.add(nums[i]);
      }

      if (lmax.size() > rmin.size()) {
        rmin.add(median);
        median = lmax.pop();
      }
      else if (lmax.size() + 1 < rmin.size()){
        lmax.add(median);
        median = rmin.pop();
      }

      if (i+1 >= k) {
        result.add(median);
      }
    }

    return result;
  }
}
