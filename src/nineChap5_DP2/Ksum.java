package nineChap5_DP2;

/**
 * http://www.lintcode.com/en/problem/k-sum/
 * Created at 2:00 PM on 11/25/15.
 */

public class Ksum {
  public static void main(String[] args) {
    int[] A = {0, 1, 2, 3, 5};
    int k = 2;
    int target = 5;
    int ans = 0;

    ans = kSum1(A, k, target);
    System.out.println("I use 9chap DP: " + ans);

    ans = kSum2(A, k, target);
    System.out.println("I use 2D DP: " + ans);

    ans = kSum3(A, k, target);
    System.out.println("I use 9chap DP: " + ans);
  }

  /**
   * 9 chap's 3D DP solution
   * 
   * @param A
   * @param k
   * @param target
   * @return
   */
  public static int kSum1(int[] A, int k, int target) {
    if (A == null || A.length == 0 || k > A.length) {
      return 0;
    }
    int aLen = A.length;
    int[][][] F = new int[aLen + 1][k + 1][target + 1];

    for (int i = 0; i < aLen + 1; ++i) {
      F[i][0][0] = 1;
    }

    for (int i = 1; i < aLen + 1; ++i) {
      for (int j = 1; j <= i && j < k + 1; j++) {
        for (int t = 1; t < target + 1; t++) {
          F[i][j][t] =
              ((t < A[i - 1]) ? 0 : F[i - 1][j - 1][t - A[i - 1]])
                  + F[i - 1][j][t]; // notice: the P? A:B + C must have () for the first part!:
                                    // (P?A:B)+C
        }
      }
    }

    return F[aLen][k][target];
  }

  /**
   * https://github.com/yuzhangcmu/LeetCode/blob/master/lintcode/dp/KSum.java
   * Seems not right: {0,1,2,5} --> 2???
   * @param A
   * @param k
   * @param target
   * @return
   */
  public static int kSum2(int[] A, int k, int target) {
    if (target < 0) {
      return -1;
    }
    int aLen = A.length;

    int[][] F = new int[aLen + 1][target + 1];
    for (int i = 0; i < aLen + 1; ++i) {
      F[i][0] = 1;
    }

    for (int i = 1; i < aLen + 1; ++i) {
      for (int j = 1; j < k + 1; ++j) {
        for (int t = 1; t < target + 1; ++t) {
          F[i][t] = ((t < A[i - 1]) ? 0 : F[i - 1][t - A[i - 1]]) + F[i][t];
        }
      }
    }

    return F[aLen][target];
  }

  public static int kSum3(int A[], int k, int target) {
    // write your code here
    if (target < 0) {
      return 0;
    }

    int len = A.length;

    int[][][] D = new int[len + 1][k + 1][target + 1];

    for (int i = 0; i < len + 1; ++i) {
      D[i][0][0] = 1;
    }

    for (int i = 1; i <= len; i++) {
      for (int j = 1; j <= i && j <= k; j++) {
        for (int t = 1; t <= target; t++) {
          D[i][j][t] = 0;
          if (t - A[i - 1] >= 0) {
            D[i][j][t] = D[i - 1][j - 1][t - A[i - 1]];
          }
          D[i][j][t] += D[i-1][j][t];
        }
      }
    }

    return D[len][k][target];
  }
}
