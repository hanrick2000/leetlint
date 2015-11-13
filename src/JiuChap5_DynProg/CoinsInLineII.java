package JiuChap5_DynProg;

import java.util.Arrays;

/**
 * http://www.lintcode.com/en/problem/coins-in-a-line-ii/
 * Created at 12:07 PM on 11/12/15.
 */
public class CoinsInLineII {
  public static void main(String[] args) {
    int[] coins = new int[]{1,2,4};
    CoinsInLineII cil2 = new CoinsInLineII();
    System.out.println(cil2.firstWillWin(coins));
  }

  public boolean firstWillWin(int[] values) {
    int[] P = new int[values.length+1];
    Arrays.fill(P, -1);
    P[0] = 0;
    int sum = 0;
    for (int v : values) {
      sum += v;
    }
    int Vleng = dfs(values, values.length, P);
    int Vleng_1 = dfs(values, values.length-1, P);
    boolean bleng = sum < Vleng*2;
    return bleng;
    //boolean bleng_1 = sum < Vleng_1*2;
    //return bleng || bleng_1;
  }

  /**
   * Find the largest value xianshou can get if with i units.
   * @param values
   * @param i
   * @param P
   * @return
   */
  public int dfs(int[] values, int i, int[] P) {
    if (P[i] != -1) {
      return P[i];
    }

    if (i == 1) {
      P[i] = values[0];
    }
    else if (i == 2) {
      P[i] = values[0] + values[1];
    }
    else if (i == 3) {
      P[i] = values[0] + values[1];
    }
    else {
      P[i] = Math.max(
        Math.min(P[i-2], P[i-3]) + values[values.length - i],
          Math.min(P[i-3], P[i-4]) + values[values.length-i] + values[values.length-i+1]
      );
    }

    return P[i];
  }
}
