package Easy;

/*-
 * Same as rotate matrix: draw a draft, find the regular pattern!
 * http://www.lintcode.com/en/problem/matrix-zigzag-traversal/
 * Created at 12:28 PM on 11/17/15.
 */
public class MatrixZigzag {
  public static void main(String[] args) {
    MatrixZigzag mz = new MatrixZigzag();
    int[][] M = new int[][] {
        {1,2,3,4},
        {5,6,7,8},
        {9,10,11,12}
    };

    int[] ans = mz.printZMatrix(M);
    for (int a : ans) {
      System.out.print(a + " ");
    }
  }

  /**
   * http://algorithm.yuanbin.me/zh-cn/problem_misc/matrix_zigzag_traversal.html
   * @param matrix
   * @return
   */
  public int[] printZMatrix(int[][] matrix) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
      return null;
    }
    int M = matrix.length-1, N = matrix[0].length-1;
    int[] ans = new int[(M+1)*(N+1)];

    int index = 0;
    for (int i = 0; i <= M+N; ++i) {
      if (i % 2 == 0) {
        for (int x = i; x >= 0; x--) {
          if (x <= M && i-x <= N) {
            ans[index++] = matrix[x][i-x];
          }
        }
      }
      else {
        for (int x = 0; x <= i; ++x) {
          if (x <= M && i-x <= N) {
            ans[index++] = matrix[x][i-x];
          }
        }
      }
    }
    return ans;
  }
}
