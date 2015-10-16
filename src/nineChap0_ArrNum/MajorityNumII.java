package nineChap0_ArrNum;

import java.util.ArrayList;

/**
 * http://www.lintcode.com/en/problem/majority-number-ii/
 * Created by 12:00 AM on 10/15/2015.
 */
public class MajorityNumII {
    public static void main(String[] args) {
        int[] test = new int[]{7,1,7,7,61,61,61,10,10,10,61}; //{1,2,1,2,1,3,3};
        ArrayList<Integer> nums = new ArrayList<>();
        for (int i : test) {
            nums.add(i);
        }
        System.out.println(new MajorityNumII().majorityNumber(nums));
    }

    public int majorityNumber(ArrayList<Integer> nums) {
        if (nums == null || nums.size() == 0) {
            return 0;
        }
        int candidate1 = -1, candidate2 = -2;
        int cnt1 = 0, cnt2 = 0;
        for (int num : nums) {
//            if (cnt1 == 0) {
//                candidate1 = num;
//                cnt1 = 1;
//            }
//            else if ()
            if (candidate1 == num) {
                cnt1++;
            }
            else if (candidate2 == num) {
                cnt2++;
            }
            else if (cnt1 == 0) {
                candidate1 = num;
                cnt1++;
            }
            else if (cnt2 == 0) {
                candidate2 = num;
                cnt2++;
            }
            else {
                cnt1--;
                cnt2--;
            }
        }

        // if left 2 candidate, which one is the result?
        if (cnt1 == 1 && cnt2 == 0)  return candidate1;
        if (cnt1 == 0 && cnt2 == 1)  return candidate2;
        cnt1 = 0;
        cnt2 = 0;
        for (int num : nums) {
            if (num == candidate1) {
                cnt1++;
            }
            else if (num == candidate2) {
                cnt2++;
            }
        }
        return cnt1 > cnt2 ? candidate1 : candidate2;
    }

    /**
     * Can't do this since I can't pair 3 to be cancelled
     * @param nums
     * @return
     */
    public int majorityNumberFailed(ArrayList<Integer> nums) {
        // write your code
        int Maj = Integer.MIN_VALUE;
        int cnt = 0;
        for (int num : nums) {
            if (cnt == 0) {
                Maj = num;
                cnt = 2;
            }
            else {
                if (num == Maj) {
                    cnt++;
                }
                else {
                    cnt--;
                }
            }
        }
        return Maj;
    }

}
