package testingAns;

import java.util.*;

/*-
 * The full code is shown in lecture, and the source code is analyzed in
 * https://sites.google.com/site/jennyshelloworld/company-blog/chapter-7---graph-search
 * 
 * @author tzhang
 *
 */
public class WordLadderII9zhang {
  public List<List<String>> findLadders(String start, String end,
      Set<String> dict) {
    List<List<String>> ladders = new ArrayList<List<String>>();
    Map<String, List<String>> map = new HashMap<String, List<String>>();
    Map<String, Integer> distance = new HashMap<String, Integer>();

    dict.add(start);
    dict.add(end);

    bfs(map, distance, start, end, dict);
    System.out.println(distance);
    System.out.println(map);

//    for (String source : map.keySet()) {
//      System.out.println(source + ": " + map.get(source));
//    }
    
    List<String> path = new ArrayList<String>();

    dfs(ladders, path, end, start, distance, map);

    return ladders;
  }

  void dfs(List<List<String>> ladders, List<String> path, String crt,
      String start, Map<String, Integer> distance, Map<String, List<String>> map) {
    path.add(crt);
    if (crt.equals(start)) {
      Collections.reverse(path);
      ladders.add(new ArrayList<String>(path));
      Collections.reverse(path);
    } else {
      for (String next : map.get(crt)) {
        if (distance.containsKey(next)
            && distance.get(crt) == distance.get(next) + 1) {
          dfs(ladders, path, next, start, distance, map);
        }
      }
    }
    path.remove(path.size() - 1);
  }

  void bfs(Map<String, List<String>> map, Map<String, Integer> distance,
      String start, String end, Set<String> dict) {
    Queue<String> q = new LinkedList<String>();
    q.offer(start);
    distance.put(start, 0);
    for (String s : dict) {
      map.put(s, new ArrayList<String>());
    }

    while (!q.isEmpty()) {
      // int Qsize = q.size();
      // for (int i = 0; i < Qsize; i++) { // it is OK to NOT to emphasize level ordering
      String crt = q.poll();

      List<String> nextList = expand(crt, dict);

      for (String next : nextList) {
        map.get(next).add(crt);
        if (!distance.containsKey(next)) {
          distance.put(next, distance.get(crt) + 1);
          q.offer(next);
        }
      }
      // }
    }
  }

  List<String> expand(String crt, Set<String> dict) {
    List<String> expansion = new ArrayList<String>();

    for (int i = 0; i < crt.length(); i++) {
      for (char ch = 'a'; ch <= 'z'; ch++) {
        if (ch != crt.charAt(i)) {
          String expanded = crt.substring(0, i) + ch + crt.substring(i + 1);
          if (dict.contains(expanded)) {
            expansion.add(expanded);
          }
        }
      }
    }

    return expansion;
  }

  private void test() {
    Set<String> dict = new HashSet<>();
    dict.add("hot");
    dict.add("dot");
    dict.add("dog");
    dict.add("lot");
    dict.add("log");
//    dict.add("dit");
//    dict.add("dig");
//    dict.add("sit");
//    dict.add("set");
//    dict.add("seg");
//    dict.add("sog");


    String start = "hit";
    String end = "cog";

    List<List<String>> ans = findLadders(start, end, dict);
//    for (List<String> list : ans) {
//      for (String num : list) {
//        System.out.print(num + " ");
//      }
//      System.out.println();
//    }
  }

  public static void main(String[] args) {
    WordLadderII9zhang wli = new WordLadderII9zhang();
    wli.test();
  }
}
