package JiuChap5_DynProg;

/**
 * http://www.geeksforgeeks.org/dynamic-programming-set-3-longest-increasing-subsequence/ Created at
 * 1:11 PM on 11/11/15.
 */
public class LongestIncreasingSubsequence {
  public static void main(String[] args) {
    int[] A = new int[] {10, 22, 9, 33, 21, 50, 41, 60, 80};
    int ans;
    LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
    ans = lis.longIncSubseq(A);
    System.out.println(ans);
  }

  /**
   * O(n^2) DP solution.
   * @param A
   * @return
   */
  public int longIncSubseq(int[] A) {
    int ans = 0;
    if (A == null || A.length == 0) {
      return ans;
    }
    int[] P = new int[A.length];
    P[0] = 1;

    for (int i = 0; i < A.length; ++i) {
      int temp = 0;
      for (int j = 0; j < i; ++j) {
        int pij = A[i] > A[j] ? P[j] + 1 : 1;
        temp = Math.max(pij, temp);
      }
      P[i] = Math.max(P[i], temp);
      ans = Math.max(P[i], ans);
    }
    return ans;
  }

  /**
   * Recursion solution, so I can see there are a lot recompute, therefore leads to DP solution.
   * @param A
   * @return
   */
  public int longIncSubseqRec(int[] A) {
    return 0;
  }

  /**
   * Best solution: O(nlgn).
   * @param A
   * @return
   */
  public int longIncSubseqBest(int[] A) {
    return 0;
  }


}
