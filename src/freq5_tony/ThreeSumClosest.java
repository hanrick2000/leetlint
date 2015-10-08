/**
 * 
 */
package freq5_tony;

import java.util.Arrays;

/**
 * @author tzhang
 * 
 */
public class ThreeSumClosest {

  /**
   * 
   */
  public int threeSumClosest(int[] input, int target) {
    Arrays.sort(input);
    int min = Integer.MAX_VALUE;
    int ret = 0;
    for (int i = 0; i < input.length; i++) {
      int start = i + 1;
      int tail = input.length - 1;

      while (start < tail) {
        int sum = input[i] + input[start] + input[tail];
        int dif = Math.abs(sum - target);
        // interesting way for cases.
        if (dif < min) {
          min = dif;
          ret = sum;
        }
        if (sum <= target) {
          start++;
        } else {
          tail--;
        }
      }
      //

    }
    return ret;
  }

  public static void main(String[] args) {
    int a[] = {-1, 2, -4, 1};
    System.out.println(new ThreeSumClosest().threeSumClosest(a, 1));
  }

}
