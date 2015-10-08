package nineChap4_DP1;

public class MinMatrixPathSum {
  public static void main(String[] args) {
    int[][] Mat = new int[][] { {1, 3, 2}, {6, 0, 5}, {4, 9, 7}, {3, 2, 1}};
    int ans = minPathSumDP1(Mat);
    System.out.println("I do dummy matrix DP: " + ans);

    ans = minPathSumDP2(Mat);
    System.out.println("I do rolling stone matrix DP: " + ans);
  }

  /**
   * 1st type of DP interview problem: matrix DP
   * 
   * @param MAT
   * @return
   */
  public static int minPathSumDP1(int[][] MAT) {
    if (MAT == null)
      return -1;
    int m = MAT.length;
    int n = MAT[0].length;
    int[][] F = new int[m][n];

    F[0][0] = MAT[0][0];
    for (int i = 1; i < n; ++i) {
      F[0][i] = F[0][i - 1] + MAT[0][i];
    }
    for (int j = 1; j < m; ++j) {
      F[j][0] = F[j - 1][0] + MAT[j][0];
    }
    for (int i = 1; i < m; ++i) {
      for (int j = 1; j < n; ++j) {
        F[i][j] = MAT[i][j] + Math.min(F[i - 1][j], F[i][j - 1]);
      }
    }
    return F[m - 1][n - 1];
  }

  /**
   * I love rolling array!!! so simple to modify from mxn DP! one more change than the optimization
   * in climbing stairs: when doing the base condition init, since we have only Two 1d array, so
   * don't do rolling for init individually. do it in the 2 loops in dp building
   * 
   * @param MAT
   * @return
   */
  public static int minPathSumDP2(int[][] MAT) {
    if (MAT == null)
      return -1;
    int m = MAT.length;
    int n = MAT[0].length;
    int[][] F = new int[2][n];

    F[0][0] = MAT[0][0];
    for (int i = 1; i < n; ++i) {
      F[0][i] = F[0][i - 1] + MAT[0][i];
    }
    // for (int j = 1; j < m; ++j) {
    // F[j][0] = F[j - 1][0] + MAT[j][0];
    // }
    for (int i = 1; i < m; ++i) {
      F[i % 2][0] = F[(i - 1) % 2][0] + MAT[i][0];
      for (int j = 1; j < n; ++j) {
        F[(i) % 2][j] =
            MAT[i][j] + Math.min(F[(i - 1) % 2][j], F[i % 2][j - 1]);
      }
    }
    return F[(m - 1) % 2][n - 1];
  }

  /**
   * Ganker!
   * 
   * @param MAT
   * @return
   */
  public static int minPathSumDP1d(int[][] MAT) {
    return 0;
  }
}
