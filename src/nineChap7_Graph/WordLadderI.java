package nineChap7_Graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * This is a good puzzle: from question->P¨®lya(4 step method)->solve by my knowledge. SO this
 * can be dissect into a simple graph shortest path problem->BFS.
 * This is a pre-question for word ladder II.
 * 
 * @author tzhang
 *
 */
public class WordLadderI {
  /**
   * @param start, a string
   * @param end, a string
   * @param dict, a set of string
   * @return an integer
   */
  public int ladderLength(String start, String end, Set<String> dict) {
    // write your code here
    if (dict == null || dict.size() == 0) {
      return 0;
    }

    Queue<String> bfsQ = new LinkedList<>();
    Set<String> visited = new HashSet<>();
    bfsQ.offer(start);
    visited.add(start);
    int level = 1;

    while (!bfsQ.isEmpty()) {
      level++;
      String cur = bfsQ.poll();
      for (String str : getNeighbors(cur, dict)) {
        if (str.equals(end)) {
          return level;
        }
        if (visited.contains(str)) {
          continue;
        }
        System.out.println(str);
        bfsQ.offer(str);
        visited.add(str);
      }
    }

    return 0;
  }

  private static String replaceChar(String str, int idx, char c) {
    String rpStr = "";
    for (int i = 0; i < str.length(); ++i) {
      if (i != idx) {
        rpStr += str.charAt(i);
      } else {
        rpStr += c;
      }
    }
    return rpStr;
  }

  private static Set<String> getNeighbors(String str, Set<String> dict) {
    Set<String> neighbors = new HashSet<>();
    for (int i = 0; i < str.length(); i++) {
      for (char c = 'a'; c <= 'z'; ++c) {
        if (c == str.charAt(i)) {
          continue;
        }
        String newStr = replaceChar(str, i, c); // create neighbors nodes to BFS
        if (dict.contains(newStr)) {
          neighbors.add(newStr);
        }
      }
    }

    return neighbors;
  }

  private void test() {
    Set<String> dict = new HashSet<>();
    dict.add("hot");
    dict.add("dot");
    dict.add("dog");
    dict.add("lot");
    dict.add("log");
    String start = "hit";
    String end = "cog";

    // dict.add("a");
    // dict.add("b");
    // dict.add("c");
    // String start = "a", end = "c";

    int ans = ladderLength(start, end, dict);
    System.out.println("Final result: " + ans);
  }

  public static void main(String[] args) {
    WordLadderI wli = new WordLadderI();
    wli.test();
  }
}
