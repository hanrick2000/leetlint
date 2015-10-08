package nineChap4_DP1;

public class UniquePathII {
  public static void main(String[] args) {
    int[][] obstacleGrid =
        new int[][] { {0, 0, 0}, {0, 0, 1}, {0, 0, 0}, {0, 0, 0}};
    int ans = uniquePathsWithObstacles(obstacleGrid);

    System.out.println(ans);
  }

  public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
    if (obstacleGrid == null /* || obstacleGrid[0][0] == 1 */) // [[1,0],[0,0],[0,0],[0,0],[0,0],[0,0]]
      return -1;
    int m = obstacleGrid.length;
    int n = obstacleGrid[0].length;
    int[][] F = new int[m][n];
    for (int i = 0; i < m && obstacleGrid[i][0] != 1; ++i) {
      F[i][0] = 1;
    }
    for (int i = 0; i < n && obstacleGrid[0][i] != 1; ++i) {
      F[0][i] = 1;
    }
    for (int i = 1; i < m; ++i) {
      for (int j = 1; j < n; ++j) {
        if (obstacleGrid[i][j] == 1) {
          // F[i][j] = 0;
          continue;
        }
        F[i][j] = F[i - 1][j] + F[i][j - 1];
      }
    }
    return F[m - 1][n - 1];
  }
}
