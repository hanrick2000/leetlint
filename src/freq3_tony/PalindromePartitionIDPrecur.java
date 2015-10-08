package freq3_tony;

import java.util.*;

/**
 * PalindromePartition I
 * N00t的第一个做法: DP记录所有parlindrom的substring. 然后recursion根据DP来判断是否parce.
 * @author tzhang
 *
 */
public class PalindromePartitionIDPrecur {

  /**
   * Build the opt[i][j], 意思是i..j是一个palindrome 从下往上. 即1位的默认是palindrome. 然后2位, ...
   * 
   * @param s
   * @return
   */
  public static boolean[][] palindromeTbl(String s) {
    boolean[][] T = new boolean[s.length()][s.length()];
    for (int i = 0; i < s.length(); ++i) {
      T[i][i] = true;
    }
    for (int i = 1; i < s.length(); ++i) {  // 注意他是2个都要跑, 例如aab里面的aa.
      // if even
      int l = i - 1, r = i;
      while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
        T[l--][r++] = true;
      }
      // if odd
      l = i - 1;
      r = i + 1;
      while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
        T[l--][r++] = true;
      }
    }
    return T;
  }

  /**
   * 
   * @param s
   * @param left
   * @param T
   * @param result
   */
  private static void partitionHelper(String s, int left, boolean[][] T,
      List<List<String>> result) {
    if (left == s.length()) {
      result.add(new ArrayList<String>());
    }
    for (int i = left; i < s.length(); ++i) {
      if (T[left][i]) {
        List<List<String>> temp = new ArrayList<List<String>>();
        partitionHelper(s, i + 1, T, temp);
        for (List<String> res : temp) {
          res.add(0, s.substring(left, i + 1));
        }
        result.addAll(temp);
      }
    }
  }
  
  public static List<List<String>> partition(String s) {
    List<List<String>> ans = new ArrayList<List<String>>();
    partitionHelper(s, 0, palindromeTbl(s), ans);
    return ans;
  }
  
  public static void main(String[] args) {
//    System.out.println(partition("aabaa"));
    String input = "aabaa";
    boolean[][] tbl = palindromeTbl(input);
    System.out.println(tbl[0][input.length()-1]);
  }

}
