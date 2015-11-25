package JiuChap7_FollowUpB;

import java.util.ArrayList;
import java.util.List;

/**
 * Created at 2:41 PM on 11/24/15.
 */
public class PreviousPermutation {
  public static void main(String[] args) {
    int[] A = new int[]{1,3,2,3};
    ArrayList<Integer> perm = new ArrayList<>();
    for (int i : A) {
      perm.add(i);
    }
    ArrayList<Integer> ans = new PreviousPermutation().previousPermuation(perm);
    System.out.println(ans);
  }
  /**
   * http://algorithm.yuanbin.me/zh-cn/exhaustive_search/previous_permuation.html
   * @param nums: A list of integers
   * @return: A list of integers that's previous permuation
   */
  public ArrayList<Integer> previousPermuation(ArrayList<Integer> nums) {
    // write your code
    ArrayList<Integer> result = new ArrayList<>();
    if (nums == null || nums.size() == 0) {
      return result;
    }

    int k = -1;
    for (int i = nums.size()-2; i >= 0; i--) {
      if (nums.get(i) > nums.get(i+1)) {
        k = i;
        break;
      }
    }

    if (k == -1) {
      reverse(nums, k+1, nums.size()-1);
      return nums;
    }

    int l = nums.size()-1;
    while (l > k && nums.get(l) >= nums.get(k)) {
      l--;
    }

    int tmp = nums.get(l);
    nums.set(l, nums.get(k));
    nums.set(k, tmp);

    reverse(nums, k+1, nums.size()-1);
    return nums;
  }

  private void reverse(List<Integer> nums, int l, int u) {
    for (int i = l, j = u; i < j; i++, j--) {
      int tmp = nums.get(i);
      nums.set(i, nums.get(j));
      nums.set(j, tmp);
    }
  }
}
