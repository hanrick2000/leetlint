package nineChap2_BinarySearch;

/**
 * 这题是还是search in rotated sort array. 但是就体现出对于lo, hi的理解. 以及他们在while结束后的值的理解.
 * 
 * @author tzhang
 *
 */
public class MinRotatedSortArr {
  public static void main(String[] args) {

  }

  /**
   * Find Minimum in Rotated Sorted Array I, 即没有duplicate
   * 
   * @param A
   * @return
   */
  public static int findmin(int[] A) {
    if (A == null || A.length == 0)
      return Integer.MAX_VALUE;
    int lo = 0, hi = A.length - 1;
    int mid;
    while (lo + 1 < hi) {
      mid = lo + (hi - lo) / 2;
      if (A[mid] < A[hi]) {
        lo = mid;
      } else
        hi = mid;
    }

    // while结束后, lo+1 会不会 >= hi? 不过至少只到, lo和hi最大间隔1.
    return Math.min(A[lo], A[hi]);
  }

  /**
   * Find Minimum in Rotated Sorted Array II, 即有duplicate. 只能是O(n). 因为没法用二分了. Total Runtime: 1626 ms
   * 实际上不应该这样做, 因为这样的时间永远是O(n). 其实还是可以照原来的二分法来做. 看看cmuYu的博客.
   * 
   * @param num
   * @return
   */
  public static int findmin2(int[] num) {
    int min = Integer.MAX_VALUE;
    if (num == null || num.length == 0)
      return min;
    for (int i = 0; i < num.length; ++i)
      min = Math.min(min, num[i]);
    return min;
  }

  /**
   * 怪不得这题叫做hard, 其实并不是难, 而是要注意有相等的情况. 我也是在做lint的时候的test case的时候才发现错误的. 反例就是:
   * [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,-1,1,1,1,1,1,1,1]. 这时候绝对不能二分, 有可能会分掉含有解的段.
   * 
   * @param num
   * @return
   */
  public static int findmin3(int[] num) {
    if (num == null || num.length == 0)
      return Integer.MAX_VALUE;
    int lo = 0, hi = num.length - 1;
    int mid;
    while (lo + 1 < hi) {
      mid = lo + (hi - lo) / 2;
      if (num[mid] >= num[lo])
        lo = mid;
      else if (num[mid] < num[lo]) {
        hi = mid;
      } else
        // 这里就是II比I难的点: 如果二分, 注意二分的目的: 是将解缩小到更小的范围, 但绝不是简单的每次二分完事儿!
        lo++; // http://www.cnblogs.com/yuzhangcmu/p/4049117.html. 必须这样写. 并不能像I那样合并起来!
    }
    return Math.min(num[lo], num[hi]);
  }
}
