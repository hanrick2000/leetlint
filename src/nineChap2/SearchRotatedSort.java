package nineChap2;

/**
 * �����㷨�ڶ��ڿε�2����Ҫ��Ŀ֮һ: rotated sort array. ��ͼ��. ���ַ�.
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
        return mid; // ��Ϊ���������duplicate�Ļ�, ��û���ö��ֵ�, ����: A[lo] = A[mid] = A[hi] = target
      //
      // if (A[mid] < target) { // why???
      // lo = mid;
      // }

      // ��ʦ������mid�����.
      if (A[lo] < A[mid]) {
        if (A[lo] <= target && A[mid] <= target) {
          hi = mid; // ����򵥵����. �������˻�Ϊһ��sorted array����.
        } else {
          lo = mid; // ��ʵ����ǻ���rotated sort array
        }
      }
      // ��ʦ�ĺ���mid�����
      if (A[lo] >= A[mid]) {
        if (A[mid] <= target && target <= A[hi]) {
          lo = mid;
        } else
          hi = mid; // ������rotated sort array.
      }
    }

    if (A[lo] == target)
      return lo;
    if (A[hi] == target)
      return hi;
    return -1;
  }
}
