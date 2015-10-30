package JiuChap2_UnionFind_Heap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * http://www.lintcode.com/en/problem/find-the-weak-connected-component-in-the-directed-graph/
 * Created this class in JiuChap2_UnionFind_Heap at 10:26 PM, 10/29/2015.
 */

public class WeakConnDigraph {
  public static void main(String[] args) {

  }

  private class UF {
    Map<DirectedGraphNode, DirectedGraphNode> map;
  }

  private class DirectedGraphNode {
    int label;
    ArrayList<DirectedGraphNode> neighbors;

    DirectedGraphNode(int x) {
      label = x;
      neighbors = new ArrayList<DirectedGraphNode>();
    }
  }

  /**
   * @param nodes a array of Directed graph node
   * @return a connected set of a directed graph
   */
  public List<List<Integer>> connectedSet2(ArrayList<DirectedGraphNode> nodes) {
    // Write your code here
    if (nodes == null || nodes.size() == 0) {
      return null;
    }

    List<List<Integer>> result = new ArrayList<>();
    return null;
  }
}
