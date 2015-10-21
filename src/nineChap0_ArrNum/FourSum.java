package nineChap0_ArrNum;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * http://www.lintcode.com/en/problem/4-sum/#
 * Question: should I do it using for(a) + 3sum? Or for(a,b) + 2Sum?
 * Created by 4:44 PM on 10/20/2015.
 */
public class FourSum {
    public static void main(String[] args) {
        int[] test = new int[]{1,0,-1,-1,-1,-1,0,1,1,1,2}; //{-1,1,0}; //{1,0,-1,0,-2,2};
        ArrayList<ArrayList<Integer>> result = new FourSum().fourSum(test, 2);
        result.forEach(System.out::println);
    }

    /**
     * 9 chap's idea.
     *
     * @param numbers
     * @param target
     * @return
     */
    public ArrayList<ArrayList<Integer>> fourSum(int[] numbers, int target) {
        //write your code here
//        if (numbers == null || numbers.length < 4) {
//            return null;
//        }
        int len = numbers.length;
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        Arrays.sort(numbers);

        for (int a = 0; a < len - 3; a++) {
            if (a != 0 && numbers[a] == numbers[a-1]) {
//                a++; if use a++ and while loop, then need to check upper bound!
                continue; // continue is easy.
            }
            for (int b = a+1; b < len - 2; b++) {
                if (b != a+1 && numbers[b] == numbers[b-1]) {  // this is to remove dup of b, so b should be diff
                                                                    // from a+1, ow, ignore num[a] = 1, nums[a+1] = 1.
//                    b++;
                    continue;
                }
                int c = b+1;
                int d = len -1;

                while (c < d) {
                    int sum = numbers[a] + numbers[b] + numbers[c] + numbers[d];
                    if (sum == target) {
                        ArrayList<Integer> unit = new ArrayList<>();
                        unit.add(numbers[a]);
                        unit.add(numbers[b]);
                        unit.add(numbers[c]);
                        unit.add(numbers[d]);
                        result.add(unit);

                        c++;
                        d--;
                        while (c < d && numbers[c] == numbers[c - 1]) {
                            c++;
                        }
                        while (c < d && numbers[d] == numbers[d + 1]) {
                            d--;
                        }
                    } else if (sum < target) {
                        c++;
                    } else {
                        d--;
                    }
                }
            }
        }

        return result;
    }
}
