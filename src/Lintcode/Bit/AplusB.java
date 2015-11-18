package Lintcode.Bit;

/**
 * http://www.lintcode.com/en/problem/a-b-problem/ Created at 11:13 PM on 11/16/15.
 */
public class AplusB {

  public static void main(String[] args) {
    AplusB apb = new AplusB();
    int ans = apb.aplusbGood(1, 2);
    System.out.println(ans);
  }

  /**
   * http://www.winterszhang.com/lintcode-ab-problem.html
   * @param a
   * @param b
   * @return
   */
  public int aplusb(int a, int b) {
    // write your code here, try to do it without arithmetic operators.
    if (b == 0) return a;
    int sum = a ^ b;
    int carry = (a & b) << 1;
    return aplusb(sum, carry);
  }

  // http://simpleandstupid.com/2014/12/21/a-b-problem-lintcode-%E8%A7%A3%E9%A2%98%E7%AC%94%E8%AE%B0/
  public int aplusbGood(int a, int b) {
    while (b != 0) {
      int carry = a & b;
      a = a ^ b;
      b = carry << 1;
    }
    return a;
  }
}
