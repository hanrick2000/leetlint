package learnJava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudyRecursion {
    public static void main(String[] args) {
        int[] input = new int[]{3, 1, 3};
//        permu1(input);
        permu2(input);
    }

    private static void permu1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        permu1helper(result, list, nums);
        result.forEach(System.out::println);
    }

    private static void permu1helper(List<List<Integer>> result, List<Integer> list, int[] nums) {
        // add list into result
        if (list.size() == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }
        // DFS with prune
        for (int num : nums) {
            if (!list.contains(num)) {
                list.add(num);
                permu1helper(result, list, nums);
                list.remove(list.size() - 1);
            }
        }
    }

    private static void permu2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        int[] visited = new int[nums.length];
        permu2helper(result, list, nums, visited);
        result.forEach(System.out::println);
    }

    private static void permu2helper(List<List<Integer>> result, List<Integer> list, int[] nums, int[] visited) {
        if (list.size() == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }

        // DFS
        for (int i = 0; i < nums.length; ++i) { // i = pos
            if (visited[i] == 1) {
                continue;
            }
            if (i != 0 && nums[i] == nums[i-1] && visited[i-1] == 0) { // i != pos
                continue;
            }
//            visited[i] = 1;
            list.add(nums[i]);
//            System.out.println(list);
            permu2helper(result, list, nums, setVisited(visited, i, 1));
            list.remove(list.size()-1);
//            visited[i] = 0;
        }
    }

    /**
     * By doing this, I can remove the set/reset before/after recursion call, NOTE: the reason is that I don't touch original array.
     * @param v
     * @param i
     * @param value
     * @return
     */
    private static int[] setVisited(int[] v, int i, int value) {
        int[] tmp = Arrays.copyOf(v, v.length); // won't work if I simply set it to 1!!!
        tmp[i] = value;
        return tmp;
    }
}
