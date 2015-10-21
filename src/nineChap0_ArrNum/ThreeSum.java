package nineChap0_ArrNum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * http://www.lintcode.com/en/problem/3-sum/
 * Find all the triplets that each three sum is 0.
 * Created by 1:37 PM on 10/20/2015.
 */
public class ThreeSum {
    public static void main(String[] args) {
        int[] test = new int[]{1,0,-1,-1,-1,-1,0,1,1,1}; //{-1, 0, 1, 2, -1, -4};
        ArrayList<ArrayList<Integer>> res = new ThreeSum().threeSumLint(test);
        for (ArrayList<Integer> list : res) {
            System.out.println(list);
        }
    }

    /**
     * Buggy implement!
     * {a,b,c}
     * So I use two pointer for each a to find proper b,c. Always check dup introduced by all index.
     *
     * @param numbers
     * @return
     */
    public ArrayList<ArrayList<Integer>> threeSum(int[] numbers) {
        // write your code here
        if (numbers == null || numbers.length <3) {
            return null;
        }
        Arrays.sort(numbers);
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        for (int i = 0; i < numbers.length - 2; ++i) {
            int j = i+1, k = numbers.length-1;
            while (j < k) {
                int gap = (numbers[j] + numbers[k] + numbers[i]) - 0;
                if (gap == 0) {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(numbers[i]);
                    list.add(numbers[j]);
                    list.add(numbers[k]);
                    result.add(list);

                    j++;
                    k--;

                    while (j<k && numbers[j-1] == numbers[j]) {
                        j++;
                    }
                    while (j<k && numbers[k] == numbers[k+1]) {
                        k--;
                    }
                }
                else if (gap < 0) {
                    k--;
                }
                else {
                    j++;
                }
            }
        }

        return result;
    }

    /**
     * My answer in lintcode envionment, got wrong for dup. Because I only checked dup for b,c. But a is unchecked.
     * @param numbers
     * @return
     */
    public ArrayList<ArrayList<Integer>> threeSumLint(int[] numbers) {
        // write your code here
        if (numbers == null || numbers.length < 3) {
            return null;
        }
        Arrays.sort(numbers);
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        int len = numbers.length;
        for (int a = 0; a < len-2; ++a) {
            while (a!= 0 && a < len-2 && numbers[a] == numbers[a-1]) {  // prevent dup introduced by a.
                a++;
            }
            int b = a+1;
            int c = len-1;
            while (b < c) {
                int sum = numbers[a]+numbers[b]+numbers[c];
//                ArrayList<Integer> list = new ArrayList<>();  // only have valid value when sum == 0, so put list in that scope
                if (sum == 0) {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(numbers[a]);
                    list.add(numbers[b]);
                    list.add(numbers[c]);
                    result.add(list);

                    // prune for the duplicates
                    b++;
                    c--;
                    while (b < c && numbers[b] == numbers[b-1]) {
                        b++;
                    }
//                    c--;  this will leave the b in previous step un-aligned with c--! must put together.
                    while (b < c && numbers[c] == numbers[c+1]) {
                        c--;
                    }
                }
                else if (sum < 0) {
                    b++;
                }
                else if (sum > 0) {
                    c--;
                }
            }
        }

        return result;
    }
}
