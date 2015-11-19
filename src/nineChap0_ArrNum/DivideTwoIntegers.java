package nineChap0_ArrNum;

import Easy.DigitCounts;

/**
 * http://www.lintcode.com/en/problem/divide-two-integers/
 * Created at 6:07 PM on 11/18/15.
 */
public class DivideTwoIntegers {
  public static void main(String[] args) {
    DivideTwoIntegers dti = new DivideTwoIntegers();
    int ans = dti.divide(-2147483648, 1);
    System.out.println(ans);
  }

  public int divide9(int dividend, int divisor) {
    if (divisor == 0) {
      return dividend >= 0? Integer.MAX_VALUE : Integer.MIN_VALUE;
    }

    if (dividend == 0) {
      return 0;
    }

    if (dividend == Integer.MIN_VALUE && divisor == -1) {
      return Integer.MAX_VALUE;
    }

    boolean isNegative = (dividend < 0 && divisor > 0) ||
        (dividend > 0 && divisor < 0);

    long a = Math.abs((long)dividend);
    long b = Math.abs((long)divisor);
    int result = 0;
    while(a >= b){
      int shift = 0;
      while(a >= (b << shift)){
        shift++;
      }
      a -= b << (shift - 1);
      result += 1 << (shift - 1);
    }
    return isNegative? -result: result;
  }

  /**
   * @param dividend the dividend
   * @param divisor the divisor
   * @return the result
   */
  public int divide(int dividend, int divisor) {
    // Write your code here
    if (divisor == 0) {
      return Integer.MAX_VALUE;
    }
    if (dividend == 0) {
      return 0;
    }
    if (dividend == Integer.MAX_VALUE && divisor == -1) {
      return Integer.MAX_VALUE;
    }

    boolean isNeg = (dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0);
    long a = Math.abs((long)dividend);
    long b = Math.abs((long)divisor);
    int result = 0;
    while ( a >= b ) {
      int shift = 0;
      while ( a >= (b<<shift) ) {
        shift++;
      }
      a -= b<<(shift-1);
      result += 1 << (shift-1);
    }
    return isNeg ? -result : result;
  }
}
