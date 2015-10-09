package interview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/10/8.
 * Meetqun's Uber/Linkedin High frequency problem
 */
public class FactoringInt {
    public static void main(String[] args) {
        List<List<Integer>> result = get(12);
        result.forEach(System.out::println); // Nice stream API
    }

    public static List<List<Integer>> get(int n) {
        List<Integer> cur = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        helper(n, n, cur, result);
        return result;
    }

    /**
     * This is a solution from Meetqun thread of the uber interview question, in fact, it's using the subset template, with accurate way to keep in order.
     *
     * @param target
     * @param start
     * @param cur
     * @param result
     */
    private static void helper(int target, int start, List<Integer> cur, List<List<Integer>> result) {
        if (target == 1) {
            List<Integer> newList = new ArrayList<>(cur);
            if (cur.size() == 1) newList.add(1);
            result.add(newList);
            return;
        }
        for (int i = start; i > 1; i--) {
            if (target >= i && target % i == 0) {
                cur.add(i);
                helper(target / i, i, cur, result);
                cur.remove(cur.size() - 1);
            }
        }
    }
}
