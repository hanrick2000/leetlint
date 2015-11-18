package Easy;

/**
 * http://www.lintcode.com/en/problem/set-matrix-zeroes
 * Created at 12:59 PM on 11/17/15.
 */
public class SetMatrixZeros {
  public static void main(String[] args) {
    SetMatrixZeros smz = new SetMatrixZeros();
    int[][] M = new int[][] {
        {1,2,0},
        {4,3,0},
        {7,8,9}
    };
    smz.setZerosO1(M);
    for (int[] m : M) {
      for (int i : m) {
        System.out.print(i + " ");
      }
      System.out.println();
    }
  }

  /**
   * Well explanation: why and when to use 1st row/col
   * http://jane4532.blogspot.com/2013/09/set-matrix-zeroleetcode.html
   * @param matrix
   */
  public void setZerosO1(int[][] matrix) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
      return;
    }
    int M = matrix.length, N = matrix[0].length;
    boolean zRow = false, zCol = false;
    for (int i = 0; i < M; ++i) {
      for (int j = 0; j < N; ++j) {
        if (matrix[i][j] == 0) {
          if (i == 0)  zRow = true;
          if (j == 0)  zCol = true;

          matrix[i][0] = 0;
          matrix[0][j] = 0;
        }
      }
    }

    for (int i = 1; i < M; ++i) {
      if (matrix[i][0] == 0) {
        for (int j = 1; j < N; ++j) {
          matrix[i][j] = 0;
        }
      }
    }

    for (int j = 1; j < N; ++j) {
      if (matrix[0][j] == 0) {
        for (int i = 1; i < M; ++i) {
          matrix[i][j] = 0;
        }
      }
    }

    if (zRow) {
      for (int j = 0; j < N; ++j) {
        matrix[0][j] = 0;
      }
    }

    if (zCol) {
      for (int i = 0; i < M; ++i) {
        matrix[i][0] = 0;
      }
    }
  }

  /**
   * O(M+N) solution
   * @param matrix
   */
  public void setZerosOM_N(int[][] matrix) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
      return;
    }
    int M = matrix.length, N = matrix[0].length;
    int[] rows = new int[M];
    int[] cols = new int[N];
    for (int i = 0; i < M; ++i) {
      for (int j = 0; j < N; ++j) {
        if (matrix[i][j] == 0) {
          rows[i] = 1;
          cols[j] = 1;
        }
      }
    }

    for (int i = 0; i < M; ++i) {
      for (int j = 0; j < N; ++j) {
        if (rows[i] == 1)  matrix[i][j] = 0;
        if (cols[j] == 1)  matrix[i][j] = 0;
      }
    }
  }

  /**
   * @param matrix: A list of lists of integers
   * @return: Void
   */
  public void setZeroesOMN(int[][] matrix) {
    // write your code here
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
      return;
    }
    int M = matrix.length, N = matrix[0].length;
    int[] zeros = new int[M*N];
    for (int i = 0; i < M; ++i) {
      for (int j = 0; j < N; ++j) {
        if (matrix[i][j] == 0) {
          zeros[i*N + j] = 1;
        }
      }
    }

    for (int m = 0; m < M*N; ++m) {
      if (zeros[m] != 1)  continue;
      int r = (m) / N;
      int c = (m) % N;
      for (int i = 0; i < M; ++i) {
        for (int j = 0; j < N; ++j) {
          matrix[r][j] = 0;
          matrix[i][c] = 0;
        }
      }
    }
  }
}
