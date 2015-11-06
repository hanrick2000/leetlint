package nineChap7_Graph;

/**
 * http://www.meetqun.com/thread-12045-1-1.html
 * Created this class in nineChap7_Graph at 12:02 PM, 11/5/2015.
 */
public class MinCoinChange {
  /**
   * Find the least coins of 1/3/7cent to make one dollar
   */
  public static void main(String[] args) {
    MinCoinChange cd = new MinCoinChange();
    System.out.println(cd.coins());
  }

  public int coins() {
    //return dfs(10, new int[]{0,0,0});
    return dp(3, 10);
  }

  int[] dx = new int[]{3,1,7};

  /**
   * http://www.fgdsb.com/2015/01/03/coin-change-problem/#more
   * @param m
   * @param V
   * @return
   */
  private int dp(int m, int V) {
    int[] opt = new int[V+1];
    int minVal;
    for (int i = 1; i <= V; ++i) {
      minVal = i;  // the max val is using all 1 cent.
      for (int j = 0; j < m; ++j) {
        if (dx[j] <= i) {
          /**
           * http://www.lintcode.com/en/problem/maximum-subarray-iii/
           * Painter's draw, multi-backpack
           */
          minVal = Math.min(opt[i - dx[j]]+1, minVal);
        }
        else {
          break;
        }
      }
      opt[i] = minVal;
    }
    return opt[V];
  }

  /**
   * This is O(2^n) solution, so not possible to solve 2^100!
   * @param value
   * @param coins
   * @return
   */
  private int dfs(int value, int[] coins) {
    if (value == 0) {
      System.out.println(coins[0] + " " + coins[1] + " " + coins[2]);
      return coins[0]+coins[1]+coins[2];
    }
    int rs = Integer.MAX_VALUE;
    for (int i = 0; i < 3; ++i) {
      if (isValid(value, i)) {
        coins[i]++;
        int tmp = dfs(value - dx[i], coins);
        rs = (tmp > 0 && tmp < rs) ? tmp : rs;
        coins[i]--;
      }
    }
    return rs;
  }

  private boolean isValid(int value, int i) {
    if (value >= dx[i]) {
      return true;
    }
    else return false;
  }
}
