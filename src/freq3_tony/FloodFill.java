package freq3_tony;
/**
 * 经典的图形学算法. 也是Surrounded Region题目的解法.
 * https://www.ktbyte.com/java-tutorial/book/flood-fill
 * http://blog.csdn.net/linhuanmars/article/details/22904855
 * @author tzhang
 *
 */
public class FloodFill {
  public static void main(String[] args) {
      String map = "##....###!#...###..!#.#..#..s!####.#...!##...###.";
      String[] lines = map.split("!");
      char[][] grid = new char[lines.length][lines[0].length()];
      for (int j=0;j<grid.length;j++) for (int i=0;i<grid[j].length;i++) grid[j][i] = lines[j].charAt(i);
//      floodFill(grid, new boolean[grid.length][grid[0].length], 2, 8); //row 2, 8 is where the 's' is
      for (int j=0;j<grid.length;j++) {
          for (int i=0;i<grid[j].length;i++) System.out.print(grid[j][i]);
          System.out.println();
      }
  }
  static void floodFill(char[][] grid, boolean[][] visited, int r, int c) {
      if(r < 0 || r >= grid.length || c < 0 || c >= grid[0].length) return;
      if(visited[r][c]) return;
      visited[r][c] = true;
      if(grid[r][c]=='#') return;
      if(grid[r][c]=='.') grid[r][c] = '*';
      floodFill(grid,visited,r+1,c);
      floodFill(grid,visited,r-1,c);
      floodFill(grid,visited,r,c+1);
      floodFill(grid,visited,r,c-1);
  }
}