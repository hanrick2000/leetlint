package Leetcode.misc;

import java.util.*;

/**
 * Created at 3:53 PM on 11/30/15.
 */
public class AlienLeet {
  Map<Character, Integer> onStack;
  public String alienOrder(String[] words) {
    if (words == null) return null;

    Map<Character, Set<Character>> graph_hm = new HashMap<>();
    onStack = new HashMap<>();

    for (int i = 0; i < words.length; i++) {
      for (int j = 0; j < words[i].length(); j++) {
        char c = words[i].charAt(j);
        if (!graph_hm.containsKey(c)) {
          onStack.put(c, 0);
          graph_hm.put(c, new HashSet<>());
        }
      }
      if (i > 0) {  // order every two words
        getOrder(words[i - 1], words[i], graph_hm);
      }
    }
    return topSort(graph_hm);
  }

  public void getOrder(String s, String t, Map<Character, Set<Character>> graph_hm) {

    for (int i = 0; i < Math.min(s.length(), t.length()); i++) {
      char c1 = s.charAt(i), c2 = t.charAt(i);

      if (c1 != c2) {
        if (!graph_hm.get(c1).contains(c2)) {
          graph_hm.get(c1).add(c2);
        }
        break;    // stop here because after one char different, remaining chars doesn't matter
      }
    }
  }

  // standard top sort algorithm
  public String topSort(Map<Character, Set<Character>> graph_hm) {

    StringBuilder sb = new StringBuilder();

/* ------------------------------------------------------------------------------------------------
 *                        Below is the Algs4's simple DFS topological sort
 * ------------------------------------------------------------------------------------------------
 */
    Stack<Character> toporder = topo(graph_hm);
    if (toporder == null) {
      return "NO answer";
    }
    while(!toporder.empty()) {
      sb.append(toporder.pop());
    }

    return sb.toString();
  }

  Queue<Character> postorder;
  Set<Character> visited;
  Stack<Character> reversepost;

  public Stack<Character> topo(Map<Character, Set<Character>> graph_hm) {
    postorder = new LinkedList<>();
    reversepost = new Stack<>();
    visited = new HashSet<>();
    for (Character node : graph_hm.keySet()) {
      if (!visited.contains(node)) {
        if (!dfs(graph_hm, node)) {
          return null;
        }
      }
    }
    for (Character node : postorder) {
      reversepost.push(node);
    }
    return reversepost;
  }

  private boolean dfs(Map<Character, Set<Character>> graph, Character node) {
    boolean rs = true;
    visited.add(node);
    onStack.put(node, onStack.get(node)+1);
    for (Character neigh : graph.get(node)) {
      if (onStack.get(neigh) >= 1)  {
        // System.out.println("Cycle detected!!!");
        return false;
      }
      if (!visited.contains(neigh)) {
        if (!dfs(graph, neigh)) {
          return false;
        }
      }
    }
    postorder.offer(node);
    onStack.put(node, onStack.get(node)-1);
    return true;
  }

  public static void main(String[] args) {
    AlienLeet outer = new AlienLeet();

    String[] words = {"wrt", "wrf", "er", "ett", "rftt"};

    System.out.println(outer.alienOrder(words));
  }
}
