package nineChap2;

/**
 * 九章算法第二节课的2个重要题目之一: rotated sort array. 画图法. 二分法.
 *
 * @author ttt
 *
 */
public class SearchRotatedSort {
  public static int sesarchRotSortArr(int[] A, int target) {
    if (A == null || A.length == 0)
      return -1;
    int lo = 0;
    int hi = A.length - 1;
    int mid;
    while (lo + 1 < hi) {
      mid = lo + (hi - lo) / 2;
      if (A[mid] == target)
        return mid; // 因为这里如果有duplicate的话, 是没法用二分的, 例子: A[lo] = A[mid] = A[hi] = target
      //
      // if (A[mid] < target) { // why???
      // lo = mid;
      // }

      // 老师的绿线mid的情况.
      if (A[lo] < A[mid]) {
        if (A[lo] <= target && A[mid] <= target) {
          hi = mid; // 先想简单的情况. 即可以退化为一个sorted array问题.
        } else {
          lo = mid; // 其实这就是还是rotated sort array
        }
      }
      // 老师的红线mid的情况
      if (A[lo] >= A[mid]) {
        if (A[mid] <= target && target <= A[hi]) {
          lo = mid;
        } else
          hi = mid; // 即还是rotated sort array.
      }
    }

    if (A[lo] == target)
      return lo;
    if (A[hi] == target)
      return hi;
    return -1;
  }
}
