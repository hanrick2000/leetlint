/**
 * 
 */
package freq5_tony;

import java.util.*; // to use HashMap.

/**
 * @author tzhang given an unsorted array with unique elements, find 2 to sum to a given target and
 *         return the index. Assume only 1 solution for each array.
 */
public class TwoSum {

  /**
   * solution 1: Naive O(N*N)
   * http://www.programcreek.com/2012/12/leetcode-solution-of-two-sum-in-java/
   */
  public static int[] twoSum1(int[] input, int target) {
    int[] ret = new int[2]; // because this is 2sum -_-|||
    for (int i = 0; i < input.length; i++) {
      for (int j = i + 1; j < input.length; j++) {
        if (input[i] + input[j] == target) {
          // How to do??? always confused in 2 or 2+ loops.
          ret[0] = i + 1; // since not 0-based.
          ret[1] = j + 1; // v.s. (vide supra) also, in <Algo in C> mentioned 0based
                          // is better in looping.
          break; // assume only 1 pair per array input.
        }
      }
    }
    return ret;
  }

  /**
   * solution 2: use head/tail tail to go towards to middle and get ride of big tail! This used
   * built-in sort which is quick sort for O(nlgn)
   */

  // check frequency_5.TwoSum.java.
  // or jiuzhang: http://www.ninechapter.com/solutions/two-sum/

  /**
   * solution 3: use hashtable (implicit quick sort) and O(1) for put/get. In CS108, everything in
   * Java is pointer.
   *
   * @return int ret[] should has only 2 elements since unique pair per input array.
   */
  public static int[] twoSum3(int[] input, int target) {
    int[] ret = new int[2];
    HashMap<Integer, Integer> tony = new HashMap<Integer, Integer>();
    // for this map, we use input val as key, and index as Mapped val.
    for (int i = 0; i < input.length; i++) {
      if (tony.containsKey(input[i])) {
        int index = tony.get(input[i]);
        ret[0] = index + 1;
        ret[1] = i + 1;
        break;
      } else
        tony.put(target - input[i], i);
    }
    return ret;
  }

  /**
   * simply check why threeSum output is null arraylist.
   */
  public static ArrayList<Integer> twoSum4(int[] input, int target) {
    // int[] ret = new int[2];
    ArrayList<Integer> ret = new ArrayList<Integer>();
    HashMap<Integer, Integer> tony = new HashMap<Integer, Integer>();
    // for this map, we use input val as key, and index as Mapped val.
    for (int i = 0; i < input.length; i++) {
      if (tony.containsKey(input[i])) {
        int index = tony.get(input[i]);
        ret.add(index + 1);
        ret.add(i + 1);
        break;
      } else
        tony.put(target - input[i], i);
    }
    return ret;
  }

  /**
   * main method for simple test.
   * 
   * @param args
   */
  public static void main(String[] args) {
    int[] a = {2, 7, 8, 11};
    int out = 9;
    // for (int i: twoSum1(a,out))
    // System.out.println(i);
    System.out.println(twoSum4(a, out));
    for (int i : twoSum3(a, out))
      System.out.println(i);
  }

}
