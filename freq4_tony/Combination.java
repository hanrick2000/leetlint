package freq4_tony;

import java.util.ArrayList;

public class Combination {

  /**
   * recursion的解法, 不要总把recursion想成DP. recursion不是要真的把n的问题变成n-1然后到1. 比如说不是C(n,k)变成C(n,k-1),...
   * 而是先找出来规律, A[0]可以推出A[1], ..., A[n]
   * 
   * @param n
   * @param k
   * @return
   */
  // public ArrayList<ArrayList<Integer>> combine(int n, int k){
  //
  // }

  /**
   * 主干: i-1的值加1且小于n-(k-i)+1就是i的i level的值,可以放到2D array里面.
   * 
   * @param s
   * @param n
   * @param k
   * @param path
   * @param res
   */
  private void combineHelper(int s, int n, int k, ArrayList<Integer> path,
      ArrayList<ArrayList<Integer>> res) {
    if (n == 0)
      return;
    if (k == 0) {
      ArrayList<Integer> r = new ArrayList<Integer>(path); // 看testArrayList.
      res.add(r);
      return;
    }
    // 不需要else
    for (int i = s; i <= n - k + 1; i++) {
      path.add(i);
      combineHelper(i + 1, n, k - 1, path, res);
      path.remove(path.size() - 1);
    }
  }
}
