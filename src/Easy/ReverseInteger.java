package Easy;

/**
 * Created by 2:30 AM on 10/15/2015.
 */
public class ReverseInteger {
    public static void main(String[] args) {
        System.out.println(reverseInteger(1534236469));
    }

    public static int reverseInteger(int n) {
        // Write your code here
        boolean sign = n < 0;
        n = Math.abs(n);
        long rev = 0;
        while (n != 0) {
            rev = rev  * 10 + n % 10;
            n = n / 10;
        }

        if (rev > Integer.MAX_VALUE || rev < Integer.MIN_VALUE) {
            return 0;
        }

        return sign ? (int)-rev : (int)rev;
    }

    public static int reverse9ch(int x) {
        int rst = 0;

        while(x != 0) {
            int next_rst = rst * 10 + x % 10;
            x = x / 10;
            if(next_rst/10 != rst) {
                rst  = 0;
                break;
            }
            rst = next_rst;
        }
        return rst;
    }
}
