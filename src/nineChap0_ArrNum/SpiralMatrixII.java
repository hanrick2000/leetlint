package nineChap0_ArrNum;

/**
 * http://www.lintcode.com/en/problem/spiral-matrix-ii/ Created at 10:44 AM on 11/28/15.
 */
public class SpiralMatrixII {
  public static void main(String[] args) {
    int[][] ans = new SpiralMatrixII().generateMatrix(4);
    for (int[] row : ans) {
      for (int e : row) {
        System.out.print(e + "  ");
      }
      System.out.println();
    }
  }
  /**
   * learned the tech from http://www.cnblogs.com/yuzhangcmu/p/4046543.html
   * @param n an integer
   * @return a square matrix
   */
  public int[][] generateMatrix(int n) {
    // Write your code here
    int[][] M = new int[n][n];
    int left = 0;
    int right = n-1;
    int top = 0;
    int bottom = n-1;

    int v = 1;
    while (left <= right && top <= bottom) {
      for (int i = left; i <= right; ++i) {
        M[top][i] = v;
        v++;
      }
      for (int i = top+1; i <= bottom-1; ++i) {
        M[i][right] = v;
        v++;
      }
      if (top != bottom) {
        for (int i = right; i >= left; i--) {
          M[bottom][i] = v;
          v++;
        }
      }
      if (left != right) {
        for (int i = bottom-1; i >= top+1; i--) {
          M[i][left] = v;
          v++;
        }
      }

      left++;
      right--;
      top++;
      bottom--;
    }
    return M;
  }
}