package nineChap2;

/**
 * 经典的先general在specific的问题.
 *
 * @author ttt
 *
 */
public class MedianTwoSort {

  public static void main(String[] args) {
    int[] A = {1, 2, 3, 4, 5, 6}, B = {2, 3, 4, 5};
    // int[] A = {}, B = {1};
    MedianTwoSort mts = new MedianTwoSort();
    double res = mts.findMedianSortedArrays(A, B);
    System.out.println(res);
  }

  public double findMedianSortedArrays(int[] A, int[] B) {
    // write your code here
    if (A == null || B == null)
      return Integer.MIN_VALUE;
    int Alen = A.length, Blen = B.length;
    int m = Alen + Blen;
    if (m % 2 == 1)
      return findkth(A, 0, B, 0, m / 2 + 1) / (double) 1.0;
    else
      return (findkth(A, 0, B, 0, m / 2) + findkth(A, 0, B, 0, m / 2 + 1))
          / (double) 2.0;
  }

  public static int findkth(int[] A, int Aidx, int[] B, int Bidx, int k) {
    if (Aidx >= A.length)
      return B[Bidx + k - 1];
    else if (Bidx >= B.length)
      return A[Aidx + k - 1];

    // base condition
    if (k == 1)
      return Math.min(A[Aidx], B[Bidx]);

    // compare pivot
    int kk = k / 2 - 1;
    int Akey = (Aidx + kk >= A.length) ? Integer.MAX_VALUE : A[Aidx + kk];
    int Bkey = (Bidx + kk >= B.length) ? Integer.MAX_VALUE : B[Bidx + kk];

    if (Akey < Bkey)
      return findkth(A, Aidx + k / 2, B, Bidx, k - k / 2);
    else
      return findkth(A, Aidx, B, Bidx + k / 2, k - k / 2);
  }
  /*
   * public double findMedian(int[] A, int[] B) { if (A== null || B==null) return Integer.MIN_VALUE;
   * int Alen = A.length, Blen = B.length; int m = Alen+Blen; if (m%2 == 0) return findKth(A, 0, B,
   * 0, m/2); else return (findKth(A, 0, B, 0, m/2) + findKth(A, 0, B, 0, m/2+1))/(double)2.0; }
   *//**
   *
   * @param A
   * @param A_start
   * @param B
   * @param B_start
   * @param k
   * @return
   */
  /*
   * private static int findKth(int[] A, int A_start, int[] B, int B_start, int k) { int Alen =
   * A.length; int Blen = B.length; // 先处理一些特殊情况. 如果A, B 剩余的长度不够分的话, 就在另一个arr里面取. 不会出现2个都不够,
   * 因为题目要求是kth, 所以肯定是有解的 if (A_start > Alen - 1) return B[B_start + k - 1]; else if (B_start > Blen
   * - 1) return A[A_start + k - 1];
   *
   * // 对于recursion, 肯定是要设计好base condition if (k == 1) return Math.min(A[A_start], B[B_start]);
   *
   * // 因为recursion是call 递归, 解出子问题的解, 所以应该每次返回子问题的解. int kk = k / 2 - 1; // 注意下标要写对, 因为是0 base的
   *
   * int keyA = (A_start + kk >= Alen) ? Integer.MAX_VALUE : A[A_start + kk]; int keyB = (B_start +
   * kk >= Blen) ? Integer.MAX_VALUE : B[B_start + kk];
   *
   * if (keyA <= keyB) { return findKth(A, A_start + k / 2, B, B_start, k - k / 2); } else return
   * findKth(A, A_start, B, B_start + k / 2, k - k / 2); }
   */
}
