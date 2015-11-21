package JiuChap5_DynamicProgram;

/**
 * How to define P[i][j]??? This is a good question.
 * http://www.lintcode.com/en/problem/maximal-square/ Created by Administrator on 11/10/15.
 */
public class MaximalSquare {
  public static void main(String[] args) {
    int[][] M = new int[][] {
        //{1, 0, 1, 0, 0},
        //{1, 0, 1, 1, 1},
        //{1, 1, 1, 1, 1},
        //{1, 0, 0, 1, 0}
        {1},
        {1},
        {1},
        {1}
    };
    MaximalSquare ms = new MaximalSquare();
    int ans = ms.maxSquareDP1(M);
    System.out.println(ans);
  }

  /**
   * Only use rolling array after O(nm)! otherwise, it's easy to get lost.
   * @param matrix: a matrix of 0 and 1
   * @return: an integer
   */
  public int maxSquareDP1(int[][] matrix) {
    // write your code here
    int R = matrix.length;
    int C = matrix[0].length;
    int[][] P = new int[R][C];
    int ans = 0;

    for (int r = 0; r < R; ++r) {
      P[r][0] = matrix[r][0];
      ans = Math.max(P[r][0], ans);  ////////////
      for (int c = 1; c < C; ++c) {  // int c = 0
        //P[0][c]
        if (r == 0) {
          P[0][c] = matrix[0][c];
        } else {
          if (matrix[r][c] == 1) {
            P[r][c] = Math.min(Math.min(P[r - 1][c], P[r][c - 1]), P[r - 1][c - 1]) + 1;
            //ans = Math.max(ans, P[r][c]);
          } else {
            P[r][c] = 0;
          }
        }
        ans = Math.max(ans, P[r][c]);
      }
    }
    return ans * ans;
  }

  /**
   * Rolling array, Don't mess up mod with input Matrix. since mod is used in states compression.
   * Also, rethink about initialization, only init those can not derived from recurrence function.
   * @param matrix
   * @return
   */
  public int maxSquareDP2(int[][] matrix) {
    // write your code here
    int R = matrix.length;
    int C = matrix[0].length;
    int[][] P = new int[R][2];
    int ans = 0;

    for (int r = 0; r < R; ++r) {
      P[r][0] = matrix[r][0];
      ans = Math.max(P[r][0], ans);
      for (int c = 1; c < C; ++c) {  // int c = 0
        //P[0][c]
        if (r == 0) {
          P[0][c%2] = matrix[0][c];  // matrix[0][c%2]. Don't mess up mod with input... only mod in states!
        } else {
          if (matrix[r][c] == 1) {
            P[r][c%2] = Math.min(Math.min(P[r - 1][c%2], P[r][(c - 1)%2]), P[r - 1][(c - 1)%2]) + 1;
            //ans = Math.max(ans, P[r][c]);
          } else {
            P[r][c%2] = 0;
          }
        }
        ans = Math.max(ans, P[r][c%2]);
      }
    }
    return ans * ans;
  }

  public int maxSquareLint(int[][] matrix) {
    // write your code here
    int ans = 0;
    if (matrix == null || matrix.length == 0) {
      return ans;
    }

    int R=matrix.length, C = matrix[0].length;
    int[][] P = new int[R][C];

    for (int i = 0; i < R; ++i) {
      P[i][0] = matrix[i][0];
      ans = Math.max(ans, P[i][0]);  // don't forget this for corner case. {1,1,1,1,1,1}.
      for (int j = 1; j < C; ++j) {
        if (i == 0) {
          P[0][j] = matrix[0][j];
        }
        else {
          if (matrix[i][j] == 0) {
            P[i][j] = 0;
          }
          else {
            P[i][j] = Math.min(Math.min(P[i-1][j], P[i][j-1]), P[i-1][j-1]) + 1;
          }
        }
        ans = Math.max(ans, P[i][j]);
      }
    }

    return ans * ans;
  }
}
