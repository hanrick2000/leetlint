package interview;

/**
 * 20151029
 * uSens interview with Yuan Wang
 * Similar problem is: connected component label, maximum submatrix, maximum Square...
 * Created this class in interview at 11:36 PM, 10/29/2015.
 */
public class LargestSea {

  public static void main(String[] args0) {
    int[][] M = new int[][]{
        {1, 0, 0, 1, 0},
        {0, 0, 1, 0, 1},
        {0, 0, 0, 1, 0},
        {1, 1, 1, 1, 1},
        {0, 0, 0, 0, 1},
        {0, 0, 0, 0, 1}
    };

    int res = new LargestSea().largestSea(M);
    System.out.println("Largest sea: "+res);
  }

  public int largestSea(int[][] M) {
    int ROW = M.length, COL = M[0].length;
    int res = 0;
    int[] ans = new int[1];
    for (int r = 0; r < ROW; ++r) {
      for (int c = 0; c < COL; ++c) {
        int size = flood(M, r, c, ans);
        if (size > 0) {
          System.out.println(size + " : " + r + ", " + c);
//        res = Math.max(res, flood(M, r, c, ans));
        }
      }
    }
    return res;
  }

  int[] dx = new int[]{1, -1, 0, 0};
  int[] dy = new int[]{0, 0, -1, 1};

  /**
   * Right way to AC.
   *
   * @param M
   * @param r
   * @param c
   * @param ans
   * @return
   */
  public int flood(int[][] M, int r, int c, int[] ans) {
    if (!isValid(M, r, c)) {
      return 0;
    }

    M[r][c] = 1;
    int rs = 1;
    for (int i = 0; i < 4; ++i) {
      int rr = r + dx[i];
      int cc = c + dy[i];
        ans[0] += 1;
        rs += flood(M, rr, cc, ans);
      }

    return ans[0] = rs;
  }

  /**
   * I did it wrong in first time!
   *
   * @param M
   * @param r
   * @param c
   * @param ans
   * @return
   */
  public int floodWrong(int[][] M, int r, int c, int[] ans) {
    int rs = 0;
    for (int i = 0; i < 4; ++i) {
      int rr = r + dx[i];
      int cc = c + dy[i];
      if (isValid(M, rr, cc)) {
        M[rr][cc] = 1;
        rs = floodWrong(M, rr, cc, ans) + 1;
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
