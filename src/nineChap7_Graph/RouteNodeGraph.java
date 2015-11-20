package nineChap7_Graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import misc.DirectedGraphNode;

/**
 * Created at 5:05 PM on 11/19/15.
 */
public class RouteNodeGraph {
  public static void main(String[] args) {
    RouteNodeGraph rng = new RouteNodeGraph();
    rng.test();
  }

  public void test() {
    DirectedGraphNode n1 = new DirectedGraphNode(1);
    DirectedGraphNode n2 = new DirectedGraphNode(2);
    DirectedGraphNode n3 = new DirectedGraphNode(3);
    DirectedGraphNode n4 = new DirectedGraphNode(4);
    DirectedGraphNode n5 = new DirectedGraphNode(5);
    n1.neighbors.add(n2);
    n1.neighbors.add(n4);
    n2.neighbors.add(n4);
    n2.neighbors.add(n3);
    n4.neighbors.add(n5);

    boolean ans = hasRoute(null, n2, n4);
    System.out.println(ans);
  }
  /**
   * @param graph: A list of Directed graph node
   * @param s: the starting Directed graph node
   * @param t: the terminal Directed graph node
   * @return: a boolean value
   */
  public boolean hasRoute(ArrayList<DirectedGraphNode> graph,
      DirectedGraphNode s, DirectedGraphNode t) {
    // write your code here
    if (s == null || t == null) {
      return false;
    }

/* ------------------------------------------------------------------------------------------------
 *                    DFS
 * ------------------------------------------------------------------------------------------------
 */
    //Set<DirectedGraphNode> visited = new HashSet<>();
    //boolean ans = dfs(s, t, visited);

/* ------------------------------------------------------------------------------------------------
 *                    BFS
 * ------------------------------------------------------------------------------------------------
 */
    boolean ans = bfs(s,t);
    return ans;
  }

  /**
   * O(V+E) in complexity, Well explained in yuanbin's report.
   * learned from http://algorithm.yuanbin.me/zh-cn/graph/route_between_two_nodes_in_graph.html
   * @param s
   * @param t
   * @return
   */
  private boolean bfs(DirectedGraphNode s, DirectedGraphNode t) {
    Set<DirectedGraphNode> visited = new HashSet<>();
    Queue<DirectedGraphNode> bfsQ = new LinkedList<>();
    bfsQ.offer(s);

    while (!bfsQ.isEmpty()) {
      //DirectedGraphNode cur = bfsQ.poll();  why I poll before looping queue???
      int size = bfsQ.size();
      for (int i = 0; i < size; ++i) {
        DirectedGraphNode node = bfsQ.poll();
        visited.add(node);
        if (node == t)  return true;
        if (node.neighbors.size() > 0) {
          for (DirectedGraphNode neigh : node.neighbors) {
            if (visited.contains(neigh))  continue;
            bfsQ.offer(neigh);
          }
        }
      }
    }

    return false;

  }

  /**
   * http://algorithm.yuanbin.me/zh-cn/graph/route_between_two_nodes_in_graph.html
   * 1. use visited to avoid circle forever loop!
   * 2. visited should add param, not neighbors.
   * @param s
   * @param t
   * @param visited
   * @return
   */
  private boolean dfs(DirectedGraphNode s, DirectedGraphNode t, Set<DirectedGraphNode> visited) {
    if (s.equals(t)) {
      return true;
    }
    visited.add(s);
    for (DirectedGraphNode node : s.neighbors) {
      //visited.add(node);
      if (visited.contains(node)) {
        continue;
      }
      if (dfs(node, t, visited)) {
        return true;
      }
    }

    return false;
  }

  /**
   * My implementation of DFS, many flaws:
   * 1. no check of cycle: may lead to forever loop
   * 2. not nessaary to OR all result, can return true once s == t!
   * @param s
   * @param t
   * @return
   */
  private boolean dfs1stTime(DirectedGraphNode s, DirectedGraphNode t) {
    // return;
    if (s == null) {
      return false;
    }
    if (s == t) {
      return true;
    }

    boolean res = false;
    for (DirectedGraphNode neig : s.neighbors) {
      boolean tmp = dfs1stTime(neig, t);
      res = res || tmp;
    }
    return res;
  }
}
