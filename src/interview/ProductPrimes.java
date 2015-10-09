package interview;

// http://www.fgdsb.com/2015/01/17/factors-of-product-of-distinct-primes/

import java.util.ArrayList;
import java.util.List;

/*-
 Print all factors of the product of a given list of distinct primes.
 Example:
 Input: 2 3 7
 Output: 1 2 3 6 7 14 21 42
 */
public class ProductPrimes {
    public static void main(String[] args) {
        int[] primes = new int[]{2, 3, 7};
        productFGDSB(primes, 0, 1);
        System.out.println();
        testSubset(primes);
//        testWrong(primes);
    }

    public static void testSubset(int[] nums) {
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        productSubset(result, path, nums, 0);
        for (List<Integer> i : result) {
            System.out.print(i + " ");
        }
    }

    public static void testWrong(int[] nums) {
        List<Integer> path = new ArrayList<>();
        productDFS(path, nums, 0, 1);
        for (Integer i : path) {
            System.out.print(i + " ");
        }
    }

    /**
     * The best way to solve this EASY dfs problem, a 5 min Google interview problem
     *
     * @param primes
     * @param id
     * @param cur
     */
    public static void productFGDSB(int[] primes, int id, int cur) {
        if (id == primes.length) {
            System.out.print(cur + " ");
            return;
        }
        productFGDSB(primes, id + 1, cur);
        productFGDSB(primes, id + 1, cur * primes[id]);
    }

    /**
     * Simply review subset template
     *
     * @param result
     * @param path
     * @param primes
     * @param pos
     */
    private static void productSubset(List<List<Integer>> result, List<Integer> path, int[] primes, int pos) {
        result.add(new ArrayList<>(path));
        if (pos == primes.length) {
            return;
        }

        for (int i = pos; i < primes.length; ++i) {
            path.add(primes[i]);
            productSubset(result, path, primes, i + 1);
            path.remove(path.size() - 1);
        }
    }

    /**
     * Finally got it right, I'm still using subset template to do this problem, note the way I use
     * cur as the value in circle as the DFS diagram
     *
     * @param path
     * @param nums
     * @param pos
     * @param cur
     */
    public static void productDFS(List<Integer> path, int[] nums, int pos, int cur) {
        // add cur into path
        path.add(cur);
        if (pos == nums.length) return;

        // DFS with prune
        for (int i = pos; i < nums.length; ++i) {
//            cur = cur * nums[id];
            /*-
             * this recursion call combines 2 knowledge tips:
             * 1. using pos to init i and update i (not pos in recursion call
             * 2. other than path (result var to save all solution during traverse), other var
             * need to be backtracking(Am I using it correctly?) by path.add(num), path.remove().
              * Or it can be done by simple
             */
            productDFS(path, nums, i + 1, cur * nums[i]);
            // of
//            cur = cur
        }
    }


}
