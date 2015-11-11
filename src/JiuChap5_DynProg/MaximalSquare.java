package JiuChap5_DynProg;

/**
 * http://www.lintcode.com/en/problem/maximal-square/
 * Created by Administrator on 11/10/15.
 */
public class MaximalSquare {
  public static void main(String[] args) {
    int[][] M = new int[][]{
        {1,0,1,0,0},
        {1,0,1,1,1},
        {1,1,1,1,1},
        {1,0,0,1,0}
    };
    MaximalSquare ms = new MaximalSquare();
    int ans = ms.maxSquare(M);
    System.out.println(ans);
  }
  /**
   * @param matrix: a matrix of 0 and 1
   * @return: an integer
   */
  public int maxSquare(int[][] matrix) {
    // write your code here
    return 0;
  }
}
