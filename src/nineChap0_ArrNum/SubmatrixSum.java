package nineChap0_ArrNum;

import java.util.HashMap;
import java.util.Map;

/**
 * http://www.lintcode.com/en/problem/submatrix-sum/
 * Created at 7:17 PM on 11/18/15.
 */
public class SubmatrixSum {
  public static void main(String[] args) {
    SubmatrixSum ss = new SubmatrixSum();
    int[][] M = new int[][] {
        {1 ,5 ,7},
        {3 ,7 ,-8},
        {4 ,-15 ,9}
        //{1,1,1,1,1,1,1,1,1,1,1,-10,1,1,1,1,1,1,1,1,1,1,1}
    };
    int[][] ans = ss.submatrixSum(M);
    for (int[] list : ans) {
      for (int i : list) {
        System.out.print(i + "\t");
      }
      System.out.println();
    }
  }
  /**
   * Followed lecture's idea
   * @param matrix an integer matrix
   * @return the coordinate of the left-up and right-down number
   */
  public int[][] submatrixSum(int[][] matrix) {
    // Write your code here
    int R = matrix.length, C = matrix[0].length;
    int[][] preSum = new int[R][C];

    int[][] res = new int[2][2];

    for (int r = 0; r < R; ++r) {
      for (int c = 0; c < C; ++c) {
        if (r == 0)  preSum[r][c] = matrix[r][c];
        else  preSum[r][c] = preSum[r-1][c] + matrix[r][c];
      }
    }

    Map<Integer, Coor> map = new HashMap<>();

    for (int lx = 0; lx < R; ++lx) {
      for (int rx = lx; rx < R; ++rx) {
        int sum = 0;
        map.put(0, new Coor(lx, -1));
        for (int c = 0; c < C; ++c) {
          sum += lx == 0 ? preSum[rx][c] : preSum[rx][c] - preSum[lx-1][c];
          if (map.containsKey(sum)) {
            res[0][0] = map.get(sum).x;
            res[0][1] = map.get(sum).y+1;
            res[1][0] = rx;
            res[1][1] = c;
            return res;
          }
          map.put(sum, new Coor(lx, c));
        }
        map.clear();
      }
    }

    //for (int lx = 0; lx < R; ++lx) {
    //  for (int rx = lx+1; rx < R; ++rx) {
    //    for (int c = 0; c < C; ++c) {
    //      map.put(0, new Coor(-1, c));
    //      sum = lx == 0 ? preSum[rx][c] : preSum[rx][c] - preSum[lx-1][c];
    //      if (map.containsKey(-sum)) {
    //        res[0][0] = map.get(-sum).x;
    //        res[0][1] = map.get(-sum).y;
    //        res[1][0] = rx;
    //        res[1][1] = c;
    //        return res;
    //      }
    //      map.put(sum, new Coor(lx, c));
    //    }
    //    map.clear();
    //  }
    //}
    return res;
  }

  class Coor {
    int x;
    int y;
    Coor(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
}
