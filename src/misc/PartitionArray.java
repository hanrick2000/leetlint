package misc;

public class PartitionArray {

  /*-
   * WRONG parition method!
   * 1. it is a dummy parition, only works for 2-way parition! so can't find the 
   * first occurance of k
   * 2. I used copy, instead of exch so not in-place
   * 3. since single pass without recursion, need addition one pass to find, still wrong
   *    since it doesn't know equal k or not less, so can't find the first occurance
   * @param nums
   * @param k
   * @return
   */
  public static int partitionArrayWRONG(int[] nums, int k) {
    // write your code here
    if (nums == null || nums.length == 0) {
      return 0;
    }

    int[] aux = new int[nums.length];
    for (int i = 0; i < nums.length; i++) {
      aux[i] = nums[i];
    }

    int l = 0, r = nums.length - 1, idx = 0;
    while (idx < nums.length) {
      if (aux[idx] < k)
        nums[l++] = aux[idx];
      else if (aux[idx] >= k)
        nums[r--] = aux[idx];
      idx++;
    }

    int printIdx = 0;
    for (int n : nums) {
      System.out.print(n + " ");
      if (printIdx++ > 10) {
        System.out.println();
        printIdx = 0;
      }
    }


    for (int p = 0; p < nums.length; p++) {
      if (nums[p] == k) {
        return p;
      }
    }

    return nums.length;
  }

  /*-
   * This is dijkstra's 3 way used in Qsort, but need sential at lo, not good 
   * for this question
   * 
   * @param arr
   * @param lo
   * @param hi
   */
  private static void dijkstra3way(int[] arr, int lo, int hi) {
    int lt = lo, i = lo + 1, gt = hi;
    int V = arr[0];

    while (i <= gt) {
      int comp = arr[i] - V;
      if (comp < 0)
        exch(arr, i++, lt++);
      else if (comp > 0)
        exch(arr, i, gt--);
      else
        i++;
    }
  }

  /**
   * Jiuzhang solution
   * 
   * @param A
   * @param k
   * @return
   */
  private static int solution(int[] A, int k) {
    int lt = 0, nlt = A.length - 1;
    while (lt <= nlt) {
      while (lt <= nlt && A[lt] < k)
        lt++;
      while (lt <= nlt && A[nlt] >= k)
        nlt--;
      if (lt <= nlt) {
        exch(A, lt++, nlt--);
      }
    }

    return lt;
  }

  private static void exch(int[] arr, int i, int j) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }

  public static void main(String[] args) {
    int[] nums = new int[] {110, 159, 48, 56, 24, 110, 49, 56, 128};
    int k = 110;
    // int ans = partitionArrayWRONG(nums, k);
    // dijkstra3way(nums, 0, nums.length-1);
    int ans = solution(nums, k);
    for (int i : nums) {
      System.out.print(i + " ");
    }
    System.out.println("\n" + ans);
  }
}
