package nineChap0_ArrNum;

import java.util.ArrayList;

/**
 * http://www.lintcode.com/en/problem/majority-number/
 * Created by 10:23 PM on 10/15/2015.
 */
public class MajorityNumI {
    public static void main(String[] args) {
        int[] test = new int[]{2,1,1,2,1,2,1,2,1}; //{1,1,1,1,2,2,2}; //{1, 1, 1, 1, 2, 2, 2, 2, 2};
        ArrayList<Integer> input = new ArrayList<>();
        for (int a : test) {
            input.add(a);
        }
        System.out.println(new MajorityNumI().majorityI(input));
    }

    /**
     * Clearn thought!
     * I failed in lintcode for several times with the same code, I thought the lintcode has issue.
     * It turns out I forgot to use else if, but if (candidate == num)!!!
     * @param nums
     * @return
     */
    public int majorityI(ArrayList<Integer> nums) {
        int cnt = 0;
        int candidate = -1;
        for (int num : nums) {
            if (cnt == 0) {
                candidate = num;
                cnt = 1;
            }
            else if (candidate == num) {
                cnt++;
            }
            else {
                cnt--;
            }
        }
        return candidate;
    }
}
