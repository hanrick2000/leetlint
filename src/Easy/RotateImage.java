package Easy;

/*-
 * Same as Zigzag matrix: draw a draft, find the regular pattern!
 * http://www.lintcode.com/en/problem/rotate-image/
 * Created at 11:04 AM on 11/17/15.
 */
public class RotateImage {
  public static void main(String[] args) {
    int[][] M = new int[][] {
        {1, 2, 3, 4},
        {5,6,7,8},
        {9,10,11,12},
        {13,14,15,16}
    };
    RotateImage ri = new RotateImage();
    ri.rotate1(M);

    for (int[] m : M) {
      for (int i : m) {
        System.out.print(i + " ");
      }
      System.out.println();
    }
  }

  /**
   * Rewrite
   * @param matrix: A list of lists of integers
   * @return: Void
   */
  public void rotate1(int[][] matrix) {
    // write your code here
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
      return;
    }

    int N = matrix.length;
    for (int i = 0; i < N/2; ++i) {
      for (int j = 0; j < (N+1)/2; ++j) {
        int tmp = matrix[i][j];
        matrix[i][j] = matrix[N-j-1][i];
        matrix[N-j-1][i] = matrix[N-i-1][N-j-1];
        matrix[N-i-1][N-j-1] = matrix[j][N-i-1];
        matrix[j][N-i-1] = tmp;
      }
    }
  }

  /**
   * 9 chap's solution: 2 things to learn:
   * 1. 4 equations using the formula: m[i][j] = m[N-j-1][i] -> learn from draft
   * 2. since each iteration rotate 4 units, so 4x4 matrix needs 4 loops, so the left-up corner
   * @param matrix
   */
  public void rotate(int[][] matrix) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
      return;
    }

    int length = matrix.length;

    for (int i = 0; i < length / 2; i++) {
      for (int j = 0; j < (length + 1) / 2; j++) {
        System.out.println(i+", " +j);
        int tmp = matrix[i][j];
        matrix[i][j] = matrix[length - j - 1][i];
        matrix[length - j - 1][i] = matrix[length - i - 1][length - j - 1];
        matrix[length - i - 1][length - j - 1] = matrix[j][length - i - 1];
        matrix[j][length - i - 1] = tmp;
      }
    }
  }
}
