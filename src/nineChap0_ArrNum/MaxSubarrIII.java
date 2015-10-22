/**
 * http://www.lintcode.com/en/problem/maximum-subarray-iii/
 * K-sum, K subarray, K-btbss are all the same Backpacking DP!!!!
 * Created by 1:30 AM on 10/21/2015.
 */

package nineChap0_ArrNum;

import java.util.ArrayList;

public class MaxSubarrIII {
    public static void main(String[] args) {
        ArrayList<Integer> nums = new ArrayList<>();
        nums.add(-1);
        nums.add(4);
        nums.add(-2);
        nums.add(3);
        nums.add(-2);
        nums.add(3);

        int ans = new MaxSubarrIII().maxSubArray(nums, 2);
        System.out.println("Got result : " + ans);
    }

    /**
     * http://www.cnblogs.com/lishiblog/p/4183917.html
     * http://www.mitbbs.com/article_t/JobHunting/32842389.html
     * http://articles.leetcode.com/2011/04/the-painters-partition-problem.html
     *
     * @param nums: A list of integers
     * @param k: An integer denote to find k non-overlapping subarrays
     * @return: An integer denote the sum of max k non-overlapping subarrays
     */
    public int maxSubArray(ArrayList<Integer> nums, int k) {
        if (nums == null || nums.size() < k) {
            return Integer.MIN_VALUE;
        }

        int len = nums.size();
        int[][] F = new int[len+1][k+1];
        for (int i = 0; i < len+1; ++i) {
            F[i][0] = 0;
        }

//        for (int i = 1; i < len+1; ++i) {             // i,j order MATTER! not a simple cache optimized issue!
//            for (int j = i; j < k+1; ++j) {               // here is the bottom up Recursion!, or say serialized recursion!
        for (int j = 1; j <= k; ++j) {
            for (int i = j; i <= len; ++i) {
                F[i][j] = Integer.MIN_VALUE;

                int endMax = 0;
                int max = Integer.MIN_VALUE;
                for (int p = i-1; p >= j-1; p--) {
                    endMax = Math.max(endMax+nums.get(p), nums.get(p));
                    max = Math.max(endMax, max);
                    if (F[i][j] < F[p][j-1] + max) {
//                        F[i][j] = F[i][j-1] + max;
                        F[i][j] = F[p][j-1] + max;
                    }
                }
            }
        }

        return F[len][k];
    }
}
