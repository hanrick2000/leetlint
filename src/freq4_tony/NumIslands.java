package freq4_tony;

/**
 * Leetcode 2015新题. 简单. 以为是SCC...
 * http://www.programcreek.com/2014/04/leetcode-number-of-islands-java/
 * @author tzhang
 *
 */
public class NumIslands {
  private int numIsIslands(char[][] grid) {
    if (grid == null || grid.length == 0 || grid[0].length == 0)
      return 0;
    int cnt = 0;
    for (int r = 0; r < grid.length; ++r) {
      for (int c = 0; c < grid[0].length; ++c) {
        if (grid[r][c] == '1') {
          cnt++;
          merge(grid, r, c);
        }
      }
    }
    return cnt;
  }

  /**
   * recursively 找到i,j所在岛屿. 将该岛屿覆盖为'2'
   * @param grid
   * @param i
   * @param j
   */
  private void merge(char[][] grid, int i, int j) {
    if (i < 0 || j < 0 || i > grid.length - 1 || j > grid[0].length - 1)
      return;
    if (grid[i][j] != '1')
      return; // 检查当前是遍历过或者为水则返回
    grid[i][j] = '2';
    merge(grid, i - 1, j);
    merge(grid, i + 1, j);
    merge(grid, i, j - 1);
    merge(grid, i, j + 1);
  }
  
  public NumIslands(){
    // char初始化蛮麻烦: http://stackoverflow.com/questions/11711228/how-to-init-char-array-using-char-literals
    char[][] map1 = new char[][] {
        "11110".toCharArray(),
        "11010".toCharArray(),
        "11000".toCharArray(),
        "00000".toCharArray()
    };
    char[][] map2 = new char[][] {
        "11110".toCharArray(),
        "11000".toCharArray(),
        "11010".toCharArray(),
        "00100".toCharArray()
    };
    int res1 = numIsIslands(map1);
    System.out.println("map 1 has " + res1);
    int res2 = numIsIslands(map2);
    System.out.println("map 2 has " + res2);
  }
  public static void main(String[] args) {
    NumIslands nis = new NumIslands();
  }
}
