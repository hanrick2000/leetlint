package nineChap2_BinarySearch;

import java.util.ArrayList;
import java.util.List;

/**
 * http://www.lintcode.com/en/problem/k-closest-numbers-in-sorted-array/
 * Created at 5:35 PM on 11/29/15.
 */
public class KclosestNumbersInSortedArray {
  public static void main(String[] args) {
    int[] A = new int[]{1,10,15,25,35,45,50,59};  //{1,2,3};  //{1,4,6,8}; //
    int[] ans = new KclosestNumbersInSortedArray().kClosestNumbers(A,30,7);
    for (int a : ans) {
      System.out.print(a+" ");
    }
  }
  /**
   * http://www.geeksforgeeks.org/find-k-closest-elements-given-value/
   * O(logN + k) solution
   * @param A an integer array
   * @param target an integer
   * @param k a non-negative integer
   * @return an integer array
   */
  public int[] kClosestNumbers(int[] A, int target, int k) {
    // Write your code here
    List<Integer> ans = new ArrayList<>();
    if (A == null || A.length == 0) {
      return new int[]{};
    }
    int cross = findCross(A , target);
    System.out.println("cross point at: " + cross);
    int l = cross;
    int r = l+1;
    int count = 0;

    //if (A[l] == target)  l--;

    while (l >= 0 && r < A.length && count < k) {
      if (target - A[l] < A[r] - target) {
        ans.add(A[l--]);
      }
      else if (target - A[l] == A[r] - target){
        ans.add(A[l--]);
      }
      else {
        ans.add(A[r++]);
      }
      count++;
    }

    while (count < k && l >= 0) {
      ans.add(A[l--]);
      count++;
    }
    while (count < k && r < A.length) {
      ans.add(A[r++]);
      count++;
    }

    int[] arr = new int[ans.size()];
    for (int i = 0; i < ans.size(); ++i) {
      arr[i] = ans.get(i);
    }
    return arr;
  }

  private int findCross(int[] A, int target) {
    int lo = 0, hi = A.length-1;
    int cross = 0;
    while (lo+1 < hi) {
      int mid = lo + (hi-lo)/2;
      if (A[mid] == target) {
        cross = mid;
        return cross;
      }
      else if (A[mid] < target) {
        lo = mid;
      }
      else {
        hi = mid;
      }
    }

    int difflo = Math.abs(A[lo] - target);
    int diffhi = Math.abs(A[hi] - target);
    cross = difflo <= diffhi ? lo : hi;
    return cross;
  }
}
