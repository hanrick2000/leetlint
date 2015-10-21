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
        for (int i = 0; i + 1 < prices.length; i++) {
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
     * In fact, my code is not good. Since the buy point is not considered!!! therefore slow!
     *
     * @param prices
     * @return
     */
    public int maxProfitMy1(int[] prices) {
        int[] profit = new int[prices.length];
        for (int lo = 0, hi = 0; lo < prices.length && hi < prices.length; ) {
            // Not efficient! I was misguide by my drawing. What if it starts by dropping? I should find the buy point too!
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

    /*-
     * My way to use two pointer, and fix the bug in maxProfitMy1 that I didn't check buy point
     * My drawback compare with Jenny's solution is that, I always forgot increasing hi in the end of each iteration so
     *  my solution always goes into forever loop at the last point.
     *
     *  learn: while (prices[i+1] < prices[i]) VS while (prices[i] < prices[i-1]). Then out of while,
     *      in first one: I got i as the spinodal.
     *      in second one: I got i as the next point of spinodal
     *
     * @param prices
     * @return
     */
    public int maxProfitMy(int[] prices) {
        int len = prices.length;
        int[] profit = new int[len];
        int res = 0;
        for (int lo = 0, hi = 0; lo < len && hi < len; ) {
            int buy, sell;
            // find buy point
            while (lo + 1 < len && prices[lo+1] < prices[lo] ) {
                lo++;
            }
            buy = lo;

            // find sell point
            hi = lo;
            while (hi + 1 < len && prices[hi+1] > prices[hi]) {
                hi++;
            }
            sell = hi;
            profit[lo] = prices[sell] - prices[buy];
            res += profit[lo];

            // setup next starting point of lo/hi. I prefer Jenny's way to hi++ when I start finding the sell point. Since
            //  sell point at buy point's profit is always 0, can see the buy+1 point. More importantly, in Jenny's one pointer
            //      solution, she just need to update i once instead of my two pointers solution must update them after
            //          inner two loops.
            hi++;
            lo = hi;
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
        while (i < prices.length) {
            int buy, sell;  // local var holders.
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
            // why no update of buy and sell??? because they are just local var. The main var is the i. Since Jenny is using one pointer here.
        }

        int res = 0;
        for (int profit : profits) {
            res = Math.max(res, profit);
        }
        System.out.println(Arrays.toString(profits));
        return res;
    }
}
