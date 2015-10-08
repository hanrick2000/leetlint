package nineChap4_DP1;

public class LongestIncreasingSubsequence {
  public static void main(String[] args) {
    int[] sequence = new int[] {10, 2, 4, 5, 3, 7};
    int ans = longestIncreasingSubsequence(sequence);
    System.out.println("I use DP : " + ans);
  }

  /**
   * 
   * @param nums
   * @return
   */
  public static int longestIncreasingSubsequence(int[] nums) {
    // write your code here
    if (nums == null || nums.length < 1) {
      return 0;
    }
    int[] F = new int[nums.length]; // the # LIS ended with i-th element
    int G = F[0];
    F[0] = 1;
    for (int i = 0; i < nums.length; ++i) {
      F[i] = 1; 
      for (int k = 0; k < i; ++k) {
        if (nums[k] <= nums[i]) {
          F[i] = Math.max(F[k]+1, F[i]);
          System.out.println(nums[k] + " <= " + nums[i] + ", " +F[i]);
        }
      }
      G = Math.max(G, F[i]);
    }
    
    int ans = 0;
    for (int i = 0; i < nums.length; ++i) {
      ans = Math.max(ans, F[i]);
    }
    System.out.println("the global optima :" + G);
    return ans;
  }
}
