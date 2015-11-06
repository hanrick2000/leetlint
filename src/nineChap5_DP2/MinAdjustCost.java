package nineChap5_DP2;

import java.util.*;

public class MinAdjustCost {
  public static void main(String[] args) {
    // int[] A = {1,4,2,3};
    ArrayList<Integer> A = new ArrayList<>();
    A.add(1);
    A.add(4);
    A.add(2);
    A.add(3);
    int target = 1;
    int cost = -1;

    cost = MinAdjustmentCostWrong(A, target);
    System.out.println("1st trial in DP: " + cost);

    cost = MinAdjustmentCost2(A, target);
    System.out.println("I use duanyu's DP: " + cost);
    
    cost = MinAdjustmentCost3(A, target);
    System.out.println("Used continue to prune: " + cost);

  }

  /**
   * 1st trial: confused in looping v/vp!
   * 
   * @param A
   * @param target
   * @return
   */
  public static int MinAdjustmentCostWrong(ArrayList<Integer> A, int target) {
    if (A == null || A.size() == 0) {
      return -1;
    }
    int aLen = A.size();
    int[][] F = new int[aLen + 1][101]; // the min cost to adjust first i items with the ith item to
                                        // new val j.

    // for (int i = 0; i < aLen+1; ++i) {
    // F[i][0]
    // }

    F[1][A.get(0)] = 0;
    for (int x = 1; x < 101; ++x) {
      F[1][x] = Math.abs(x - A.get(1));
    }

    for (int i = 2; i < aLen + 1; ++i) {
      for (int j = 1; j < 101; ++j) { // so wrong!
        for (int v = 1; v < 101; ++v) { // so wrong!
          F[i][j] = Integer.MAX_VALUE; // so DAMN wrong
          if (Math.abs(v - j) <= target) {
            int dif = Math.abs(A.get(i - 1) - v);
            F[i][j] = Math.min(F[i - 1][j] + dif, F[i][j]);
          }
        }
      }
    }

    int ans = Integer.MAX_VALUE;
    for (int i = 1; i < 101; ++i) {
      ans = Math.min(F[aLen][i], ans);
    }

    return ans;
  }

  /**
   * 
   * @param A
   * @param target
   * @return
   */
  public static int MinAdjustmentCost2(ArrayList<Integer> A, int target) {
    if (A == null || A.size() == 0) {
      return -1;
    }
    int aLen = A.size();
    int[][] F = new int[aLen + 1][101];

    for (int x = 1; x < 101; ++x) {
      F[1][x] = Math.abs(x - A.get(0));
    }

    for (int i = 2; i < aLen + 1; ++i) {
      for (int j = 1; j < 101; ++j) { // A[i] -> j
        F[i][j] = Integer.MAX_VALUE;
        for (int v = 1; v < 101; ++v) { // A[i-1] = v
          if (Math.abs(v - j) <= target) {
            int dif = Math.abs(A.get(i - 1) - j);
            F[i][j] = Math.min(F[i - 1][v] + dif, F[i][j]);
          }
        }
      }
    }

    int ans = Integer.MAX_VALUE;
    for (int i = 1; i < 101; ++i) {
      ans = Math.min(F[aLen][i], ans);
    }

    return ans;
  }

  /**
   * 
   * @param A
   * @param target
   * @return
   */
  public static int MinAdjustmentCost3(ArrayList<Integer> A, int target) {
    if (A == null || A.size() == 0) {
      return -1;
    }
    int aLen = A.size();
    int[][] F = new int[aLen + 1][101];

    for (int x = 1; x < 101; ++x) {
      F[1][x] = Math.abs(x - A.get(0));
    }

    for (int i = 2; i < aLen + 1; ++i) {
      for (int j = 1; j < 101; ++j) { // A[i] -> j
        F[i][j] = Integer.MAX_VALUE;
        for (int v = 1; v < 101; ++v) { // A[i-1] = v
          if (Math.abs(v - j) > target) {
            continue; // am I looping v for F[i-1] or F[i]???
          }
          int dif = Math.abs(A.get(i - 1) - j);
          F[i][j] = Math.min(F[i - 1][v] + dif, F[i][j]);
        }
      }
    }

    int ans = Integer.MAX_VALUE;
    for (int i = 1; i < 101; ++i) {
      ans = Math.min(F[aLen][i], ans);
    }

    return ans;
  }
}
