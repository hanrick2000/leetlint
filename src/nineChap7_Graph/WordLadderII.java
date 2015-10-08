package nineChap7_Graph;

import java.util.*;

/**
 * As N00tc0d3r's solution: use BFS to find the minimum path length, and DFS to
 * backtrack to find all the actuall minimum paths solutions.
 * 
 * @author tzhang
 *
 */
public class WordLadderII {

  /**
   * @param start, a string
   * @param end, a string
   * @param dict, a set of string
   * @return a list of lists of string
   */
  public List<List<String>> findLadders(String start, String end,
      Set<String> dict) {
    // write your code here
    // Map<String, List<String>> wordMap = new HashMap<String, ArrayList<String>>();
    Map<String, List<String>> wordMap = new HashMap<String, List<String>>();
    Map<String, Integer> distance = new HashMap<String, Integer>();
    dict.add(start); // do you know why also add start into dict? bcuz it is used in getNeighbors()
                     // without Visited[] since visited[] is replace with distance[]
    dict.add(end); // a cool way to consider both last or intermediate.


    // use BFS to find minimum length
    bfsMin(start, end, dict, wordMap, distance);
//    System.out.println(distance);
    System.out.println(wordMap);
    // use DFS to backtracking all valid paths
    List<List<String>> ladders = new ArrayList<List<String>>();
    dfs(ladders, new ArrayList<String>(), end, start, wordMap, distance);
//    System.out.println(ladders);
    return ladders;
  }

  /**
   * 
   * @param start
   * @param end
   * @param dict
   * @param wordMap
   * @param distance
   */
  public static void bfsMin(String start, String end, Set<String> dict,
      Map<String, List<String>> wordMap, Map<String, Integer> distance) {
    if (dict == null || dict.size() == 0) {
      return;
    }

    Queue<String> bfsQ = new LinkedList<>();
    // Set<String> visited = new HashSet<>();
    bfsQ.offer(start);
    // visited.add(start);
    distance.put(start, 1);
    for (String str : dict) {
      wordMap.put(str, new ArrayList<String>());
    }

    int level = 1;
    while (!bfsQ.isEmpty()) {
      level++; // the level of polled words
      int Qsize = bfsQ.size();
      for (int i = 0; i < Qsize; ++i) {
        String curWord = bfsQ.poll();
        List<String> neighbors = getNeighbors(curWord, dict);
        for (String nextWord : neighbors) {
          wordMap.get(nextWord).add(curWord);
          if (!distance.containsKey(nextWord)) {
            distance.put(nextWord, level);
            bfsQ.offer(nextWord); //
          }
        }
        /*
         * for (char c = 'a'; c <= 'z'; c++) { // examine all neighbors of polled words
         * for (int j = 0; j < curWord.length(); ++j) {
         * if (c == curWord.charAt(j)) {
         * continue;
         * }
         * String neigStr = replaceChar(curWord, j, c);
         * if (visited.contains(neigStr)) {
         * continue;
         * }
         * if (neigStr.equals(end)) {
         * distance.put(neigStr, level + 1); // no return
         * wordMap.get(curWord).add(neigStr);
         * }
         * if (dict.contains(neigStr)) {
         * bfsQ.offer(neigStr);
         * visited.add(neigStr);
         * wordMap.get(curWord).add(neigStr);
         * }
         * }
         * }
         */
      }
    }

  }

  private static void dfs(List<List<String>> ladders, List<String> path,
      String cur, String start, Map<String, List<String>> wordMap,
      Map<String, Integer> distance) {
    path.add(cur);
    if (cur.equals(start)) {
      Collections.reverse(path);
      ladders.add(new ArrayList<String>(path));
      Collections.reverse(path);
    }
    else {
      for (String next : wordMap.get(cur)) {
//        if (distance.containsKey(next) 
//            && distance.get(next)+1 == distance.get(cur)) {
        if ( distance.get(next)+1 == distance.get(cur) ) {
          dfs(ladders, path, next, start, wordMap, distance);
        }
      }
    }
    
    path.remove(path.size()-1);
  }

  /***********************************
   * util
   **********************************/
  private static String replaceChar(String str, int idx, char ch) {
    char[] strArr = str.toCharArray();
    strArr[idx] = ch;
    // return strArr.toString();
    return new String(strArr);
  }

  private static List<String> getNeighbors(String str, Set<String> dict) {
    if (dict == null) {
      return null;
    }
    List<String> neighbors = new LinkedList<String>();
    for (char c = 'a'; c <= 'z'; c++) {
      for (int i = 0; i < str.length(); ++i) {
        if (c == str.charAt(i)) {
          continue;
        }
        String newWord = replaceChar(str, i, c);
        if (dict.contains(newWord)) {
          neighbors.add(newWord);
        }
      }
    }

    return neighbors;
  }

  private void test() {
    Set<String> dict = new HashSet<>();
//    dict.add("hot");
//    dict.add("dot");
//    dict.add("dog");
//    dict.add("lot");
//    dict.add("log");
    // dict.add("dit");
    // dict.add("dig");
    // dict.add("sit");
    // dict.add("set");
    // dict.add("seg");
    // dict.add("sog");

//  dict.add("hot");
//  dict.add("dot");
//  dict.add("dog");
//  dict.add("git");
//  dict.add("got");
//  dict.add("god");
//  dict.add("zit");
//  dict.add("zot");
//  dict.add("zog");
    
//    dict.add("hot");
//    dict.add("cot");
//    dict.add("bit");
//    dict.add("biz");
//    dict.add("boz");
//    dict.add("bog");
//
//    String start = "hit";
//    String end = "cog";
    
    dict.add("a");
    dict.add("b");
    dict.add("c");
    String start = "a", end = "c";

    List<List<String>> ans = findLadders(start, end, dict);
    for (List<String> list : ans) {
      for (String num : list) {
        System.out.print(num + " ");
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    WordLadderII wli = new WordLadderII();
    wli.test();
  }
}
