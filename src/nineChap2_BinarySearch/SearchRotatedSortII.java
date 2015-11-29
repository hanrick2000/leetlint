package nineChap2_BinarySearch;

/**
 * http://www.cnblogs.com/yuzhangcmu/p/4197788.html
 * Created at 9:43 PM on 11/28/15.
 */
public class SearchRotatedSortII {
  public static void main(String[] args) {
    int[] A = new int[]{2,2,2,2,3,4,2,2,2,2,2,2,2,2,2,2};
    boolean ans = new SearchRotatedSortII().search(A, 3);
    System.out.println(ans);
  }
  /**
   * http://www.cnblogs.com/yuzhangcmu/p/4197788.html
   * param A : an integer ratated sorted array and duplicates are allowed
   * param target :  an integer to be search
   * return : a boolean
   */
  public boolean search(int[] A, int target) {
    // write your code here
    if (A == null || A.length == 0) {
      return false;
    }
    int lo = 0, hi = A.length-1;
    while (lo+1 < hi) {
      int mid = lo + (hi-lo)/2;
      if (A[mid] == target) {
        return true;
      }
      if (A[lo] < A[mid]) {
        if (A[lo] > target || A[mid] < target) {
          lo = mid+1;
        }
        else {
          hi = mid-1;
        }
      }
      else if (A[lo] > A[mid]) {
        if (A[mid] > target || A[hi] < target) {
          hi = mid-1;
        }
        else {
          lo = mid+1;
        }
      }
      else {
        lo++;
      }
    }

    if (A[lo] == target || A[hi] == target) {
      return true;
    }
    return false;
  }
}
