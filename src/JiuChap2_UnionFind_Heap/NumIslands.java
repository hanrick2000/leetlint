package JiuChap2_UnionFind_Heap;

/**
 * Revised number of islands I, still making mistakes in BFS recursion with updating values.
 * Created this class in JiuChap2_UnionFind_Heap at 2:59 PM, 11/1/2015.
 */
public class NumIslands {
  public static void main(String[] args) {
    NumIslands nis = new NumIslands();
    nis.test();
  }

  public void test() {
    int[][] grid = new int[][]{
//        {1,1,0},
//        {0,0,1},
//        {1,0,0}
        {1, 1, 0, 0, 0},
        {0, 1, 1, 0, 1},
        {1, 1, 0, 1, 1},
        {0, 0, 0, 0, 0},
        {0, 0, 0, 0, 1}
    };

    int islands = numIslands(grid);
    System.out.println("result: " + islands);
  }

  int[] dx = new int[]{1, 0, -1, 0};
  int[] dy = new int[]{0, 1, 0, -1};

  public int numIslands(int[][] grid) {
    if (grid == null || grid.length == 0) {
      return 0;
    }

    int[][] steps = new int[grid.length][grid[0].length];
    int res = 0;
    for (int i = 0; i < grid.length; ++i) {
      for (int j = 0; j < grid[0].length; ++j) {
        if (grid[i][j] == 1) {
          int tmp = flood(grid, i, j, steps);
          if (tmp > 0) {
            System.out.println("Area of islands: " + tmp);
            res++;
          }
        }
      }
    }

    return res;
  }

  /**
   * Revised on 20151101. Inspired by ski/knight
   *
   * @param grid
   * @param r
   * @param c
   * @param steps : the "min" area at this point
   * @return
   */
  public int flood(int[][] grid, int r, int c, int[][] steps) {
    if (!isValid(grid, r, c)) {
      return 0;
    }
    grid[r][c] = 0;
    int levelRC = 0;  // simply a temp var, the final result is store into steps!
    for (int i = 0; i < 4; ++i) {
      int rr = r + dx[i];
      int cc = c + dy[i];
      levelRC += flood(grid, rr, cc, steps);
    }
    return steps[r][c] = levelRC+1;
//    return levelRC = steps[r][c] + 1; // got confused of var that i defined: levelRC is temp var, steps is the result!!!
  }

  /**
   * Wrong due to un-well defined algs before coding!!!
   * @param grid
   * @param r
   * @param c
   * @return
   */
  public int floodWrong(int[][] grid, int r, int c) {
    int rs = 0;
    for (int i = 0; i < 4; ++i) {
      int rr = r + dx[i];
      int cc = c + dy[i];
      if (isValid(grid, rr, cc)) {
//        rs = flood(grid, rr, cc) + 1;
        rs = floodWrong(grid, rr, cc) + 1;
      }
    }
    return rs;
  }

  private boolean isValid(int[][] grid, int r, int c) {
    if (r < 0 || r > grid.length-1 || c < 0 || c > grid[0].length-1 ||
        grid[r][c] == 0) {
      return false;
    }
    grid[r][c] = 0;
    return true;
  }

}
