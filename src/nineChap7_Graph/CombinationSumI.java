package nineChap7_Graph;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class CombinationSumI {
  public CombinationSumI() {
    int[] nums = new int[]{1}; // {2,3,6,7};
    int target = 1;
    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    ans = combinationSum(nums, target);
    
    System.out.println("result: ");
    for (List<Integer> l : ans) {
      for (Integer i: l) {
        System.out.print(i + " ");
      }
      System.out.println();
    }
  }
  
  /**
   * @param candidates: A list of integers
   * @param target:An integer
   * @return: A list of lists of integers
   */
  public List<List<Integer>> combinationSum(int[] candidates, int target) {
      // write your code here
    Arrays.sort(candidates);
    if (candidates == null || candidates.length == 0 || target < candidates[0]) {
      return null;
    }
    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    List<Integer> list = new ArrayList<Integer>();
    combSumHelper(ans, list, 0, candidates, target);
    return ans;
  }
  
  private static void combSumHelper(List<List<Integer>> result, 
      List<Integer> list, int pos, int[] nums, int target) {
//    if (nums == null || nums.length == 0 || target < nums[0]) {
//      return;
//    }
    
    // get list into result
    if (target == 0) {
//      result.add(list);
      result.add(new ArrayList<Integer>(list));
    }
    
    // DFS with pruning and subset pos
    for (int i = pos; i < nums.length; i++) {
      if (nums[i] > target) {
        break; // pruning if current least available number is greater than target
      }
      
      list.add(nums[i]);
//      combSumHelper(result, list, i+1, nums, target-nums[i]);
      combSumHelper(result, list, i, nums, target-nums[i]);
      list.remove(list.size()-1);
    }
  }
  
  public static void main(String[] args) {
    CombinationSumI csi = new CombinationSumI();
  }
}
