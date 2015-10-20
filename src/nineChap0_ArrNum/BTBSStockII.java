package nineChap0_ArrNum;

import java.util.Arrays;

/**
 * http://www.lintcode.com/en/problem/best-time-to-buy-and-sell-stock-ii/
 *
 * You can buy/sell multiple times to gain the max profit, the only thing is you can keep one stock only
 *
 * Always draw a plot to understand, although a bad plot will lead to wrong direction!
 *
 * Created by 6:17 PM on 10/19/2015.
 */
public class BTBSStockII {
    public static void main(String[] args) {
        int[] prices = new int[]{1,2,5,3,10,7};
        int res;
        res = new BTBSStockII().maxProfitMy(prices);
        System.out.println(res);
    }

    /**
     * SImpliest way to tackle this simple problem. The idea is interesting.
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            int diff = prices[i+1] - prices[i];
            if (diff > 0) {
                profit += diff;
            }
        }
        return profit;
    }

    /**
     * Method 2: directly measure all the diff of each monolithic increasing interval.
     * Practice my skill in two pointers.
     *
     * @param prices
     * @return
     */
    public int maxProfitMy(int[] prices) {
        int[] profit = new int[prices.length];
        for (int lo = 0, hi = 0; lo < prices.length && hi < prices.length; ) {
            while (hi + 1< prices.length && prices[hi+1] > prices[hi]) {
                hi++;
            }
            profit[hi] = prices[hi] - prices[lo];
            hi++;
            lo = hi;
        }

        int res = 0;
        for (int p : profit) {
            res += p;
        }

        return res;
    }

    /**
     * Jenny's design, look at the Loop {loop + loop}
     *
     * @param prices
     * @return
     */
    public int maxProfitJenny(int[] prices) {
        if (prices.length <2){
            return 0;
        }
        int[] profits = new int[prices.length];
        int i = 0;
        while (i < prices.length-1) {
            int buy = 0, sell = 0;
            while (i+1 < prices.length && prices[i+1] < prices[i]) {
                i++;
            }
            buy = i;
            i++;
//            while (i+1 < prices.length && prices[i+1] >= prices[i]) {
//                i++;
//            }
//            sell = i;
            while (i<prices.length && prices[i] >= prices[i-1]) {
                i++;
            }
            sell = i-1;
            profits[i] = prices[sell] - prices[buy];
        }

        int res = 0;
        for (int profit : profits) {
            res = Math.max(res, profit);
        }
        System.out.println(Arrays.toString(profits));
        return res;
    }
}
