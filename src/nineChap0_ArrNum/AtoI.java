package nineChap0_ArrNum;

/**
 * http://www.lintcode.com/en/problem/string-to-integeratoi/
 * Created at 4:55 PM on 11/25/15.
 */
public class AtoI {
  /**
   * @param str: A string
   * @return An integer
   */
  public int atoi(String str) {
    // write your code here
    if (str == null) {
      return 0;
    }
    str = str.trim();
    if (str.length() == 0) {
      return 0;
    }
    int sign = 1;
    int index = 0;

    if (str.charAt(0) == '+') {
      index++;
    }
    else if (str.charAt(0) == '-') {
      index++;
      sign = -1;
    }

    long num = 0;
    for (; index < str.length(); ++index) {
      char ch = str.charAt(index);
      if (ch < '0' || ch > '9') {
        break;
      }
      num = num * 10 + ch - '0';
      if (num > Integer.MAX_VALUE) {
        break;
      }
    }
    if (num * sign >= Integer.MAX_VALUE) {
      return Integer.MAX_VALUE;
    }
    if (num * sign <= Integer.MIN_VALUE) {
      return Integer.MIN_VALUE;
    }
    return (int) num * sign;
  }
}