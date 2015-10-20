package nineChap0_ArrNum;

import java.util.Arrays;

/**
 * http://www.lintcode.com/en/problem/best-time-to-buy-and-sell-stock/
 *  [3, 2, 5, 3, 10, 6] -> [2,10], so this is not "continuous" LIS.
 *
 * Created by 5:53 PM on 10/19/2015.
 */
public class BTBSStockI {
    public static void main(String[] args) {
        int[] test = new int[]{1,2,4,2,5,7,2,9,0}; //{3,2,3,1,2}; //{3,2,5,4,10,6};
        int result = new BTBSStockI().maxProfitTTT1Wrong(test);
        System.out.println(result);
    }

    /**
     * Classical DP: local, global to find the optimal solution
     * Don't understand my code's logic, damn, need to write idea and notes in code
     * Also, this is wrong.
     * @param prices
     * @return
     */
    public int maxProfitTTT1Wrong(int[] prices) {
        // write your code here
        int len = prices.length;
        int[] maxR = new int[len];
        int[] profit = new int[len];

        int result = -1;
        for (int i = len - 1; i >= 0; i--) {
            if (i == len-1) {
                maxR[i] = prices[i];
                profit[i] = 0;
            }
            else {
//                maxR[i] = Math.max(maxR[i-1], prices[i]);
//                profit[i] = prices[i] - maxR[i-1];
//                result = Math.max(result, profit[i]);
                maxR[i] = Math.max(maxR[i+1], prices[i]);  // ???
                profit[i] = maxR[i+1] - prices[i];
                result = Math.max(result, profit[i]);
            }
        }
        System.out.println(Arrays.toString(maxR));
        System.out.println(Arrays.toString(profit));
        return result;
    }

    /*-
     * 9 chap's solution
     * This problem is simple <=> find the Max, Min of an array.
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int len = prices.length;
        int min = Integer.MAX_VALUE;
        int profit = 0;

        for (int price : prices) {
            min = Math.min(min, price);
            profit = Math.max(price - min, profit);
        }

        return profit;
    }
}
