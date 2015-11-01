package JiuChap2_UnionFind_Heap;

import java.util.*;

/**
 * What's the time complexity now? O(1): ammotized analysis
 * Generic version of the Union Find class.
 * @param <T> the type of the unit
 *
 * Created this class in JiuChap2_UnionFind_Heap at 9:33 AM, 10/31/2015.
 */
public class UF9ch<T> {
  Map<T, T> father;
  UF9ch() {
    //
  }
  UF9ch(Set<T> nodes) {
    if (nodes.size() == 0) {
      //
      return;
    }
    father = new HashMap<>();
    for (T node : nodes) {
      father.put(node, node); // every node's parent is itself
    }
  }

  UF9ch(ArrayList<T> nodes) {
    if (nodes.size() == 0) {
      return;
    }
    father = new HashMap<>();
    for (T node : nodes) {
      father.put(node, node);
    }
  }

  public T find(T x) {
    T parent = father.get(x);
    while (parent != father.get(parent)) {
      parent = father.get(parent);
    }
    return parent;
  }

  public T compress_find(T x) {
    T parent = father.get(x);
    while (parent != father.get(parent)) {
      parent = father.get(parent);
    }

    T temp = null;
    T fa = x; // father.get(x);
    while (fa != father.get(fa)) {
      temp = father.get(fa);
      father.put(fa, parent);
      fa = temp;
    }
    return parent;
  }

  public void union(T p, T q) {
    T pa_p = find(p);
    T pa_q = find(q);
    if (pa_p != pa_q) {
      father.put(pa_p, pa_q);
    }
  }

  public void test() {
    Set<Integer> data = new HashSet<>();
    data.add(1);
    data.add(2);
    data.add(3);
    data.add(4);
    data.add(5);
    data.add(6);
    data.add(7);
    data.add(8);

    UF9ch<Integer> uf9 = new UF9ch<>(data);
    uf9.father.put(5, 4);
    uf9.father.put(4,3);
    uf9.father.put(3,2);
    uf9.father.put(2,1);

    uf9.father.put(7,6);
    uf9.father.put(8,5);

    int pa_8 = uf9.compress_find(8);
    int pa_7 = uf9.compress_find(7);
//    StdOut.println(pa_8);
//    StdOut.println(pa_7);
//    StdOut.println(uf9.find(8));
//    StdOut.println(uf9.find(7));

    for (Map.Entry<Integer, Integer> entry : uf9.father.entrySet()) {
      System.out.println(entry.getKey() + " : " + entry.getValue());
    }
  }

  public static void main(String[] args) {
    new UF9ch<Integer>().test();
  }
}
