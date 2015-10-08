package nineChap7_Graph;

import java.util.ArrayList;
import java.util.List;

public class CombinationSumII {
  public CombinationSumII() {
    int[] nums = new int[]{1}; // {2,3,6,7};
    int target = 1;
    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    ans = combinationSum2(nums, target);
    
    System.out.println("result: ");
    for (List<Integer> l : ans) {
      for (Integer i: l) {
        System.out.print(i + " ");
      }
      System.out.println();
    }
  }
  /**
   * @param num: Given the candidate numbers
   * @param target: Given the target number
   * @return: All the combinations that sum to target
   */
  public List<List<Integer>> combinationSum2(int[] num, int target) {
      // write your code here
      List<List<Integer>> result = new ArrayList<List<Integer>>();
      List<Integer> list = new ArrayList<Integer>();
      helper(result, list, 0, num, target);
      return result;
  }
  
  public static void helper(List<List<Integer>> result, List<Integer> list, int pos, int[] num, int target) 
  {
      // add list into result
      if (target == 0) {
          result.add(new ArrayList<Integer>(list));
      }
      
      // dfs and prune
      for (int i = pos; i < num.length; i++) {
          if (num[i] <= target) {
              if (i != pos && num[i] == num[i-1]) {
                  continue;
              }
              
              list.add(num[i]);
              helper(result, list, i+1, num, target - num[i]);
              list.remove(list.size()-1);
          }
      }
              
  }
}
