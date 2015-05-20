package nineChap1;

import java.util.*;

public class Subset {
  public static void main(String[] args) {
    int[] num = {1, 2, 2};
    List<List<Integer>> res = subset(num);
    System.out.println(res);
  }

  /**
   * 经典模版: NP搜索模版, 记得先sort,再搜索. 可以解决duplicate的问题.
   * 
   * @param A
   * @return
   */
  public static List<List<Integer>> subset(int[] A) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    if (A == null || A.length == 0)
      return res;
    Arrays.sort(A);
    dfs(A, 0, new ArrayList<Integer>(), res);
    return res;
  }

  /**
   * 模版的内层recursion. 不同题目的区别就是控制子问题的参数: pos. 因为这里是subset, 所以是一直往后推. 所以子问题就是用一个int来控制就行了. 但是排列组合就不同了,
   * 但还是要有一个控制/选择子问题的参数, 所以不用int pos, 而是int[] used.
   * 
   * @param A
   * @param pos
   * @param path
   * @param res
   */
  private static void dfs(int[] A, int pos, List<Integer> path,
      List<List<Integer>> res) {
    res.add(new ArrayList<Integer>(path));
    for (int i = pos; i < A.length; ++i) {
      if (i > pos && A[i] == A[i - 1]) // key
        continue;
      path.add(A[i]);
      dfs(A, i + 1, path, res);
      path.remove(path.size() - 1);
    }
  }
}
