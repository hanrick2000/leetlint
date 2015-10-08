package nineChap2;

/**
 *
 * @author ttt
 *
 */
public class SearchInsert {
  public static void main(String[] args) {
    int[] num = {1, 3, 6, 7, 13};
    int res = searchInsert(num, 4);
    System.out.println(res);
  }

  /**
   * 直接套用模版: 结果出来只有2个数: lo和hi.
   *
   * @param A
   * @param target
   * @return
   */
  public static int searchInsertfirst(int[] A, int target) {
    if (A == null)
      return 0; // 如果A = [], target = 9. 应该return 0.
    int start = 0, end = A.length - 1;

    while (start + 1 < end) {
      int mid = start + (end - start) / 2;
      if (A[mid] == target) {
        return mid;
      } else if (A[mid] < target) {
        start = mid;
      } else {
        end = mid;
      }
    }

    // key, 其实画个图就能分析的出来.
    if (A[start] >= target) { // 考虑了相等和大于的情况
      return start;
    } else if (A[end] >= target) { // 考虑了相等和大于的情况.
      return end;
    } else { // 这个是???
      return end + 1;
    }
  }

  // 不好的写法, 因为没有真正理解lo,hi的意义
  public static int searchInsert(int[] A, int target) {
    if (A == null || A.length == 0)
      return -1;
    int lo = 0, hi = A.length - 1, mid;
    while (lo + 1 < hi) {
      mid = lo + (hi - lo) / 2;
      if (A[mid] == target)
        return mid;
      else if (A[mid] < target)
        lo = mid;
      else
        hi = mid;
    }
    if (A[lo] >= target)
      return lo;
    if (A[hi] <= target)
      return hi + 1;
    else
      return hi;
  }
}
