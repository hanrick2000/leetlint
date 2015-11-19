package nineChap0_ArrNum;

import java.util.ArrayList;

/**
 * Created at 8:37 PM on 11/17/15.
 */
public class GrayCode {
  public static void main(String[] args) {
    GrayCode gc = new GrayCode();
    int n = 3;
    ArrayList<Integer> ans = gc.grayCode(n);
    System.out.println(ans);
  }
  /**
   * http://blog.csdn.net/worldwindjp/article/details/21536103
   * @param n a number
   * @return Gray code
   */
  public ArrayList<Integer> grayCode(int n) {
    // Write your code here
    if (n == 1) {
      ArrayList<Integer> result = new ArrayList<>();
      result.add(0);
      result.add(1);
      return result;
    }

    ArrayList<Integer> LSB = grayCode(n-1);
    int addMSB = 1 << (n-1);
    ArrayList<Integer> result = new ArrayList<>(LSB);
    for (int i = LSB.size()-1; i >= 0; i--) {
        result.add(addMSB+result.get(i));
    }
    return result;
  }
}
