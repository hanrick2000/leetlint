package nineChap5_DP2;

public class DistinceSubsequnce {
  public static void main(String[] args) {
    String S = "rabibbit";
    String T = "rabbit";
    int ans = disSubseq1(S, T);
    System.out.println("I use simple 2 sequence DP: " + ans);

    ans = disSubseq2(S, T);
    System.out.println("I use 1d rolling DP: " + ans);
  }

  /**
   * 
   */
  public static int disSubseq1(String S, String T) {
    if (S == null || T == null || S.length() < T.length()) {
      return 0;
    }
    int m = S.length();
    int n = T.length();
    int[][] F = new int[2][n + 1];

    for (int i = 0; i < m; ++i) {
      F[i % 2][0] = 1;
    }
    for (int j = 1; j < n; ++j) {
      F[0][j] = 0;
    }

    for (int i = 1; i < m + 1; ++i) {
      for (int j = 1; j < n + 1; ++j) { // j < i && j < n + 1
        if (S.charAt(i - 1) == T.charAt(j - 1)) {
          F[i % 2][j] = F[(i - 1) % 2][j - 1] + F[(i - 1) % 2][j];
        } else {
          F[i % 2][j] = F[(i - 1) % 2][j];
        }
      }
    }

    return F[m % 2][n];
  }


  /**
   * 1d rolling DP, notice the F[j] means the previous row, so the inner loop should go from end to
   * front so as to use previous row's val without update
   * 
   * @param S
   * @param T
   * @return
   */
  public static int disSubseq2(String S, String T) {
    if (S == null || T == null || S.length() < T.length()) {
      return 0;
    }
    int m = S.length();
    int n = T.length();
    int[] F = new int[n + 1];
    F[0] = 1;  // null is a subsequence in any string.
    for (int i = 1; i < m + 1; ++i) {
      for (int j = n; j > 0; j--) {
        // if (i == 0) {
        // F[j] = 1; 
        // continue;
        // }
        F[j] += (S.charAt(i - 1) == T.charAt(j - 1) ? F[j - 1] : 0);
      }
    }

    return F[n];
  }

  /**
   * First trial, the logic is wrong, shouldn't look for pattern from some samples, but need to
   * THINK!!!
   * 
   * @param S
   * @param T
   * @return
   */
  public static int disSubseqWrong(String S, String T) {
    if (S == null || T == null || S.length() < T.length()) {
      return 0;
    }
    int m = S.length();
    int n = T.length();
    int[][] F = new int[m + 1][n + 1];

    for (int i = 0; i < m; ++i) {
      F[i][0] = 0;
    }
    for (int j = 0; j < n; ++j) {
      F[0][j] = 0;
    }

    char last = 0;
    for (int i = 1; i < m + 1; ++i) {
      for (int j = 1; j < i && j < n + 1; ++j) {
        if (S.charAt(i - 1) == T.charAt(j - 1)) {
          if (last == S.charAt(i - 1)) {
            F[i][j] = F[i - 1][j - 1] + 1;
          } else {
            F[i][j] = F[i - 1][j - 1];
          }

          last = S.charAt(i - 1);
        } else {
          if (j < n) {
            F[i][j] = F[i - 1][j - 1];
          } else {
            F[i][j] = 0;
          }
        }
      }
    }

    return F[m][n];
  }
}
