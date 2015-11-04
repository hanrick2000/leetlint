package JiuChap1_intro;

import java.util.Arrays;

/**
 * http://www.lintcode.com/en/problem/two-sum-ii/
 * Created this class in JiuChap1_intro at 11:26 PM, 11/3/2015.
 */
public class TwoSumII {

  public static void main(String[] args) {
    TwoSumII ts = new TwoSumII();
    int[] data = new int[]{-1,0,1}; //{2,7,11,15};
    int res = ts.twoSum2(data, 0);
    System.out.println(res);
  }
  /**
   * @param nums: an array of integer
   * @param target: an integer
   * @return: an integer
   */
  public int twoSum2(int[] nums, int target) {
    // Write your code here
    Arrays.sort(nums);
    int cnt = 0;
    for (int i = 0; i < nums.length-1; ++i) {
      int v = target - nums[i];
      int l = i+1, r = nums.length-1;

      while (l + 1 < r) {
        int mid = l + (r-l)/2;
        if (nums[mid] <= v) {
          l = mid;
        }
        else {
          r = mid;
        }
      }
      if (nums[r] > v) {
        cnt += nums.length -1 - r +1;
      }
      else if (nums[l] > v) {
        cnt += nums.length -1 - l + 1;
      }
    }

    return cnt;
  }
}
