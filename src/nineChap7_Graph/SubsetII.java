package nineChap7_Graph;

import java.util.ArrayList;
import java.util.Collections;

/*-
 * Similar to subset I, but here the set has duplicate value.
 * @author tzhang
 *
 */
public class SubsetII {
  /**
   * @param S: A set of numbers.
   * @return: A list of lists. All valid subsets.
   */
  public ArrayList<ArrayList<Integer>> subsetsWithDup(ArrayList<Integer> S) {
      // write your code here
    ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
    if (S == null || S.size() == 0) {
      return result;
    }
    Collections.sort(S);
    ArrayList<Integer> list = new ArrayList<>();
    subset2Helper(result, list, S, 0);
    return result;
  }
  
  /*-
   * What's the difference from II to I?
   * Ans: the way to deal with duplicate, draw DFS tree instead of manualy debug. 
   *      Trace to debug is like debugging recursion by tracing, low efficient, the key
   *      is to understand the flow by thinking not tracing!   
   * @param result
   * @param list
   * @param nums
   * @param pos
   */
  private static void subset2Helper(ArrayList<ArrayList<Integer>> result,
      ArrayList<Integer> list, ArrayList<Integer> nums, int pos) {
    
    // add list into result
    result.add(new ArrayList<Integer>(list));
    
    // DFS traverse
    for (int i = pos; i < nums.size(); ++i) {
      if (i != pos && nums.get(i) == nums.get(i-1)) { // Key point
        continue;
      }
      list.add(nums.get(i));
      subset2Helper(result, list, nums, i+1);
      list.remove(list.size()-1);
    }
  }
  
  private static ArrayList<Integer> testSubsetI() {
    ArrayList<Integer> nums = new ArrayList<>();
    nums.add(1);
    nums.add(2);
    nums.add(2);

    return nums;
  }

  public SubsetII() {
    ArrayList<Integer> nums = testSubsetI();
    ArrayList<ArrayList<Integer>> result = subsetsWithDup(nums);
    for (ArrayList<Integer> list : result) {
      for (Integer num : list) {
        System.out.print(num + " ");
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    SubsetII sii = new SubsetII();
  }
}
