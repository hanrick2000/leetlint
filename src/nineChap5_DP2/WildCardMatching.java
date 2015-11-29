package nineChap5_DP2;

/*-
 * http://www.lintcode.com/en/problem/wildcard-matching/
 * Created at 9:25 AM on 11/29/15.
 */
public class WildCardMatching {
  public static void main(String[] args) {
    String s = "zab";
    String p = "x?*";
    boolean ans = new WildCardMatching().isMatch(s, p);
    System.out.println(ans);
  }

  /*-
   * http://www.cnblogs.com/yuzhangcmu/p/4116153.html
   * http://m4tiku.duapp.com/report?pid=123
   * I understand the DP function, but why j == 0, curFlag is false?
   * @param s: A string
   * @param p: A string includes "?" and "*"
   * @return: A boolean
   */
  public boolean isMatch(String s, String p) {
    // write your code here
    boolean[] preFlag = new boolean[s.length() + 1];
    boolean[] curFlag = new boolean[s.length() + 1];
    int starCnt = 0;
    for (char pc : p.toCharArray()) {
      if (pc == '*') {
        starCnt++;
      }
      if (starCnt > s.length()) {
        return false;
      }
    }
    preFlag[0] = true;
    for (int i = 0; i < p.length(); ++i) {
      for (int j = 0; j <= s.length(); ++j) {
        if (p.charAt(i) == '?') {
          curFlag[j] = j == 0 ? false : preFlag[j - 1];
        } else if (p.charAt(i) == '*') {
          curFlag[j] = j == 0 ? preFlag[j] : curFlag[j - 1] || preFlag[j];
        } else {
          curFlag[j] = (j == 0 || p.charAt(i) != s.charAt(j - 1)) ? false : preFlag[j - 1];
        }
      }
      boolean[] tmp = preFlag;
      preFlag = curFlag;
      curFlag = tmp;
    }
    return preFlag[s.length()];
  }
}
