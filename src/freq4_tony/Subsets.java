package freq4_tony;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
 * 用的九章算法的Permutation模版
 */
public class Subsets {
  public List<List<Integer>> subsets(int[] num) {
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    if (num == null || num.length == 0) {
      return result;
    }
    Arrays.sort(num);
    helper(result, new ArrayList<Integer>(), num, 0);
    return result;
  }

  public static void helper(List<List<Integer>> result, List<Integer> path, int[] input, int pos) {
    result.add(new LinkedList<Integer>(path));
    for (int i = pos; i < input.length; i++) {
      /* this is used to skip duplication 
       * 
      if (i > pos && input[i]==input[i-1])
        continue;
      */
      path.add(input[i]);
      helper(result, path, input, i + 1);
      path.remove(path.size() - 1); // 剪枝
    }
  }

  public static void main(String[] args) {
    int[] a = {2, 2, 1, 2};
    List<List<Integer>> ans = new Subsets().subsets(a);
    for (List<Integer> i : ans)
      System.out.println(i);
  }
}


