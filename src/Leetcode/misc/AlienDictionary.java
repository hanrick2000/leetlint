package Leetcode.misc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * http://beyondcoder.blogspot.com/2015/09/alien-dictionary.html
 * Created at 12:42 PM on 11/30/15.
 */

public class AlienDictionary {

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
    //graph_hm.get('f').add('r');
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

    // count initial indegree for every char vertex
    Map<Character, Integer> indegree = new HashMap<>();
    for (char c : graph_hm.keySet()) {
      for (char a : graph_hm.get(c)) {
        int count = indegree.containsKey(a) ? indegree.get(a) + 1 : 1;
        indegree.put(a, count);
      }
    }

    // find indegree==0, initialize the queue
    Queue<Character> queue = new LinkedList<>();
    for (char c : graph_hm.keySet()) {
      if (!indegree.containsKey(c)) {
        queue.offer(c);
      }
    }

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

/* ------------------------------------------------------------------------------------------------
 *                        BFS in-degree based topological sort
 * ------------------------------------------------------------------------------------------------
 */

    //// topological sort
    //while (!queue.isEmpty()) {
    //  char c = queue.poll();
    //  sb.append(c);
    //  for (char a : graph_hm.get(c)) {
    //    indegree.put(a, indegree.get(a) - 1);
    //    if (indegree.get(a) == 0) {
    //      queue.offer(a);
    //    }
    //  }
    //}
    //
    //for (int a : indegree.values()) {  // if there is any non sorted, this is not a DAG, return false
    //  if (a != 0) return "";
    //}
    //return sb.toString();
  }

/* ------------------------------------------------------------------------------------------------
 *                        Below is the Algs4's simple DFS topological sort
 * ------------------------------------------------------------------------------------------------
 */
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
        System.out.println("Cycle detected!!!");
        return false;
      }
      if (!visited.contains(neigh)) {
        return dfs(graph, neigh);
      }
    }
    postorder.offer(node);
    onStack.put(node, onStack.get(node)-1);
    return true;
  }

  public static void main(String[] args) {
    AlienDictionary outer = new AlienDictionary();

    String[] words = {"wrt", "wrf", "er", "ett", "rftt"};

    System.out.println(outer.alienOrder(words));
  }
}