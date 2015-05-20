package freq3_tony;
/*
 * 就是最简单的DNA sequence alignment
 * 但是有3个操作: insert, remove, replace
 * 所以有以下几种情况:
 * dp[i][j] 代表的opt substructure, 即从1...i和1...j已经是答案了.
 * 1. x!=y, x insert y into word1, then dp[i][j-1]+1; 因为只剩下i和j-1要排
 * 2. x!=y, x remove from word1, then dp[i-1][j] + 1; 因为只剩下i-1和j要排
 * 3. x!=y, x replace y into word1, then dp[i-1][j-1] + 1; 因为只剩下i-1和j-1要排.
 * 
 * 4. x == y, then dp[i-1][j-1] + 0;
 */
public class Editdistance {
  public int dp(String word1, String word2) {
    if (word1 == null || word2 == null) 
      throw new IllegalArgumentException("wrong inputs");
    // 注意String的话要用length(), 而不是直接length. 
      // 而且index是从1...i/j的. 所以要word1.length()+1;
    int[][] opt = new int[word1.length()+1][word2.length()+1];
    // init the base opt, 意思是从word1的i...INF, 和word2的0开始排. 当然是i个调整, 选cost最小的operation,
    for (int i = 1; i < word1.length(); i++) {
      opt[i][0] = i ;
    }
    // 因为这里3个operation的cost一样, 所以随便选.
    for (int j = 1; j < word2.length(); j++) {
      opt[0][j]= j;
    }
    
    // dp
    for (int i = 1; i < word1.length() + 1; i++) {
      for (int j = 1; j < word2.length()+1; j++) {
        opt[i][j] = Math.min(opt[i-1][j-1] + (word1.charAt(i-1)==word2.charAt(j-1)?0:1) ,
            Math.min(opt[i-1][j] + 1, opt[i][j-1] + 1));
      }
    }
    
    for (int i = 0; i< word1.length()+1; i++) {
      for (int j = 0; j < word2.length()+1; j++) {
        
      }
    }
    return opt[word1.length()][word2.length()];
  }
  
  public static void main(String[] args) {
    Editdistance ed = new Editdistance();
    System.err.println(ed.dp("bbbb", "aaaa"));
  }
}
