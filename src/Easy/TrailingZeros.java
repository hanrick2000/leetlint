package Easy;

/**
 * Created by 1:28 PM on 10/14/2015.
 */
public class TrailingZeros {
    public static void main(String[] args) {
        System.out.println(new TrailingZeros().trailingZeros(11));
    }

    /**
     * Learn the algs from dada:
     * http://www.danielbit.com/blog/puzzle/leetcode/leetcode-factorial-trailing-zeroes
     *
     * @param n
     * @return
     */
    public long trailingZeros(long n) {
        if (n < 0)  return -1;
        long cnt = 0;
        for (long i = 5; n/i >= 1; i*=5) {
            cnt += n/i;
        }
        return cnt;
    }

    /**
     * O(1)??? Wrong way, see 25!
     *
     * @param n
     * @return An integer, denote the number of trailing zeros in n!
     */
    public long trailingZerosWrong(long n) {
        // write your code here
        long cnt2, cnt5, cnt10;
        cnt2 = n / 2;
        cnt5 = n / 5;
        cnt10 = n / 10;
        return Math.min(cnt2, cnt5) - cnt10;
    }

}
