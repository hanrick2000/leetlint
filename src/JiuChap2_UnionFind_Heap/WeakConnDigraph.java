package JiuChap2_UnionFind_Heap;

import java.util.*;

/**
 * http://www.lintcode.com/en/problem/find-the-weak-connected-component-in-the-directed-graph/
 * Created this class in JiuChap2_UnionFind_Heap at 10:26 PM, 10/29/2015.
 */

public class WeakConnDigraph {

  public static void main(String[] args) {
    WeakConnDigraph wcd = new WeakConnDigraph();
    wcd.test();
  }

  public void test(){
    ArrayList<DirectedGraphNode> nodes = new ArrayList<>();
    DirectedGraphNode yi = new DirectedGraphNode(1);
    DirectedGraphNode er = new DirectedGraphNode(2);
    DirectedGraphNode sa = new DirectedGraphNode(3);
    DirectedGraphNode si = new DirectedGraphNode(4);
    DirectedGraphNode wu = new DirectedGraphNode(5);
    DirectedGraphNode li = new DirectedGraphNode(6);
    DirectedGraphNode qi = new DirectedGraphNode(7);

//    qi.addNei(yi);
//    yi.addNei(er);
//    yi.addNei(si);
//    sa.addNei(wu);
//    si.addNei(er);
//    li.addNei(wu);
//
//    nodes.add(qi);
//    nodes.add(yi);
//    nodes.add(er);
//    nodes.add(sa);
//    nodes.add(si);
//    nodes.add(wu);
//    nodes.add(li);

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

    List<List<Integer>> result = connectedSet2(nodes);
    for (List<Integer> res : result) {
      System.out.println(res);
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
    UF uf = new UF(nodes);

    // phase 1: build init uf
    for (DirectedGraphNode node : nodes) {
      for (DirectedGraphNode nei : node.neighbors) {
        int pa_node = uf.find(node);
        int pa_nei = uf.find(nei);
        if (pa_node != pa_nei) {
          uf.union(node, nei);
        }
      }
    }
    uf.printUF();

    System.out.println("----");
    // phase 2: compress UF
    for (DirectedGraphNode node : nodes) {
      uf.compress_find(node);
    }

    uf.printUF();

    System.out.println("----");
//    Map<Integer, List<Integer>> res = uf.findUnion();
//    for (Map.Entry<Integer, List<Integer>> ks : res.entrySet()) {
//      System.out.println(ks.getKey() + " : " + ks.getValue());
//    }

    List<List<Integer>> res = new ArrayList<>();
    Map<Integer, List<Integer>> hashmap = new HashMap<>();
    for (DirectedGraphNode node : nodes) {
      int pa_i = uf.find(node);
      if (!hashmap.containsKey(pa_i)) {
        hashmap.put(pa_i, new ArrayList<>());
      }
      hashmap.get(pa_i).add(node.label);
    }

    for (int i : hashmap.keySet()) {
      List<Integer> list = hashmap.get(i);
      Collections.sort(list);
      res.add(list);
    }
    return res;
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

  private class UF {
    Map<Integer, Integer> father;

    UF(ArrayList<DirectedGraphNode> nodes) {
      father = new HashMap<>();
      for (DirectedGraphNode node : nodes) {
        father.put(node.label, node.label);
      }
    }

    public int find(DirectedGraphNode node) {
      int fa = father.get(node.label);
      while (fa != father.get(fa)) {
        fa = father.get(fa);
      }
      return fa;
    }

    public int compress_find(DirectedGraphNode x) {
      int parent = father.get(x.label);
      while (parent != father.get(parent)) {
        parent = father.get(parent);
      }

      int temp = -1;
      int pa = x.label; //father.get(x.label);
      while (pa != father.get(pa)) {
        temp = father.get(pa);
        father.put(pa, parent);
        pa = temp;
      }
      return parent;
    }

    public void union(DirectedGraphNode p, DirectedGraphNode q) {
      int fa_p = find(p);
      int fa_q = find(q);
      if (fa_p != fa_q) {
        father.put(fa_p, fa_q);
      }
    }

    public void printUF() {
      for (Map.Entry<Integer, Integer> es : father.entrySet()) {
        System.out.println(es.getKey() + " : " + es.getValue());
      }
    }

    public Map<Integer, List<Integer>> findUnion() {
      Map<Integer, List<Integer>> result = new HashMap<>();
      for (Map.Entry<Integer, Integer> ks : father.entrySet()) {
        if (!result.containsKey(ks.getValue())) {
          result.put(ks.getValue(), new ArrayList<>());
          result.get(ks.getValue()).add(ks.getKey());
        }
        else {
          result.get(ks.getValue()).add(ks.getKey());
        }
      }

      return result;
    }
  }
}
