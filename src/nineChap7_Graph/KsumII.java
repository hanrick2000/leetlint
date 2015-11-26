package nineChap7_Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * http://www.lintcode.com/en/problem/k-sum-ii/ Created at 2:00 PM on 11/25/15.
 */
public class KsumII {
  public static void main(String[] args) {
    int[] A = new int[] {1,4,7,10,12,15,16,18,21,23,24,25,26,29};
    int k = 5, target = 113;
    ArrayList<ArrayList<Integer>> ans = new KsumII().kSumII(A, k, target);
    for (ArrayList<Integer> list : ans) {
      System.out.println(list);
    }
  }

  /**
   * @param A: an integer array.
   * @param k: a positive integer (k <= length(A))
   * @param target: a integer
   * @return a list of lists of integer
   */
  public ArrayList<ArrayList<Integer>> kSumII(int A[], int k, int target) {
    // write your code here
    ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
    if (A == null || A.length == 0 || A.length < k) {
      return ans;
    }

    Map<Integer, Integer> map = new HashMap<>();
    backtracking(A, k, target, 0, ans, new ArrayList<>(), map);
    return ans;
  }

  public void backtracking(int[] A, int k, int target, int step,
      ArrayList<ArrayList<Integer>> result, ArrayList<Integer> path, Map<Integer, Integer> map) {
    if (path.size() == k) {
      if (target == 0) {
        result.add(new ArrayList<>(path));
      }
      return;
    }

    for (int i = step; i < A.length; ++i) {  // forgot DFS template again...
      //if (!map.containsKey(a)) {
        path.add(A[i]);
        //map.put(a, 1);
        backtracking(A, k, target - A[i], i + 1, result, path, map);
        //map.remove(a);
        path.remove(path.size() - 1);
      //}
    }
  }
}
