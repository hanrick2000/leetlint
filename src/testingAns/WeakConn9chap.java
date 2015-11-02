package testingAns;

/**
 * Created this class in testingAns at 12:44 PM, 11/1/2015.
 */

import java.util.*;

public class WeakConn9chap {

  public static void main(String[] args) {
    WeakConn9chap wcd = new WeakConn9chap();
    wcd.test();
  }

  public void test() {
    ArrayList<DirectedGraphNode> nodes = new ArrayList<>();
    DirectedGraphNode yi = new DirectedGraphNode(1);
    DirectedGraphNode er = new DirectedGraphNode(2);
    DirectedGraphNode sa = new DirectedGraphNode(3);
    DirectedGraphNode si = new DirectedGraphNode(4);
    DirectedGraphNode wu = new DirectedGraphNode(5);
    DirectedGraphNode li = new DirectedGraphNode(6);
    DirectedGraphNode qi = new DirectedGraphNode(7);

//    yi.addNei(er);
//    yi.addNei(si);
//    sa.addNei(wu);
//    si.addNei(er);
//    li.addNei(wu);
//    qi.addNei(yi);
    yi.addNei(er);
    yi.addNei(wu);
    er.addNei(sa);
    sa.addNei(si);
    si.addNei(er);

    nodes.add(yi);
    nodes.add(er);
    nodes.add(sa);
    nodes.add(si);
    nodes.add(wu);
//    nodes.add(li);
//    nodes.add(qi);

    List<List<Integer>> result = connectedSet2(nodes);
    for (List<Integer> res : result) {
      System.out.println(res);
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

  class UnionFind {
    HashMap<Integer, Integer> father = new HashMap<Integer, Integer>();

    UnionFind(HashSet<Integer> hashSet) {
      for (Integer now : hashSet) {
        father.put(now, now);
      }
    }

    int find(int x) {
      int parent = father.get(x);
      while (parent != father.get(parent)) {
        parent = father.get(parent);
      }
      return parent;
    }

    int compressed_find(int x) {
      int parent = father.get(x);
      while (parent != father.get(parent)) {
        parent = father.get(parent);
      }
      int temp = -1;
      int fa = father.get(x);
      while (fa != father.get(fa)) {
        temp = father.get(fa);
        father.put(fa, parent);
        fa = temp;
      }
      return parent;

    }

    void union(int x, int y) {
      int fa_x = find(x);
      int fa_y = find(y);
      if (fa_x != fa_y)
        father.put(fa_x, fa_y);
    }
  }

  List<List<Integer>> print(HashSet<Integer> hashSet, UnionFind uf, int n) {
    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    HashMap<Integer, List<Integer>> hashMap = new HashMap<Integer, List<Integer>>();
    for (int i : hashSet) {
      int fa = uf.find(i);
      if (!hashMap.containsKey(fa)) {
        hashMap.put(fa, new ArrayList<>());
      }
//      List<Integer> now = hashMap.get(fa);
//      now.add(i);
//      hashMap.put(fa, now);
      hashMap.get(fa).add(i);
    }
    for (List<Integer> now : hashMap.values()) {
      Collections.sort(now);
      ans.add(now);
    }
    return ans;
  }

  public List<List<Integer>> connectedSet2(ArrayList<DirectedGraphNode> nodes) {
    // Write your code here

    HashSet<Integer> hashSet = new HashSet<Integer>();
    for (DirectedGraphNode now : nodes) {
      hashSet.add(now.label);
      for (DirectedGraphNode neighbour : now.neighbors) {
        hashSet.add(neighbour.label);
      }
    }
    UnionFind uf = new UnionFind(hashSet);


    for (DirectedGraphNode now : nodes) {

      for (DirectedGraphNode neighbour : now.neighbors) {
        int fnow = uf.find(now.label);
        int fneighbour = uf.find(neighbour.label);
        if (fnow != fneighbour) {
          uf.union(now.label, neighbour.label);
        }
      }
    }


    return print(hashSet, uf, nodes.size());
  }

}
