package nineChap2;
/**
 * 严格使用九章的模版. 注意4点.
 * @author ttt
 *
 */
public class BinarySearch {
  public static void main(String[] args) {
    int[] num = {1, 3, 6, 6, 6, 7, 13};
    int res = binarySearch(num, 6);
    System.out.println(res);
  }

  public static int binarySearch(int[] A, int target) {
    if (A.length == 0)
      return -1;
    int lo = 0;
    int hi = A.length-1;
    int mid;

    while (lo + 1 < hi) {
      mid = lo + (hi-lo)/2;
      if (A[mid] > target)
        hi = mid;
      else if (A[mid] < target)
        lo = mid;
      else
        hi = mid;
    }
    if (A[lo] == target)
      return lo;
    if (A[hi] == target)
      return hi;
    return -1;
  }
}
