package JiuChap5_DynProg;

/**
 * http://www.lintcode.com/en/problem/best-time-to-buy-and-sell-stock-iv/
 * Created at 11:03 PM on 11/12/15.
 */
public class BestTimeStockIV {
  public static void main(String[] args) {
    int[] prices = new int[]{1,2,4,2,5,7,2,4,9,0}; //{4,4,6,1,1,4,2,5};  //{1,2}; //
    BestTimeStockIV btbss4 = new BestTimeStockIV();
    int ans = btbss4.maxProfitBetter(4, prices);
    System.out.println(ans);

    //int gai = btbss4.gain(prices, 1, 2);
    //System.out.println(gai);
  }

  /**
   * Jiu Chap solution: Function and State is the  key. Also, this is n->1 but using iteration, not
   *    recursion. THINK.
   * @param k
   * @param prices
   * @return
   */
  public int maxProfit(int k, int[] prices) {
    if (k == 0) {
      return 0;
    }
    if (k > prices.length/2) {  // trap, since meaningful transaction is buy/sell, so maximum days/2.
      int profit = 0;
      for (int i = 1; i < prices.length; ++i) {
        if (prices[i] > prices[i-1]) {
          profit += prices[i]-prices[i-1];
        }
      }
      return profit;
    }
    int leng = prices.length;
    int[][] mustSell = new int[leng+1][k+1];
    int[][] globalBest = new int[leng+1][k+1];
    iteration(prices, k, mustSell, globalBest);
    return globalBest[leng-1][k];
  }

  public void iteration(int[] prices, int k, int[][] local, int[][] global) {
    local[0][0] = global[0][0] = 0;
    for (int i = 1; i <= k; ++i) {
      local[0][i] = global[0][i] = 0;
    }

    int n = prices.length;
    for (int i = 1; i < n; ++i) {
      int gainorlose = prices[i] - prices[i-1];
      global[i][0] = 0;
      for (int j  = 1; j <= k; ++j) {
        local[i][j] = Math.max(global[i-1][j-1] + gainorlose, local[i-1][j] + gainorlose);
        global[i][j] = Math.max(global[i-1][j], local[i][j]);
      }
    }
  }

  /**
   * http://liangjiabin.com/blog/2015/04/leetcode-best-time-to-buy-and-sell-stock.html
   * Inspired by liangjiabin, I think his explanation of recurrence function is the best!
   * @param k
   * @param prices
   * @return
   */
  public int maxProfitBetter(int k, int[] prices) {
    if (k == 0) {
      return 0;
    }
    int leng = prices.length;
    if (k >= leng/2) {
      int profit = 0;
      for (int i = 1; i < leng; ++i) {
        if (prices[i] > prices[i-1]) {
          profit += prices[i] - prices[i-1];
        }
      }
      return profit;
    }

    int[][] local = new int[2][k+1];
    int[][] global = new int[2][k+1];
    for (int i = 1; i < leng; ++i) {
      int gainorlose = prices[i] - prices[i-1];
      for (int j = 1; j <= k; ++j) {
        local[i%2][j] = Math.max(global[(i-1)%2][(j-1)] + gainorlose, local[(i-1)%2][j] + gainorlose);
        global[i%2][j] = Math.max(global[(i-1)%2][j], local[i%2][j]);
      }
    }

    return global[(leng-1)%2][k];
  }

/* ------------------------------------------------------------------------------------------------
 *        Below is the Jiu Chap's simple algs: O(n^2 * k), failed in Time Limit Exceeded
 * ------------------------------------------------------------------------------------------------
 */

  /**
   * Follow exactly the function: P[i][k] means the max profit gain by k transactions in first i days.
   *   NOTICE: i, k are 1-based, but prices[i] is 0-based, so careful on the index
   * @param k: An integer
   * @param prices: Given an integer array
   * @return: Maximum profit
   */
  public int maxProfitOn2k(int k, int[] prices) {
    if (k == 0) {
      return 0;
    }
    if (k >= prices.length/2) {
      int profit = 0;
      for (int i = 1; i < prices.length; ++i) {
        if (prices[i] > prices[i-1]) {
          profit += prices[i] - prices[i-1];
        }
      }
      return profit;
    }
    // write your code here
    int[][] P = new int[prices.length+1][k+1];
    //for (int i = 0; i < k; ++i) {
    //  P[0][i] = 0;
    //  P[i][0] = -1;  // why
    //}

    //int ans = dfs(prices, P, prices.length-1, k);  // spent 1 hour on debugging the index!!!
    int ans = dfs(prices, P, prices.length, k);  // fix index
    return ans;
  }

  /**
   * @param prices
   * @param P
   * @param i
   * @param k
   * @return
   */
  private int dfs(int[] prices, int[][] P, int i, int k) {
    if (P[i][k] != 0) {
      return P[i][k];
    }
    if (i == 0) {
      P[0][k] = 0;
    }
    else if (k == 0) {
      P[i][0] = -1000;
    }
    else {
      int rs = 0;
      for (int x = 0; x < i; ++x) {
        int tmp = dfs(prices, P, x, k-1) + gain(prices, x+1, i);
        rs = Math.max(tmp, rs);
      }
      P[i][k] = rs;
    }
    return P[i][k];
  }

  /**
   * Carefully follow the exact function, so i,k are 1-based, and prices[] is 0-based, so notice
   *  the index, and boundary "<="
   * @param prices
   * @param s
   * @param e
   * @return
   */
  private static int gain(int[] prices, int s, int e) {
    int ans = 0;
    int curMin = prices[s-1];
    for (int i = s; i <= e; ++i) {
      curMin = Math.min(prices[i-1], curMin);
      ans = Math.max(ans, prices[i-1] - curMin);
    }
    return ans;
  }

/* ------------------------------------------------------------------------------------------------
*    Below failed in {1,2}, pass {4,4,6,1,1,4,2,5}, since the index is wrong designed
* ------------------------------------------------------------------------------------------------
*/
  private int dfsINDEX(int[] prices, int[][] P, int i, int k) {
    //if (P[i][k] > 0) {
    //  return P[i][k];
    //}

    if (i == 0) {
      return P[0][k] = 0;
    }
    else if (k == 0 && i > 0) {
      return P[i][0] = -1000;
    }

    int rs = 0;
    for (int x = 0; x < i; ++x) {
      int tmp = dfs(prices, P, x, k-1) + gainINDEX(prices, x+1, i);
      rs = Math.max(rs, tmp);
    }
    return P[i][k] = rs;
  }

  /**
   * find the max profit in [b, e]
   * @param prices
   * @param b: begin
   * @param e: end
   * @return
   */
  private static int gainINDEX(int[] prices, int b, int e) {
    int ans = 0;
    int curMin = prices[b];

    for (int i = b; i <= e; ++i) {
      curMin = Math.min(prices[i], curMin);
      ans = Math.max(ans, prices[i] - curMin);
    }
    //for (int i = b; i < e; ++i) {
    //  int j = i;
    //  while (j+1 < e && prices[j] < prices[j+1]) {
    //    j++;
    //  }
    //  int tmp = prices[j]-prices[i];
    //  ans = Math.max(tmp, ans);
    //}
    return ans;
  }
}
