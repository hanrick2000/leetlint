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
   * ֱ������ģ��: �������ֻ��2����: lo��hi.
   *
   * @param A
   * @param target
   * @return
   */
  public static int searchInsertfirst(int[] A, int target) {
    if (A == null)
      return 0; // ���A = [], target = 9. Ӧ��return 0.
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

    // key, ��ʵ����ͼ���ܷ����ĳ���.
    if (A[start] >= target) { // ��������Ⱥʹ��ڵ����
      return start;
    } else if (A[end] >= target) { // ��������Ⱥʹ��ڵ����.
      return end;
    } else { // �����???
      return end + 1;
    }
  }

  // ���õ�д��, ��Ϊû���������lo,hi������
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
