package JiuChap4_TwoPointers;

import java.util.HashMap;

/**
 * http://www.lintcode.com/en/problem/minimum-window-substring/
 * Created this class in TwoPointers at 7:38 PM, 11/8/2015.
 */
public class MinWindowSubstring {
  public static void main(String[] args) {
    String source = "ADOBECODEBANC";
    String target = "ABC";
    MinWindowSubstring ms = new MinWindowSubstring();
    String output = ms.minWindow(source, target);
    System.out.println(output);
  }

  /**
   * http://xmruibi.github.io/2015/10/08/minimum-window-substring/
   * http://articles.leetcode.com/2010/11/finding-minimum-window-in-s-which.html
   */
  public String minWindow(String source, String target) {
    // preload for target checking
    if (source == null || source.length() == 0 || target == null || target.length() == 0) return "";

    int tarLen = target.length();
    HashMap<Character, Integer> dict = new HashMap<>();  // target hash
    for (char c : target.toCharArray())
      dict.put(c, dict.containsKey(c) ? dict.get(c) + 1 : 1);

    int hitCount = 0; // record current window hits how many characters in target
    int l = 0; // record the left bound of current window
    int minWindow = source.length() + 1; // initial the minimum window length
    int start = 0;
    for (int r = 0; r < source.length(); r++) {
      char cur = source.charAt(r);
      // if current char is not in dict, continue
      if (!dict.containsKey(cur)) continue;

      dict.put(cur, dict.get(cur) - 1);
      if (dict.get(cur) >= 0) hitCount++;

      // check the windows has amount of this char more than it in target string
      // loop until the amount back to normal, but always reduce the prev index char
      while (hitCount == tarLen) {
        if (minWindow > r - l + 1) {
          start = l;
          minWindow = r - l + 1;
        }
        char prevChar = source.charAt(l);
        if (dict.containsKey(prevChar)) {
          dict.put(prevChar, dict.get(prevChar) + 1);  // beautiful part: dict recover, means I haven't found prevChar to start next round
          if (dict.get(prevChar) > 0) hitCount--;
        }
        l++;
      }
    }
    //
    if (minWindow > source.length()) return "";
    return source.substring(start, start + minWindow);
  }

  /**
   * How to improve from O(256n) to O(n)?
   * MESSED up here.
   */
  public String minWindowFail(String source, String target) {
    int ans = Integer.MAX_VALUE;
    int[] Thash = new int[256];
    int[] Shash = new int[256];
    int[] Diff = new int[256];
    int cnt = 0;
    int charSize = toTargetHash(Thash, target);
    int leng = source.length();
    String minStr = "";
    int j = 0;
    for (int i = 0; i < leng; ++i) {
      while (cnt < charSize && j < leng) {
        char charj = source.charAt(j);
        Shash[charj]++;
        Diff[charj] = Shash[charj] - Thash[charj];
        if (Diff[charj] == 0) {
          cnt++;
        }
        j++;
      }
      if (cnt == charSize) {
        if (ans > j - i) {
          ans = Math.min(ans, ans);
          minStr = source.substring(i, j);
        }
      }
      int diff = Shash[source.charAt(j)] - Thash[source.charAt(j)];
      Shash[source.charAt(j)]--;
      //Diff[source.charAt(j)] = Shash[source.charAt(j)] - Thash[source.charAt(j)];
      if (cnt == charSize && diff == 0) {
        Diff[source.charAt(j)] = Shash[source.charAt(j)] - Thash[source.charAt(j)];
        cnt--;
      }
    }
    return minStr; // stub
  }

  /**
   * @param source: A string
   * @param target: A string
   * @return: A string denote the minimum window
   * Return "" if there is no such a string
   */
  public String minWindowO256n(String source, String target) {
    // write your code
    int ans = Integer.MAX_VALUE;
    int[] Thash = new int[256];
    int[] Shash = new int[256];
    toTargetHash(Thash, target);
    int leng = source.length();
    String minStr = "";
    int j = 0;
    for (int i = 0; i < source.length(); ++i) {
      while (!valid(Shash, Thash) && j < leng) {
        Shash[source.charAt(j)]++;
        j++;
      }
      if (valid(Shash, Thash)) {
        if (ans > j - i) {
          ans = Math.min(ans, j - i);
          minStr = source.substring(i, j);
        }
      }
      Shash[source.charAt(i)]--;
    }
    return minStr;
  }

  private static boolean valid(int[] Shash, int[] Thash) {
    for (int i = 0; i < 256; ++i) {
      if (Shash[i] < Thash[i]) {
        return false;
      }
    }
    return true;
  }

  /**
   * Init the target hash, return the different char in target.
   */
  private static int toTargetHash(int[] Thash, String target) {
    int cnt = 0;
    for (int i = 0; i < target.length(); ++i) {
      Thash[target.charAt(i)]++;
    }
    for (int j = 0; j < 256; ++j) {
      if (Thash[j] > 0) {
        cnt++;
      }
    }
    return cnt;
  }
}
