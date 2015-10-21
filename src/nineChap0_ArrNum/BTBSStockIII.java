package nineChap0_ArrNum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * http://www.lintcode.com/en/problem/best-time-to-buy-and-sell-stock-iii/#
 *
 * Created by 10:19 PM on 10/19/2015.
 */
public class BTBSStockIII {

    public static void main(String[] args) {
        int[] test = new int[]{2,1,2,0,1}; //{1,2,4,2,5,7,2,4,9,0}; //{3,2,6,5,0,3}; //{1,2}; //{4,4,6,1,1,4,2,5};
        int res = new BTBSStockIII().maxProfit(test);
        System.out.println(res);
    }

    /**
     * 9chap's way, cleaner!
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int len = prices.length;
        int[] left = new int[len];
        int[] right = new int[len];

        left[0] = 0;
        int min = prices[0];
        for (int i = 1; i < len; ++i) {
            min = Math.min(prices[i], min);
            left[i] = Math.max(left[i-1], prices[i] - min);
        }

        right[len-1] = 0;
        int max = prices[len-1];
        for (int j = len-2; j >= 0; j--) {
            max = Math.max(prices[j], max);
            right[j] = Math.max(right[j+1], max - prices[j]);
        }

        int profit = Integer.MIN_VALUE;
        for (int i = 0; i < len; ++i) {
            profit = Math.max(left[i]+right[i], profit);
        }
        return profit;
    }
    /**
     * My way: brute force.
     *
     * @param prices
     * @return
     */
    public int maxProfitMy(int[] prices) {
        // write your code here
//        List<List<Integer>> iList = new ArrayList<>();
//        int len = prices.length;
//        int[] iMax = new int[len];
//        List<Integer> stampList = new ArrayList<>();
//
//        for (int lo = 0, hi = 0; lo < len && hi < len; ) {
//            while (hi+1 < len && prices[hi+1] > prices[hi]) {
//                hi++;
//            }
//            stampList.add(prices[lo]);
//            stampList.add(prices[hi]);
//            hi++;
//            lo = hi;
//        }

//        int[] stamp = new int[stampList.size()];

        int[] stamp = prices;
        int stampLen = stamp.length;
        int[] maxL = new int[stampLen]; // the largest diff in [0, i]  ===> BTBSSI
        int[] maxR = new int[stampLen]; // the largest diff in [i, len-1]

        maxL = btbssi(stamp);
        maxR = btbssiRev(stamp);

        System.out.println(Arrays.toString(maxL));
        System.out.println(Arrays.toString(maxR));

        int res = 0;
        for (int i = 0; i< stampLen; ++i) {
//            int maxRi1 = i + 1 < stampLen ? maxR[i+1] : 0;
//            int restmp = Math.max(res, maxL[i] + maxRi1);
//            res = Math.max(restmp, maxL[i]);
            res = Math.max(maxL[i] + maxR[i], res);
        }
        return res;
    }

    /**
     * Took me some time to make it correct, need to well define the meaning of profits[i]. also I need to get buy or sell point?
     *  1. The profits[i] means the local maximum profit to this i point,
     *  2. ~~for i : 0 to len. I should find the local min, so buy point.~~
     *      ~~for j : len-1 to 0. I should find the local max, so sell point.~~
     *      Doesn't matter looking for buy/sell in in-order/rev-order.
     *
     *  3. If the base case is know, not need to start i from 0! so as to prevent if/else in looping
     *
     * @param stamp
     * @return
     */
    private int[] btbssi(int[] stamp) {
        int len = stamp.length;
        int[] profits = new int[len];
        profits[0] = 0;
        int buy = stamp[0]; //Integer.MIN_VALUE;
        for (int i = 0; i < len; ++i) {
//            if (i == 0) {
//                sell = stamp[i];
//                profit[i] = 0;
//            }
            if (i == 0) { // because the profit at [0] is know to be 0, since buy/sell same thing earns 0 dollar.
                buy = Math.min(stamp[i], buy);
                profits[i] = Math.max(stamp[i] - buy, 0);
            }
            else {
                buy = Math.min(stamp[i-1], buy);
                profits[i] = Math.max(stamp[i] - buy, profits[i-1]);
            }
        }

        return profits;
    }

    private int[] btbssiRev(int[] stamp) {
        int len = stamp.length;
        int[] profits = new int[len];
        int[] maxR = new int[len];
        int sell = Integer.MIN_VALUE;
        for (int i = len-1; i >= 0; i--) {
            if (i == len -1) {
                sell = Math.max(stamp[i], sell);
                profits[i] = Math.max(profits[len-1], sell - stamp[i]);
            }
            else {
                sell = Math.max(stamp[i+1], sell);
                profits[i] = Math.max(profits[i+1], sell - stamp[i]);
            }
        }
        return profits;
    }

    /**
     * I forgot again that btbssi is to find one time max profit, not find LICS!
     * @param stamp
     * @return
     */
    private int[] btbssiWRONG(int[] stamp) {
        int[] res = new int[stamp.length];
        int len = res.length;
        for (int lo = 0, hi = 0; lo < len && hi < len; ) {
            while (hi+1 < len && stamp[hi+1] > stamp[hi]) {
                hi++;
            }
            res[hi] = stamp[hi] - stamp[lo];
            hi++;
            lo = hi;
        }
        return res;
    }

    /**
     * I forgot again that btbssi is to find one time transaction, not find LICS!
     *
     * @param stamp
     * @return
     */
    private int[] btbssiRevWRONG(int[] stamp) {
        int[] res = new int[stamp.length];
        int[] maxR = new int[stamp.length];
        int len = res.length;
        for (int lo = len-1, hi = len-1; lo >= 0 && hi >= 0; ) {
            while (lo-1 >= 0 && stamp[lo-1] < stamp[lo]) {
                lo--;
            }
            res[lo] = stamp[hi] - stamp[lo];
//            int maxRlo1 = lo == len-1 ? 0 : maxR[lo+1];
//            maxR[lo] = Math.max(res[lo], maxRlo1);
            lo--;
            hi = lo;
        }

        for (int lo = len-2; lo>=0; lo--) {
            maxR[lo] = Math.max(res[lo], maxR[lo+1]);
        }
        return maxR;
    }
}
