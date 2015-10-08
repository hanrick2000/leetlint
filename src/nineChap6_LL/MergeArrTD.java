package nineChap6_LL;

public class MergeArrTD {
  private int[] arr;
  public MergeArrTD(){
    int[] arr = new int[]{2, 6, 3, 5, 1}; // {17, 17, 24, 6, 3, 19, 21, 4, 8, 22};
//    dbgMerge(arr);
    Msort(arr);
//    MsortAlgs4(arr);
  }
  
  /*
   * I use the CMU way: put back result from dst to a.
   */
  public static void merge(int[] a, int[] dst, int lo, int mid, int hi) {
    if (a == null || lo > mid || mid > hi) {
      return;
    }
    int i = lo, j = mid+1;
    for (int xi = lo; xi <= hi; ++xi) {
      if (i > mid)  dst[xi] = a[j++];
      else if (j > hi)  dst[xi] = a[i++];
      else if (a[i] > a[j])  dst[xi] = a[j++];
      else  dst[xi] = a[i++];
    }
    
//    a = dst.clone(); // why this doesn't work?
    for (int k = 0; k < a.length; ++k) {
      a[k] = dst[k];
    }
  }
  
  /*
   * Algs4 way to merge, so the result is in dst
   */
  public static void mergeAlgs4(int[] a, int[] dst, int lo, int mid, int hi) {
    if (a == null || lo > mid || mid > hi) {
      return;
    }
    int i = lo, j = mid+1;
    for (int xi = lo; xi <= hi; ++xi) {
      if (i > mid)  dst[xi] = a[j++];
      else if (j > hi)  dst[xi] = a[i++];
      else if (a[i] > a[j])  dst[xi] = a[j++];
      else  dst[xi] = a[i++];
    }
  }
  
  /**
   * CMU's way to mergeSort, dst used as a temp
   * Take a while to debug, notice the result is store into dst or src? after recursion, what's the param for merge?
   * @param src
   * @param dst
   * @param lo
   * @param hi
   */
  public static void sort(int[] src, int[] dst, int lo, int hi) {
    if (lo >= hi) {
      return;
    }
    int mid = lo + (hi-lo)/2;
    sort(src, dst, lo, mid);
    sort(src, dst, mid+1, hi);
    
    merge(src, dst, lo, mid, hi);  
  }
  
  // Algs4's way to do mergeSort, dst is the result
  public static void sortAlgs4(int[] src, int[] dst, int lo, int hi) {
    if (lo >= hi) {
      return;
    }
    int mid = lo + (hi-lo)/2;
    sortAlgs4(dst, src, lo, mid); // 
    sortAlgs4(dst, src, mid+1, hi);
    
    mergeAlgs4(src, dst, lo, mid, hi);  
    
//    sortAlgs4(src, dst, lo, mid); // WRONG
//    sortAlgs4(src, dst, mid+1, hi);
//    
//    mergeAlgs4(dst, src, lo, mid, hi);  
  }
  
  public void Msort(int[] arr) {
    int[] dst = arr.clone();
    sort(arr, dst, 0, arr.length-1);
    for (int i : arr) {
      System.out.print(i + " ");
    }
  }
  
  public void MsortAlgs4(int[] arr) {
    int[] dst = arr.clone();
    sortAlgs4(arr, dst, 0, arr.length-1);
    for (int i : dst) {
      System.out.print(i + " ");
    }
  }
  
  public void dbgMerge(int[] arr) {
//  int[] dst = new int[arr.length]; // wrong
    int[] dst = arr.clone();
    int hi = arr.length-1;
    MergeArrTD.merge(arr, dst, 0, hi/2, hi);
    for (int i : arr) {
      System.out.print(i + " ");
    }
  }
  /*
  public static void exch(int[] a, int i, int j) {
    if (a == null || i > a.length-1 || j > a.length-1) {
      return;
    }
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }*/
  
  public static void main(String[] args) {
    MergeArrTD matd = new MergeArrTD();
  }
}
