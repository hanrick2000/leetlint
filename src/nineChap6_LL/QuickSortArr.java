package nineChap6_LL;

import java.util.Arrays;
import java.util.Collections;

public class QuickSortArr {
//  public int[] src;
  public QuickSortArr(){
    int[] src = new int[] {2, 3, 1, 6, 5};
    sort(src);
    for (int i : src) {
      System.out.print(i+" ");
    }
//    int a = partition(src, 0, 4);
//  System.out.println(a);
//    for (int i: src) {
//      System.out.print(i+" ");
//    }
  }
  
  public static int partition(int[] src, int lo, int hi) {
    int i = lo, j = hi;
    int sen = src[lo];
    
    while (true) {
      while (src[i] <= sen)  {
        if (i == hi)  break;
        i++;
      }
      while (src[j] >= sen)  {
        if (j == lo)  break;
        j--;
      }
      if (i >= j)  break;
      exch(src, i, j);
    }
    exch(src, lo, j);  // need to move pivit into final position
    return j;
  }
  
  private static void exch(int[] src, int i, int j) {
    int temp = src[i];
    src[i] = src[j];
    src[j] = temp;
  }
  
  public static void sort(int[] src) {
    Collections.shuffle(Arrays.asList(src));
    qsort(src, 0, src.length-1);
  }
  
  public static void qsort(int[] src, int lo, int hi) {
    if (lo >= hi)  return;
    int piv = partition(src, lo, hi);
    qsort(src, lo, piv-1);
//    qsort(src, lo, piv);
    qsort(src, piv+1, hi);
  }
  
  public static void main(String[] args) {
    QuickSortArr qsa = new QuickSortArr();
    
  }
}
