package JiuChap2_UnionFind_Heap;

import java.util.PriorityQueue;

/**
 * Beautiful heap utilization, and the idea of trap-rain-water-i
 * http://www.lintcode.com/en/problem/trapping-rain-water-ii/
 * Created this class in JiuChap2_UnionFind_Heap at 10:04 AM, 11/4/2015.
 */
public class TrapRainII {
  public static void main(String[] args) {
    int[][] M = new int[][]{
        {12,13,0,12},
        {13,4,13,12},
        {13,8,10,12},
        {12,13,12,12},
        {13,13,13,13}
    };
    TrapRainII tr2 = new TrapRainII();
    int ans = tr2.trapRainWater(M);
    System.out.println(ans);
  }

  static final int[] dx = new int[]{1, -1, 0, 0};
  static final int[] dy = new int[]{0, 0, -1, 1};

  /**
   * 9 chap's solution
   * @param heights: a matrix of integers
   * @return: an integer
   */
  public int trapRainWater(int[][] heights) {
    // write your code here
    if (heights == null || heights.length == 0) {
      return 0;
    }

    PriorityQueue<Cell> minq = new PriorityQueue<>();
    int n = heights.length, m = heights[0].length;
    int[][] visited = new int[n][m];
    for (int r = 0; r < n; ++r) {
      minq.offer(new Cell(r, 0, heights[r][0]));
      minq.offer(new Cell(r, m-1, heights[r][m-1]));
      visited[r][0] = 1;
      visited[r][m-1] = 1;
    }

    for (int c = 0; c < m; ++c) {
      minq.offer(new Cell(0, c, heights[0][c]));
      minq.offer(new Cell(n-1, c, heights[n-1][c]));
      visited[0][c]= 1;
      visited[n-1][c] = 1;
    }

    int ans = 0;
    while (!minq.isEmpty()) {
      Cell now = minq.poll();  // get the shortest bar
      for (int i = 0; i < 4; ++i) {
        int xx = now.x + dx[i];
        int yy = now.y + dy[i];
        if (isValid(heights, visited, xx, yy)) {
          visited[xx][yy] = 1;
          minq.offer(new Cell(xx, yy, Math.max(now.h, heights[xx][yy])));
          ans += Math.max(0, now.h - heights[xx][yy]);
        }
      }
    }

    return ans;
  }

  private static boolean isValid(int[][] M, int[][] visited, int r, int c) {
    int R = M.length, C = M[0].length;
    if (r < 0 || r >= R || c < 0 || c >= C || visited[r][c] == 1) {
      return false;
    }
    return true;
  }

  private class Cell implements Comparable<Cell> {
    public int x, y, h;
    Cell() {}
    Cell(int xx, int yy, int hh) {
      x = xx;
      y = yy;
      h = hh;
    }

    //@Override public int compare(Cell o1, Cell o2) {
    //  if (o1.h > o2.h) {
    //    return 1;
    //  }
    //  else if (o1.h == o2.h) {
    //    return 0;
    //  }
    //  else {
    //    return -1;
    //  }
    //}

    @Override public int compareTo(Cell o) {
      return this.h - o.h;
    }
  }
}
