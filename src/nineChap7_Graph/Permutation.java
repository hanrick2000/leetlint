package nineChap7_Graph;

import java.util.ArrayList;
import java.util.List;

/*-
 * Classical dfs -> backtracking problem, often use helper function to make easy
 * to use recursion method
 * 
 * @author tzhang
 *
 */
public class Permutation {
  
  /*-
   * This is backtracking, always use recursion helper function
   * 
   * @param nums
   * @return
   */
  public List<List<Integer>> permute(List<Integer> nums) {
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    if (nums == null || nums.size() == 0) {
      return result;
    }

    List<Integer> list = new ArrayList<>();
    permuteHelper(result, list, nums);

    return result;
  }

  /*-
   * helper function to do dfs recursion, so need param to save temp result
   * NOTICE: endpoint return is NOT a must, check subset
   * 
   * @param result
   * @param list
   * @param nums
   */
  private void permuteHelper(List<List<Integer>> result,
      List<Integer> list, List<Integer> nums) {
    
    // only save leaf into result
    if (list.size() == nums.size()) {
      result.add(new ArrayList<Integer>(list));
      return;
    }
//    result.add(new ArrayList<Integer>(list)); I can also save nodes

    //
    for (int num : nums) {
      if (!list.contains(num)) {
        list.add(num);
        permuteHelper(result, list, nums);
        list.remove(list.size() - 1);
      }
    }
//    result.add(new ArrayList<Integer>(list)); save nodes reversely
  }
  
  private static List<Integer> testPermute() {
    List<Integer> nums = new ArrayList<>();
    nums.add(1);
    nums.add(2);
    nums.add(3);
    
    return nums;
  }
  
  public Permutation() {
    List<Integer> input = testPermute();
    List<List<Integer>> ans  = permute(input);
    
    for (List<Integer> list : ans) {
      for (Integer num : list) {
        System.out.print(num + " ");
      }
      System.out.println();
    }
  }
  
  public static void main(String[] args) {
    Permutation p = new Permutation();
  }
}
