package JiuChap7_FollowUpB;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Do it without recursion
 * http://algorithm.yuanbin.me/zh-cn/exhaustive_search/permutations.html
 * http://www.lintcode.com/en/problem/permutations/
 * Created at 1:42 PM on 11/24/15.
 */
public class PermutationIteration {
  /**
   * @param nums: A list of integers.
   * @return: A list of permutations.
   */
  public ArrayList<ArrayList<Integer>> permute(ArrayList<Integer> nums) {
    // write your code here
    ArrayList<ArrayList<Integer>> result = new ArrayList<>();
    if (nums == null || nums.size() == 0) {
      return result;
    }
    Collections.sort(nums);
    //result.add(nums);
    List<Integer> perm = new ArrayList<>(nums);
    while (true) {
      result.add(new ArrayList<Integer>(perm));
      int size = nums.size();
      int k = -1; // pivot

      // step 1: find the first nums[k] < nums[k+1]
      for (int i = size-2; i >= 0; i--) {
        if (nums.get(i) < nums.get(i+1)) {
          k = i;
          break;
        }
      }

      // if current rank is the largest, exit while loop
      if (k == -1)  break;

      // step 2: swap the pivot with the smallest one greater than it.
      for (int i = size-1; i >= 0; i--) {
        if (nums.get(i) > nums.get(k)) {
          int tmp = nums.get(i);
          nums.set(i, nums.get(k));
          nums.set(k, tmp);
        }
      }

      // step 3: reverse all numbers from k+1 to n
      reverse(nums, k+1, size);
    }
    //while (true) {
    //  result.add(new ArrayList<>(perm));
    //  perm = nextPermute(perm);
    //}
    return result;
  }

  private List<Integer> nextPermute(List<Integer> nums) {
    if (nums == null || nums.size() == 0) {
      return nums;
    }

    int size = nums.size();
    int k = -1; // pivot

    // step 1: find the first nums[k] < nums[k+1]
    for (int i = size-2; i >= 0; i--) {
      if (nums.get(i) < nums.get(i+1)) {
        k = i;
        break;
      }
    }

    // step 2: swap the pivot with the smallest one greater than it.
    for (int i = size-1; i >= 0; i--) {
      if (nums.get(i) > nums.get(k)) {
        int tmp = nums.get(i);
        nums.set(i, nums.get(k));
        nums.set(k, tmp);
      }
    }

    // step 3: reverse all numbers from k+1 to n
    reverse(nums, k+1, size-1);
    return nums;
  }

  private void reverse(List<Integer> nums, int lb, int ub) {
    for (int i =lb, j = ub; i < j; i++, j--) {
      int tmp = nums.get(i);
      nums.set(i, nums.get(j));
      nums.set(j, tmp);
    }
  }
}
