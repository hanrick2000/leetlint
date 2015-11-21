package nineChap0_ArrNum;

/**
 * http://www.lintcode.com/en/problem/integer-to-roman/
 * Created at 2:08 PM on 11/20/15.
 */
public class IntegerToRoman {
  public static void main(String[] args) {
    int a = 1;
    IntegerToRoman itr = new IntegerToRoman();
    String res = itr.intToRoman(a);
    System.out.println(res);
  }
  /**
   * http://www.jiuzhang.com/solutions/integer-to-roman/
   * @param n The integer
   * @return Roman representation
   */
  public String intToRoman(int n) {
    // Write your code here
    if (n <= 0) {
      return "";
    }
    String[] sym = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX","V","IV","I"};
    int[] val = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    StringBuilder sb = new StringBuilder();
    int digit = 0;
    while (n > 0) {
      int times = n / val[digit];
      n -= val[digit] * times;
      for (; times > 0; times--) {
        sb.append(sym[digit]);
      }
      digit++;
    }
    return sb.toString();
  }
}
