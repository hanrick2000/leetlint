package nineChap4_DP1;

public class UniquePathI {
  public static void main(String[] args) {
    int m = 3, n = 7;
    int ans = uniqRolling(m, n);
    System.out.println("\nI do matrix DP:" + ans);

//    ans = uniqRec(m - 1, n - 1);
//    System.out.println("I do dummy recursion:" + ans);
  }

  public int uniquePaths(int m, int n) {
    if (m == 0 || n == 0) {
      return 0;
    }

    int[][] sum = new int[m][n];
    for (int i = 0; i < m; i++) {
      sum[i][0] = 1;
    }
    for (int i = 0; i < n; i++) {
      sum[0][i] = 1;
    }
    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        sum[i][j] = sum[i - 1][j] + sum[i][j - 1];
      }
    }
    return sum[m - 1][n - 1];
  }

  /**
   * This is a regular divide and conquer way. The path to this point is the sum of to the left + to
   * the above points of it.
   * 
   * @param m
   * @param n
   * @return
   */
  public static int uniqRec(int m, int n) {
    if (m == 0 || n == 0) {
      return 1;
    }
    return uniqRec(m - 1, n) + uniqRec(m, n - 1);
  }

  /**
   * 
   * @param m
   * @param n
   * @return
   */
  public static int uniq2d(int m, int n) {
    int[][] F = new int[m][n];
    // F[0][0] = 1;
    for (int i = 0; i < m; ++i) {
      F[i][0] = 1;
    }
    for (int j = 0; j < n; ++j) {
      F[0][j] = 1;
    }
    for (int i = 1; i < m; ++i) {
      for (int j = 1; j < n; ++j) {
        F[i][j] = F[i - 1][j] + F[i][j - 1];
      }
    }
    return F[m - 1][n - 1];
  }
  
  /**
   * 
   * @param m
   * @param n
   * @return
   */
  public static int uniqRolling(int m, int n) {
    int[][] F = new int[2][n];
    for (int j = 0; j < n; ++j) {
      F[0][j] = 1;
//      F[1][j] = 1;
    }
    for (int i = 1; i < m; ++i) {
      for (int j = 0; j < n; ++j) {
        if (j == 0) {
          F[i%2][0] = 1;
        } else
          F[i%2][j] = F[(i-1)%2][j] + F[i%2][j-1];
      }
    }
    for (int i : F[0])
      System.out.print(i+" ");
    System.out.println();
    for (int i : F[1])
      System.out.print(i+" ");
    return F[(m-1)%2][n-1];
  }
}
