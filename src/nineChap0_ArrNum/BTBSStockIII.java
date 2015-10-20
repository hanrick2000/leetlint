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
        int[] test = new int[]{1,2,4,2,5,7,2,4,9,0}; //{3,2,6,5,0,3}; //{1,2}; //{4,4,6,1,1,4,2,5};
        int res = new BTBSStockIII().maxProfit(test);
        System.out.println(res);
    }

    /**
     * My way: brute force.
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
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
            int maxRi1 = i + 1 < stampLen ? maxR[i+1] : 0;
            int restmp = Math.max(res, maxL[i] + maxRi1);
            res = Math.max(restmp, maxL[i]);
        }
        return res;
    }

    private int[] btbssi(int[] stamp) {
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

    private int[] btbssiRev(int[] stamp) {
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
