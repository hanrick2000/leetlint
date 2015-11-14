package JiuChap5_DynProg;

import java.util.Arrays;

/**
 * http://www.lintcode.com/en/problem/post-office-problem/
 * Created at 8:00 PM on 11/13/15.
 */
public class PostOffice {
  public static void main(String[] args) {
    PostOffice po = new PostOffice();
    int[] houses = new int[]{112,122,360,311,85,225,405,53,405,43,342,13,588,424,299,37,104,289,404,414}; // {1,2,3,4,5};
    int ans = po.postOffice(houses, 3);
    System.out.println(ans);
  }

  /**
   * @param A an integer array
   * @param k an integer
   * @return an integer
   */
  public int postOffice(int[] A, int k) {
    // Write your code  here
    int n = A.length;
    if (k == 0 || k >= n) {
      return 0;
    }
    Arrays.sort(A);  // !!!! important! otherwise the DP is wrong. Why?

    int[][] dis = init(A);
    int[][] P = new int[n+1][k+1];
    for (int i = 0; i <= n; ++i) {
      P[i][1] = dis[1][i]; //dis[1][n];
    }

    for (int nk = 2; nk <= k; ++nk) {
      for (int i = nk; i <= n; ++i) {
        P[i][nk] = Integer.MAX_VALUE;
        for (int j = 0; j < i; ++j) {
          if (P[i][nk] == Integer.MAX_VALUE || P[i][nk] > P[j][nk-1] + dis[j+1][i]) {
            P[i][nk] = P[j][nk-1] + dis[j+1][i];
          }
        }
      }
    }

    return P[n][k];
  }

  private static int[][] init(int[] A) {
    int n = A.length;

    int[][] dis = new int[n+1][n+1];
    for (int i = 1; i <= n; i++) {
      for (int j = i+1; j <= n; ++j) {
        for (int k = i; k <= j; ++k) {
          int mid = (i+j)/2;
          dis[i][j] += Math.abs(A[mid-1] - A[k-1]);
        }
      }
    }

    return dis;
  }

}