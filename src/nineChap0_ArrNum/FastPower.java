package nineChap0_ArrNum;

/**
 * http://www.lintcode.com/en/problem/fast-power/
 * Created at 5:12 PM on 11/18/15.
 */
public class FastPower {
  public static void main(String[] args) {
    FastPower fp = new FastPower();
    int ans = fp.fastPower(2, 3, 31);
    System.out.println(ans);
  }
  /**
   * @param a, b, n: 32bit integers
   * @return: An integer
   */
  public int fastPower(int a, int b, int n) {
    // write your code here
    if (a == 0) {
      return 0;
    }

    if (n == 0) {
      return 1 % b;
    }
    if (n == 1) {
      return a % b;
    }

    long ret = 0;

    ret = fastPower(a, b, n / 2);
    ret *= ret;
    ret = ret % b;  // prevent over flow
    if (n % 2 == 1) {
      ret *= fastPower(a, b, 1);
    }

    ret = ret % b;

    return (int) ret;
  }
}
