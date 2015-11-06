package nineChap6_LL;

/**
 * do a partition on array prior parition list
 * 
 * @author tzhang
 *
 */
public class PartitionArray {
  public PartitionArray() {
    int[] a = new int[] {5, 100, 13, 1}; //{5, 3, 10, 13, 1, 13, 2, 100, 15, 8, 13};
    int[] res = new int[] {};
//    partitionArraya(a, 13);
    partitionArrayb(a, 13);
    for (int i : a) {
      System.out.print(i+" ");
    }
  }

  /**
   * first try, OK, followed the classical while with 2 while
   * 
   * @param a: since it is array, so an object, so pass-by-val, so will update.
   * @param sentinel
   */
  public void partitionArraya(int[] a, int sentinel) {
    if (a == null) {
      return;
    }

    int l, r; // two pointers: left/right. all (0, left] is less than sentinel, all [right, inf] is
              // larger than sentinel.
    l = 0;
    r = a.length-1;
    
    while (l < r) {
      while (l < a.length && a[l] <= sentinel)  ++l;
      while (r > 0 && a[r] >= sentinel) --r;
      if (l < r) {
        int temp = a[l];
        a[l] = a[r];
        a[r] = temp;
        ++l;
        --r;
      }
    }
    
//    return 
  }
  
  /**
   * Dijkstra 3-way qsort
   * @param a
   * @param s
   */
  public void partitionArrayb(int[] a, int s) {
    if (a == null || a.length == 1) {
      return;
    }
    
    int lp = 0, i = lp+1, gp = a.length-1;
    while (i <= gp) {
      if (a[i] > s)  exch(a, i, gp--); // since post arithmetic, so can combine into param
      if (a[i] < s)  exch(a, i++, lp++);
      if (a[i] == s)  i++;
    }
    
  }
  
  private static void exch(int[] a, int i, int j) {
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }
  
  public static void main(String[] args) {
    PartitionArray pa = new PartitionArray();
    
  }
}
