package JiuChap2_UnionFind_Heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import misc.HashHeap;

/**
 * HashHeap + merge interval problem
 * http://www.lintcode.com/en/problem/building-outline/
 * Created this class in JiuChap2_UnionFind_Heap at 10:04 AM, 11/4/2015.
 */
public class BuildOutline {

  public static void main(String[] args) {
    //ArrayList<ArrayList<Integer>> buildings = new ArrayList<>();
    //buildings.add(new ArrayList<Integer>(Arrays.asList(1,3,3)));
    //buildings.add(new ArrayList<Integer>(Arrays.asList(2,4,4)));
    //buildings.add(new ArrayList<Integer>(Arrays.asList(5,6,1)));

    int[][] buildings = new int[][]{
        {1,3,3},
        {2,4,4},
        {5,6,1}
    };
    ArrayList<ArrayList<Integer>> ans = null;
    ans = new BuildOutline().buildingOutline(buildings);
    for (ArrayList<Integer> list : ans) {
      System.out.println(list);
    }
  }

  class Edge implements Comparable<Edge>{
    int pos;
    int height;
    boolean isStart;

    public Edge(int p, int h, boolean s) {
      this.pos = p;
      this.height = h;
      this.isStart = s;
    }

    @Override public int compareTo(Edge o) {
      if (this.pos != o.pos) {
        return less(this.pos, o.pos);
      }
      if (this.isStart && o.isStart) {
        //return less(this.height, o.height);  // catch
        return less(o.height, this.height);
      }
      if (!this.isStart && !o.isStart) {
        return less(this.height, o.height);
      }
      return this.isStart ? -1 : 1;
    }

    private int less(int p, int q) {
      return p - q;
    }
  }

  /**
   * @param buildings: A list of lists of integers
   * @return: Find the outline of those buildings
   */
  public ArrayList<ArrayList<Integer>> buildingOutline(int[][] buildings) {
    // write your code here
    ArrayList<ArrayList<Integer>> res = new ArrayList<>();
    if (buildings == null || buildings.length == 0) {
      return res;
    }
    List<Edge> edges = new ArrayList<>();
    for (int[] building : buildings) {
      Edge startEdge = new Edge(building[0], building[2], true);
      edges.add(startEdge);
      Edge endEdge = new Edge(building[1], building[2], false);
      edges.add(endEdge);
    }
    Collections.sort(edges);
    HashHeap heap = new HashHeap("max");
    ArrayList<Integer> now = null;
    for (Edge edge : edges) {
      if (edge.isStart) {
        if (heap.isEmpty() || edge.height > heap.peek()) {
          now = new ArrayList<>(Arrays.asList(edge.pos, edge.height));
          res.add(now);
        }
        heap.add(edge.height);
      }
      else {
        heap.remove(edge.height);
        if (heap.isEmpty() || edge.height > heap.peek()) {
          if (heap.isEmpty()) {
            now = new ArrayList<>(Arrays.asList(edge.pos, 0));
          }
          else {
            now = new ArrayList<>(Arrays.asList(edge.pos, heap.peek()));
          }
          res.add(now);
        }
      }
    }
    return output(res);
  }

  ArrayList<ArrayList<Integer>> output(ArrayList<ArrayList<Integer>> res) {
    ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
    if (res.size() > 0) {
      int pre = res.get(0).get(0);
      int height = res.get(0).get(1);
      for (int i = 1; i < res.size(); i++) {
        ArrayList<Integer> now = new ArrayList<>();
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
}
