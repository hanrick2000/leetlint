package nineChap0_ArrNum;

import java.util.Arrays;

/**
 * Created by 12:16 AM on 10/21/2015.
 */
public class SortColorI {
    public static void main(String[] args) {
        int[] test = new int[]{0,0,1, 0,0,0}; //{1,0,2,0, 0, 2, 1};
        new SortColorI().sortColors(test);
    }

    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int i = 0, j = nums.length - 1;
        while (true) {
            while (i < nums.length && nums[i] == 0) {
                i++;
            }
            while (0 <= j && nums[j] != 0) {
                j--;
            }
            if (i > j) {
                break;
            }
            exch(nums, i, j);
        }

        j = nums.length -1;
        while (true) {
            while (i < nums.length && nums[i] == 1) {
                i++;
            }
            while (j >= 0 && nums[j] == 2) {
                j--;
            }
            if (i > j) {
                break;
            }
            exch(nums,i,j);
        }

        System.out.println(Arrays.toString(nums));
    }

    private void exch(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public void sortColorsCountSort(int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return;
        }

        int cnt0 = 0, cnt1 = 0, cnt2 = 0;
        for (int i : nums) {
            switch (i) {
                case 0:
                    cnt0++;
                    break;
                case 1:
                    cnt1++;
                    break;
                case 2:
                    cnt2++;
                    break;
                default:
                    System.out.println("NO WAY!");
                    break;
            }
        }

        for (int i = 0; i < nums.length; ++i) {
            if (i < cnt0) {
                nums[i] = 0;
            }
            else if (i < cnt0 + cnt1) {
                nums[i] = 1;
            }
            else {
                nums[i] = 2;
            }
        }
        System.out.println(Arrays.toString(nums));
    }
}
