package nineChap0_ArrNum;

import java.util.ArrayList;

/**
 * http://www.lintcode.com/en/problem/maximum-subarray-ii/
 * Created by 10:11 PM on 10/14/2015.
 */
public class MaxSumSubarrII {
    public static void main(String[] args) {
        MaxSumSubarrII mss2 = new MaxSumSubarrII();
        mss2.testMaxMinUtil();
    }

    public void testMaxMinUtil() {
        int[] testMin = new int[]{-1,-1}; //{1, 3, -1, 2, -1, 2}; //{1, -1, -2, 1};
        ArrayList<Integer> nums = new ArrayList<>();
        for (int i : testMin) {
            nums.add(i);
        }
        System.out.println(maxSumSubArrII(nums));
    }

    /**
     * First time
     * @param nums
     * @return
     */
    public int maxSumSubArrII(ArrayList<Integer> nums) {
        // write your code
        if (nums == null || nums.size() == 0) {
            return 0;
        }

        int size = nums.size();
        int[] dpL = new int[size];  // dpL[i]: max sum in [0, i]
        int[] globalL = new int[size]; // globalL[i]: max of dpL[0, i]

        int[] dpR = new int[size];  // dpR[i]: max sum in [i, size]


        dpL[0] = nums.get(0);
        globalL[0] = dpL[0];
        for (int i = 1; i < size; ++i) {
            dpL[i] = nums.get(i) + Math.max(dpL[i - 1], 0);
            globalL[i] = Math.max(globalL[i - 1], dpL[i]);
        }

        dpR[size - 1] = nums.get(size - 1);
        int ans = Integer.MIN_VALUE;
        for (int i = size - 1; i >= 0; i--) {
            if (i == size - 1) {
                dpR[i] = nums.get(size - 1);
            } else {
                dpR[i] = nums.get(i) + Math.max(dpR[i + 1], 0);
            }

            if (i > 0) {
                ans = Math.max(ans, dpR[i] + globalL[i - 1]);
            }
        }

        return ans;
    }

    public int maxSumSubArr(ArrayList<Integer> nums) {
        // write your code
        if (nums == null || nums.size() == 0) {
            return 0;
        }

        int[] arr = new int[nums.size()];
        int[] dp = new int[nums.size()];
        dp[0] = nums.get(0);
        for (int i = 0; i < nums.size(); ++i) {
            arr[i] = nums.get(i);
        }

        int global = dp[0];
        for (int i = 1; i < nums.size(); ++i) {
            dp[i] = ((dp[i - 1] < 0) ? 0 : dp[i - 1]) + nums.get(i);
            global = Math.max(global, dp[i]);
        }
        return global;
    }

    public int minSumSubArr(ArrayList<Integer> nums) {
        if (nums == null || nums.size() == 0) {
            return 0;
        }

        int[] dp = new int[nums.size()];
        dp[0] = nums.get(0);
        int global = dp[0];
        for (int i = 1; i < nums.size(); ++i) {
            dp[i] = nums.get(i) + Math.min(0, dp[i - 1]);
            global = Math.min(dp[i], global);
        }
        return global;
    }
}
