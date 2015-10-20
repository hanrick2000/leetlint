package nineChap0_ArrNum;

import java.util.*;

/*-
 * numbers=[2, 7, 11, 15], target=9
 *                    return [1, 2]
 * Created by 2:26 PM on 10/14/2015.
 */
public class TwoSum {
    public static void main(String[] args) {
        int[] test = new int[]{2, 3, 4, 5, 5, 5, 7, 7, 11, 15};
        int[] ans = new TwoSum().twoSumGood(test, 9);
//        System.out.println(Arrays.toString(ans));
    }

    /**
     * O(nlgn) time but O(1) space, Classical 2 pointers problem, also the tag is 2 pointers.
     * Why O(nlgn)+O(n) time? because every number check only one time!
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSumGood(int[] nums, int target) {
        Arrays.sort(nums);
        Set<Integer> result = new HashSet<>();

        int lo = 0, hi = nums.length - 1;
        while (nums[lo] + nums[hi] > target && lo < hi) hi--;
        while (nums[lo] + nums[hi] == target) {
            result.add(nums[lo]);
            result.add(nums[hi]);
            hi--;
        }
        while (nums[lo] + nums[hi] < target && lo < hi) lo++;
        while (nums[lo] + nums[hi] == target) {
            result.add(nums[lo]);
            result.add(nums[hi]);
            lo++;
        }
        System.out.println(result);
        return new int[]{0, 0};
    }

    /**
     * This is the so-so solution since I used hashmap to search, therefore O(n) space and O(n)
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSumNromal(int[] numbers, int target) {
        // write your code here
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; ++i) {
            map.put(numbers[i], i);
        }

        int[] res = new int[]{-1, -1};
        for (int i = 0; i < numbers.length; ++i) {
            if (map.containsKey(target - numbers[i])) {
                res[0] = i + 1;
                res[1] = map.get(target - numbers[i]) + 1;
                break;
            }
        }
        return res;
    }
}
