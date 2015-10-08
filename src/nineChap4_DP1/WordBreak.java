package nineChap4_DP1;

import java.util.HashSet;
import java.util.Set;

public class WordBreak {
  public static void main(String[] args) {
    String[] dictionary = new String[] {"a"}; // {"bc", "cd", "de", "fg", "abcd", "efg"}; //
                                              // {"programcree",
                                              // "programc",
                                              // "creek", "k"}; //
    // {"leet", "lint",
    // "li", "code"};
    Set<String> dict = new HashSet<String>();
    for (String s : dictionary) {
      dict.add(s);
    }

    String s = "a";
    boolean ans = false;
    ans = wordBreak1st(s, dict);
    System.out.println("\nI use DP for 1st trial: " + ans);

    ans = wordBreak2nd(s, dict);
    System.out.println("\nI use DP for 2nd trial: " + ans);

    ans = wordBreak3rd(s, dict);
    System.out.println("\nGanker's DP: " + ans);

    ans = wordBreak4th(s, dict);
    System.out.println("My lint code: " + ans);
  }

  /**
   * 1st trial: I'm so confused on state definition and substring right bound
   * 
   * @param s
   * @param dict
   * @return
   */
  public static boolean wordBreak1st(String s, Set<String> dict) {
    if (s == null || s.length() == 0) {
      return true;
    }
    boolean[] F = new boolean[s.length() + 1];
    F[0] = true;
    for (int i = 1; i < s.length(); ++i) {
      for (int k = 0; k < i; ++k) {
        if (k == 0) {
          if (F[k] == true && dict.contains(s.substring(k, i + 1))) {
            // System.out.println(s.substring(k, i + 1) + "," + k + "+" + i);
            F[i] = true;
          }
        } else if (F[k] == true && dict.contains(s.substring(k + 1, i + 1))) {
          F[i] = true;
          // System.out.println(s.substring(k + 1, i + 1) + "," + k + "+" + i);
          break;
        }
      }
    }
    // for (boolean b : F)
    // System.out.print(b + " ");
    return F[s.length() - 1];
  }

  /**
   * 2nd time: better understand on state define and substring
   * 
   * Fail TLE!!! Becasue I had done O(n**2) times substring. Substring time complexity is linear!
   * check Ganker's solution.
   * 
   * @param s
   * @param dict
   * @return
   */
  public static boolean wordBreak2nd(String s, Set<String> dict) {
    if (s == null || s.length() == 0) {
      return true;
    }
    boolean[] F = new boolean[s.length() + 1]; // string DP always leave 0th as no select always
                                               // true, so the DP space is len+1.
    F[0] = true;
    for (int i = 0; i < s.length(); ++i) { // notice the init and end of i for states
      for (int k = 0; k <= i; ++k) {
        if (F[k] == true && dict.contains(s.substring(k, i+1))) {
          // System.out.println(s.substring(k, i) + "," + k + "+" + i);
          F[i+1] = true;
        }
      }
    }
    // for (boolean b : F)
    // System.out.print(b + " ");
    return F[s.length()];
  }

  /**
   * Failed TLE
   * 
   * @param s
   * @param dict
   * @return
   */
  public static boolean wordBreak3rd(String s, Set<String> dict) {
    if (s == null || s.length() == 0) {
      return true;
    }
    boolean[] F = new boolean[s.length() + 1]; // string DP always leave 0th as no select always
                                               // true, so the DP space is len+1.
    F[0] = true;
    for (int i = 1; i < s.length() + 1; ++i) { // notice the init and end of i for states
      StringBuilder sb = new StringBuilder(s.substring(0, i));
      // sb = s.substring(0, i); // they are different type, should init sb in new!
      for (int k = 0; k < i; ++k) {
        if (F[k] == true && dict.contains(sb.toString())) {
          // System.out.println(s.substring(k, i) + "," + k + "+" + i);
          F[i] = true;
          break;
        }
        sb.deleteCharAt(0);
      }
    }
    // for (boolean b : F)
    // System.out.print(b + " ");
    return F[s.length()];
  }

  /**
   * 9 chap solution. Finally pass the TLE, the real problem doesn't really come from substring but
   * the range of k.
   * 
   * @param s
   * @param dict
   * @return
   */
  public static boolean wordBreak4th(String s, Set<String> dict) {
    // write your code here
    if (s == null || s.length() == 0)
      return true;
    boolean[] F = new boolean[s.length() + 1];
    F[0] = true;
    int maxLen = maxWord(dict);
    for (int i = 0; i < s.length(); ++i) {
      int k = i - maxLen + 1 >= 0 ? i - maxLen + 1 : 0;
      for (; k <= i; ++k) {
        // System.out.println("the limit of k is: " + (k) + ": " + sb.substring(k));
        if (F[k] == true && dict.contains(s.substring(k, i + 1))) {
          // System.out.println(s.substring(k, i + 1) + "," + k + "+" + i);
          F[i + 1] = true;
          break;
        }
      }
    }
    return F[s.length()];
  }

  /**
   * Find the longest word in dict, normally smaller than 22, and the time is O(dict size).
   * 
   * @param dict
   * @return
   */
  private static int maxWord(Set<String> dict) {
    int maxLen = 0;
    if (dict == null)
      return maxLen;
    for (String s : dict) {
      maxLen = Math.max(s.length(), maxLen);
    }
    return maxLen;
  }
  /*
   * public static boolean wordBreak(String s, Set<String> dict) { if (s == null || s.length() == 0)
   * return true; boolean[] res = new boolean[s.length() + 1]; res[0] = true; for (int i = 1; i <
   * s.length(); i++) { StringBuilder str = new StringBuilder(s.substring(0, i + 1)); for (int j =
   * 0; j <= i; j++) { if (res[j] && dict.contains(str.toString())) { res[i + 1] = true; // break; }
   * str.deleteCharAt(0); } } return res[s.length()]; }
   */
}
