package nineChap7_Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/*-
 * In this jiuzhang's example, learned BFS to traverse a graph from a given node
 * The reason to prefer BFS rather than DFS is efficiency, no backward walk if dead end
 * 
 * However, in Algs4, Topological Sort is done by DFS
 * @TODO   graph serialization
 * 
 * @author tzhang
 *
 */
public class TopoSort {
  private static class DGraphNode {
    int label;
    List<DGraphNode> neighbors;

    DGraphNode(int x) {
      label = x;
      neighbors = new ArrayList<>();
    }

    // henng's dummy print: list of adacency list
    public static void print(DGraphNode root) {
      if (root == null) {
        return;
      }

      Queue<DGraphNode> bfsQ = new LinkedList<>();
      Map<DGraphNode, Integer> map = new HashMap<>();
      bfsQ.offer(root);
      map.put(root, 0); // hold the node

      while (!bfsQ.isEmpty()) {
        DGraphNode node = bfsQ.poll();
        if (map.get(node) == 0) { // not yet print
          System.out.print(node.label + ": ");
        }
        map.put(node, 1); // already print it

        for (DGraphNode neighbor : node.neighbors) {
          if (!map.containsKey(neighbor)) { // only put into Q 1 time
            bfsQ.offer(neighbor);
            map.put(neighbor, 0); // hold the node
          }
          System.out.print(neighbor.label + " ");
        }
        System.out.println();
      }
    }
  }

  public static List<DGraphNode> testSort() {
    DGraphNode yi = new DGraphNode(1);
    DGraphNode er = new DGraphNode(2);
    DGraphNode sa = new DGraphNode(3);
    DGraphNode si = new DGraphNode(4);

    yi.neighbors.add(er);
    yi.neighbors.add(sa);
    er.neighbors.add(sa);
    er.neighbors.add(si);
    sa.neighbors.add(si);
    // si.neighbors.add(er); // if DAG, then no Topological sort
    // si.neighbors.add(sa);

    List<DGraphNode> NodeList = new ArrayList<>();
    NodeList.add(yi);
    NodeList.add(er);
    NodeList.add(sa);
    NodeList.add(si);

    return NodeList;

  }

  /**
   * 
   * @param list of graph
   * @return
   */
  public static ArrayList<DGraphNode> topoSort(List<DGraphNode> graph) {
    Map<DGraphNode, Integer> inCount = new HashMap<>();

    // find inCount with BFS
    for (DGraphNode node : graph) {
      for (DGraphNode neighbor : node.neighbors) {
        if (!inCount.containsKey(neighbor)) {
          inCount.put(neighbor, 1);
        } else {
          inCount.put(neighbor, inCount.get(neighbor) + 1);
        }
      }
    }

    // init bfsQ with node has 0 fanin
    Queue<DGraphNode> bfsQ = new LinkedList<DGraphNode>();
    ArrayList<DGraphNode> result = new ArrayList<>();
    for (DGraphNode node : graph) {
      if (!inCount.containsKey(node)) {
        bfsQ.offer(node);
        result.add(node); // only need to add once in here
      }
    }

    // bfs the graph and remove each node with 0 fan in then put into result list

    while (!bfsQ.isEmpty()) {
      DGraphNode node = bfsQ.poll();
      // result.add(node);
      for (DGraphNode n : node.neighbors) {
        inCount.put(n, inCount.get(n) - 1);
        if (inCount.get(n) == 0) {
          bfsQ.offer(n);
          result.add(n);
        }
      }
    }

    // resullt
    return result;
  }

  public TopoSort() {
    List<DGraphNode> nodeList = TopoSort.testSort();
    ArrayList<DGraphNode> ans = TopoSort.topoSort(nodeList);
    for (DGraphNode node : ans) {
      System.out.print(node.label + " ");
    }
  }

  public static void main(String[] args) {
    TopoSort ts = new TopoSort();
  }
}
