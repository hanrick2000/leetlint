package interview;

import java.util.*;

// http://www.meetqun.com/forum.php?mod=viewthread&tid=11298&extra=page%3D1%26filter%3Dtypeid%26typeid%3D52

/*-

 因式分解，
 输入12
 输出
 12*1
 6*2
 4*3
 3*2*2

 不许有重复，而且不能输出2*3*2，一定要3*2*2.。
 */
public class FactoringNum {
  public static void main(String[] args) {
    int x = 12;
    System.out.println(factor(x));
  }

  /*-
   * idea 1: 
   * phase 1: get the list of factors
   * phase 2: do subset II, then each result is the product of the subset + rest number 
   * 
   */

  /*****************************************************
   * util
   ******************************************************/

  public static List<Integer> factor(int x) {
    if (x < 2)
      return null;
    List<Integer> factors = new ArrayList<>();
    while ((x & 1) == 0) {
      System.out.print(2 + " ");
      factors.add(2);
      x = x >> 1;
    }

    int maxFactor = (int) Math.sqrt(x);
    for (int i = 3; i <= maxFactor; ++i) {
      while ((x % i) == 0) {
        System.out.print(i + " ");
        factors.add(x);
        x /= i;
      }
    }

    if (x > 2) { // there is at most one prime factor > sqrt(n)
      System.out.println(x);
      factors.add(x);
    }
    return factors;
  }

}
