package testingAns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import misc.HashHeap;

/**
 * Created at 8:10 PM on 11/16/15.
 */
public class Outline9Chap {

  public static void main(String[] args) {
    Outline9Chap bo = new Outline9Chap();
    bo.test();
  }

  public void test() {
    int[][] buildings = new int[][]{
        //{1,3,3},
        //{2,4,4},
        //{5,6,1}
        {4,67,187},{3,80,65},{49,77,117},{67,74,9},{6,42,92},{48,67,69},{10,13,58},{47,99,152},{66,99,53},{66,71,34},{27,63,2},{35,81,116},{47,49,10},{68,97,175},{20,33,53},{24,94,20},{74,77,155},{39,98,144},{52,89,84},{13,65,222},{24,41,75},{16,24,142},{40,95,4},{6,56,188},{1,38,219},{19,79,149},{50,61,174},{4,25,14},{4,46,225},{12,32,215},{57,76,47},{11,30,179},{88,99,99},{2,19,228},{16,57,114},{31,69,58},{12,61,198},{70,88,131},{7,37,42},{5,48,211},{2,64,106},{49,73,204},{76,88,26},{58,61,215},{39,51,125},{13,38,48},{74,99,145},{4,12,8},{12,33,161},{61,95,190},{16,19,196},{3,84,8},{5,36,118},{82,87,40},{8,44,212},{15,70,222},{16,25,176},{9,100,74},{38,78,99},{23,77,43},{45,89,229},{7,84,163},{48,72,1},{31,88,123},{35,62,190},{21,29,41},{37,97,81},{7,49,78},{83,84,132},{33,61,27},{18,45,1},{52,64,4},{58,98,57},{14,22,1},{9,85,200},{50,76,147},{54,70,201},{5,55,97},{9,42,125},{31,88,146}
    };
    ArrayList<ArrayList<Integer>> ans;

    ans = buildingOutline(buildings);
    for (ArrayList<Integer> a : ans)
      System.out.println(a);
  }

  //class HashHeap {
  //  ArrayList<Integer> heap;
  //  String mode;
  //  int size_t;
  //  HashMap<Integer, Node> hash;
  //
  //  class Node {
  //    public Integer id;
  //    public Integer num;
  //
  //    Node(Node now) {
  //      id = now.id;
  //      num = now.num;
  //    }
  //
  //    Node(Integer first, Integer second) {
  //
  //      this.id = first;
  //      this.num = second;
  //    }
  //  }
  //
  //  public HashHeap(String mod) {
  //    // TODO Auto-generated constructor stub
  //    heap = new ArrayList<Integer>();
  //    mode = mod;
  //    hash = new HashMap<Integer, Node>();
  //    size_t = 0;
  //  }
  //
  //  public int peek() {
  //    return heap.get(0);
  //  }
  //
  //  public int size() {
  //    return size_t;
  //  }
  //
  //  public Boolean isEmpty() {
  //    return (heap.size() == 0);
  //  }
  //
  //  int parent(int id) {
  //    if (id == 0) {
  //      return -1;
  //    }
  //    return (id - 1) / 2;
  //  }
  //
  //  int lson(int id) {
  //    return id * 2 + 1;
  //  }
  //
  //  int rson(int id) {
  //    return id * 2 + 2;
  //  }
  //
  //  boolean comparesmall(int a, int b) {
  //    if (a <= b) {
  //      if (mode == "min") {
  //        return true;
  //      } else {
  //        return false;
  //      }
  //    } else {
  //      if (mode == "min") {
  //        return false;
  //      } else {
  //        return true;
  //      }
  //    }
  //  }
  //
  //  void swap(int idA, int idB) {
  //    int valA = heap.get(idA);
  //    int valB = heap.get(idB);
  //
  //    int numA = hash.get(valA).num;
  //    int numB = hash.get(valB).num;
  //
  //    hash.put(valB, new Node(idA, numB));
  //    hash.put(valA, new Node(idB, numA));
  //    heap.set(idA, valB);
  //    heap.set(idB, valA);
  //  }
  //
  //  public Integer poll() {
  //    size_t--;
  //    Integer now = heap.get(0);
  //    Node hashnow = hash.get(now);
  //    if (hashnow.num == 1) {
  //      swap(0, heap.size() - 1);
  //      hash.remove(now);
  //      heap.remove(heap.size() - 1);
  //      if (heap.size() > 0) {
  //        siftdown(0);
  //      }
  //    } else {
  //      hash.put(now, new Node(0, hashnow.num - 1));
  //    }
  //    return now;
  //  }
  //
  //  public void add(int now) {
  //    size_t++;
  //    if (hash.containsKey(now)) {
  //      Node hashnow = hash.get(now);
  //      hash.put(now, new Node(hashnow.id, hashnow.num + 1));
  //    } else {
  //      heap.add(now);
  //      hash.put(now, new Node(heap.size() - 1, 1));
  //    }
  //
  //    siftup(heap.size() - 1);
  //  }
  //
  //  public void delete(int now) {
  //    size_t--;
  //    Node hashnow = hash.get(now);
  //    int id = hashnow.id;
  //    int num = hashnow.num;
  //    if (hashnow.num == 1) {
  //
  //      swap(id, heap.size() - 1);
  //      hash.remove(now);
  //      heap.remove(heap.size() - 1);
  //      if (heap.size() > id) {
  //        siftup(id);
  //        siftdown(id);
  //      }
  //    } else {
  //      hash.put(now, new Node(id, num - 1));
  //    }
  //  }
  //
  //  void siftup(int id) {
  //    while (parent(id) > -1) {
  //      int parentId = parent(id);
  //      if (comparesmall(heap.get(parentId), heap.get(id)) == true) {
  //        break;
  //      } else {
  //        swap(id, parentId);
  //      }
  //      id = parentId;
  //    }
  //  }
  //
  //  void siftdown(int id) {
  //    while (lson(id) < heap.size()) {
  //      int leftId = lson(id);
  //      int rightId = rson(id);
  //      int son;
  //      if (rightId >= heap.size()
  //          || (comparesmall(heap.get(leftId), heap.get(rightId)) == true)) {
  //        son = leftId;
  //      } else {
  //        son = rightId;
  //      }
  //      if (comparesmall(heap.get(id), heap.get(son)) == true) {
  //        break;
  //      } else {
  //        swap(id, son);
  //      }
  //      id = son;
  //    }
  //  }
  //}

