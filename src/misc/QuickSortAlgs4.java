package misc;

import java.util.Collections;

/**
 * ALgs4里面的2种quickSort: 先是简单的2个while(). 然后是常见的3way用2个pointer
 * 
 * @author tzhang
 *
 */
public class QuickSortAlgs4 {
  /**
   * sort client
   * 
   * @param a
   */
  private static void sort(Comparable[] a) {
    // Collections.shuffle(a);
    // sortDummy(a, 0, a.length-1);
    sort3way(a, 0, a.length - 1);
  }

  /**
   * algs4的第一个sort, 使用dummy的partition
   * 
   * @param a
   * @param lo
   * @param hi
   */
  private static void sortDummy(Comparable[] a, int lo, int hi) {
    if (hi <= lo)
      return;
    int j = partition(a, lo, hi);
    sortDummy(a, lo, j - 1);
    sortDummy(a, j + 1, hi);
  }

  /**
   * Dummy partition
   * 
   * @param a
   * @param lo
   * @param hi
   * @return
   */
  private static int partition(Comparable[] a, int lo, int hi) {
    int i = lo;
    int j = hi + 1; // why hi+1?
    Comparable v = a[lo];
    for (;;) {
      while (less(a[++i], v))
        if (i == hi)
          break;
      while (less(v, a[--j]))
        if (j == lo)
          break;
      if (i >= j)
        break;
      //
      exch(a, i, j);
    }
    exch(a, lo, j); // put v at a[j]
    return j;
  }

  /**
   * Dijkstra's 3 way quick sort, simple partition!
   * 
   * @param a
   * @param lo
   * @param hi
   */
  private static void sort3way(Comparable[] a, int lo, int hi) {
    if (hi <= lo)
      return;
    int lt = lo, i = lo + 1, gt = hi;
    Comparable v = a[lo];
    while (i <= gt) {
      int cmp = a[i].compareTo(v);
      if (cmp < 0)
        exch(a, lt++, i++);
      else if (cmp > 0)
        exch(a, i, gt--);
      else
        i++;
    }
    sort3way(a, lo, lt - 1);
    sort3way(a, gt + 1, hi);
  }

  // helper
  private static boolean less(Comparable a, Comparable b) {
    return (a.compareTo(b) < 0);
  }

  private static void exch(Comparable[] a, int i, int j) {
    Comparable temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }

  public static void main(String[] args) {
    Comparable[] arr = new Comparable[] {10, 3, 7, 2, 1};
    for (Comparable e : arr) {
      System.out.print(e + " ");
    }
    sort(arr);
    System.out.println("\nHello");
    for (Comparable e : arr) {
      System.out.print(e + " ");
    }

  }
}
