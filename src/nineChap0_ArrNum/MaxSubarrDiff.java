package nineChap0_ArrNum;

import java.util.ArrayList;

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
        int[] array = new int[]{2, -1, -2, 1, -4, 2, 8}; //{1, 2, -10, -3, 1}; //{2, 99, 1000}; //]{-2, -99, -1000}; //
        ArrayList<Integer> nums = new ArrayList<>();
        for (int a : array) {
            nums.add(a);
        }
        System.out.println("hello");
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
        return 0;
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
