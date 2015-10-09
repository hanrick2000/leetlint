package nineChap7_Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given a list of numbers with duplicate number in it. Find all unique permutations.
 * Learned a big lesson:
 * 1. list of Integer and list of int are different, because == treated different
 * 2. Don't use indexOf
 * 3. fully understand the trace of DFS recursion
 * 4. pre is similar to the substr in palidrome partition
 *
 * @author tzhang
 */
public class PermutationII {
    /**
     * @param nums: A list of integers.
     * @return: A list of unique permutations.
     */
    public List<List<Integer>> permuteUnique(List<Integer> nums) {
        // write your code here
//    ArrayList<ArrayList<Integer>> path = new ArrayList<ArrayList<Integer>>();
        List<List<Integer>> path = new ArrayList<List<Integer>>();
        if (nums == null || nums.size() == 0) {
            return path;
        }
        Collections.sort(nums);
        List<Integer> list = new ArrayList<>();
        int[] used = new int[nums.size()];
//    helperWRONG(path, list, used, 0, nums);
//    helperIdxOf(path, list, nums, used);
//    helperIndx(path, list, nums, used);
        helperPre(path, list, nums, used);

        path.forEach(System.out::println);
        return path;
    }

    private static void helperWRONG(ArrayList<ArrayList<Integer>> result, List<Integer> list,
                                    int[] used, int pos, List<Integer> nums) {
        // add list into result
        if (list.size() == nums.size()) {
            result.add(new ArrayList<>(list));
            return;
        }
        // DFS with prune
//    int[] not = new int[nums.size()];
        for (int i = pos; i < nums.size() && used[i] == 0; ++i) {
//      if (i != pos && i > 0 && nums.get(i) == nums.get(i-1)) {
//        continue;
//      }
            used[i] = 1;
            list.add(nums.get(i));
            System.out.println(list);
            helperWRONG(result, list, used, i + 1, nums);
            list.remove(list.size() - 1);
            used[i] = 0;
        }
    }

    /**
     * WRONG ! indexOf() is not truly index of list
     *
     * @param result
     * @param list
     * @param nums
     * @param used
     */
    private void helperIdxOfWrong(List<List<Integer>> result, List<Integer> list, List<Integer> nums, int[] used) {
        // only save leaf into result
        if (list.size() == nums.size()) {
            result.add(new ArrayList<Integer>(list));
            return;
        }
        //
        for (Integer num : nums) {
            if (used[nums.indexOf(num)] == 0) {
                list.add(num);
                used[nums.indexOf(num)] = 1;
                helperIdxOfWrong(result, list, nums, used);
                list.remove(list.size() - 1);
                used[nums.indexOf(num)] = 0;
            }
        }
    }

    /**
     * base on index, the 9chap's 1st solution
     *
     * @param path
     * @param list
     * @param nums
     * @param used
     */
    private static void helperIndx(List<List<Integer>> path, List<Integer> list,
                                   List<Integer> nums, int[] used) {
        // add list into result
        if (list.size() == nums.size()) {
            path.add(new ArrayList<>(list));
            return;
        }
        // DFS with prune
//    int[] not = new int[nums.size()];
        for (int i = 0; i < used.length; ++i) {
            if (used[i] == 1 ||
                    (i > 0 && nums.get(i) == nums.get(i - 1) && used[i - 1] == 0)) {
                continue;
            }
            used[i] = 1;
            list.add(nums.get(i));
            System.out.println(list);
            helperIndx(path, list, nums, used);
            list.remove(list.size() - 1);
            used[i] = 0;
        }
    }

    /**
     * based on pre, http://www.cnblogs.com/yuzhangcmu/p/4141085.html
     *
     * @param list
     * @param nums
     */
    private static void helperPre(List<List<Integer>> result, List<Integer> list,
                                  List<Integer> nums, int[] visited) {
        // add list into result
        if (list.size() == nums.size()) {
            result.add(new ArrayList<>(list));
            return;
        }
        // DFS and prune for repeated
        int pre = Integer.MIN_VALUE; // using pseudo-global pre to denote each loop's state!
        for (int i = 0; i < nums.size(); ++i) {
            int n = nums.get(i);
            if (visited[i] == 1 || pre == n) {
                continue;
            }

            pre = n;
            visited[i] = 1;
            list.add(nums.get(i));
            helperPre(result, list, nums, visited);
            list.remove(list.size() - 1);
            visited[i] = 0;
        }
    }

    public PermutationII() {
        List<Integer> nums = new ArrayList<>();
        nums.add(3);
        nums.add(8);
        nums.add(8);
//    Integer ba1 = new Integer(8);
//    Integer ba2 = new Integer(8);
//    nums.add(ba1);
//    nums.add(ba2);
//    System.out.println(nums.indexOf(ba1));
//    System.out.println(nums.indexOf(ba2));
        permuteUnique(nums);
    }

    public static void main(String[] args) {
        PermutationII pii = new PermutationII();
    }
}
