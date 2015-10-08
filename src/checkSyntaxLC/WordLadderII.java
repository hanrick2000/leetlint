package checkSyntaxLC;

import java.util.*;

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

    dict.add(start); // why this? Ans: because this DFS need trace back
    dict.add(end); // this is general trick

    Map<String, List<String>> wordMap = new HashMap<String, List<String>>();
    Map<String, Integer> distance = new HashMap<String, Integer>();
    for (String word : dict) {
      wordMap.put(word, new ArrayList<String>());
    }
    // BFS to build the wordMap(WHOLE!)
    bfs(wordMap, distance, start, end, dict);
    System.out.println(distance);
    System.out.println(wordMap);

    List<String> path = new ArrayList<>();
    // List<List<String>> result = new ArrayList<ArrayList<String>>();
    List<List<String>> result = new ArrayList<List<String>>();
    // DFS then use the wordMap and minimum Level to find all solution
    dfs(result, path, wordMap, distance, end, start, dict);
    System.out.println(result);
    return result;
  }

  private static void bfs(Map<String, List<String>> wordMap,
      Map<String, Integer> distance, String start, String end, Set<String> dict) {
    if (dict == null || dict.size() == 0) {
      return;
    }
    Queue<String> bfsQ = new LinkedList<String>();
    bfsQ.offer(start);
    wordMap.put(start, new ArrayList<>());
    distance.put(start, 1); // start is level 1, the same condition as WordLadder I

    int level = 1;
    while (!bfsQ.isEmpty()) {
      int Qsize = bfsQ.size();
      level++;
      for (int i = 0; i < Qsize; i++) {
        String cur = bfsQ.poll();
//        wordMap.put(cur, new ArrayList<>()); // WRONG!WRONG!WRONG!
        for (String neighbor : getNeighbors(cur, dict)) {
          wordMap.get(neighbor).add(cur); // KEY, why get(neighbor) instead of get(cur)? why don't
                                          // check distance? because I need whole Map!
          if (!distance.containsKey(neighbor)) {
            // wordMap.get(neighbor).add(cur); // KEY, why get(neighbor) instead of get(cur)?
            distance.put(neighbor, level);
            bfsQ.offer(neighbor);
          }
        }
      }
    }

    return; // safety
  }

  private static void dfs(List<List<String>> result, List<String> path,
      Map<String, List<String>> wordMap, Map<String, Integer> distance,
      String cur, String start, Set<String> dict) {
    path.add(cur);
    if (cur.equals(start)) {
      Collections.reverse(path);
      result.add(new ArrayList<String>(path));
      Collections.reverse(path);
    } else {
      for (String next : wordMap.get(cur)) {
        if (distance.get(next) + 1 == distance.get(cur)) {
          dfs(result, path, wordMap, distance, next, start, dict);
        }
      }
    }
    path.remove(path.size() - 1);
  }

  private static String replaceChar(String src, int idx, char ch) {
    if (idx >= src.length()) {
      return src;
    }
    char[] charArr = src.toCharArray();
    charArr[idx] = ch;
    return new String(charArr);
  }

  // dummy dict, not much checking
  private static List<String> getNeighbors(String str, Set<String> dict) {
    List<String> neighbors = new ArrayList<>();
    for (char ch = 'a'; ch <= 'z'; ++ch) {
      for (int i = 0; i < str.length(); ++i) {
        String newWord = replaceChar(str, i, ch);
        if (!str.equals(newWord)) {
          if (dict.contains(newWord)) {
            neighbors.add(newWord);
          }
        }
      }
    }

    return neighbors;
  }

  private void test() {
    Set<String> dict = new HashSet<>();
    // dict.add("hot");
    // dict.add("dot");
    // dict.add("dog");
    // dict.add("lot");
    // dict.add("log");
    // dict.add("dit");
    // dict.add("dig");
    // dict.add("sit");
    // dict.add("set");
    // dict.add("seg");
    // dict.add("sog");

    // dict.add("hot");
    // dict.add("dot");
    // dict.add("dog");
    // dict.add("git");
    // dict.add("got");
    // dict.add("god");
    // dict.add("zit");
    // dict.add("zot");
    // dict.add("zog");

    // dict.add("hot");
    // dict.add("cot");
    // dict.add("bit");
    // dict.add("biz");
    // dict.add("boz");
    // dict.add("bog");
    //
    // String start = "hit";
    // String end = "cog";

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
