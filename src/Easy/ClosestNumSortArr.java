package Easy;

/**
 * http://www.lintcode.com/en/problem/closest-number-in-sorted-array/
 * Created at 9:34 PM on 11/13/15.
 */
public class ClosestNumSortArr {
  public static void main(String[] args) {
    int[] A = new int[]{1,2,8};
    int t = 4;
    ClosestNumSortArr cnsa = new ClosestNumSortArr();
    int ans = cnsa.closetNumber(A, t);
    System.out.println(ans);
  }

  public int closetNumber(int[] A, int target) {
    if (A == null || A.length == 0) {
      return -1;
    }
    int n = A.length;
    int l = 0, r = n-1;
    while (l+1 < r) {
      int mid = (l+r) /2;
      if (A[mid] == target) {
        return mid;
      }
      else if (A[mid] < target) {
        l = mid;
      }
      else {
        r = mid;
      }
    }
    int al = Math.abs(A[l] - target);
    int ar = Math.abs(A[r] - target);
    return al < ar ? l : r;
  }
}
