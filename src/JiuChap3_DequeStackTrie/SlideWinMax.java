package JiuChap3_DequeStackTrie;

import java.util.ArrayList;

/**
 * http://www.lintcode.com/en/problem/sliding-window-maximum/
 * Created this class in JiuChap3_DequeStackTrie at 10:23 PM, 11/2/2015.
 */
public class SlideWinMax {

  public static void main(String[] args) {
    SlideWinMax swm = new SlideWinMax();
    int[] data = new int[] { 1, 2, 7, 7, 8 };
    ArrayList<Integer> res = swm.maxSlidingWindow(data, 3);
    System.out.println(res);
  }

  /**
   * @param nums: A list of integers.
   * @return: The maximum number inside the window at each moving.
   */
  public ArrayList<Integer> maxSlidingWindow(int[] nums, int k) {
    // write your code here
    ArrayList<Integer> result = new ArrayList<>();
    result = fornk(nums, k);
    return result;
  }

  /**
   * Dummy O(nk) method
   * @param nums
   * @param k
   * @return
   */
  private ArrayList<Integer> fornk(int[] nums, int k) {
    ArrayList<Integer> result = new ArrayList<>();

    for (int i = 0; i + k <= nums.length; ++i) {
      int maxi = Integer.MIN_VALUE;
      for (int j = i; j < nums.length && j < i + k; ++j) {
        maxi = Math.max(maxi, nums[j]);
      }
      result.add(maxi);
    }

    return result;
  }

  /**
   * Using Hash Heap to able remove(idx) and add(v)
   *
   * @param nums
   * @param k
   * @return
   */
  private ArrayList<Integer> forNlgK(int[] nums, int k) {
    ArrayList<Integer> result = new ArrayList<>();
    return result;
  }
}
