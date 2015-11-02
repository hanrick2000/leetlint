package JiuChap2_UnionFind_Heap;

import java.util.*;

/**
 * Created this class in JiuChap2_UnionFind_Heap at 2:40 PM, 11/1/2015.
 */
public class WeakConnDigraphLint {
  public static void main(String[] args) {
    WeakConnDigraphLint wcd = new WeakConnDigraphLint();
    wcd.test();
  }

  public void test(){
    ArrayList<DirectedGraphNode> nodes = new ArrayList<>();
    DirectedGraphNode yi = new DirectedGraphNode(1);
    DirectedGraphNode er = new DirectedGraphNode(2);
    DirectedGraphNode sa = new DirectedGraphNode(3);
    DirectedGraphNode si = new DirectedGraphNode(4);
    DirectedGraphNode wu = new DirectedGraphNode(5);

    yi.addNei(er);
    er.addNei(sa);
    sa.addNei(si);
    si.addNei(er);
    yi.addNei(wu);

    nodes.add(yi);
    nodes.add(er);
    nodes.add(sa);
    nodes.add(si);
    nodes.add(wu);

    List<List<Integer>> result = connectedSet(nodes);
    for (List<Integer> res : result) {
      System.out.println(res);
    }
  }
  /**
   * @param nodes a array of Directed graph node
   * @return a connected set of a directed graph
   */
  public List<List<Integer>> connectedSet(ArrayList<DirectedGraphNode> nodes) {
    // Write your code here
    if (nodes == null) {
      return null;
    }

    Set<Integer> hashset = new HashSet<>();
    for (DirectedGraphNode node : nodes) {
      hashset.add(node.label);
    }

    // phase 0: init union find
    UF uf = new UF(hashset);

    // phase 1: setup union find
    for (DirectedGraphNode node : nodes) {
      for (DirectedGraphNode neig : node.neighbors) {
        int pa_node = uf.find(node.label);
        int pa_neig = uf.find(neig.label);
        if (pa_node != pa_neig) {
          uf.union(node.label, neig.label);
        }
      }
    }

    // phase 2: get result from union find
    List<List<Integer>> result = new ArrayList<>();
    Map<Integer, List<Integer>> map = new HashMap<>();

    // for (Map.Entry<Integer, Integer> ks : uf.father.entrySet()) {
    //     if (!map.containsKey(ks)
    // }
    for (int i : hashset) {
      int pa_i = uf.find(i);
      if (!map.containsKey(pa_i)) {
        map.put(pa_i, new ArrayList<>());
      }
      map.get(pa_i).add(i);
    }

    for (List<Integer> list : map.values()) {
      Collections.sort(list);
      result.add(list);
    }

    return result;
  }

  private class UF {
    Map<Integer, Integer> father = new HashMap<>();
    UF(Set<Integer> nodes) {
      for (int node : nodes) {
        father.put(node, node);
      }
    }

    public int find(int node) {
      int parent = father.get(node);
      while (parent != father.get(parent)) {
        parent = father.get(parent);
      }
      return parent;
    }

    public void union(int p, int q) {
      int pa_p = find(p);
      int pa_q = find(q);
      if (pa_p != pa_q) {
        father.put(pa_p, pa_q);
      }
    }
  }

  private class DirectedGraphNode {
    int label;
    ArrayList<DirectedGraphNode> neighbors;

    DirectedGraphNode(int x) {
      label = x;
      neighbors = new ArrayList<>();
    }

    public void addNei(DirectedGraphNode x) {
      this.neighbors.add(x);
    }
  }
}
