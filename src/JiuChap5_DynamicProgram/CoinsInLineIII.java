package JiuChap5_DynamicProgram;

/**
 * Classical memorization DP with min/max tree.
 * One level indirection: "win" means the first hand got more than half profit.
 * 
 * http://www.lintcode.com/en/problem/coins-in-a-line-iii/
 * Created at 12:29 PM on 11/12/15.
 */
public class CoinsInLineIII {
  public static void main(String[] args) {
    int[] values = new int[]{3,2,2};
    CoinsInLineIII cil3 = new CoinsInLineIII();
    boolean res = cil3.firstWillWin(values);
    System.out.println(res);
  }

  public boolean firstWillWin(int[] values) {
    int sum = 0;
    for (int v : values) {
      sum += v;
    }
    int n = values.length;
    int[][] P = new int[n][n];
    boolean[][] visited = new boolean[n][n];
    //for (int i = 0; i < values.length; ++i) {
    //  P[i][i] = values[i];
    //  P[i][i+1] = values[i]+values[i+1];
    //}

    int res = dfs(values, P, visited, 0, n-1);  // the result of dfs(0, n) is P[0,n] means the max profit at [0,n]
    //int res = MemorySearch(0, n-1, P, visited, values);
    return res * 2 > sum;  // one level indirection: win -> first hand got more than half profits!
  }

  /**
   * Do recursion call, not direct the P[][]. The recursion function returns the related P[][]!!!
   * Do init boundary in recursion, as an exit point.
   * Memorization DP is transit from large state to small states, so the [i,j] is smaller in recursion call.
   * @param values
   * @param P
   * @param visited
   * @param i
   * @param j
   * @return
   */
  public int dfs(int[] values, int[][] P, boolean[][] visited, int i, int j) {
    if (visited[i][j]) {
      return P[i][j];
    }
    if (i > j) {
      P[i][j] = 0;
    }
    else if (i==j) {
      P[i][j] = values[i];
    }
    else if (i == j - 1) {
      P[i][j] = Math.max(values[i], values[j]); //values[i] + values[j];
    }
    else {
      P[i][j] = Math.max(
          //Math.min(P[i+2][j], P[i+1][j-1]) + values[i],  // Why I always forgot! The recurrence function returns the related P[i][j].
          //Math.min(P[i+1][j-1], P[i][j-2]) + values[j]
          Math.min(dfs(values, P, visited, i+2, j), dfs(values, P, visited, i+1, j-1)) + values[i],
          Math.min(dfs(values, P, visited, i+1, j-1), dfs(values, P, visited, i, j-2)) + values[j]
      );
    }
    visited[i][j] = true;
    return P[i][j];
  }
}
