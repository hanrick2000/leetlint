package nineChap0_ArrNum;

import java.util.*;

/**
 * Created at 3:00 PM on 11/24/15.
 */
public class PermutationSequence {
  public static void main(String[] args) {
    String ans = new PermutationSequence().getPermutation(3,4);
    System.out.println(ans);
  }
  /**
   * http://blog.csdn.net/linhuanmars/article/details/22028697
   * @param n: n
   * @param k: the kth permutation
   * @return: return the k-th permutation
   */
  public String getPermutation(int n, int k) {
    if (n <= 0) {
      return "";
    }
    k--;
    StringBuilder sb = new StringBuilder();
    int factorial = 1;
    List<Integer> nums = new ArrayList<>();
    for (int i = 2; i < n; ++i) {
      factorial *= i;
    }
    for (int i = 1; i < 9; ++i) {
      nums.add(i);
    }
    int round = n-1;
    while (round >= 0) {
      int index = k / factorial;
      k %= factorial;
      sb.append(nums.get(index));
      nums.remove(index);
      if (round > 0) {
        factorial /= round;
      }
      round--;
    }
    return sb.toString();
  }
}
