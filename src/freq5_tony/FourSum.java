/**
 * 
 */
package freq5_tony;

import java.util.*;

/**
 * @author tzhang
 * 
 */
public class FourSum {

  /**
   * Solution: continue to use start/tail pointer, since only 2 pointers so there will be 2 for
   * loops: i and j.
   */
  public ArrayList<ArrayList<Integer>> fourSum(int[] input, int target) {
    ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
    HashSet<ArrayList<Integer>> hashSet = new HashSet<>();
    Arrays.sort(input);
    for (int i = 0; i < input.length - 3; i++) {
      for (int j = i + 1; j < input.length - 2; j++) {
        int start = j + 1;
        int tail = input.length - 1;
        while (start < tail) {
          int sum = input[i] + input[j] + input[start] + input[tail];
          // case 1:
          if (sum == target) {
            ArrayList<Integer> tmp = new ArrayList<>();
            tmp.add(input[i]);
            tmp.add(input[j]);
            tmp.add(input[start]);
            tmp.add(input[tail]);
            if (!hashSet.contains(tmp)) {
              hashSet.add(tmp);
              ret.add(tmp);
            }
            // ttt: forgot to change start, tail. then HALT
            start++;
            tail--;
          }
          // case 2:
          if (sum < target) {
            start++;
          }
          if (sum > target) {
            tail--;
          }
        }
      }
    }
    return ret;
  }


  public static void main(String[] args) {
    int a[] = {1, 0, -1, 0, -2, 2};
    System.out.println(new FourSum().fourSum(a, 0));
  }
}
