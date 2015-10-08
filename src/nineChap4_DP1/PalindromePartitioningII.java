package nineChap4_DP1;

/**
 * http://www.lintcode.com/en/problem/palindrome-partitioning-ii
 * 
 * @author tzhang
 *
 */
public class PalindromePartitioningII {
  public static void main(String[] args) {
    String s = "abc";
    int ans = minCut1st(s);
    System.out.println("I use DP : " + ans);
  }

  /**
   * 
   * @param s
   * @return
   */
  public static int minCut1st(String s) {
    if (s == null || s.length() < 2)
      return 0;
    int[] M = new int[s.length()+1];
    M[0] = 0; // ?
    for (int i = 0; i < s.length(); ++i) {
//      M[i] = i!=0? Integer.MAX_VALUE/100: M[i];
      M[i+1] = Integer.MAX_VALUE;
      for (int k = 0; k <= i; ++k) {
        if (M[k] != Integer.MAX_VALUE && isParlindrome(s.substring(k, i+1))) {
          System.out.println("parline " + s.substring(k, i+1));
          if (k == 0)  {
            M[i+1] = M[k];
            System.out.println((i+1)+" OK "+M[i+1]);
            break;
          }
          M[i+1] = Math.min(M[k]+1, M[i+1]);
        }
      }
    }
    for (int m : M) {
      System.out.println("the m is: " + m);
    }
    return M[s.length()];
  }
  
  private static boolean isParlindrome(String s) {
    if (s == null || s.length() == 0) 
      return true;
    int i = 0, j = s.length()-1;
    while (i < j) {
      if (s.charAt(i) != s.charAt(j)) {
        return false;
      }
      i++;
      j--;
    }
    return true;
  }
}
