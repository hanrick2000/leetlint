package nineChap7_Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SubsetI {
    public ArrayList<ArrayList<Integer>> subsets(ArrayList<Integer> S) {
        // write your code here
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (S == null || S.size() == 0) {
            return result;
        }
        Collections.sort(S);
        ArrayList<Integer> list = new ArrayList<>();
        subset1Helper(result, list, S, 0); // subsets I
        return result;
    }

    /*-
     * This is helper function to subset I: distinct nums input
     * Also I had 2 mistakes: 1. I added list.contains, learned from permutation
     *                        2. the goal is to not choose the value has been chosen, so i+1, not pos+1
     *
     * @param result
     * @param list
     * @param S
     * @param pos
     */
    private static void subset1Helper(ArrayList<ArrayList<Integer>> result,
                                      ArrayList<Integer> list, ArrayList<Integer> S, int pos) {

        // endpoint
        result.add(new ArrayList<>(list));

        // brute force traverse
        for (int i = pos; i < S.size(); i++) {
            // if (!list.contains(S.get(i))) {
            list.add(S.get(i));
            // subsetHelper(result, list, S, pos + 1); // always wrong here! NOT pos+1!
            subset1Helper(result, list, S, i + 1); // fully realize the meaning of i/pos.
            list.remove(list.size() - 1);
            // }
        }

    }

    private static ArrayList<Integer> testSubsetI() {
        ArrayList<Integer> nums = new ArrayList<>();
        nums.add(1);
        nums.add(2);
        nums.add(3);

        return nums;
    }

    public SubsetI() {
        ArrayList<Integer> nums = testSubsetI();
        ArrayList<ArrayList<Integer>> result = subsets(nums);
        for (ArrayList<Integer> list : result) {
            for (Integer num : list) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        new SubsetI();
    }
}
