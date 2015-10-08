package nineChap2_BinarySearch;

/**
 * 一个小技巧: 3步反转法
 * @author tzhang
 *
 */
public class RecoverRotate {
  public static void main(String[] args) {
    int[] A = {3,5, 1,2};
    reverse(A);
    for (int i : A) 
      System.out.print(i + " ");
  }
  
  public static void reverse(int[] A) {
    for (int i = 0; i < A.length-1; ++i) {
      if (A[i+1] < A[i]) {
        reverseHelper(A, 0, i);
        reverseHelper(A, i+1, A.length-1);
        reverseHelper(A, 0, A.length-1);
      }
    }
  }
  
  /**
   * 
   * @param A
   * @param start
   * @param end
   */
  private static void reverseHelper(int[] A, int start, int end) {
    if (A == null || A.length-1 < end)
      return;
    int lo = start, hi = end;
    while (lo < hi) {
      int tmp = A[hi];
      A[hi] = A[lo];
      A[lo] = tmp;
      lo++;
      hi--;
    }
  }
  // 烂API设计, 空置量太少了!
  private static void reverseArr(int[] A) {
    if (A == null || A.length <2)
      return;
    int lo = 0, hi = A.length-1;
    while (lo < hi) {
      int tmp = A[hi];
      A[hi] = A[lo];
      A[lo] = tmp;
      lo++;
      hi--;
    }
  }
}
