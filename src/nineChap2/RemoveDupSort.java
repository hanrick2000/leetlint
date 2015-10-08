package nineChap2;

public class RemoveDupSort {
  public static void main(String[] args) {
    int[] A = {1, 1, 1, 3, 4, 4, 5};
    int ans = removeDupSortII(A);
    for (int i : A) {
      System.out.print(i + " ");
    }
  }


  public static int removeDupSortII(int[] A) {
    if (A == null || A.length == 0)
      return 0;
    int idx = 0, cnt = 0;
    for (int i = 0; i < A.length; ++i) {
      if (i > 0 && A[i] == A[i - 1]) {
        cnt++;
        if (cnt >= 3)
          continue;
      } else {
        cnt = 1;
      }
      A[idx++] = A[i];
    }
    return idx;
  }

  /**
   * 这种easy 题目绝不能错.因为是inplace的, 本来的5当然继续显示.
   *
   * @param A
   * @return
   */
  public static int removeDupSortII_bad(int[] A) {
    if (A == null || A.length == 0)
      return 0;
    int len = 1;
    int cnt = 1;
    for (int i = 1; i < A.length; ++i) {
      if (A[i] == A[i - 1])
        cnt++;
      if (cnt < 3) {
        A[len++] = A[i];
      } else
        cnt = 1;
    }
    return len;
  }

  /**
   * Ganker的做法
   *
   * @param A
   * @return
   */
  public static int removeDupSortI(int[] A) {
    if (A == null || A.length == 0)
      return -1;
    int index = 1; // 始终保证copy第一个数
    for (int i = 1; i < A.length; ++i) {
      if (A[i] != A[i - 1]) {
        A[index++] = A[i];
      }
    }
    return index;
  }
}
