package nineChap0_ArrNum;

import java.util.HashMap;
import java.util.Map;

/**
 * http://www.lintcode.com/en/problem/roman-to-integer/
 * Created at 9:30 AM on 11/17/15.
 */
public class Roman2Int {
  public static void main(String[] args) {
    String a = "DCXXI";
    int res = new Roman2Int().romanToInt(a);
    System.out.println(res);
  }
  /**
   * @param s Roman representation
   * @return an integer
   */
  public int romanToInt(String s) {
    // Write your code here
    if (s == null || s.length() == 0) {
      return 0;
    }
    //int ans = 0;
    Map<Character, Integer> T = new HashMap<>();
    T.put('I', 1);
    T.put('V', 5);
    T.put('X', 10);
    T.put('L', 50);
    T.put('C', 100);
    T.put('D', 500);
    T.put('M', 1000);
    int leng = s.length();
    int result = T.get(s.charAt(leng-1));
    for (int i = leng-2; i >= 0; i--) {
      if (T.get(s.charAt(i+1)) <= T.get(s.charAt(i))) {
        result += T.get(s.charAt(i));
      }
      else {
        result -= T.get(s.charAt(i));
      }
    }
    return result;
  }
}
