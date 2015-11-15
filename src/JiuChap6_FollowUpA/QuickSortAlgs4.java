package JiuChap6_FollowUpA;

import edu.princeton.cs.algs4.StdRandom;

/**
 * Created at 6:15 PM on 11/14/15.
 */
public class QuickSortAlgs4 {
  public static void main(String[] args) {
    int[] A = new int[]{5,3,7,1,2};
    QuickSortAlgs4 qs = new QuickSortAlgs4();
    qs.quickSort(A);
    //int par = qs.partitionTTT(A, 0, A.length-1);
    //System.out.println(par);
    //qs.quickSortGeek(A, 0, A.length-1);
    for (int i = 0; i < A.length; ++i) {
      System.out.print(A[i] + " ");
    }
  }

  public void quickSort(int[] A) {
    StdRandom.shuffle(A);
    sort(A, 0, A.length-1);
  }

  public void sort(int[] A, int start, int end) {
    if (start >= end)  return;
    int par = partitionTTT(A, start, end);
    sort(A, start, par-1);
    sort(A, par+1, end);
  }

  /**
   * Failed: fall into forever loop on A[] = {2,2,2}!
   * Fixing is easy, just update l,r after valid exch.
   * @param A
   * @param start
   * @param end
   * @return
   */
  public int partitionTTT(int[] A, int start, int end) {
    int l = start+1, r = end;  // must add 1 so pivot not modified.
    int pivotal = A[start];
    while (l <= r) {
      while (A[l] < pivotal) {  // l stay the same, that caused forever loop.
        l++;
      }
      while (A[r] > pivotal) {
        r--;
      }
      if (l >= r) {
        break;
      }
      exch(A, l, r);
      l++;  // hoho, this solved the forever looping issue!
      r--;
    }
    exch(A, start, r);
    return r;
  }

  /**
   * ALgs4's implementation
   *
   * @param a
   * @param lo
   * @param hi
   * @return
   */
  private int partition(int[] a, int lo, int hi) {
    int i = lo;
    int j = hi + 1;  // +1 for the  --j.
    int v = a[lo];
    while (true) {
      // find item on lo to swap
      while (less(a[++i], v))  // the ++ prevents forever loop on all same value array
        if (i == hi) break;

      // find item on hi to swap
      while (less(v, a[--j]))
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

  private boolean less(int a, int b) {
    return a - b < 0;
  }

  private void exch(int[] A, int i, int j) {
    int tmp = A[i];
    A[i] = A[j];
    A[j] = tmp;
  }

  /**
   * http://www.vogella.com/tutorials/JavaAlgorithmsQuicksort/article.html
   * SImple to follow than Algs4's code
   * @param arr
   * @param low
   * @param high
   */
  public static void quickSortGeek(int[] arr, int low, int high) {
    if (arr == null || arr.length == 0)
      return;

    if (low >= high)
      return;

    // pick the pivot
    //int middle = low + (high - low) / 2;
    //int pivot = arr[middle];
    int pivot = arr[low];

    // make left < pivot and right > pivot
    int i = low, j = high;
    while (i <= j) {
      while (arr[i] < pivot) {
        i++;
      }

      while (arr[j] > pivot) {
        j--;
      }

      if (i <= j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        i++;
        j--;
      }
    }

    // recursively sort two sub parts
    if (low < j)
      quickSortGeek(arr, low, j);

    if (high > i)
      quickSortGeek(arr, i, high);
  }
}
