package nineChap8_DSA;

public class Median {
  public static void main(String[] args) {
    int[] arr = new int[] {4}; // {3, 5, 1, 2, 4};
    System.out.println(median(arr));
  }

  public static int median(int[] nums) {
    int medianIdx = (nums.length - 1) / 2, end = nums.length - 1;
    return findK1(nums, medianIdx, 0, end);
  }

  /**
   * Classical Qselect derived from Qsort
   * http://www.cnblogs.com/EdwardLiu/p/4340933.html
   * Algs4 p346
   * 
   * @param nums
   * @param k
   * @param start
   * @param end
   * @return
   */
  public static int Qselect(int[] nums, int k, int start, int end) {
    int lp = start, gp = end;
    int pivot = start;
    while (true) {
      while (nums[lp] <= nums[pivot] && lp < gp) {
        lp++;
      }
      while (nums[gp] > nums[pivot] && lp < gp) {
        gp--;
      }
      if (lp == gp)
        break;
      exch(nums, lp, gp);
    }
    exch(nums, pivot, lp); // after this, [0...lp] <= nums[lp]
    if (k == lp + 1)
      return nums[lp];
    else if (k > lp + 1)
      return Qselect(nums, k, lp + 1, end);
    else
      return Qselect(nums, k, start, lp - 1);
  }

  /**
   * Algs4's partition P290
   * @param a
   * @param k
   * @param lo
   * @param hi
   * @return
   */
  public static int findK(int[] a, int k, int lo, int hi) {
    int i = lo, j = hi+1;
    int v = a[lo];
    
    while (true) {
        while (less(a[++i], v))  if (i == hi)  break;
        while (less(v, a[--j]))  if (j == lo)  break;
        if  (i >= j)  break;
        exch(a, i, j);
    }
    exch(a, lo, j);
    
    if (k == j)  return a[k];
    else if (k > j)  return findK(a, k, j+1, hi);
    else return findK(a, k, lo, j-1);
}
  
  public static int findK1(int[] nums, int k, int start, int end) {
    int lp = start, gp = end+1;
    int pivot = start;
    
    while (true) {
        while (less(nums[++lp], nums[pivot]))  if (lp == end)  break;
        while (less(nums[pivot], nums[--gp]))  if (gp == start)  break;
        if (lp >= gp)  break;
        exch(nums, lp, gp);
    }
    exch(nums, start, gp);
    
    if (k == gp)  return nums[gp];
    else if (k > gp)  return findK1(nums, k, gp+1, end);
    else return findK1(nums, k, start, gp-1);
}

  private static void exch(int[] nums, int i, int j) {
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
  }

  private static boolean less(int i, int j) {
    return i < j;
  }
}
