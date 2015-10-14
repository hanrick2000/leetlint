package Easy;

/**
 * http://www.lintcode.com/en/problem/find-the-missing-number/
 * Created by 11:01 PM on 10/12/2015.
 */
public class MissingNum {
    public static void main(String[] args) {
        int[] data;
        data = new int[]{1,2};
//        data = new int[]{0,1};
//        data = new int[]{0,2};
        System.out.println(findMissingV2(data));
    }


    /**
     * Totally wrong direction, the number are not mono-increasing!
     * @param nums
     * @return
     */
    public static int findMissingWrong(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return (nums[0] == 0) ? 1 : 0;
        }
        int i = 0, j = 1;
        while (j < nums.length) {
            if (nums[i] + 1 == nums[j]) {
                i++;
                j++;
            } else {
                return nums[i] + 1;
            }
        }
        if (nums[0] == 0) {
            return nums[i] + 1;
        } else {
            return 0;
        }
    }

    /**
     * a so-so solution
     *
     * @param nums
     * @return
     */
    public static int findMissingV1(int[] nums) {
        long target = 0, sum = 0;
        for (int i = 0; i < nums.length; ++i) {
            target += i + 1;
            sum += nums[i];
        }
        if (target == sum) {
            return 0;
        }
        else {
            return (int) (target - sum);
        }
    }

    public static int findMissingV2(int[] nums) {
        int bit = 0, val = 1;
        for (int i : nums) {
            bit ^= i ^ val;
            val++;
        }
        return bit;
    }
}
