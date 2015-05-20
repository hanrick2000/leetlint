package misc;

import java.util.*;

/**
 * From Algs4, Chap 1.1. A simple Binary search to find the position of a key from a sorted int array
 * Application: whitelist filtering
 * @author tzhang
 *
 */
public class BinarySearch {
  private BinarySearch(){
    //
  }
  // super simple while loop to search for a key
  public static int rank(int key, int[] a) {
    int lo = 0;
    int hi = a.length-1;
    while (lo <= hi) {
      int mid = lo + (hi-lo)/2;
      if (a[mid] > key)  hi = mid-1; 
      else if (a[mid] < key)  lo = mid+1;
      else return mid;
    }
    return -1;
  }
  
  /**
   * http://stackoverflow.com/questions/363681/generating-random-integers-in-a-range-with-java
   * http://stackoverflow.com/questions/9572795/convert-list-to-array-in-java
   * @return
   */
  private static int[] randInt() {
    Random rand = new Random();
    int max = 20, min = 0;
    List<Integer> res = new ArrayList<>();
    for (int i = 0; i < 10; ++i) {
      res.add(rand.nextInt(max-min + 1) + min);
    }
    int[] result = new int[res.size()];
    for (int i = 0; i < 10; ++i) {
      result[i] = res.get(i);
      System.out.print(res.get(i) + ",");
    }
    return result;
    
  }
  public static void main(String[] args) {
//    int[] whitelists = randInt();
    int[] arr = new int[]{12,3,7,8,2,10,14,7,9,13};
    Arrays.sort(arr);
    System.err.println(rank(13, arr));
  }
}
