package nineChap0_ArrNum;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * http://www.lintcode.com/en/problem/maximum-subarray-difference/
 * find the max|sum(subarrA) - sum(subarrB)|, NOTICE: "NON-OVERLAPPING"
 * <p>
 *     http://www.fgdsb.com/2015/01/05/max-dif-of-two-subarrays/
 * Array: { 2, -1, -2, 1, -4, 2, 8 }
 * Result subarrays: {-1, -2, 1, -4 }, { 2, 8 }
 * Maximum difference = 16
 * <p>
 * Created by 9:06 PM on 10/14/2015.
 */
public class MaxSubarrDiff {

    public static void main(String[] args) {
        int[] array = new int[]{1,2,-3,1}; //{2, -1, -2, 1, -4, 2, 8}; //{1, 2, -10, -3, 1}; //{2, 99, 1000}; //]{-2, -99, -1000}; //
        ArrayList<Integer> nums = new ArrayList<>();
        for (int a : array) {
            nums.add(a);
        }
//        System.out.println("hello");
        System.out.println(new MaxSubarrDiff().maxDiffSubArrays(nums));
    }

    /**
     * DP!!!!!!!!! and understand the meaning of NON-overlapping = disjoint contiguous
     *
     * @param nums
     * @return
     */
    public int maxDiffSubArrays(ArrayList<Integer> nums) {
        // write your code
        if (nums == null) {
            return 0;
        }

        int len = nums.size();
        int[] maxL = new int[len];  maxL[0] = nums.get(0);
        int[] minL = new int[len];  minL[0] = nums.get(0);
        int[] maxR = new int[len];  maxR[len-1] = nums.get(len-1);
        int[] minR = new int[len];  minR[len-1] = nums.get(len-1);

        int[] maxG = new int[len];  maxG[0] = nums.get(0);
        int[] minG = new int[len];  minG[0] = nums.get(0);

        // phase 1 get left sum array(max/min)
        for (int i = 1; i < nums.size(); ++i) {
            maxL[i] = nums.get(i) + Math.max(0, maxL[i-1]);
            minL[i] = nums.get(i) + Math.min(0, minL[i-1]);
            maxG[i] = Math.max(maxL[i], maxG[i-1]);
            minG[i] = Math.min(minL[i], minG[i-1]);
        }

        int maxAns = nums.get(len-1);
        int ac = Integer.MIN_VALUE, ad = Integer.MIN_VALUE, bc = Integer.MIN_VALUE, bd = Integer.MIN_VALUE;

        for (int i = 0; i < len; ++i) {
            if (i == 0) {
//                maxR[len-i-1] = nums.get(len-i-1);
//                minR[len-i-1] = nums.get(len-i-1);
            }
            else {
                maxR[len - i - 1] = nums.get(len - i - 1) + Math.max(0, maxR[len - i]);
                minR[len - i - 1] = nums.get(len - i - 1) + Math.min(0, minR[len - i]);
            }

            if (i == len - 1) {
                //
            }
            else {
                ac = Math.abs(minR[len - i - 1] - minG[len - i - 2]);
                ad = Math.abs(minR[len - i - 1] - maxG[len - i - 2]);
                bc = Math.abs(maxR[len - i - 1] - minG[len - i - 2]);
                bd = Math.abs(maxR[len - i - 1] - maxG[len - i - 2]);
                int temp = Math.max(Math.max(ac, ad), Math.max(bc, bd));
                maxAns = Math.max(maxAns, temp);
                System.out.println("maxAns: " + maxAns);
            }
        }

//        System.out.println(Arrays.toString(minL));
//        System.out.println(Arrays.toString(maxL));
//        System.out.println(Arrays.toString(minR));
//        System.out.println(Arrays.toString(maxR));
//
//        System.out.println(Arrays.toString(minG));
//        System.out.println(Arrays.toString(maxG));

        return maxAns;
    }

    /**
     * I misconcept subarry/subsequence: subarray is continuous, subsequence can choose discrite index!
     *
     * @param nums
     * @return
     */
    public int maxDiffSubseq(ArrayList<Integer> nums) {
        // write your code
        // phase 1, separate neg, pos. Then the left - right is result. Or nums are same signed ,then simply least - rest
        if (nums == null || nums.size() < 2) {
            return 0;
        }

        int[] arr = new int[nums.size()];
        for (int idx = 0; idx < nums.size(); ++idx) {
            arr[idx] = nums.get(idx);
        }
        int i = 0, j = arr.length - 1;
        while (true) {
            while (i < arr.length && arr[i] < 0) {
                i++;
            }
            while (j >= 0 && arr[j] >= 0) {
                j--;
            }
            if (i > j) {
                break;
            }
            exch(arr, i, j);
        }

        System.out.println(i + " " + j); // so i is right's zuo boundary, j is left's you boundary.
        for (int a : arr) {
            System.out.print(a + " ");
        }

        // phase 2: find max diff
        int sum = 0, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int elem : arr) {
            sum += elem;
            min = Math.min(elem, min);
            max = Math.max(elem, max);
        }

        if (i == nums.size()) {
            return 2 * max - sum;
        } else if (j == -1) {
            return sum - 2 * min;
        }

        int negSum = 0;
        for (int left = 0; left < i; ++left) {
            negSum += arr[left];
        }

        return Math.abs(sum - 2 * negSum);
    }

    private void exch(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
