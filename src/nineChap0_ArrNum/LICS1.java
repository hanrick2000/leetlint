package nineChap0_ArrNum;

import java.util.Arrays;

/**
 * http://www.lintcode.com/submission/2039086/
 * Longest continuous increasing subsequence 1
 *
 * Created by 5:02 PM on 10/19/2015.
 */
public class LICS1 {
    public static void main(String[] args) {
        int[] test = new int[]{3,2,3,1,2,4,9, 7};
        System.out.println("Start");
        int[] res = new LICS1().BAD(test);
        System.out.println(Arrays.toString(res));
        System.out.println("end");
    }

    /**
     * This is not Lonest increasing subarray! [1,4,2,9,7]->[1,9]!
     * Wrong design: why use while { while } ? why is for, but how come it never stopped?
     * I got the same fault in Longest rectangle area which is for + while + stack.
     *
     * @param prices
     * @return
     */
    public int[] BAD(int[] prices) {
        // write your code here
        if (prices == null || prices.length < 2) {
            return null;
        }
        int buy = 0, sell = 0;
        int[] result = new int[prices.length];
        while (sell < prices.length) {
            while (sell + 1 < prices.length && prices[sell+1] > prices[sell]) {
                sell++;
            }
            if (sell >= prices.length) {
                return null;  //
            } else {
                result[sell] = prices[sell] - prices[buy];
                sell++;
                buy = sell;
            }
        }

        return result;
    }

    /**
     * In deed both for+while or while+while are fine, only the index is important!
     *
     * @param prices
     * @return
     */
    public int[] OK(int[] prices) {
        if (prices == null || prices.length < 2) {
            return null;
        }

        int[] result = new int[prices.length];
        for (int buy = 0, sell = 0; sell < prices.length; ) {
            while (sell + 1 < prices.length && prices[sell] < prices[sell+1]) {
                sell++;
            }
            result[sell] = prices[sell] - prices[buy];
            sell++;
            buy = sell;
        }
        return result;
    }
}
