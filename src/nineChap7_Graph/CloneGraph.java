package nineChap7_Graph;

/*-
 * Report: Clone graph
 * To traverse a graph from one node, usually use BFS, in order to have history
 * of traverse, implement Queue with LinkedList.
 * As always, use map to hold the relation of old and new
 */
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Map;
import java.util.HashMap;

public class CloneGraph {

  private static class DGraphNode {
    int label;
    List<DGraphNode> neighbors;

    DGraphNode(int x) {
      label = x;
      neighbors = new ArrayList<>();
    }

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

  /**
   * forgot to use List to define bfsQ's type, and used Queue, then it has no get()!!!
   * 
   * @param node
   * @return
   */
  public DGraphNode cloneGraph(DGraphNode node) {
    // write your code here
    if (node == null) {
      return null;
    }

    // Queue<DGraphNode> bfsQ = new LinkedList<DGraphNode>();
    List<DGraphNode> bfsQ = new LinkedList<>();
    Map<DGraphNode, DGraphNode> map = new HashMap<>();

    bfsQ.add(node);
    DGraphNode cpyNode = new DGraphNode(node.label);
    map.put(node, cpyNode);
    int start = 0;

    // phase 1: copy node
    while (start < bfsQ.size()) {
      DGraphNode root = bfsQ.get(start++);
      for (DGraphNode neighbor : root.neighbors) {
        if (!map.containsKey(neighbor)) {
          bfsQ.add(neighbor);
          cpyNode = new DGraphNode(neighbor.label);
          map.put(neighbor, cpyNode);
        }
      }
    }

    // phase 2: copy edge
    for (DGraphNode oldNode : bfsQ) {
      for (DGraphNode neighbor : oldNode.neighbors) {
        map.get(oldNode).neighbors.add(map.get(neighbor));
      }
    }

    return map.get(node);
  }


  public DGraphNode cloneUDGraph(DGraphNode root) {
    if (root == null) {
      return null;
    }

    /*-    Queue<UDGraphNode> bfsQ = new ArrayList<>();
     Cant! since arraylist doesn't implement queue: http://stackoverflow.com/a/26280958
     */
    /*-    Queue<UDGraphNode> queue = new LinkedList<>();
     Normally, use linkedlist as queue to instantiate, but here for edge traverse, need history, use list to mimic queue
     */

    /**
     * using list to mimic queue for bfs and traverse to copy edges
     */
    List<DGraphNode> bfsQ = new ArrayList<>();
    Map<DGraphNode, DGraphNode> map = new HashMap<>();

    // phase 1: copy nodes
    bfsQ.add(root);
    map.put(root, new DGraphNode(root.label));
    int start = 0;
    while (start < bfsQ.size()) {
      DGraphNode nod = bfsQ.get(start++);
      for (DGraphNode neighbor : nod.neighbors) {
        if (!map.containsKey(neighbor)) {
          DGraphNode cpyNod = new DGraphNode(neighbor.label);
          bfsQ.add(neighbor);
          map.put(neighbor, cpyNod);
        }
      }
    }

    // phase 2: copy edge
    for (DGraphNode oldNode : bfsQ) {
      DGraphNode newNode = map.get(oldNode);
      for (DGraphNode neighbor : oldNode.neighbors) {
        newNode.neighbors.add(map.get(neighbor));
      }
    }

    return map.get(root);

    // while (!bfsQ.isEmpty()) {
    // List<Integer> level = new ArrayList<>();
    // int size = bfsQ.size(); // Must save into temp var: size since bfsQ is changing!!! same
    // reason
    // // that O(n) list2BST need global var: cur
    // for (int i = 0; i < size; ++i) {
    // UDGraphNode nod = bfsQ.get(i);
    // for (UDGraphNode udg : nod.neighbors) {
    // bfsQ.add(udg);
    // map.put(udg, new UDGraphNode(udg.label));
    // }
    // bfsQ.remove(???);
    // }
    // }
  }

  public static DGraphNode testPrint() {
    DGraphNode yi = new DGraphNode(1);
    DGraphNode er = new DGraphNode(2);
    DGraphNode sa = new DGraphNode(3);
    DGraphNode si = new DGraphNode(4);

    yi.neighbors.add(er);
    yi.neighbors.add(sa);
    er.neighbors.add(sa);
    er.neighbors.add(si);
    sa.neighbors.add(si);
    si.neighbors.add(er);
    si.neighbors.add(sa);

    return yi;

  }

  public CloneGraph() {
    DGraphNode root = testPrint();
    DGraphNode.print(root);
    DGraphNode rootCpy = cloneUDGraph(root);
    DGraphNode.print(rootCpy);

  }

  public static void main(String[] args) {
    CloneGraph cg = new CloneGraph();
  }
}
