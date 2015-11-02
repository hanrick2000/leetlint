package JiuChap2_UnionFind_Heap;

import java.util.*;

/**
 * http://www.lintcode.com/en/problem/number-of-islands-ii
 * Created this class in JiuChap2_UnionFind_Heap at 8:11 PM, 11/1/2015.
 */
public class NumIslandsII {

  public static void main(String[] args) {
    NumIslandsII ni2 = new NumIslandsII();
    ni2.test();
  }

  public void test() {
//    int n = 3, m = 3;
//    Point p1 = new Point(0,0);
//    Point p2 = new Point(0,1);
//    Point p3 = new Point(2,2);
//    Point p4 = new Point(2,1);
//    Point[] p = new Point[]{p1, p2, p3, p4};
//    int n = 1, m = 1;
//    Point[] p = new Point[0];

    int n = 3, m = 3;
    Point p1 = new Point(0,1);
    Point p2 = new Point(1, 2);
    Point p3 = new Point(1, 0);
    Point p4 = new Point(2, 1);
    Point p5 = new Point(1,1);
    Point[] p = new Point[]{p1, p2, p3, p4, p5};
    numIslands2(n, m, p);
  }

  /**
   * @param n         an integer
   * @param m         an integer
   * @param operators an array of point
   * @return an integer array
   */
  public List<Integer> numIslands2(int n, int m, Point[] operators) {
    // Write your code here
    List<Integer> result = new ArrayList<>();
    if (operators == null || operators.length == 0) {
      return result;
    }
    int leng = n * m;
    int[][] grid = new int[n][m];
//    UF uf = new UF(operators, n, m);
    UF uf = new UF(n, m);
    int num = 0;
    //
    for (int k = 0; k < operators.length; ++k) {
      num++;
      Point op = operators[k];
      grid[op.x][op.y] = 1;
      int rc = op.x * m + op.y;
//      uf.put(rc, m);
      for (int i = 0; i < 4; ++i) {
        int rr = op.x + dx[i];
        int cc = op.y + dy[i];
        int rrcc = rr * m + cc;
        if (isValid(grid, n, m, rrcc)) {
          int pa_rrcc = uf.find(rrcc);
          int pa_rc = uf.find(rc);
          if (pa_rrcc != pa_rc) {
            num--;       // got from jiuzhang's solution but still failed at @ 50%, why???
            uf.union(rc, rrcc);
          }
        }
      }

//      uf.compress_find(rc); // everytime a new point, do a compression.
//      num = uf.countUnion();
      System.out.println(num);
      result.add(num);
    }
    System.out.println(result);
    return result;
  }

  int[] dx = new int[]{1, -1, 0, 0};
  int[] dy = new int[]{0, 0, 1, -1};

  private boolean isValid(int[][] grid, int R, int C, int rc) {
    int r = rc / C;
    int c = rc % C;
    if (r < 0 || r >= R || c < 0 || c >= C || grid[r][c] == 0) {
      return false;
    }
    return true;
  }

  private class UF {
    Map<Integer, Integer> father;

    UF(int n, int m) {
      father = new HashMap<>();
      for (int r = 0; r < n; ++r) {
        for (int c = 0; c < m; ++c) {
          int id = r * m + c;
          father.put(id, id);
        }
      }
    }

    UF(Point[] op, int R, int C) {
      father = new HashMap<>();
      for (Point o : op) {
        int rc = o.x * C + o.y;
        father.put(rc, rc);
      }
    }

    public int find(int x) {
      int parent = father.get(x);
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

    public int compress_find(int x) {
      int parent = find(x);
      int temp = -1;
      int pa = x; //father.get(x);
      while (pa != father.get(pa)) {
        temp = father.get(pa);
        father.put(pa, parent);
        pa = temp;
      }
      return parent;
    }

    public int countUnion() {
      Set<Integer> res = new HashSet<>();
      for (int i : father.values()) {
        res.add(i);
      }
      return res.size();
    }

  }

  private class Point {
    int x;
    int y;

    Point() {
      x = 0;
      y = 0;
    }

    Point(int a, int b) {
      x = a;
      y = b;
    }
  }
}
