package JiuChap4_TwoPointers;

import java.util.HashMap;
import java.util.Map;

/**
 * http://www.lintcode.com/en/problem/longest-substring-with-at-most-k-distinct-characters/ Created
 * this class in JiuChap4_TwoPointers at 9:33 AM, 11/10/2015.
 */
public class LengthLongSubstringKdistinct {
  public static void main(String[] args) {
    String s = "b"; //"eqgkcwGFvjjmxutystqdfhuMblWbylgjxsxgnoh"; //"eceba";  //
    int k = 1; //16; //3; //
    LengthLongSubstringKdistinct lsk = new LengthLongSubstringKdistinct();
    int ans = lsk.lengthOfLongestSubstringKDistinctWHU(s, k);
    System.out.println(ans);
  }

  public int lengthOfLongestSubstringKDistinctBUGGY(String s, int k) {
    // write your code here
    int ans = 0;
    int j = 0;
    Map<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < s.length(); ++i) {
      while (j < s.length()) {
        char chj = s.charAt(j);
        if (map.size() <= k) {
          if (map.containsKey(chj)) {
            map.put(chj, map.get(chj) + 1);
            ans = Math.max(ans, j - i + 1);
          } else {
            int cnt = j - i;
            if (map.size() < k-1)  cnt++;
            ans = Math.max(ans, cnt);
            map.put(chj, 1);
          }
          j++;
        } else {
          //ans = Math.max(ans, j - i);
          break;
        }
      }
      char chi = s.charAt(i);
      if (map.get(chi) > 1) {
        map.put(chi, map.get(chi) - 1);
      } else {
        map.remove(chi);
      }
    }
    return ans;
  }

  /**
   * http://blog.csdn.net/whuwangyi/article/details/42451289 Same idea as Rui Bi's minimum size
   * substring contains target string.
   */
  public int lengthOfLongestSubstringKDistinctWHU(String s, int k) {
    // write your code here
    int ans = 0;
    int i = 0;
    Map<Character, Integer> map = new HashMap<>();
    for (int j = 0; j < s.length(); ++j) {
      char ch = s.charAt(j);
      if (map.containsKey(ch)) {
        map.put(ch, map.get(ch) + 1);
      } else {
        map.put(ch, 1);
        while (map.size() > k) {  // the key is to maintain the constraint: map.size().
          char preChar = s.charAt(i++);
          if (map.get(preChar) > 1) {
            map.put(preChar, map.get(preChar) - 1);
          } else {
            map.remove(preChar);
          }
        }
      }
      ans = Math.max(ans, j - i + 1);
    }
    return ans;
  }

  /**
   * @param s : A string
   * @return : The length of the longest substring that contains at most k distinct characters.
   */
  public int lengthOfLongestSubstringKDistinctWRONG(String s, int k) {
    // write your code here
    int ans = 0;
    int j = 0;
    Map<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < s.length(); ++i) {
      while (j < s.length()) {
        if (map.size() <= k) {
          map.put(s.charAt(j), map.containsKey(s.charAt(j)) ? map.get(s.charAt(j)) + 1 : 1);
          ans = Math.max(ans, j - i);
          j++;
          continue;
        } else {
          map.put(s.charAt(i), map.containsKey(s.charAt(i)) ? map.get(s.charAt(i)) - 1 : 0);
          break;
        }
      }
    }
    return ans;
  }
}
