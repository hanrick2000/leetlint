package nineChap5_DP2;

/**
 * http://www.lintcode.com/en/problem/regular-expression-matching/
 * Created at 9:59 AM on 11/29/15.
 */
public class RegularExpressionMatching {
  public static void main(String[] args) {
    String s = "aaaab";
    String p = "a*b";
    boolean ans = new RegularExpressionMatching().isMatch(s,p);
    System.out.println(ans);
  }
  /**
   * @param s: A string
   * @param p: A string includes "." and "*"
   * @return: A boolean
   */
  public boolean isMatch(String s, String p) {
    // write your code here

    // Ganker recursion
    //return gankerRec(s,p,0,0);

    // hehejun's DP
    return heheDP(s, p);
  }

  /**
   * Ganker's explanation is the best.
   * @param s
   * @param p
   * @param i
   * @param j
   * @return
   */
  private boolean gankerRec(String s, String p, int i, int j) {
    if (j == p.length()) {
       return i == s.length();
    }

    if (j == p.length()-1 || p.charAt(j+1) != '*') {
      if (i == s.length() || s.charAt(i)!=p.charAt(j) && p.charAt(j) != '.') {
        return false;
      }
      else {
        return gankerRec(s,p,i+1,j+1);
      }
    }
    // p.charAt(j+1) == '*'
    while (i < s.length() && (p.charAt(j) == '.' || s.charAt(i) == p.charAt(j))) {
      if (gankerRec(s,p,i,j+2)) {
        return true;
      }
      i++;
    }

    return gankerRec(s,p,i,j+2);
  }

  /**
   * http://hehejun.blogspot.com/2014/11/leetcoderegular-expression-matching_4.html
   * So clean and elegant DP!
   * @param s
   * @param p
   * @return
   */
  private boolean heheDP(String s, String p) {
    if (s == null || p == null) {
      return false;
    }
    int lens = s.length();
    int lenp = p.length();
    boolean[][] F = new boolean[lenp+1][lens+1];
    F[0][0] = true;
    for (int i = 0; i < lenp; ++i) {
      if (p.charAt(i) != '*') {
        F[i+1][0] = false;
      }
      else {
        F[i+1][0] = F[i-1][0];
      }
    }

    for (int i = 0; i < lenp; ++i) {
      for (int j = 0; j < lens; ++j) {
        if (p.charAt(i) == s.charAt(j) || p.charAt(i) == '.') {
          F[i+1][j+1] = F[i][j];
        }
        else if (p.charAt(i) == '*') {
          F[i+1][j+1] = F[i][j+1] || F[i-1][j+1] ||
              (F[i+1][j] && s.charAt(j) == p.charAt(i-1)
                  || p.charAt(i-1) == '.');
        }
      }
    }
    return F[lenp][lens];
  }
}
