package nineChap2_BinarySearch;

public class FindBad {
  public static void main(String[] args) {
    int[] nums = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1};
    int ans = findGood(nums);
    System.out.println(ans);
  }

  /**
   * 其实就是二分找first. 直接上二分的模版
   * 
   * @param A
   * @return
   */
  public static int findBad(int[] A) {
    if (A == null || A.length == 0)
      return -1;
    int lo = 0, hi = A.length - 1;
    int mid = 0;
    while (lo + 1 < hi) {
      mid = lo + (hi - lo) / 2;
      if (A[mid] == 0) {
        lo = mid;
      } else if (A[mid] == 1) {
        hi = mid;
      }
    }
    if (A[lo] == 1) {
      return lo;
    }
    if (A[hi] == 1) {
      return hi;
    }
    return -1;
  }

  /**
   * 
   * @param A
   * @return
   */
  public static int findGood(int[] A) {
    if (A == null || A.length == 0)
      return -1;
    int lo = 0, hi = A.length - 1;
    int mid = 0;
    while (lo + 1 < hi) {
      mid = lo + (hi - lo) / 2;
      if (A[mid] == 0) {
        lo = mid;
      }
      if (A[mid] == 1) {
        hi = mid;
      }
    }
    if (A[hi] == 0) {
      return hi;
    }
    if (A[lo] == 0) {
      return lo;
    }
    System.out.println(lo + " " + hi);
    return -1;
  }
}
