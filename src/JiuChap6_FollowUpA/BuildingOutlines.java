package JiuChap6_FollowUpA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import misc.HashHeap;

/**
 * http://www.lintcode.com/en/problem/building-outline
 * Created at 6:47 PM on 11/16/15.
 */
public class BuildingOutlines {
  public static void main(String[] args) {
    BuildingOutlines bo = new BuildingOutlines();
    bo.test();
  }

  public void test() {
    //Building t1 = new Building(1,10);
    //Building t2 = new Building(2,3);
    //Building t3 = new Building(5,8);
    //Building t4 = new Building(4,7);
    //List<Building> list = new ArrayList<>();
    //list.add(t1);
    //list.add(t2);
    //list.add(t3);
    //list.add(t4);
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
  /**
   * @param buildings: A list of lists of integers
   * @return: Find the outline of those buildings
   */
  public ArrayList<ArrayList<Integer>> buildingOutline(int[][] buildings) {
    // write your code here
    ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
    if (buildings == null) {
      return ans;
    }
    List<Edge> edges = new ArrayList<>();
    for (int[] building : buildings) {
      edges.add(new Edge(building[0], 1, building[2]));
      edges.add(new Edge(building[1], 0, building[2]));
    }

    Collections.sort(edges);
    ArrayList<Integer> point = new ArrayList<>();
    HashHeap heap = new HashHeap("max");
    for (Edge edge : edges) {
      if (edge.flag == 1) {
        if (heap.isEmpty() || edge.height > heap.peek()) {
          point = new ArrayList<>(Arrays.asList(edge.time, edge.height));
          ans.add(point);
        }
        heap.add(edge.height);
      }
      else {
        heap.remove(edge.height);
        if (heap.isEmpty() || edge.height > heap.peek()) {
          if (heap.isEmpty()) {
            point = new ArrayList<>(Arrays.asList(edge.time, 0));
          }
          else {
            point = new ArrayList<>(Arrays.asList(edge.time, heap.peek()));
          }
          ans.add(point);
        }
      }
    }

    return convert(ans);
  }

  private ArrayList<ArrayList<Integer>> convert(ArrayList<ArrayList<Integer>> points) {
    ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
    if (points.size() > 0) {
      int pre = points.get(0).get(0);
      int height = points.get(0).get(1);
      for (int i =1; i < points.size(); ++i) {
        ArrayList<Integer> now = new ArrayList<>();
        int ts = points.get(i).get(0);
        if (height > 0) {
          now.add(pre);
          now.add(ts);
          now.add(height);
          ans.add(now);
        }
        pre = ts;
        height = points.get(i).get(1);
      }
    }

    return ans;
  }

  class Res {
    int timestamp;
    int height;
    Res(int ts, int h) {
      timestamp = ts;
      height = h;
    }
  }

  class Building {
    int start, end, height;
    Building(int s, int e, int h) {
      start = s;
      end = e;
      height = h;
    }
  }

  class Edge implements Comparable<Edge> {
    int time;
    int flag; // start is larger than end, for the purpose of sorting
    int height;
    Edge(int t, int f, int h) {
      this.time = t;
      this.flag = f;
      this.height = h;
    }

    @Override public int compareTo(Edge other) {
      if (this.time != other.time) {
        return compareInteger(this.time, other.time);
      }
      if (this.flag == other.flag) {  // does the order matter here?
        return compareInteger(this.height, other.height);
      }
      return this.flag == 1 ? -1 : 1;
    }

    int compareInteger(int a, int b) {
      return a <= b ? -1 : 1;
    }
  }
}
