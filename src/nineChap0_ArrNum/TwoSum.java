package nineChap0_ArrNum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*-
 * numbers=[2, 7, 11, 15], target=9
 *                    return [1, 2]
 * Created by 2:26 PM on 10/14/2015.
 */
public class TwoSum {
    public static void main(String[] args) {
        int[] test = new int[]{2, 7, 11, 15};
        int[] ans = new TwoSum().twoSum(test, 9);
        System.out.println(Arrays.toString(ans));
    }

    public int[] twoSum(int[] numbers, int target) {
        // write your code here
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; ++i) {
            map.put(numbers[i], i);
        }

        int[] res = new int[]{-1,-1};
        for (int i = 0; i < numbers.length; ++i) {
            if (map.containsKey(target-numbers[i])) {
                res[0] = i+1;
                res[1] = map.get(target-numbers[i])+1;
                break;
            }
        }
        return res;
    }
}
