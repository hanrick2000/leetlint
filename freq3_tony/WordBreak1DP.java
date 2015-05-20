package freq3_tony;

import java.util.*;

/**
 * 虽然勉强搞出来word break I. 但是效率很低! 最快的方法是DP. 这里是I. 即只要判断是否可以break成dict组成的substring. wordBreak
 * II方在另一个class里面.
 * 
 * @author tzhang
 *
 */
public class WordBreak1DP {
  /**
   * Programcreek的DP解法. 和recursion无关. 简单直白. t[i] 表示0...i是可以segment的
   * 
   * 这里面也是有设计的. 怎么个判断的顺序, 是continue还是break?
   * O(n*dict_size) time, O(n) space. 所以dict小于string size这样好.
   * @param s
   * @param dict
   * @return
   */
  private static boolean wordBreakIprogcreek(String s, Set<String> dict) {
    boolean[] t = new boolean[s.length() + 1]; // 为什么要加1?
    t[0] = true; // 为什么init t[0]=1? 如果第一个不可以seg的话? Ans: 因为下面的for loop第一个就判断, 不然就进行不下去了.

    for (int i = 0; i < s.length(); i++) {
      // 当目前没match的时候, 换成下一个substring
      if (!t[i]) {
        continue;
      }
      // 拿出来每一个dict的字来和substring对比.
      for (String a : dict) {
        int len = a.length();
        int end = i + len;
        // 如果因为长度已经超出string的length, 即当前不可能match, 换下一个pattern
        if (end > s.length()) {
          continue;
        }
        // 如果当前match"过了", 换下一个pattern. 因为要找"全部的segment"
        if (t[end])
          continue;
        // 如果当前match了, 就mark.
        if (s.substring(i, end).equals(a)) {
          t[end] = true; // DP的memory
        }
      }
    }
    return t[s.length()]; // 代表0~s.length()的string可以被segment
  }

  /**
   * N00t的第一个DP解法: 2D DP. O(n3)time, O(n2) space. 但是还是有redudent.
   * 
   * 不过这是一个不要recursion的DP! 因为是Buttom-up!!!
   * 
   * @param s
   * @param dict
   * @return
   */
  private static boolean isSegmented1N00t(String s, Set<String> dict) {
    int n = s.length();
    if (n < 1)
      return false;

    // T[i] == true IFF s(0...i) is segmentable according to the dict.
    boolean[][] seg = new boolean[n][n];
    for (int len = 0; len < n; ++len) {
      for (int i = 0; i < n - len; ++i) {
        int j = i + len;
        // 第一种情况: 这个s(i..j)就是可以segment的
        if (dict.contains(s.substring(i, j + 1))) {
          seg[i][j] = true;
          continue; // 这里是因为只要自身可以seg, 也就不用分开看2个segment可不可以了. 所以continue.
        }
        // 第二种情况: 这个s(i..j)本身不是, 但其子可以. Q: 为什么不用recursion? 因为是buttom-up!
        for (int k = i; k < j; ++k) {
          if (seg[i][k] && seg[k + 1][j]) {
            seg[i][j] = true;
            break; // 这里只要确定是否可以用2个segment来拼, 所以不用continue了.
          }
        }
      }
    }

    return seg[0][n - 1];
  }

  /**
   * N00t的1D dp解法. 注意这时候的复杂度和programcreek是不一样的.
   * O(n2) time, O(n)space
   * 所以dict大于string size这样好.
   * @param s
   * @param dict
   * @return
   */
  private static boolean isSegmentedN00t2(String s, Set<String> dict) {
    int n = s.length();
    if (n < 1)
      return false;
    // 这里的opt[]设计和programcreek一样. 但是algs不同.
    boolean[] opt = new boolean[n];
    for (int i = 0; i < n; ++i) {
      // update opt[0..i], if already true. 但是为什么不是只要判断dict.contain?
      opt[i] = (opt[i] || dict.contains(s.substring(0, i + 1)));
      // if not segmented, go check current prefix
      if (!opt[i])
        continue;
      // 这里的话就是opt[i]=true.
      for (int j = i + 1; j < n; ++j) {
        opt[j] = (opt[j] || dict.contains(s.substring(i+1, j+1)));
      }
      // fast return
      if (opt[n-1])   return true;
    }
    return false;
  }

  public static void main(String[] args) {
    Set<String> dict = new HashSet<>();
    String[] strings = new String[] {"i"}; // {"i", "am", "iam", "like", "sam", "sung", "samsung",
    // "mobile", "ice", "cream", "icecream", "man", "go", "mango"};
    for (String str : strings) {
      dict.add(str);
    }
    String s = "ai"; // "icemango";// "iceiam"; // "igoicecream";
    System.out.println("Program creek says: " + wordBreakIprogcreek(s, dict));
  }
}
