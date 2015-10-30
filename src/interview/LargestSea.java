package interview;

/**
 * 20151029
 * uSens interview with Yuan Wang
 * Created this class in interview at 11:36 PM, 10/29/2015.
 */
public class LargestSea {

  public static void main(String[] args0) {
    int[][] M = new int[][]{
        {1, 0, 1, 1, 0},
        {0, 0, 1, 0, 0},
        {0, 0, 0, 1, 0},
        {1, 1, 1, 1, 1},
        {0, 0, 0, 0, 1},
        {0, 0, 0, 0, 1}
    };

    int res = new LargestSea().largestSea(M);
    System.out.println(res);
  }

  public int largestSea(int[][] M) {
    int ROW = M.length, COL = M[0].length;
    int res = 0;
    int[] ans = new int[1];
    for (int r = 0; r < ROW; ++r) {
      for (int c = 0; c < COL; ++c) {
        res = Math.max(res, flood(M, r, c, ans));
      }
    }
    return res;
  }

  int[] dx = new int[]{1, -1, 0, 0};
  int[] dy = new int[]{0, 0, -1, 1};

  public int flood(int[][] M, int r, int c, int[] ans) {
    int rs = 0;
    for (int i = 0; i < 4; ++i) {
      int rr = r + dx[i];
      int cc = c + dy[i];
      if (isValid(M, rr, cc)) {
        M[rr][cc] = 1;
        rs = flood(M, rr, cc, ans) + 1;
      }
    }

    return ans[0] = rs;
  }

  private boolean isValid(int[][] M, int r, int c) {
    if (r < 0 || r >= M.length || c < 0 || c >= M[0].length || M[r][c] == 1) {
      return false;
    }
    return true;
  }
}
