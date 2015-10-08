package nineChap2_BinarySearch;

public class MergeSortII {
  public static void main(String[] args) {
    int[] A = new int[5], B = {4, 5};
    A[0] = 1;
    A[1] = 2;
    A[2] = 3;
    for (int i : A) {
      System.out.print(i + " ");
    }
    System.out.println("");
    // merge(A, B);
    merge(A, 3, B, 2);
    for (int i : A) {
      System.out.print(i + " ");
    }
  }

  /**
   * 这个是常用的关于array的操作, 加入了index的控制. 在median of two sorted array也用到了
   * 
   * @param A
   * @param m
   * @param B
   * @param n
   */
  public static void merge(int[] A, int m, int[] B, int n) {
    if (A == null || B == null)
      return;
    int Aidx = m - 1, Bidx = n - 1;
    int len = m + n - 1;
    while (Aidx >= 0 && Bidx >= 0) {
      if (A[Aidx] >= B[Bidx]) {
        A[len--] = A[Aidx--];
      } else
        A[len--] = B[Bidx--];
    }
    while (Bidx >= 0) {
      A[len--] = B[Bidx--];
    }
  }

  /**
   * 最原始的解法, 很丑陋.
   * 
   * @param A
   * @param B
   */
  public static void merge(int[] A, int[] B) {
    int al = A.length, bl = B.length;
    int i = 2, j = bl - 1;
    int k = al - 1;
    while (i >= 0 && j >= 0) {
      if (A[i] >= B[j]) {
        A[k] = A[i];
        i--;
      } else {
        A[k] = B[j];
        j--;
      }
      k--;
    }
    if (k == 0)
      return;
    if (i > 0) {
      while (i > 0) {
        A[k] = A[i];
        k--;
        i--;
      }
    } else {
      while (j > 0) {
        A[k] = B[j];
        k--;
        j--;
      }
    }
  }
}
