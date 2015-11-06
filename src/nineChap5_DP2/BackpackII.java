package nineChap5_DP2;

/**
 * http://www.lintcode.com/en/problem/backpack-ii/
 * Given n items with size Ai and val Vi, and a backpack with size m. What's the maximum val can you put into the backpack?
 * <p>
 * eg:
 * Given 4 items with size [2, 3, 5, 7] and val [1, 5, 2, 4], and a backpack with size 10. The maximum val is 9.
 */
public class BackpackII {
    public static void main(String[] args) {
        int ans = 0;
        int[] weight = {2, 3, 5, 7};
        int[] val = {1, 5, 2, 4};
        int size = 13;
        ans = backpackII1(size, weight, val);
        System.out.println("I use simple DP: " + ans);
    }

    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A & V: Given n items with size A[i] and val V[i]
     * @return: The maximum val
     */
    public static int backpackII1(int m, int[] A, int[] V) {
        if (A == null || A.length == 0 || V == null || A.length != V.length) {
            return -1;
        }
        if (m < A[0]) {
            return -1;
        }

        int aLen = A.length;

        /**
         * F[i][m] denotes the max profit by picking from first i items ands weights m.
         */
        int[][] F = new int[aLen + 1][m + 1];

        for (int i = 0; i < aLen + 1; ++i) {
            F[i][0] = 0;
        }
        for (int j = 1; j < m + 1; ++j) { // notice the range starts from 1! NOTICE: no overwritten
            F[0][j] = Integer.MIN_VALUE; // why: to find the max val, so init to -infinity.
        }

        int ans = -1; // global optimal
        for (int i = 1; i < aLen + 1; ++i) {
            for (int j = 1; j < m + 1; ++j) {
                if (j < A[i - 1]) {
                    continue;
                }
                F[i][j] = Math.max(F[i - 1][j], F[i - 1][j - A[i - 1]] + V[i - 1]);
                ans = Math.max(ans, F[i][j]);
            }
        }

        return ans;
    }

    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A  & V: Given n items with size A[i] and val V[i]
     * @return: The maximum val
     */
    public static int backPackII2(int m, int[] A, int V[]) {
        // write your code here
        int len = A.length;
        int[][] F = new int[len + 1][m + 1];
        //TODO

        return 0; // ttt
    }
}
