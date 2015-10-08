package nineChap2;

/**
 * �����ǻ���search in rotated sort array. ���Ǿ����ֳ�����lo, hi�����. �Լ�������while�������ֵ�����.
 *
 * @author ttt
 *
 */
public class MinRotatedSortArr {
  public static void main(String[] args) {

  }

  /**
   * Find Minimum in Rotated Sorted Array I, ��û��duplicate
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

    // while������, lo+1 �᲻�� >= hi? ��������ֻ��, lo��hi�����1.
    return Math.min(A[lo], A[hi]);
  }

  /**
   * Find Minimum in Rotated Sorted Array II, ����duplicate. ֻ����O(n). ��Ϊû���ö�����. Total Runtime: 1626 ms
   * ʵ���ϲ�Ӧ��������, ��Ϊ������ʱ����Զ��O(n). ��ʵ���ǿ�����ԭ���Ķ��ַ�����. ����cmuYu�Ĳ���.
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
   * �ֲ����������hard, ��ʵ��������, ����Ҫע������ȵ����. ��Ҳ������lint��ʱ���test case��ʱ��ŷ��ִ����. ��������:
   * [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,-1,1,1,1,1,1,1,1]. ��ʱ����Բ��ܶ���, �п��ܻ�ֵ����н�Ķ�.
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
        // �������II��I�ѵĵ�: �������, ע����ֵ�Ŀ��: �ǽ�����С����С�ķ�Χ, �������Ǽ򵥵�ÿ�ζ������¶�!
        lo++; // http://www.cnblogs.com/yuzhangcmu/p/4049117.html. ��������д. ��������I�����ϲ�����!
    }
    return Math.min(num[lo], num[hi]);
  }
}
