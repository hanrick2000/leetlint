package nineChap5_DP2;

/**
 * http://www.lintcode.com/en/problem/k-sum/ Created at 2:00 PM on 11/25/15.
 */

public class Ksum {
  public static void main(String[] args) {
    int[] A = {1,4,7,10,12,15,16,18,21,23,24,25,26,29}; //{0, 1, 2, 3, 5};
    int k = 5;
    int target = 113;
    int ans = -1;

    ans = kSumWRONG(A, k, target);
    System.out.println("I use 9chap DP: " + ans);

    ans = kSum2(A, k, target);
    System.out.println("I use 2D DP: " + ans);

    ans = kSum3(A, k, target);
    System.out.println("I use 9chap DP: " + ans);

    ans = kSumYu(A, k, target);
    System.out.println("Cmu Yu: " + ans);

    ans = kSum(A, k, target);
    System.out.println("jiuzhang: " + ans);
  }

  /**
   * 9 chap's 3D DP solution
   */
  public static int kSumWRONG(int[] A, int k, int target) {
    if (A == null || A.length == 0 || k > A.length) {
      return 0;
    }
    int aLen = A.length;
    int[][][] F = new int[aLen + 1][k + 1][target + 1];

    for (int i = 0; i < aLen + 1; ++i) {
      F[i][0][0] = 1;
    }

    for (int i = 1; i < aLen + 1; ++i) {
      for (int j = 1; j < k + 1; j++) {
        for (int t = 1; t < target + 1; t++) {
          F[i][j][t] =
              ((t < A[i - 1]) ? F[i - 1][j][t] : (F[i - 1][j - 1][t - A[i - 1]])
                  + F[i - 1][j][t]); // notice: the P? A:B + C must have () for the first part!:
          // P?A:(B+C)
        }
      }
    }

    return F[aLen][k][target];
  }

  /**
   * https://github.com/yuzhangcmu/LeetCode/blob/master/lintcode/dp/KSum.java Seems not right:
   * {0,1,2,5} --> 2???
   */
  public static int kSum2(int[] A, int k, int target) {
    if (target < 0) {
      return -1;
    }
    int aLen = A.length;

    int[][] F = new int[k + 1][target + 1];
    F[0][0] = 1;

    for (int i = 1; i < aLen + 1; ++i) {
      //for (int j = 1; j < k + 1; ++j) {  WRONG2: orders matter!!!
      //  //for (int t = 1; t < target + 1; ++t) {  WRONG1: should reversely loop, @zeroPack
      //  for (int t = target; t >= 0; t--) {
      for (int t = target; t > 0; t--) {
        for (int j = 1; j <= k; j++) {
          F[j][t] = t < A[i - 1] ? F[j][t] : (F[j - 1][t - A[i - 1]] + F[j][t]);
        }
      }
    }

    return F[k][target];
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
      for (int j = 1; j <= k; j++) {
        for (int t = 1; t <= target; t++) {
          D[i][j][t] = 0;
          if (t - A[i - 1] >= 0) {
            D[i][j][t] = D[i - 1][j - 1][t - A[i - 1]];
          }
          D[i][j][t] += D[i - 1][j][t];
        }
      }
    }

    return D[len][k][target];
  }

  /**
   * @param A: an integer array.
   * @param k: a positive integer (k <= length(A))
   * @param target: a integer
   * @return an integer
   */
  public static int kSumYu(int A[], int k, int target) {
    // write your code here
    if (target < 0) {
      return 0;
    }

    int len = A.length;

    int[][][] D = new int[len + 1][k + 1][target + 1];

    for (int i = 0; i <= len; i++) {
      for (int j = 0; j <= k; j++) {
        for (int t = 0; t <= target; t++) {
          if (j == 0 && t == 0) {
            // select 0 number from i to the target: 0
            D[i][j][t] = 1;
          } else if (!(i == 0 || j == 0 || t == 0)) {
            D[i][j][t] = D[i - 1][j][t];
            if (t - A[i - 1] >= 0) {
              D[i][j][t] += D[i - 1][j - 1][t - A[i - 1]];
            }
          }
        }
      }
    }

    return D[len][k][target];
  }

  public static int kSum(int A[], int k, int target) {
    int n = A.length;
    int[][][] f = new int[n + 1][k + 1][target + 1];
    for (int i = 0; i < n + 1; i++) {
      f[i][0][0] = 1;
    }
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= k && j <= i; j++) {
        for (int t = 1; t <= target; t++) {
          f[i][j][t] = 0;
          if (t >= A[i - 1]) {
            f[i][j][t] = f[i - 1][j - 1][t - A[i - 1]];
          }
          f[i][j][t] += f[i - 1][j][t];
        } // for t
      } // for j
    } // for i
    return f[n][k][target];
  }
}
