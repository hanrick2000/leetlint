package freq3_tony;

/**
 * 相比1337更好的DP方法. 换一个思路和看法: PathNum(i,j) 表示从(0,0)到(i,j)的unique path. 而不像1337那样的从(i,j)到终点的unique path
 * 
 * 还有: 算是是DP, 而且是2d的路径, 但不一定要O(m*n)的空间储存记录!
 * 
 * @author tzhang
 *
 */
public class UniquePathN00t {
  // 一般的unique path问题的DP解法: 已经优化到O(min(m,n))空间
  public int uniquePathDP(int m, int n) {
    if (m == 0 || n == 0)
      return 0;
    int x = Math.min(m, n);
    int y = Math.max(m, n);
    int[] row = new int[x];

    row[0] = 1;
    for (int i = 0; i < y; i++) {
      for (int j = 1; j < x; j++) {
        row[j] += row[j - 1];
      }
    }
    return row[x - 1];
  }

  public int uniquePathDPObjstacles(int[][] obstacleGrid) {
    int m = obstacleGrid.length;
    if (m == 0)
      return 0;
    int n = obstacleGrid[0].length;
    if (n == 0)
      return 0;

    int[] row = new int[n];
    row[0] = 1;
    for (int i = 0; i < m; i++) {
      int totalEachRow = 0;  // 检查full row are 0
      for (int j = 0; j < n; j++) {
        if (obstacleGrid[i][j] == 1)
          row[j] = 0; // 说明起点到这个点是没有路径的.
        else if (j > 0)  // 这才叫用在点子上的else if
          row[j] += row[j - 1];
        totalEachRow += row[j];  // 别漏了
      }
      if (totalEachRow == 0)  // 剪枝!
        return 0;
    }
    return row[n - 1];
  }

  /**
   * ctor to call the client
   */
  public UniquePathN00t() {
    // System.out.println(uniquePathDP(7,3));
    int[][] obs = new int[][] { {0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
    System.out.println(uniquePathDPObjstacles(obs));
  }

  public static void main(String[] args) {
    UniquePathN00t npnt = new UniquePathN00t();
  }
}
