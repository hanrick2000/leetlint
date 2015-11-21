package JiuChap5_DynamicProgram;

import java.util.Arrays;
import java.util.Stack;

/**
 * https://leetcode.com/problems/maximal-rectangle/ Created at 10:41 AM on 11/11/15.
 */
public class MaximumRectangle {
  public static void main(String[] args) {
    int[][] M = new int[][] {
        {1, 0, 1, 0, 0},
        {1, 0, 1, 1, 1},
        {1, 1, 1, 1, 1},
        {1, 0, 0, 1, 0}
    };
    MaximumRectangle ms = new MaximumRectangle();
    int ans = ms.maxReclichen(M);
    System.out.println(ans);
  }

  /**
   * Time: O(n^2), Space: O(n^2).
   * Idea from http://www.cnblogs.com/lichen782/p/leetcode_maximal_rectangle.html
   */
  public int maxReclichen(int[][] matrix) {
    int R = matrix.length;
    int C = matrix[0].length;
    int[][] heights = new int[R][C];

    for (int i = 0; i < R; ++i) {
      for (int j = 0; j < C; ++j) {
        if (matrix[i][j] == 1) {
          heights[i][j] = i == 0 ? 1 : heights[i - 1][j] + 1;
        } else {
          heights[i][j] = 0;
        }
      }
    }

    int ans = 0;
    for (int i = 0; i < R; ++i) {
      ans = Math.max(ans, maxHisArea(heights[i]));
    }
    return ans;
  }

  /**
   * Time: O(n*n), but Sapce is O(n) now.
   * Tia's optimal comment in http://www.cnblogs.com/lichen782/p/leetcode_maximal_rectangle.html
   */
  public int maxRecTia(int[][] matrix) {
    int R = matrix.length;
    int C = matrix[0].length;
    int[] heights = new int[C];

    int ans = 0;
    for (int i = 0; i < R; ++i) {
      for (int j = 0; j < C; ++j) {
        if (matrix[i][j] == 1) {
          heights[j] = i == 0 ? 1 : heights[j] + 1;  // 2D -> 1D optimize, similar to rolling array
        } else {
          heights[j] = 0;
        }
      }
      ans = Math.max(ans, maxHisArea(heights));
    }
    return ans;
  }

  /**
   * Normally, if want O(n), then have to use stack/queue
   * O(n)
   * 9 chap's implementation, from jenny's blog
   */
  private int maxHisArea(int[] heights) {
    int ans = 0;
    Stack<Integer> incStk = new Stack<>();
    for (int i = 0; i <= heights.length; ++i) {
      int curHeight;
      curHeight = i == heights.length ? 0 : heights[i];
      // BUG: while (!incStk.isEmpty() && curHeight <= incStk.peek())
      while (!incStk.isEmpty() && curHeight <= heights[incStk.peek()]) {
        int j = incStk.pop();
        //ans = Math.max(ans, heights[j] * (i-j));  flaw for the last element left in
        // the stack, the width is from 0 to i!
        int w;
        if (!incStk.isEmpty()) {
          w = i - incStk.peek() - 1;
          //w = i - j;  WRONG: because the index in stack is not continuous, only heights[i] are continuous increasing!
        } else {
          w = i;
        }
        ans = Math.max(ans, w * heights[j]);
      }
      // remember to put after finished processing. Compare with Sliding window problems: add one,
      // remove one. Here I remove valid, and then add.
      incStk.push(i);
    }
    return ans;
  }

  /**
   * Leetcode Discussion's best solution: Time: O(n*n), Sapce is O(3n)
   * https://leetcode.com/discuss/20240/share-my-dp-solution
   * @param matrix
   * @return
   */
  public int maximalRectangle(int[][] matrix) {
    if (matrix.length == 0) return 0;
    int m = matrix.length;
    int n = matrix[0].length;
    int left[] = new int[n], right[] = new int[n], height[] = new int[n];
    Arrays.fill(right, n); //left and height will be default having values 0
    int maxA = 0;
    for (int i = 0; i < m; i++) {
      int curleft = 0, curright = n - 1;
      for (int j = 0; j < n; j++) { // compute height (can do this from either side)
        if (matrix[i][j] == 1) {
          height[j]++;
        } else {
          height[j] = 0;
        }
      }
      for (int j = 0; j < n; j++) { // compute left (from left to right)
        if (matrix[i][j] == 1) {
          left[j] = Math.max(left[j], curleft);
        } else {
          left[j] = 0;
          curleft = j + 1;
        }
      } // compute right (from right to left)
      for (int j = n - 1; j >= 0; j--) {
        if (matrix[i][j] == 1) {
          right[j] = Math.min(right[j], curright);
        } else {
          right[j] = n - 1;
        }
        curright = j - 1;
      }
    } // compute the area of rectangle (can do this from either side)
    for (int j = 0; j < n; j++) {
      maxA = Math.max(maxA, (right[j] - left[j] + 1) * height[j]);
    }
    return maxA;
  }


/* ------------------------------------------------------------------------------------------------
 *                        Scott's code separator
 * ------------------------------------------------------------------------------------------------
 */

  /**
   * Can I use Max Square's DP to solve it? seems a bit more code. Wrong function
   */

  public int maxRecWRONG(int[][] matrix) {
    // write your code here
    int R = matrix.length;
    int C = matrix[0].length;
    Rec[][] P = new Rec[R][C];
    for (int r = 0; r < R; ++r) {
      for (int c = 0; c < C; ++c) {
        P[r][c] = new Rec(0, 0);
      }
    }

    int ans = 0;

    int yiR = 0;
    for (int r = 0; r < R; ++r) {
      if (matrix[r][0] == 1) {
        yiR++;
        P[r][0].w = 1;
      } else {
        yiR = 0;
        P[r][0].w = 0;
      }
      P[r][0].h = yiR;
    }

    int yiC = 0;
    for (int c = 1; c < C; ++c) {
      if (matrix[0][c] == 1) {
        yiC++;
        P[0][c].h = 1;
      } else {
        yiC = 0;
        P[0][c].h = 0;
      }
      P[0][c].w = yiC;
    }

    for (int r = 1; r < R; ++r) {
      for (int c = 1; c < C; ++c) {
        if (matrix[r][c] == 1) {
          if (P[r - 1][c].areaH1() > P[r][c - 1].areaw1()) {  // wrong function
            P[r][c].w = P[r - 1][c].w;
            P[r][c].h = P[r - 1][c].h + 1;
          } else {
            P[r][c].w = P[r][c - 1].w + 1;
            P[r][c].h = P[r][c - 1].h;
          }
        } else {
          P[r][c] = new Rec(0, 0);
        }
        ans = Math.max(P[r][c].area(), ans);
      }
    }
    return ans;
  }

  private class Rec implements Comparable<Rec> {
    int w, h;

    Rec(int w, int h) {
      this.w = w;
      this.h = h;
    }

    // compare the size of this rectangle
    public int compareTo(Rec o) {
      return this.w * this.h - o.w * o.h;
    }

    public int area() {
      return this.w * this.h;
    }

    public int areaw1() {
      return (this.w + 1) * this.h;
    }

    public int areaH1() {
      return (this.h + 1) * this.w;
    }
  }
}
