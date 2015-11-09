package TwoPointers;

import java.util.HashMap;
import java.util.Map;

/**
 * http://www.lintcode.com/en/problem/longest-substring-without-repeating-characters/
 * Created this class in TwoPointers at 8:03 PM, 11/8/2015.
 */
public class LongSubstringWithoutRepeatChar {
  public static void main(String[] args) {
    String string = "aab"; //"abcabcbb";  //"bbbbb"; //
    LongSubstringWithoutRepeatChar app = new LongSubstringWithoutRepeatChar();
    int ans = app.lengthOfLongestSubstringOn2(string);
    System.out.println(ans);
  }

  /**
   * Correct way to use the template
   * @param s
   * @return
   */
  public int lengthOfLongestSubstringOn(String s) {
    int ans = 0;
    if (s == null || s.length() == 0) {
      return ans;
    }
    int leng = s.length();
    Map<Character, Integer> map = new HashMap<>();
    for (int i = 0, j = 0; i < leng; ++i) {
      while (j < leng) {
        if (!map.containsKey(s.charAt(j))) {
          map.put(s.charAt(j), 1);
          ans = Math.max(ans, j - i + 1);  // since it is valid j, so need to add 1
          j++;
        } else {
          break;  // don't do ans update in invalid so I can prevent corner case: j = leng.
        }
      }
      map.remove(s.charAt(i));  // remove the ith element, since the windows moved forward, and map is used for dup check so remove, not decrement.
    }

    return ans;
  }

  /**
   * I followed the template, but did it in a wrong way.
   */
  public int lengthOfLongestSubstringOnWRONG(String s) {
    int ans = 0;
    if (s == null || s.length() == 0) {
      return ans;
    }
    int leng = s.length();
    Map<Character, Integer> map = new HashMap<>();
    for (int i = 0, j = 0; i < leng; ++i) {
      while (j < leng) {
        if (!map.containsKey(s.charAt(j))) {
          map.put(s.charAt(j), 1);
          ans = Math.max(ans, j - i + 1);
          j++;
        } else {
          //map.clear();
          break;
        }
      }
      ans = Math.max(ans, j - i);
      //map.put(s.charAt(i), map.get(s.charAt(i)) - 1);
      map.put(s.charAt(i), 0);
    }
    return ans;
  }

  /**
   * This is a more naive solution, with O(n^2)
   */
  public int lengthOfLongestSubstringOn2(String s) {
    int ans = 0;
    if (s == null || s.length() == 0) {
      return ans;
    }
    int leng = s.length();
    Map<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < leng; ++i) {
      int j;
      for (j = i; j < leng; ++j) {
        if (!map.containsKey(s.charAt(j))) {
          map.put(s.charAt(j), 1);
          ans = Math.max(ans, j-i+1);
        } else {
          //ans = Math.max(ans, j - i);
          break;
        }
      }
      //if (j == i + 1) ans = Math.max(ans, j - i);
      map.clear();
    }
    return ans;
  }

  /**
   * Failed in TLE, becasue this is O(n^3)
   *
   * @param s: a string
   * @return: an integer
   */
  public int lengthOfLongestSubstringOn3(String s) {
    int ans = 0;
    if (s == null || s.length() == 0) {
      return ans;
    }
    int leng = s.length();
    Map<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < leng; ++i) {
      for (int j = i; j < leng; ++j) {
        int k;
        for (k = i; k <= j; ++k) {
          if (!map.containsKey(s.charAt(k))) {
            map.put(s.charAt(k), 1);
            ans = Math.max(ans, k-i+1);  // better place to update ans to prevent corner case when k = j+1
          } else {
            //ans = Math.max(ans, k - i);  // not good to update in invalid
            break;
          }
        }
        //if (k == j + 1) ans = Math.max(ans, j - i);
        map.clear();
      }
    }
    return ans;
  }
}
