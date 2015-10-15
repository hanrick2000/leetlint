package nineChap0_ArrNum;

/**
 * For example, given the array [2,3,-2,4], the contiguous subarray [2,3] has the largest product = 6.
 * Created by 2:37 PM on 10/14/2015.
 */
public class MaxProdSubarr {
    public static void main(String[] args) {
        int[] data = new int[]{1, 0, -1, 2, 3, -5, -2}; // {2, 0, 3, -2, 4}  // {-5}; //
        System.out.println(new MaxProdSubarr().maxProduct(data));

    }


    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {  // must do this, ow, it will do the loop and recalc itself
            return nums[0];
        }
        int max = nums[0], min = nums[0];
        int global = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            int maxCpy = max;
            max = Math.max(Math.max(nums[i] * max, nums[i]), nums[i] * min);
            min = Math.min(Math.min(nums[i] * min, nums[i]), nums[i] * maxCpy);
            global = Math.max(max, global);
        }
        return global;
    }

    /**
     * https://shepherdyuan.wordpress.com/2014/07/23/linkedin-maximum-sumproduct-subarray/
     * Good for max product = positive
     * Failed at {-5}.
     *
     * @param nums
     * @return
     */
    public int maxProductFailEdge(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] max = new int[nums.length];
        int[] min = new int[nums.length];
        max[0] = Math.max(nums[0], 1);
        min[0] = Math.min(nums[0], 1);
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] == 0) {
                max[0] = 1;
                min[0] = 1;
            }
            else if (nums[i] > 0) {
                max[i] = nums[i] * max[i-1];
                min[i] = Math.min(min[i-1] * nums[i], 1);
            }
            else {
                max[i] = Math.max(nums[i] * min[i-1], 1);
                min[i] = nums[i] * max[i-1];
            }
        }

        int ans = Integer.MIN_VALUE;
        for (int i : max) {
            ans = Math.max(i, ans);
        }

        return ans;
    }

    /**
     * What's the diff with maxSumDP?
     * Why the same idea from max sum of subarray doesn't work in here?
     *
     * @param nums
     * @return
     */
    public int maxProductDPwrong(int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        int preN = -1, Neg = -1;
        dp[0] = nums[0];
        int ans = dp[0];
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] > 0) {
                dp[i] = nums[i] * (dp[i - 1] > 0 ? dp[i - 1] : 1);
            } else {
                dp[i] = nums[i] * (dp[i - 1] < 0 ? dp[i - 1] : 1);
            }
            ans = Math.max(ans, dp[i]);
        }
        for (int i : dp) {
            System.out.print(i + " ");
        }
        System.out.println();
        return ans;
    }
}
