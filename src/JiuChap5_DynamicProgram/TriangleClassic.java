package JiuChap5_DynamicProgram;

/**
 * Liu, RuJia classical backtracking, iteration, vs memorization DP
 * Created at 5:33 PM on 11/11/15.
 */
public class TriangleClassic {
  public static void main(String[] args) {
    int[][] T = new int[][]{
        {1},
        {3,2},
        {4,10,1},
        {4,3,2,20}
    };
    System.out.println(T.length + " " + T[T.length-1].length);
    int ans = 0;
    TriangleClassic tc = new TriangleClassic();
    ans = tc.mdp(T);
    System.out.println(ans);
  }

  public int mdp(int[][] T) {
    int[][] P = new int[T.length][T[T.length-1].length];
    int ans = 0;
    for (int i = 0; i < T.length; ++i) {
      for (int j = 0; j < T[T.length-1].length; ++j) {
        P[i][j]  = -1;
      }
    }
    //for (int i = 0; i < T[0].length-1; ++i) {
    //  ans = Math.max(ans, dfs(T, P, T.length-2, i));
    //}
    //ans = dfs(T, P, 0, 0);
    dfsLiuRJ(T,P,0,0);
    ans = P[0][0];
    return ans;
  }

  /**
   * I implement the recurrence function in recursion, but not using memorization, so it's same as
   * backtracking. so it's O(2^n)!!!
   * @param T
   * @param P
   * @param r
   * @param c
   * @return
   */
  private int dfs(int[][] T, int[][] P, int r, int c) {
    //if (r*c == 0) {
    //  return P[r][c];
    //}
    if (r == T.length-1) {
      P[r][c] = T[r][c];
      return P[r][c];
    }
    int left = dfs(T, P, r+1, c);
    int right = dfs(T, P, r+1, c+1);
    return P[r][c] =  Math.max(left, right) + T[r][c];
  }

  /**
   * Liu Rujia's memorization DP solution
   * This improved from O(2^n) to O(n^2)
   * @param T
   * @param P
   * @param r
   * @param c
   * @return
   */
  private int dfsLiuRJ(int[][] T, int[][] P, int r, int c) {
    if (P[r][c] != -1) {
      return P[r][c];
    }
    return P[r][c] = T[r][c] + (r == T.length-1 ? 0 : Math.max(dfsLiuRJ(T, P, r+1, c), dfsLiuRJ(T, P, r+1, c+1)));
  }
}
