package freq3_tony;

import java.util.*;

/**
 * N00t大神给出的recursion , Greedy解法~~~ http://n00tc0d3r.blogspot.com/2013/03/word-segment.html
 * http://www.geeksforgeeks.org/dynamic-programming-set-32-word-break-problem/
 * http://www.programcreek.com/2014/03/leetcode-word-break-ii-java/
 * 
 * @author tzhang
 *
 */
public class WordBreakDummy {

  /**
   * N00t的dummy做法. 她只给了for loop, 没有加上边界条件.
   * 
   * @param s
   * @param dictionary
   * @return
   */
  private static boolean wordSegDummy(String s, Set<String> dictionary) {
    // 加上s为空的处理. 不然是不对的.
    if (s.length() == 0)
      return true;
    for (int i = 0; i < s.length(); i++) {
      if (dictionary.contains(s.substring(0, i + 1))
          && wordSegDummy(s.substring(i + 1), dictionary))
        return true;
    }
    return false;
  }

  /**
   * programcreek的Naive解法. 其实也是一样的. 不过verbose一些
   * 
   * @param s : 此时的string
   * @param dict : 不变的, 相当于一个global var
   * @param start : 当前处理的起点.
   * @return
   */
  private static boolean wordBreakHelper(String s, Set<String> dict, int start) {
    // 边界条件: 当处理到最边界. 即成功了.
    if (s.length() == start)
      return true;

    // pc用的是for loop, 一个一个的拿出来比较. 没有nt的简洁.
    for (String pattern : dict) {
      int len = pattern.length();
      int end = start + len;

      // 又一个边界: 处理的条件是当前的substr是有效的.
      if (end > s.length())
        continue; // 换下一个dict里面的pattern.

      // 注意这里先判断是不是符合, 若是, 则recursion. 注意这里的return true是运行到leaf才返回的. 这个if不要else.
      if (s.substring(start, start + len).equals(pattern)) {
        if (wordBreakHelper(s, dict, start + len)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * 错误的recursion写法. param!和逻辑关系不对.
   */
  private static List<List<String>> wordSegDummyPrintWrong(String s,
      HashSet<String> dictionary, List<String> res, List<List<String>> result) {
    // if (s.length() == 0)
    // return result;
    for (int i = 0; i < s.length(); i++) {
      String prefix = s.substring(0, i + 1);
      String rest = s.substring(i + 1);
      if (dictionary.contains(prefix)) {
        // if no more element, add one set of res into result.
        if (i == s.length() - 1) {
          res.add(prefix);
          // result.add(res);
          System.out.println(i + "@" + res);
          return result;
        }
        // or continue to recursion
        res.add(prefix);
        wordSegDummyPrintWrong(rest, dictionary, res, result);
        // res = new ArrayList<String>();
      }
    }
    return result;
  }

  /**
   * 从segmentString改过来的. 还是搞不掂...
   * 
   * @param s
   * @param dict
   * @return
   */
  private static List<List<String>> wordSegDummyPrint(String s,
      HashSet<String> dict) {
    // set itself
    List<List<String>> result = new LinkedList<List<String>>();
    List<String> root = new LinkedList<String>();
    if (dict.contains(s))
      root.add(s);
    if (root != null) {
      result.add(root);
      System.out.println(result);
    }

    for (int i = 1; i < s.length(); i++) {
      String prefix = s.substring(0, i);
      if (dict.contains(prefix)) {
        String rest = s.substring(i);
        List<List<String>> resultTemp = wordSegDummyPrint(rest, dict);
        if (resultTemp != null) {
          for (List<String> str : resultTemp) {
            List<String> res = new LinkedList<>();
            res.add(prefix);
            res.addAll(str);
            result.add(res);
          }
        }
        // else {
        // result.remove(result.size()-1);
        // }
      }
    }
    System.out.println(result);
    return result;
  }

  /**
   * N00t的palindrome partition I改编过来, 对咯!
   * 完美的recursion! 简单简洁! 20150329
   * @param s
   * @param l
   * @param result
   */
  private static void partitionHelper(String s, int left, Set<String> dict,
      List<List<String>> result) {
    if (left == s.length()) {
      result.add(new ArrayList<String>());
    }
    for (int i = left; i < s.length(); ++i) {
      String substr = s.substring(left, i + 1);
      if (true) { //dict.contains(substr)
        List<List<String>> res = new ArrayList<List<String>>();
        partitionHelper(s, i + 1, dict, res);
        for (List<String> partition : res) {
          System.out.println(left + "@ " + substr);
          partition.add(0, substr);
        }
        result.addAll(res);
      }
    }
  }

  /**
   * 终于搞对了, 但是为什么之前的做法的结果那么奇怪呢?
   * 
   * @param s
   * @param dictionary
   * @param str
   * @param result
   * @return
   */
  private static List<String> wordSegDummyPrint(String s,
      HashSet<String> dictionary, String str, List<String> result) {
    // if (s.length() == 0)
    // return result;
    for (int i = 0; i < s.length(); i++) {
      String prefix = s.substring(0, i + 1);
      String rest = s.substring(i + 1);
      if (dictionary.contains(prefix)) {
        // if no more element, add one set of res into result.
        if (i == s.length() - 1) {
          // res.add(prefix);
          str += prefix;
          result.add(str);
          System.out.println(str);
          return result;
        }
        // or continue to recursion
        wordSegDummyPrint(rest, dictionary, str + prefix + " ", result);
        // res = new ArrayList<String>();
      }
    }
    return result;
  }

  /**
   * 直接照搬C++代码反而就对了. 这和我上面的有什么区别呢?
   * 
   * @param str
   * @param dict
   * @param result
   */
  private static void segprint(String str, HashSet<String> dict, String result) {
    for (int i = 0; i < str.length(); i++) {
      String prefix = str.substring(0, i + 1);
      if (dict.contains(prefix)) {
        if (i == str.length() - 1) {
          result += prefix;
          System.out.println(result);
          return;
        }
        segprint(str.substring(i + 1), dict, result + prefix + " ");
      }
    }
  }

  public WordBreakDummy() {
    HashSet<String> dict = new HashSet<>();
    // dict.add("code");
    // dict.add("review");
    // dict.add("discuss");
    // String s = "codereviewdiscuss";
    String[] strings =
        new String[] {"i", "am", "iam", "like", "sam", "sung", "samsung",
            "mobile", "ice", "iceman", "cream", "icecream", "man", "go",
            "mango"};
    for (String str : strings) {
      dict.add(str);
    }
    String s = "ab"; //"iceiam"; // "igoicecream";
    List<String> res = new ArrayList<>();
    List<List<String>> result = new ArrayList<List<String>>();
    // System.out.println("result :" + wordSegDummyPrintWrong(s, dict, res, result));
    // segprint(s, dict, new String());
    // System.out.println("result are : " + wordSegDummyPrint(s, dict));
    // String a = "";
    // System.out.println(dict.contains(a));
    // System.err.println("I got: " + wordBreakHelper(s, dict, 0));

    partitionHelper(s, 0, dict, result);
    System.out.println("palindrome: " + result);
  }


  public static void main(String[] args) {
    WordBreakDummy ws = new WordBreakDummy();
    // String s = "igo";
    // System.out.println(s.substring(0,1));
  }

}
