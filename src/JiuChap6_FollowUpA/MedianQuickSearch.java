package JiuChap6_FollowUpA;

import java.util.Arrays;

/**
 * Question before the nuts & bolts Created at 12:52 PM on 11/14/15.
 */
public class MedianQuickSearch {
  public static void main(String[] args) {
    int[] A = new int[]{2,1,3}; // {21, 2, 1, 5, 10, 12, 14};
    MedianQuickSearch mqs = new MedianQuickSearch();
    //int par = mqs.partitionALgs(A, 0, A.length-1);
    int par = mqs.partition(A);
    System.out.println(par);
    for (int i : A)
      System.out.print(i+" ");

  }


  public int median(int[] A) {
    int n = A.length;
    int median = recursion(A, 0, n);
    return median;
  }

  public int recursion(int[] A, int l, int r) {
    int peak = A[l];
    while (l + 1 < r) {

    }
    return -1;
  }

  /**
   * Remember this partition in detail!
   * learn from http://algs4.cs.princeton.edu/23quicksort/Quick.java.html
   * @param A
   */
  private int partition(int[] A) {
    int pivotal = A[0];
    int lo = 0, hi = A.length-1;
    int l = lo+1, r = hi;
    while (true) {
      while (l < A.length && pivotal > A[l]) {
        l++;
      }
      while (r > 0 && pivotal < A[r]) {
        r--;
      }
      if (l >= r) {
        break;
      }
      exch(A, l, r);
    }
    //if (l == A.length) {
    //  l = A.length-1;
    //}
    //exch(A, 0, l);
    System.out.println(lo + " " + r);
    exch(A, lo, r);
    return r;
  }

  private int partitionALgs(int[] a, int lo, int hi) {
    int i = lo;
    int j = hi + 1;
    int v = a[lo];
    while (true) {

      // find item on lo to swap
      while (a[++i]< v)
        if (i == hi) break;

      // find item on hi to swap
      while (v< a[--j])
        if (j == lo) break;      // redundant since a[lo] acts as sentinel

      // check if pointers cross
      if (i >= j) break;

      exch(a, i, j);
    }

    // put partitioning item v at a[j]
    exch(a, lo, j);

    // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
    return j;
  }

  private void exch(int[] A, int i, int j) {
    int l = i, r = j;
    int tmp = A[l];
    A[l] = A[r];
    A[r] = tmp;
  }
}
