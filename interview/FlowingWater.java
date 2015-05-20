package interview;

import java.util.*;

public class FlowingWater {
  public static void main(String[] args) {
    FlowingWater fw = new FlowingWater();
  }

  public FlowingWater() {
    int[][] mat = new int[][] {{1,2,3}, {4,2,4}, {6,7,1}};
//        new int[][] { {1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}, {2, 4, 5, 3, 1},
//            {6, 7, 1, 4, 5}, {5, 1, 1, 2, 4}};
    List<Point> res = flowing_water(mat);
    for (Point p : res) {
      System.out.println(p.x + " " + p.y);
    }
  }

  static class Point {
    int x, y;

    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
    
    public String toString(){
      String res = this.x + " " + this.y;
      return res;
    }
  }

  void search(Point pt, HashMap<Point, Boolean> visited, int[][] mat) {
    int[][] dirs = { {0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    for (int i = 0; i < 4; ++i) {
      int[] dir = dirs[i];
      Point new_pt = new Point(dir[0] + pt.x, dir[1] + pt.y);
      if (new_pt.x < 0 || new_pt.x >= mat.length || new_pt.y < 0
          || new_pt.y >= mat.length) {
        continue;
      }
      if (mat[new_pt.x][new_pt.y] < mat[pt.x][pt.y]
          || visited.containsKey(new_pt)) {
        continue;
      }
      visited.put(new_pt, true);
      search(new_pt, visited, mat);
    }
  }

  public List<Point> flowing_water(int[][] mat) {
    int n = mat.length;

    HashMap<Point, Boolean> visited_pac = new HashMap<Point, Boolean>();
    for (int i = 0; i < n; ++i) {
      Point p = new Point(0, i);
      visited_pac.put(p, true);
      System.out.println(visited_pac.keySet());
      search(p, visited_pac, mat);
    }
//    for (int i = 0; i < n; ++i) {
//      Point p = new Point(i, 0);
//      visited_pac.put(p, true);
//      search(p, visited_pac, mat);
//    }
//
//    HashMap<Point, Boolean> visited_alt = new HashMap<Point, Boolean>();
//    for (int i = 0; i < n; ++i) {
//      Point p = new Point(n - 1, i);
//      visited_alt.put(p, true);
//      search(p, visited_alt, mat);
//    }
//
//    for (int i = 0; i < n; ++i) {
//      Point p = new Point(i, n - 1);
//      visited_alt.put(p, true);
//      search(p, visited_alt, mat);
//    }
    ArrayList<Point> ret = new ArrayList<Point>();
//    for (Point key : visited_alt.keySet()) {
//      if (visited_pac.containsKey(key)) {
//        ret.add(key);
//      }
//    }
    return ret;
  }
}
