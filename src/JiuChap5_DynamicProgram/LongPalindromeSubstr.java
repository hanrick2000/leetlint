package JiuChap5_DynamicProgram;

/**
 * Created at 3:21 PM on 11/12/15.
 */
public class LongPalindromeSubstr {
  public static void main(String[] args) {
    String a = "abcdzdcab";
    String ans;
    LongPalindromeSubstr lps = new LongPalindromeSubstr();
    ans = lps.longestPalindromeBetter(a);
    System.out.println(ans);
  }

  /**
   * http://articles.leetcode.com/2011/11/longest-palindromic-substring-part-i.html
   * @param s input string
   * @return the longest palindromic substring
   */
  public String longestPalindrome(String s) {
    // Write your code here
    int[][] P = new int[s.length()][s.length()];

    int longest = 1;
    int beg = 0;

    for (int i = 0; i < s.length(); ++i) {
      P[i][i] = 1;
      if (i < s.length()-1 && s.charAt(i) == s.charAt(i+1)) {
        P[i][i+1] = 2;
        longest = 2;
        beg = i;
      }
    }
    //int i;
    //for (i = 0; i < s.length()-1; ++i) {
    //  for (int j = i; j < s.length(); ++j) {
    //    if (i+1 <= j-1 && P[i+1][j-1] >= longest && (s.charAt(i)==s.charAt(j))) {
    //      P[i][j] = P[i+1][j-1] + 2;
    //      longest+=2;
    //      beg = i;
    //    }
    //  }
    //}

    for (int len = 3; len <= s.length(); ++len) {
      for (int i = 0; i < s.length() - len + 1; ++i) {
        int j = i + len - 1;
        if (P[i+1][j-1] > 0 && s.charAt(i) == s.charAt(j)) {
          P[i][j] = P[i+1][j-1] + 2;
          beg = i;
          longest = P[i][j];
        }
      }
    }
    return s.substring(beg, beg+longest);
  }

  /**
   * http://articles.leetcode.com/2011/11/longest-palindromic-substring-part-i.html
   * @param s input string
   * @return the longest palindromic substring
   */
  public String longestPalindromeBetter(String s) {
    // Write your code here
    if (s == null || s.length() == 0) {
      return s;
    }
    String longest = s.substring(0,1);

    for (int i = 0; i < s.length(); ++i) {
      String p1 = expandCenter(s, i, i);
      longest = p1.length() > longest.length() ? p1 : longest;
      String p2 = expandCenter(s, i, i+1);
      longest = p1.length() > longest.length() ? p2 : longest;
    }

    return longest;
  }

  private String expandCenter(String s, int l, int r) {
    int leng = s.length();
    while (l >= 0 && r < leng && s.charAt(l) == s.charAt(r)) {
      l--;
      r++;
    }
    return s.substring(l+1, r);
  }
}
