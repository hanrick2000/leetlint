package nineChap0_ArrNum;

import java.util.HashSet;
import java.util.Set;

/**
 * Before doing the Anagram, I create a subproblem: find all groups of number mod the same
 * eg: [1, 5, 9, 7, 11, 4], mod 3 = 0 and 1 => [9] + [1,4, 7].
 * Created by 12:38 AM on 10/16/2015.
 */
public class GroupNum {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 5, 9, 7, 11, 4};
        group(nums);
    }

    public static void group(int[] nums) {
        Set<Integer> mod0 = new HashSet<>();
        Set<Integer> mod1 = new HashSet<>();
        Set<Integer> modNot2 = new HashSet<>();  // create a set contains numbers mode 3 = 2.

        for (int num : nums) {
            if (modNot2.isEmpty()) {
                for (int i = 0; i < 10; ++i) {
                    if (i % 3 == 0) {
                        modNot2.add(i);
                    }
                    else if (i % 3 == 1) {
                        modNot2.add(i);
                    }
                }
            }
            else if (modNot2.contains(num)) {
                System.out.println(num);
            }
        }

    }
}
