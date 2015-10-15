package nineChap0_ArrNum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * http://www.lintcode.com/en/problem/subarray-sum/
 * Given [-3, 1, 2, -3, 4], return [0, 2] or [1, 3]
 * Created by 1:21 PM on 10/14/2015.
 */
public class SubarrSum {
    public static void main(String[] args) {
        int[] test = new int[]{1,2,3,-3,-2};
        System.out.println(new SubarrSum().subarraySum(test));
    }

    /**
     * http://www.cnblogs.com/yuzhangcmu/p/4174507.html
     * Smart using map and simple algs: a + sum = a => sum == 0. Notice he use dummy head for edge case : 0!
     *
     * @param nums
     * @return
     */
    public ArrayList<Integer> subarraySum(int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return null;
        }

        ArrayList<Integer> res = new ArrayList<>();

        Map<Integer, Integer> map = new HashMap<>(); // KV: <sum, index>

        map.put(0, -1);
        int sum = 0;
        for (int i = 0; i < nums.length; ++i) {
            sum += nums[i];
            // For example:
            //        -3  1  2 -3 4
            // SUM: 0 -3 -2  0 -3 1
            // then we got the solution is : 0 - 2
            if (map.containsKey(sum)) {
                res.add(map.get(sum) + 1);
                res.add(i);
                break;
            }
            map.put(sum, i);
        }
        return res;
    }
}
