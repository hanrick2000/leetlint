package nineChap0_ArrNum;

import java.util.Arrays;

/**
 * http://www.lintcode.com/en/problem/3-sum-closest/
 * Created by 2:18 PM on 10/20/2015.
 */
public class ThreeSumClosest {
    public static void main(String[] args) {
        int[] test = new int[]{2,7,11,15}; //{1,0,-1,-1,-1,-1,0,1,1,1,2}; //{-1,2,1,-4}; //{-2,-3,5,-1,-4,5,-11,7,1,2,3,4,-7,-1,-2,-3,-4,-5}; //
        int res = new ThreeSumClosest().threeSumClosest(test, 3);
        System.out.println(res);
    }

    /**
     * 9 chap
     * The difference of threeSumClosest to threesum is that it always accepting smaller gap and update index
     * And the output of former is: it's an int. The latter is getting all the results.
     *
     * @param numbers
     * @param target
     * @return
     */
    public int threeSumClosest(int[] numbers ,int target) {
        // write your code here
        if (numbers == null || numbers.length < 3) {
            return -1;
        }

        Arrays.sort(numbers);
        int rst = -1;
        int minGap = Integer.MAX_VALUE;
        for (int a = 0; a < numbers.length -2; ++a) {
            int b = a+1;
            int c = numbers.length-1;
            while (b < c) {
                int gap = (numbers[a] + numbers[b] + numbers[c]) - target;

                if (Math.abs(gap) < minGap) {
                    minGap = Math.abs(gap);
                    rst = gap + target;
                }
                if (gap < 0) {
                    b++;
                }
                else if (gap > 0) {
                    c--;
                }
                else {
                    return rst;
                }
            }
        }
        return rst;
    }
}
