package JiuChap7_FollowUpB;

import java.util.Arrays;

/**
 * http://www.lintcode.com/en/problem/maximum-gap/
 * Created at 11:34 AM on 11/24/15.
 */
public class MaxGap {
  public static void main(String[] args) {
    int[] A = new int[]{1,9,2,5};
    int ans = 0;
    MaxGap mx = new MaxGap();
    ans = mx.maximumGap(A);
    System.out.println(ans);
  }
  /**
   * @param nums: an array of integers
   * @return: the maximum difference
   */
  public int maximumGap(int[] nums) {
    // bucket sort for in-bucket and successive buckets' max-min
    // TODO
    return 0;
  }

  /**
   * Dummy solution
   * @param nums
   * @return
   */
  public int maximumGapNlogN(int[] nums) {
    // write your code here
    if (nums == null || nums.length < 2) {
      return 0;
    }

    Arrays.sort(nums);
    int diff = Integer.MIN_VALUE;
    for (int i = 1; i < nums.length; ++i) {
      diff = Math.max(diff, Math.abs(nums[i] - nums[i-1]));
    }
    return diff;
  }
}
