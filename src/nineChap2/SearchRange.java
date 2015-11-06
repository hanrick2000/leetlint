package nineChap2;

/**
 * Given a sorted array of integers, find the starting and ending position of a given target val.
 *
 * @author ttt
 *
 */
public class SearchRange {
  public static void main(String[] args) {
    int[] num = new int[] {9,9,9}; //new int[]{5, 7, 7, 8, 8, 10};
    int[] res = searchRange(num, 9);
    System.out.println(res[0] + " " + res[1]);
  }

  /**
   * http://blog.csdn.net/linhuanmars/article/details/20593391
   * @param A
   * @param target
   * @return
   */
  public static int[] searchRange(int[] A, int target) {
    int[] res = new int[]{-1,-1};
    if (A == null || A.length == 0)
      return res;
    // 先用binary search找左边界.
    int len = A.length;
    int lo = 0, hi = len-1;
    int mid;
    while (lo <= hi) {
      mid = lo + (hi-lo)/2;
      if (A[mid] < target)
        lo = mid+1;
      else if (A[mid] > target)
        hi = mid-1;
      else
        hi = mid-1;  // key : 相等的时候还向左二分查找
    }
    res[0] = lo;
    // 再用binary search找右边界
    lo = 0; hi = len-1;
    while (lo <= hi) {
      mid = lo + (hi-lo)/2;
      if (A[mid] < target) {
        lo = mid+1;
      }
      else if (A[mid] > target) {
        hi = mid-1;
      }
      else {
        lo = mid+1; // key : 找到的时候继续向右二分查找.
      }
    }
    res[1] = hi;

    if (res[0] <= res[1])
      return res;
    else
      return new int[]{-1,-1};
  }
}
