package Easy;

/**
 * Created by 2:55 AM on 10/14/2015.
 */
public class ProdArrExcSelf {
    public static void main(String[] args) {
        int[] nums = new int[]{1,0,3};
        int[] B = prod(nums);
        for (int i : B) {
            System.out.print(i + " ");
        }
    }

    private static int[] prod(int[] nums) {
        int[] B = new int[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            int b = 1;
            for (int j = 0; j < nums.length; ++j) {
                b *= j == i ? 1 : nums[j];
            }
            B[i] = b;
        }
        return B;
    }


}
