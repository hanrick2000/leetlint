package nineChap5_DP2;

public class LCSubsequence {
  public static void main(String[] args) {
    String a = "bedaacbade";
    String b = "dccaeedbeb";
    int ans = longestCommonSubsequence(a, b);
    System.out.println("I use simple 2 sequence DP: " + ans);
    
    ans = longestCommonSubsequenceRolling(a,b);
    System.out.println("I use 2d rolling DP: " + ans);
  }

  public static int longestCommonSubsequence(String A, String B) {
    if (A == null || B == null || A.length() == 0 || B.length() == 0) {
      return 0;
    }
    int m = A.length();
    int n = B.length();
    int[][] F = new int[m + 1][n + 1];
    for (int i = 0; i < m + 1; ++i) {
      F[i][0] = 0;
    }
    for (int j = 0; j < n + 1; ++j) {
      F[0][j] = 0;
    }

    for (int i = 1; i < m + 1; ++i) {
      for (int j = 1; j < n + 1; ++j) {
        if (A.charAt(i - 1) == B.charAt(j - 1)) {
          F[i][j] = F[i - 1][j - 1] + 1;
        } else {
          F[i][j] = Math.max(F[i - 1][j], F[i][j - 1]);
        }
      }
    }

    return F[m][n];
  }


  public static int longestCommonSubsequenceRolling(String A, String B) {
    if (A == null || B == null || A.length() == 0 || B.length() == 0) {
      return 0;
    }
    int m = A.length();
    int n = B.length();
    int[][] F = new int[m + 1][n + 1];
    for (int j = 0; j < n + 1; ++j) {
      F[0][j] = 0;
    }

    for (int i = 1; i < m + 1; ++i) {
      F[i % 2][0] = 0;
      for (int j = 1; j < n + 1; ++j) {
        if (A.charAt(i - 1) == B.charAt(j - 1)) {
          F[i % 2][j] = F[(i - 1) % 2][j - 1] + 1;
        } else {
          F[i % 2][j] = Math.max(F[(i - 1) % 2][j], F[i % 2][j - 1]);
        }
      }
    }

    return F[m%2][n];
  }
}
