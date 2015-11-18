package JiuChap6_FollowUpA;

import java.util.ArrayList;
import java.util.List;

/**
 * http://www.lintcode.com/en/problem/find-peak-element-ii/ It's also a regular interview question,
 * called find hill. Created at 12:12 PM on 11/14/15.
 */
public class FindPeakII {
  public static void main(String[] args) {
    int[][] nums = {
        {1, 2, 3, 6, 5},
        {16, 41, 23, 22, 6},
        {15, 17, 24, 21, 7},
        {14, 18, 19, 20, 10},
        {13, 14, 11, 10, 9}
    };
    List<Integer> peaks = new FindPeakII().findPeakII(nums);
    for (int p : peaks) {
      System.out.println(p);
    }
  }

  /**
   * O(nlgn) solution, and there is a O(n) solution
   * @param A: An integer matrix
   * @return: The index of the peek
   */
  public List<Integer> findPeakII(int[][] A) {
    // write your code here
    int R = A.length, C = A[0].length;
    int l = 1, r = R - 2;
    List<Integer> result = new ArrayList<>();
    while (l + 1 < r) {
      int mid = (l + r) / 2;
      int col = findMax(mid, A);
      if (A[mid][col] < A[mid - 1][col]) {
        r = mid - 1;
      } else if (A[mid][col] < A[mid + 1][col]) {
        l = mid + 1;
      } else {
        result.add(mid);
        result.add(col);
        break;
      }
    }
    return result;
  }

  private static int findMax(int i, int[][] A) {
    int n = A.length;
    int col = 0;
    for (int j = 0; j < n; ++j) {
      if (A[i][j] > A[i][col]) {
        col = j;
      }
    }
    return col;
  }
}
