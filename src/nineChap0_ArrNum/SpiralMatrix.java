package nineChap0_ArrNum;

import java.util.ArrayList;
import java.util.List;

/**
 * http://www.lintcode.com/en/problem/spiral-matrix/ Created at 10:24 AM on 11/28/15.
 */
public class SpiralMatrix {
  public static void main(String[] args) {
    int[][] Matrix = new int[][] {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}
    };
    List<Integer> ans = new SpiralMatrix().spiralOrder(Matrix);
    System.out.println(ans);
  }

  /**
   * @param matrix a matrix of m x n elements
   * @return an integer list
   */
  public List<Integer> spiralOrder(int[][] matrix) {
    // Write your code here
    List<Integer> ans = new ArrayList<>();
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
      return ans;
    }

    int row = matrix.length;
    int col = matrix[0].length;

    int left = 0;
    int right = col - 1;
    int top = 0;
    int bottom = row - 1;

    while (left <= right && top <= bottom) {
      for (int i = left; i <= right; ++i) {
        ans.add(matrix[top][i]);
      }
      for (int i = top + 1; i <= bottom - 1; ++i) {
        ans.add(matrix[i][right]);
      }
      if (top != bottom) {
        for (int i = right; i >= left; i--) {
          ans.add(matrix[bottom][i]);
        }
      }

      if (left != right) {
        for (int i = bottom - 1; i >= top + 1; i--) {
          ans.add(matrix[i][left]);
        }
      }

      left++;
      right--;
      top++;
      bottom--;
    }

    return ans;
  }
}
