package JiuChap5_DynamicProgram;

/**
 * http://www.lintcode.com/en/problem/longest-increasing-continuous-subsequence-ii/
 * This is the same as POJ ski problem
 * Created at 1:25 PM on 11/11/15.
 */
public class LongIncContSubseqII {
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
    LongIncContSubseqII lics2 = new LongIncContSubseqII();
    int ans = lics2.longestIncreasingContinuousSubsequenceII(M);
    System.out.println(ans);
  }

  /**
   * @param A an integer matrix
   * @return  an integer
   */
  public int longestIncreasingContinuousSubsequenceII(int[][] A) {
    // Write your code here
    int R = A.length;
    int C = A[0].length;
    int[][] P = new int[R][C];
    int ans = 0;
    //Arrays.fill(P, -1);
    boolean[][] flag = new boolean[R][C];
    for (int i = 0; i < R; ++i) {
      for (int j = 0; j < C; ++j) {
        int tmp = helper(A, P, flag, i, j);
        ans = Math.max(ans, tmp);
      }
    }
    return ans;
  }

  int[] dx = new int[]{0,0,-1,1};
  int[] dy = new int[]{1,-1,0,0};

  /**
   * 9 chap's template of memorized searching DP.
   * Why not like recursion that I need to set/reset global state??? for example: 8 queens.
   * @param A
   * @param path
   * @param visited : indicate this node's function has been done, so just return it.
   * @param r
   * @param c
   * @return
   */
  private int helper(int[][] A, int[][] path, boolean[][] visited, int r, int c) {
    if (visited[r][c]) {
      return path[r][c];
    }

    int rs = 0;
    for (int i = 0; i < 4; ++i) {
      int rr = r + dx[i];
      int cc = c + dy[i];
      if (isValid(A, path, r, c, rr, cc)) {
        //visited[rr][cc] = true;
        int tmp = helper(A, path, visited, rr, cc);
        rs = Math.max(rs, tmp);
        //visited[rr][cc] = false;
      }
    }
    visited[r][c] = true;
    return path[r][c] = rs + 1;
  }

  private static boolean isValid(int[][] A, int[][] path, int r, int c, int nr, int nc) {
    if (nr < 0 || nr >= A.length || nc < 0 || nc >= A[0].length || A[nr][nc] <= A[r][c]) {
      return false;
    }
    return true;
  }
}
