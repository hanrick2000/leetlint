package JiuChap6_FollowUpA;

/**
 * http://www.lintcode.com/en/problem/find-peak-element/
 * Created at 11:18 PM on 11/13/15.
 */
public class FindPeakI {
  public static void main(String[] args) {
    int[] nums = {1, 10, 9, 8, 7, 6, 5, 4}; // {1, 2, 1, 3, 4, 5, 7, 6};
    int ans = -1;
    ans = new FindPeakI().findPeak(nums);
    System.out.println(ans);
  }

  /**
   * @param A: An integers array.
   * @return: return any of peek positions.
   */
  public int findPeak(int[] A) {
    // write your code here
    if (A == null || A.length == 0) {
      return -1;
    }
    int n = A.length;
    int l = 0, r = n-1;
    while (l + 1 < r) {
      int mid = (l+r)/2;
      //if (A[l] < A[mid] && A[mid] > A[r]) {
      //  return mid;
      //}
      //else if
      if (A[mid] < A[mid-1]) {
        r = mid;
      }
      else if (A[mid] > A[mid+1]) {
        l = mid;
      }
      else {
        l = mid;
        r = mid;
        break;
      }
    }
    if (A[l] < A[r]) {
      return r;
    }
    if (A[l] > A[r]) {
      return l;
    }
    return -1;
  }
}
