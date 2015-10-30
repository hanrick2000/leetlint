package JiuChap2_UnionFind_Heap;

import edu.princeton.cs.algs4.StdOut;

import java.util.*;

/**
 * Created this class in JiuChap2_UnionFind_Heap at 11:11 AM, 10/27/2015.
 */
public class CCUdgraph {
  public static void main(String[] args) {
    CCUdgraph cs = new CCUdgraph();
    cs.test();
  }

  public void test() {
    UndirectedGraphNode ugA = new UndirectedGraphNode(1);
    UndirectedGraphNode ugB = new UndirectedGraphNode(2);
    UndirectedGraphNode ugC = new UndirectedGraphNode(3);
    UndirectedGraphNode ugD = new UndirectedGraphNode(4);
    UndirectedGraphNode ugE = new UndirectedGraphNode(5);
    UndirectedGraphNode ugF = new UndirectedGraphNode(6);
    UndirectedGraphNode ugG = new UndirectedGraphNode(7);

    ugA.addNeighbor(ugB);
    ugA.addNeighbor(ugD);
    ugB.addNeighbor(ugD);
    ugC.addNeighbor(ugE);

    ugD.addNeighbor(ugF);
    ugF.addNeighbor(ugG);

//        ugA.printNeighbor();
//        ugB.printNeighbor();
//        ugC.printNeighbor();
//        ugD.printNeighbor();
//        ugE.printNeighbor();

    ArrayList<UndirectedGraphNode> nodes = new ArrayList<>();
    nodes.add(ugA);
    nodes.add(ugB);
    nodes.add(ugC);
    nodes.add(ugD);
    nodes.add(ugE);

    for (List<Integer> list : connectedSet(nodes)) {
      StdOut.print(list);
    }
  }

  private class UndirectedGraphNode {
    int label;
    ArrayList<UndirectedGraphNode> neighbors;
    UndirectedGraphNode(int x) {
      this.label = x;
      this.neighbors = new ArrayList<>();
    }

    void addNeighbor(UndirectedGraphNode node) {
      this.neighbors.add(node);
      node.neighbors.add(this);
    }
  }

  /**
   * @param nodes a array of Undirected graph node
   * @return a connected set of a Undirected graph
   */
  public List<List<Integer>> connectedSet(ArrayList<UndirectedGraphNode> nodes) {
    if (nodes == null || nodes.size() == 0) {
      return null;
    }

    List<List<Integer>> result = new ArrayList<>();
    Set<UndirectedGraphNode> visited = new HashSet<>();
    for (UndirectedGraphNode node : nodes) {
      if (!visited.contains(node)) {
        bfs(node, visited, result);
      }
    }

    return result;
  }

  private void bfs(UndirectedGraphNode node,
                   Set<UndirectedGraphNode> visited, List<List<Integer>> result) {
    Queue<UndirectedGraphNode> bfsQ = new LinkedList<>();
    bfsQ.offer(node);
    visited.add(node);  // I forgot to add into visited
    List<Integer> union = new ArrayList<>();
    while (!bfsQ.isEmpty()) {
      UndirectedGraphNode nd = bfsQ.poll();
      union.add(nd.label);
      for (UndirectedGraphNode nei : nd.neighbors) {
        if (!visited.contains(nei)) {
          visited.add(nei);
//          union.add(nei.label);  // I re-add this one here!!!
          bfsQ.offer(nei);
        }
      }
    }

    Collections.sort(union);
    result.add(union);
  }
}
