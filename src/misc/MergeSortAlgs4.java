package misc;

import java.util.*;

public class MergeSortAlgs4 {
  private static void merge(Comparable[] arr, int lo, int mid, int hi) {
    Comparable[] aux = new Comparable[arr.length];
    int i = lo, j = mid + 1;
    for (int k = lo; k <= hi; ++k) {
      aux[k] = arr[k];
    }
    for (int k = lo; k <= hi; ++k) {
      if (i > mid)
        arr[k] = aux[j++];
      else if (j > hi)
        arr[k] = aux[i++];
      else if (less(aux[j], aux[i]))
        arr[k] = aux[j++];
      else
        arr[k] = aux[i++];
    }
  }

  private static boolean less(Comparable a, Comparable b) {
    return (a.compareTo(b) < 0);
  }

  /**
   * Top down sorting, so it is easy to make but recursion so huge stack
   * 
   * @param a
   * @param lo
   * @param hi
   */
  private static void sortTD(Comparable[] a, int lo, int hi) {
    if (hi <= lo)
      return;
    int mid = lo + (hi - lo) / 2;
    sortTD(a, lo, mid);
    sortTD(a, mid + 1, hi);
    merge(a, lo, mid, hi);
  }

  private static void sortBU(Comparable[] a) {
    int N = a.length;
    Comparable[] aux = new Comparable[N];
    for (int sz = 1; sz < N; sz = sz + sz) {
      for (int lo = 0; lo < N - sz; lo += sz + sz) {
        merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
      }
    }
  }

  public static void main(String[] args) {
    Integer[] a = new Integer[] {10, 3, 2, 7, 1};
    for (Integer ae : a)
      System.out.print(ae + " ");
    System.out.println("\nnew line");
    sortTD(a, 0, 4);
    // sortBU(a);

    System.out.println("\nnew line"); // : 这样就导致了每次print都不同! 所以要print句子
    System.err.println("\n abc"); 
    for (Integer ae : a)
      System.out.print(ae + " ");
  }
}
