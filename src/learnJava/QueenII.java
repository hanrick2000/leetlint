package learnJava;

public class QueenII {
  /**
   * Get all distinct N-Queen solutions
   * 
   * @param n: The number of queens
   * @return: All distinct solutions
   *          For example, A string '...Q' shows a queen on forth position
   */
  public static int solveNQueens(int n) {
    // write your code here
    int[] sum = new int[1];
    if (n == 0) {
      return 0;
    }
    helper(sum, new int[n], 0, n);
    return sum[0];
  }

  private static void helper(int[] sum, int[] list, int level, int n) {
    // add list into result
    if (level == n) {
      sum[0] += 1;
      return;
    }

    // DFS and prune
    
    /*
     *  for (int i = 0; i < level; ++i) I was wrong in Lintcode to mess up with level/n, always have a
     *  clear mind in long logic chain, the way to solve is to write done the idea
     */
    for (int i = 0; i < n; ++i) {
      if (isValid(list, level, i)) {
        list[level] = i;
        helper(sum, list, level + 1, n);
        list[level] = -1;
      }
    }
  }

  private static boolean isValid(int[] list, int row, int cur) {
    if (row == 0) {
      return true;
    }

    // row Not needed
    for (int i = 0; i < row; i++) {
      // col
      if (cur == list[i]) {
        return false;
      }
      // diagnal
      if (Math.abs(cur - list[i]) == Math.abs(row - i)) {
        return false;
      }
    }

    return true;
  }

  public static void testNQueenI() {
    int ans = solveNQueens(1);;
    System.out.println(ans);
  }

  public static void main(String[] args) {
    QueenII.testNQueenI();
  }
}
