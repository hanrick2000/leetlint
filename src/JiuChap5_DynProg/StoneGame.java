package JiuChap5_DynProg;

/**
 * poj 1738 An old Stone Game
 * http://www.lintcode.com/en/problem/stone-game/
 * Created at 11:51 AM on 11/15/15.
 */
public class StoneGame {
  public static void main(String[] args) {
    StoneGame sg = new StoneGame();
    int[] stones = new int[]{1,1,1,1}; //{4, 4, 5, 9};  //
    int ans = sg.stoneGame(stones);
    System.out.println(ans);
  }

  /**
   * I followed the O(n^3) DP, from type00A's lec note
   * Updated with O(n^2) DP, simply using the property of 2D/1D DP's rectangle inequality.
   * @param A an integer array
   * @return an integer
   */
  public int stoneGame(int[] A) {
    // Write your code here
    if (A == null || A.length == 0) {
      return 0;
    }

    int n = A.length;
    int[][] P = new int[n+1][n+1];
    int[] W = new int[n+1];
    for (int i = 0; i < n; ++i) {
      P[i][i] = 0;
    }

    int s = 0;
    for (int i = 1; i < n+1; ++i) {
      s += A[i-1];
      W[i] = s;
    }

    int ans = Integer.MAX_VALUE;
    //for (int i = 1; i <= n; ++i) {
    //  for (int j = i+1; j <= n; ++j) {
    //    //http://www.cloudlunar.com/view/CodeVS/1048
    //    HOW???????
    //  }
    //}

    for (int len = 2; len <= n; ++len) {
      for (int i = 1; i <= n - len + 1; ++i) {
        int j = i + len - 1;
        P[i][j] = Integer.MAX_VALUE;
        for (int k = i; k <= j-1; ++k) {
          P[i][j] = Math.min(P[i][j], P[i][k] + P[k+1][j] + W[j]-W[i-1]);
        }
      }
    }

    return P[1][n];
  }
}
