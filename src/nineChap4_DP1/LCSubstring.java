package nineChap4_DP1;

public class LCSubstring {
  public static void main(String[] args) {
    String a = "ABCD";
    String b = "CBCE";
    int ans = longestCommonSubstring(a, b);
    System.out.println("I use simple 2 sequence DP: " + ans);

  }

  public static int longestCommonSubstring(String A, String B) {
    if (A == null || B == null || A.length() == 0 || B.length() == 0) {
      return 0;
    }
    int m = A.length();
    int n = B.length();
    int[][] F = new int[2][n + 1];
    int global = 0;
    for (int j = 0; j < n + 1; ++j) {
      F[0][j] = 0;
    }
    for (int i = 1; i < m+1; ++i) {
      F[i%2][0] = 0;
      for (int j = 1; j < n+1; ++j) {
        if (A.charAt(i-1) == B.charAt(j-1)) {
          F[i%2][j] = F[(i-1)%2][j-1] + 1;
        }
        else {
          F[i%2][j] = 0;
        }
        global = Math.max(F[i%2][j], global);
      }
    }
    
    return global;
  }
}
