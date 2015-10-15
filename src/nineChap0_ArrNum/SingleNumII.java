package nineChap0_ArrNum;

/**
 * http://www.lintcode.com/en/problem/single-number-ii/
 * A.size() = 3*k + 1, find the single number.
 *
 * This is cool to use 3 jing zhi! HOWDY!
 * Created by 1:13 AM on 10/15/2015.
 */
public class SingleNumII {
    public static void main(String[] args) {
        int[] test = new int[]{1,1,2,3,3,3,2,2,4,1};
        System.out.println(new SingleNumII().singleNumberII(test));
    }

    public int singleNumberII(int[] A) {
        // write your code here
        if (A == null || A.length == 0) {
            return -1; // can't reach here
        }
        int[] bits = new int[32];
        int result = 0;
        for (int i = 0; i < 32; ++i) { // finishing one bit for all number each time.
            for (int j = 0; j < A.length; ++j) {
                bits[i] += A[j] >> i & 1;
                bits[i] %= 3;
            }
            result |= (bits[i] << i);
        }
        return result;
    }
}
