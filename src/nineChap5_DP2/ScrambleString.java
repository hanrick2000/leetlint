package nineChap5_DP2;

/**
 * http://www.lintcode.com/en/problem/scramble-string/ Created at 11:04 PM on 11/25/15.
 */
public class ScrambleString {
  public static void main(String[] args) {
    String s1 = "great", s2 = "rgtae";
    ScrambleString ss = new ScrambleString();
    boolean ans = ss.isScramble(s1, s2);
    System.out.println(ans);
  }

  /**
   * @param s1 A string
   * @param s2 Another string
   * @return whether s2 is a scrambled string of s1
   */
  public boolean isScramble(String s1, String s2) {
    // Write your code here
    if (s1.equals("") && s2.equals("")) {
      return false;
    }
    boolean ans = yuzhangDP(s1, s2);
    return ans;
  }

  /* ------------------------------------------------------------------------------------------------
   *      Memorization DP, yu's blog said it's O(N^4)
   * ------------------------------------------------------------------------------------------------
   */
  public boolean yuzhangDP(String s1, String s2) {
    if (s1 == null || s2 == null) {
      return false;
    }

    int len1 = s1.length();
    int len2 = s2.length();
    if (len1 != len2) {
      return false;
    }

    /**
     * 3rd param: the length of sub1, sub2 with length: 1 ~ sub1.length()
     */
    boolean[][][] P = new boolean[len1][len1][len1 + 1];

    for (int subLen = 1; subLen <= len1; ++subLen) {
      for (int i1 = 0; i1 <= len1 - subLen; ++i1) {
        for (int i2 = 0; i2 <= len1 - subLen; ++i2) {
          if (subLen == 1) {
            P[i1][i2][subLen] = s1.charAt(i1) == s2.charAt(i2);
            continue;
          }
          P[i1][i2][subLen] = false;
          //for (int k = 0; k <= subLen; ++k) {
          for (int k = 1; k < subLen; ++k) {
            P[i1][i2][subLen] = P[i1][i2][k] && P[i1 + k][i2 + k][subLen - k] ||
                P[i1][i2 + subLen - k][k] && P[i1 + k][i2][subLen - k];
            if (P[i1][i2][subLen] == true) {
              break;
            }
          }
        }
      }
    }

    return P[0][0][len1];
  }

/* ------------------------------------------------------------------------------------------------
 *      Recursion with Memory, yu's blog said it's O(N^4)
 * ------------------------------------------------------------------------------------------------
 */

  public boolean yuzhangMem(String s1, String s2) {
    int len1 = s1.length();
    int len2 = s2.length();
    if (len1 != len2) {
      return false;
    }

    int[][][] mem = new int[len1][len1][len1];
    for (int i = 0; i < len1; i++) {
      for (int j = 0; j < len1; ++j) {
        for (int k = 0; k < len1; ++k) {
          mem[i][j][k] = -1;  // -1 means unseted.
        }
      }
    }

    return recMem(s1, 0, s2, 0, len1, mem);
  }

  private boolean recMem(String s1, int ind1, String s2, int ind2, int leng, int[][][] mem) {
    if (leng == 1) {
      return s1.charAt(ind1) == s2.charAt(ind2);
    }

    int ret = mem[ind1][ind2][leng - 1];
    if (ret != -1) {  // memorization search : return if result in memory
      return ret == 1;
    }

    ret = 0;
    for (int l = 1; l < leng; ++l) {   // loop length of s1's length from 1 to leng-1 in recursion
      if (recMem(s1, ind1, s2, ind2, l, mem) && recMem(s1, ind1 + l, s2, ind2 + l, leng - l, mem)) {
        ret = 1;
        break;
      }
      if (recMem(s1, ind1, s2, ind2 + leng - l, l, mem) && recMem(s1, ind1 + l, s2, ind2, leng - l,
          mem)) {
        ret = 1;
        break;
      }
    }

    mem[ind1][ind2][leng - 1] = ret;
    return ret == 1;
  }

/* ------------------------------------------------------------------------------------------------
 *      Recursion with Prune, F[n] = 3F[n-1]=> F[n] = O(3^n)
 * ------------------------------------------------------------------------------------------------
 */

  public boolean fisherlei(String s1, String s2) {
    if (s1.length() == 1) {
      return s1.equals(s2);
    }

    int A[] = new int[26];
    for (int i = 0; i < s1.length(); ++i) {
      A[s1.charAt(i) - 'a']++;
    }
    for (int i = 0; i < s2.length(); ++i) {
      A[s2.charAt(i) - 'a']--;
    }
    for (int a : A) {
      if (a != 0) {
        return false;
      }
    }

    if (s1.length() == 1 && s2.length() == 1) {
      return true;
    }

    for (int i = 1; i < s1.length(); ++i) {
      boolean result =
          fisherlei(s1.substring(0, i), s2.substring(0, i)) && fisherlei(s1.substring(i),
              s2.substring(i));
      result =
          result || fisherlei(s1.substring(0, i), s2.substring(s2.length() - i)) && fisherlei(
              s1.substring(s1.length() - i),
              s2.substring(0, i));
      //return result;
      if (result == true) {
        return true;
      }
    }
    return false;
  }
}
