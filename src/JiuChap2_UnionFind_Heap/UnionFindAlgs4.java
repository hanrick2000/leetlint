package JiuChap2_UnionFind_Heap;

import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;
import java.util.Map;

/**
 * http://algs4.cs.princeton.edu/15uf/
 * Created this class in JiuChap2_UnionFind_Heap at 10:38 PM, 10/29/2015.
 */
public class UnionFindAlgs4 {
  Map<Node, Node> map;

  private static class Node {
    int label;

    Node(int l) {
      this.label = l;
    }

    public boolean equals(Node other) {
      return this.label == other.label;
    }
  }


  UnionFindAlgs4() {
    this.map = new HashMap<>();
  }

  public Node find(Node p) {
    while (!p.equals(map.get(p))) {
      p = map.get(p);
    }
    return p;
  }

  /**
   * merge set contains p to set contains q
   *
   * @param p
   * @param q
   */
  public void union(Node p, Node q) {
    Node pa_p = find(p);
    Node pa_q = find(q);
    if (pa_p.equals(pa_q))  return;
    map.put(pa_p, pa_q);
  }

  /**
   * BAD design of test. Should make Node to be static nested classes!!!!
   * https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html
   *
   * So I still need to review my knowledge in Java
   *
   * @param ufa4
   */
//  private void test(UnionFindAlgs4 ufa4) {
//    Node oo = new Node(0);
//    Node yi = new Node(1);
//    Node er = new Node(2);
//    Node sa = new Node(3);
//    Node si = new Node(4);
//    Node wu = new Node(5);
//    Node li = new Node(6);
//    Node qi = new Node(7);
//    Node ba = new Node(8);
//    Node ji = new Node(9);
//
//    ufa4.map.put(oo, yi);
//    ufa4.map.put(yi, yi);
//    ufa4.map.put(er, yi);
//    ufa4.map.put(sa, ba);
//    ufa4.map.put(si, sa);
//    ufa4.map.put(wu, oo);
//    ufa4.map.put(li, wu);
//    ufa4.map.put(qi, yi);
//    ufa4.map.put(ba, ba);
//    ufa4.map.put(ji, ba);
//
//    StdOut.println(ufa4.find(wu).label);
//    StdOut.println(ufa4.find(ji).label);
//
//    ufa4.union(wu, ji);
//
//    StdOut.println(ufa4.find(wu).label);
//    StdOut.println(ufa4.find(ji).label);
//  }

  public static void main(String[] args) {
//    Node oo = new Node(0, 1);
    UnionFindAlgs4 ufa4 = new UnionFindAlgs4();
    Node oo = new Node(0);
    Node yi = new Node(1);
    Node er = new Node(2);
    Node sa = new Node(3);
    Node si = new Node(4);
    Node wu = new Node(5);
    Node li = new Node(6);
    Node qi = new Node(7);
    Node ba = new Node(8);
    Node ji = new Node(9);

    ufa4.map.put(oo, yi);
    ufa4.map.put(yi, yi);
    ufa4.map.put(er, yi);
    ufa4.map.put(sa, ba);
    ufa4.map.put(si, sa);
    ufa4.map.put(wu, oo);
    ufa4.map.put(li, wu);
    ufa4.map.put(qi, yi);
    ufa4.map.put(ba, ba);
    ufa4.map.put(ji, ba);

    StdOut.println(ufa4.find(wu).label);
    StdOut.println(ufa4.find(ji).label);

    ufa4.union(wu, ji);

    StdOut.println(ufa4.find(wu).label);
    StdOut.println(ufa4.find(ji).label);
  }
}
