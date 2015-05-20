package freq3_tony;

import java.util.*;

/**
 * 这里和word break I的区别在于I只要判断. 而II要输出所有结果. 这里的DP[i]用来保存到i的segment
 * 这里是DP+DFS. DFS当然是recursion, DP只要call DFS, 不用recursion.
 * http://n00tc0d3r.blogspot.com/2013/03/word-segment.html
 * 标准的DP, DFS模版!
 * @author tzhang
 *
 */
public class WordBreakIIDP {
  /**
   * Progaram creek的解法, 跟着word break I的思路写的. 很完美的DP+DFS来backtracking不对的result
   * @param s
   * @param dict
   * @return
   */
  public static List<String> wordBreakIIPC(String s, Set<String> dict) {
    // create an array of ArrayList<String>
    List<String> dp[] = (ArrayList<String>[]) new ArrayList[s.length() + 1];
    dp[0] = new ArrayList<String>();

    for (int i = 0; i < s.length(); i++) {
      if (dp[i] == null)
        continue;

      // so dp[i] != null
      for (String word : dict) {
        int len = word.length();
        int end = i + len;
        if (end > s.length())
          continue;

        if (s.substring(i, end).equals(word)) {
          if (dp[end] == null) {
            dp[end] = new ArrayList<String>();
          }
          dp[end].add(word);
        }
      }
    }

    List<String> result = new LinkedList<String>();
    // 即到了最后, 发现没有结尾的segment, 说明是无解的. 于是返回null.
    if (dp[s.length()] == null) {
      System.err.println("NO segments!");
      return result;
    }
    // 此时已经处理完所有DP, 现在开始load result from DP
    ArrayList<String> temp = new ArrayList<String>();
    dfs(dp, s.length(), result, temp);

    return result;
  }

  /**
   * 这个就是我之前dummy里面应该用的, 即单独用dfs()来判断当前的dp[]是否是一个走完string的pattern match. 如果是则注入result.
   * 这个DFS写的是很好的. 边界. recursion后面call的remove相当于up the tree
   * @param dp
   * @param end
   * @param result
   * @param tmp
   */
  public static void dfs(List<String> dp[], int end, List<String> result,
      ArrayList<String> tmp) {
    if (end <= 0) {
      String path = tmp.get(tmp.size() - 1);
      for (int i = tmp.size() - 2; i >= 0; i--) {
        path += " " + tmp.get(i);
      }
      result.add(path);
      return;
    }

    for (String str : dp[end]) {
      tmp.add(str);
      dfs(dp, end - str.length(), result, tmp);
      tmp.remove(tmp.size() - 1);
    }
  }
  
  /**
   * 检查当前的
   */

  public WordBreakIIDP() {
    HashSet<String> dict = new HashSet<>();
    String[] strings =
        new String[] {"i", "am", "iam", "like", "sam", "sung", "samsung",
            "mobile", "ice", "iceman", "cream", "icecream", "man", "go",
            "mango"};
    for (String str : strings) {
      dict.add(str);
    }
    String s = "iceiam"; // "igoicecream";
    List<String> result = wordBreakIIPC(s, dict);
    System.out.println(result);
  }

  public static void main(String[] args) {
    WordBreakIIDP wb2 = new WordBreakIIDP();
  }
}
