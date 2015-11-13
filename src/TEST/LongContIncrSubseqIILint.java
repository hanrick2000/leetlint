package TEST;

/**
 * Created at 2:47 PM on 11/12/15.
 */
public class LongContIncrSubseqIILint {

  public static void main(String[] args) {
    int[][] M = new int[][]{
        //{1 ,2 ,3 ,4 ,5},
        //{16,17,24,23,6},
        //{15,18,25,22,7},
        //{14,19,20,21,8},
        //{13,12,11,10,9}
        //{9,5,6},
        //{1,4,7},
        //{2,3,8}
        {1,2,3,4,5,6},
        {14,15,16,17,18,8},
        {12,13,11,10,9,7}
    };
    LongContIncrSubseqIILint lics2 = new LongContIncrSubseqIILint();
    int ans = lics2.longestIncreasingContinuousSubsequenceII(M);
    System.out.println(ans);
  }

  /**
   * @param A an integer matrix
   * @return  an integer
   */
  public int longestIncreasingContinuousSubsequenceII(int[][] A) {
    // Write your code here
    int ans = 0;
    if (A == null || A.length == 0) {
      return ans;
    }
    int R = A.length, C = A[0].length;
    int[][] P = new int[R][C];
    boolean[][] visited = new boolean[R][C];
    for (int i = 0; i < R; ++i) {
      for (int j = 0; j < C; ++j) {
        ans = Math.max(ans, dfs(A, P, visited, i, j));
      }
    }
    return ans;
  }

  int[] dx = new int[]{0,0,-1,1};
  int[] dy = new int[]{1,-1,0,0};

  private int dfs(int[][] A, int[][] P, boolean[][] visited, int r, int c) {
    if (visited[r][c]) {
      return P[r][c];
    }
    int ans = 0;
    //visited[r][c] = true;  // should not check here since it will return.
    for (int i = 0; i < 4; ++i) {
      int nr = r + dx[i];
      int nc = c + dy[i];
      if (isValid(A, P, visited, r, c, nr, nc)) {
        int tmp = dfs(A, P, visited, nr, nc);
        ans = Math.max(ans, tmp);
      }
    }
    visited[r][c] = true;  // only set after this level has done!
    return P[r][c] = ans + 1;
  }

  private static boolean isValid(int[][] A, int[][] P, boolean[][] F, int r, int c, int rr, int cc) {
    if (rr < 0 || rr >= A.length || cc < 0 || cc >= A[0].length || A[rr][cc] <= A[r][c]) { // F[rr][cc] == true
      return false;
    }
    return true;
  }
}
