package JiuChap2_UnionFind_Heap;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * What's the time complexity now?
 * Created this class in JiuChap2_UnionFind_Heap at 9:33 AM, 10/31/2015.
 */
public class UF9ch {
  Map<Integer, Integer> father;
  UF9ch(Set<Integer> nodes) {
    if (nodes.size() == 0) {
      //
      return;
    }
    father = new HashMap<>();
    for (Integer node : nodes) {
      father.put(node, node); // every node's parent is itself
    }
  }

  public int find(Integer node) {
    return 0;
  }

  public int compress_find(Integer node) {
    return 0;
  }

  public void union(Integer p, Integer q) {

  }


}
