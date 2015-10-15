package Easy;

/**
 * Given [1, 2, 3, 4], return [1, 3, 2, 4]
 * Created by 1:45 AM on 10/14/2015.
 */
public class ArrayOddEven {
    public static void main(String[] args) {
        int[] test = new int[]{1,3,5}; //{1,2,3,4};
        new ArrayOddEven().partitionArray(test);
        for (int i : test) {
            System.out.print(i + " ");
        }
    }

    public void partitionArray(int[] nums) {
        // write your code here;
        int od = 0, ev = nums.length - 1;
        while (true) {
            while (od < nums.length && (nums[od] & 1) == 1) {
                od++;
            }
            while (ev >= 0 && (nums[ev] & 1) == 0) {
                ev--;
            }
            if (od > ev) break;
            exch(nums, od, ev);
        }
    }

    private void exch(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
