package nineChap5_DP2;

public class EditDistance {
  public static void main(String[] args) {
    String a = "mart";
    String b = "karma";
    int ans = editDistanceDP(a,b);
    System.out.println("I use DP regular: " + ans);
  }

  public static int editDistanceDP(String word1, String word2) {
    if (word1 == null || word1.length() == 0) {
      return word2.length();
    }
    if (word2 == null || word2.length() == 0) {
      return word1.length();
    }
    int[][] F = new int[word1.length()+1][word2.length()+1]; // F[i][j] means the edit value of i elem, j elem.
    
    F[0][0] = 0;
    for (int i = 1; i < word1.length()+1; ++i) {
      F[i][0] = i;
    }
    for (int j = 1; j < word2.length()+1; ++j) {
      F[0][j] = j;
    }
    
    for (int i = 1; i < word1.length()+1; ++i) {
      for (int j = 1; j < word2.length()+1; ++j) {
        if (word1.charAt(i-1) == word2.charAt(j-1)) {
          F[i][j] = F[i-1][j-1];
        }
        else {
          F[i][j] = Math.min(Math.min(F[i-1][j], F[i][j-1]), F[i-1][j-1]) + 1;
        }
      }
    }
    
    return F[word1.length()][word2.length()];
  }
}
