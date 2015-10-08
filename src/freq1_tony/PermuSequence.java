package freq1_tony;

import java.util.ArrayList;
import java.util.List;

/**
 * N00t说: this is a follow-up question of "next permutation". 最简单的方法就是直接call next permutation k次.
 * 但这样明显是没有意义的. 就如同1 edit distance那题. 而且要注意k-th说明是1-base. 所以开头的地方k--. 
 * 
 * @author tzhang
 *
 */
public class PermuSequence {
  public static void main(String[] args) {
    int n = 4, k = 15;
    System.out.println(permSeq(n,k));
  }
  
  public static String permSeq(int n, int k) {
    if (n <= 0 )
      return "";
    int fact = 1;
    for (int i = 2; i < n; ++i) {
      fact *= i;
    }
    k--;
    StringBuilder str = new StringBuilder();
    List<Integer> remains = new ArrayList<>();
    for (int i = 0; i < n; ++i) {
      remains.add(i+1);
    }
    int round = n-1;
    while (round >= 0) {
      int index = k / fact;
      k = k%fact;
      str.append(remains.get(index));
      remains.remove(index);
      System.out.println(index);
      if (round > 0)  fact /= round;
      round--;
    }
    return str.toString();
  }
}
