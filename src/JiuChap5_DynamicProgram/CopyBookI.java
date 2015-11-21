package JiuChap5_DynamicProgram;

/**
 * http://www.lintcode.com/en/problem/copy-books/
 * Created at 5:43 PM on 11/13/15.
 */
public class CopyBookI {
  public static void main(String[] args) {
    CopyBookI cb1 = new CopyBookI();
    int[] books = new int[]{3,2,4};
    int ans = cb1.copyBooks(books, 2);
    System.out.println(ans);
  }
  /**
   * 9 chap's O(n^2 * k).
   * @param pages: an array of integers
   * @param k: an integer
   * @return: an integer
   */
  public int copyBooks(int[] pages, int k) {
    // write your code here
    int n = pages.length;
    int[][] w = new int[n+1][n+1];
    for (int i = 1; i <= n; ++i) {
      for (int j = i+1; j <= n; ++j) {
        for (int m = i; m <= j; ++m) {
          w[i][j] += pages[m-1];
        }
      }
    }

    int[][] P = new int[n+1][k+1];

    int ans = -1;
    if (n <= k) {
      for (int i = 0; i < n; ++i) {
        ans = Math.max(ans, pages[i]);
      }
      return ans;
    }

    for (int i = 0; i <= n; ++i) {
      P[i][1] = w[1][i];
    }

    for (int nk = 2; nk <= k; ++nk) {
      for (int i = nk; i <= n; ++i) {
        P[i][nk] = Integer.MAX_VALUE;
        for (int j = 0; j < i; j++) {
          if (P[i][nk] == Integer.MAX_VALUE || P[i][nk] > Math.max(P[j][nk-1], w[j+1][i])) {
            P[i][nk] = Math.max(P[j][nk-1], w[j+1][i]);  // exactly followed 9chap's lecture!
          }
        }
      }
    }

    return P[n][k];
  }
}
