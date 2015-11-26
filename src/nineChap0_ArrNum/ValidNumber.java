package nineChap0_ArrNum;

/**
 * http://www.lintcode.com/en/problem/valid-number/
 * Created at 5:02 PM on 11/25/15.
 */
public class ValidNumber {
  public static void main(String[] args) {
    String s = " 0.1";
    boolean ans = new ValidNumber().isNumber(s);
    System.out.println(ans);
  }
  /**
   * Automata's method
   *
   * http://www.cnblogs.com/yuzhangcmu/p/4060348.html
   * @param s the string that represents a number
   * @return whether the string is a valid number
   */
  public boolean isNumber(String s) {
    // Write your code here
    if (s == null) {
      return false;
    }
    s = s.trim();
    int len = s.length();

    boolean num = false;
    boolean exp = false;
    boolean dot = false;

    for (int i = 0; i < len; ++i) {
      char ch = s.charAt(i);
      if (ch == 'e') {
        if (!num || exp) {
          return false;
        }
        exp = true;
        //num = false; // ???
      }
      else if (ch >= '0' && ch <= '9') {
        num =  true;
      }
      else if (ch == '.') {
        if (exp || dot) {
          return false; // e0.2???
        }
        dot = true;
      }
      else if (ch == '+' || ch == '-') {
        if (i != 0 && s.charAt(i-1) != 'e') {
          return false;
        }
      }
      else {
        return false;
      }
    }

    return num;
  }
}