  class Edge {
    int pos;
    int height;
    boolean isStart;

    public Edge(int pos, int height, boolean isStart) {
      this.pos = pos;
      this.height = height;
      this.isStart = isStart;
    }
  }

  class EdgeComparator implements Comparator<Edge> {
    @Override
    public int compare(Edge arg1, Edge arg2) {
      Edge l1 = (Edge) arg1;
      Edge l2 = (Edge) arg2;
      if (l1.pos != l2.pos) {
        return compareInteger(l1.pos, l2.pos);
      }
      if (l1.isStart && l2.isStart) {
        return compareInteger(l2.height, l1.height);
      }
      if (!l1.isStart && !l2.isStart) {
        return compareInteger(l1.height, l2.height);
      }
      return l1.isStart ? -1 : 1;
    }

    int compareInteger(int a, int b) {
      return a <= b ? -1 : 1;
    }
  }

  ArrayList<ArrayList<Integer>> output(ArrayList<ArrayList<Integer>> res) {
    ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();
    if (res.size() > 0) {
      int pre = res.get(0).get(0);
      int height = res.get(0).get(1);
      for (int i = 1; i < res.size(); i++) {
        ArrayList<Integer> now = new ArrayList<Integer>();
        int id = res.get(i).get(0);
        if (height > 0) {
          now.add(pre);
          now.add(id);
          now.add(height);
          ans.add(now);
        }
        pre = id;
        height = res.get(i).get(1);
      }
    }
    return ans;
  }

  public ArrayList<ArrayList<Integer>> buildingOutline(int[][] buildings) {
    // write your code here
    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();

    if (buildings == null || buildings.length == 0
        || buildings[0].length == 0) {
      return res;
    }
    ArrayList<Edge> edges = new ArrayList<Edge>();
    for (int[] building : buildings) {
      Edge startEdge = new Edge(building[0], building[2], true);
      edges.add(startEdge);
      Edge endEdge = new Edge(building[1], building[2], false);
      edges.add(endEdge);
    }
    Collections.sort(edges, new EdgeComparator());

    HashHeap heap = new HashHeap("max");

    ArrayList<Integer> now = null;
    for (Edge edge : edges) {
      if (edge.isStart) {
        if (heap.isEmpty() || edge.height > heap.peek()) {
          now = new ArrayList<Integer>(Arrays.asList(edge.pos,
              edge.height));
          res.add(now);
        }
        heap.add(edge.height);
      } else {
        heap.remove(edge.height);
        if (heap.isEmpty() || edge.height > heap.peek()) {
          if (heap.isEmpty()) {
            now = new ArrayList<Integer>(Arrays.asList(edge.pos, 0));
          } else {
            now = new ArrayList<Integer>(Arrays.asList(edge.pos,
                heap.peek()));
          }
          res.add(now);
        }
      }
    }
    return output(res);
  }
}