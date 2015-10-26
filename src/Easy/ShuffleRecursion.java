package Easy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created this class in Easy at 12:07 AM, 10/26/2015.
 */
public class ShuffleRecursion {
  public static void main(String[] args) {
    Integer[] data = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8};
    List<Integer> list = Arrays.asList(data);
    testRecur(list, 0);
  }

  public static void testLoop() {
    Integer[] data = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8};
    List<Integer> list = Arrays.asList(data);

    for (int t = 0; t < 3; ++t) {
      Collections.shuffle(list);
      for (int i : list) {
        System.out.print(i + " ");
      }
      System.out.println();
    }
  }

  public static void testRecur(List<Integer> list, int t) {
    if (t == 3) {
      return;
    }
    Collections.shuffle(list);
    for (int i : list) {
      System.out.print(i + " ");
    }
    System.out.println();

    testRecur(list, t+1);
  }
}
