package nineChap3_BST;

/**
 * http://www.lintcode.com/en/problem/unique-binary-search-trees/
 * Created at 5:31 PM on 11/22/15.
 */
public class UniqueBST {
  public static void main(String[] args) {
    UniqueBST ub = new UniqueBST();
    int ans = ub.numTrees(0);
    System.out.println(ans);
  }
  /**
   * How to loop?
   * @paramn n: An integer
   * @return: An integer
   */
  public int numTrees(int n) {
    // write your code here
    if (n < 0) {
      return -1;
    }
    if (n < 2) {
      return 1;
    }
    int[] F = new int[n+1];
    F[0] = 1;
    F[1] = 1;
    for (int i = 2; i <= n; ++i) {
      for (int j = 0; j < i; ++j) {
        F[i] += F[j] * F[i-j-1];
      }
    }
    return F[n];
  }
}
