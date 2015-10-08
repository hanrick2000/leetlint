package nineChap2_BinarySearch;

/**
 * 觉得这里的CMUyu的图示最容易懂: http://www.cnblogs.com/yuzhangcmu/p/4202633.html
 * 
 * @author tzhang
 *
 */
public class FindPeak {
  public static void main(String[] args) {
    int[] nums = {1, 10, 9, 8, 7, 6, 5, 4}; // {1, 2, 1, 3, 4, 5, 7, 6};
    int ans = -1;
    ans = findPeak9Chap(nums);
    System.out.println(ans);
  }

  /**
   * O(n)做法: Total Runtime: 1811 ms
   * 
   * @param A
   * @return
   */
  public static int findPeakDummy(int[] A) {
    if (A == null || A.length < 3)
      return -1;
    for (int i = 1; i < A.length - 1; ++i) {
      if (A[i] > A[i - 1] && A[i] > A[i + 1])
        return i;
    }
    return -1;
  }


  /**
   * Total Runtime: 2424 ms
   * @param A
   * @return
   */
  public static int findPeak9Chap(int[] A) {
    if (A == null || A.length < 3) {
      return -1;
    }
    int lo = 0, hi = A.length - 1;
    int mid = -1;
    while (lo + 1 < hi) {
      mid = lo + (hi - lo) / 2;
      if (A[mid] > A[mid - 1] && A[mid] > A[mid + 1]) {
        return mid; // any
      } else if (A[mid] > A[mid + 1]) {
        hi = mid;
      } else if (A[mid] < A[mid + 1]) {
        lo = mid;
      } else {
        hi = mid; // 因为/ ... \
      }
    }
    return mid;
  }
}
