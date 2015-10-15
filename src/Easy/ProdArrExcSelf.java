package Easy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 2:55 AM on 10/14/2015.
 */
public class ProdArrExcSelf {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 0, 3};
//        int[] B = prod(nums);
//        for (int i : B) {
//            System.out.print(i + " ");
//        }
        ArrayList<Integer> input = new ArrayList<>();
        for (int i : nums) {
            input.add(i);
        }
        System.out.println(productExcludeItself(input));
    }

    public static ArrayList<Long> productExcludeItself(ArrayList<Integer> A) {
        if (A == null || A.size() == 0) {
            return null;
        }
        ArrayList<Long> res = new ArrayList<>();
        for (int i = 0; i < A.size(); ++i) {
            long holder = 1;
            for (int j = 0; j < A.size(); ++j) {
                if (j == i) {
                    holder *= 1;
                } else {
                    holder *= A.get(j);
                }
            }
            res.add(holder);
        }
        return res;
    }

    /**
     * Always use Lintcode API to save time!
     *
     * @param nums
     * @return
     */
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
