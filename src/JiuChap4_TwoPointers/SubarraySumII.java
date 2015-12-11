package JiuChap4_TwoPointers;

/**
 * http://www.lintcode.com/en/problem/subarray-sum-ii/
 * Created at 4:19 PM on 11/29/15.
 */
public class SubarraySumII {
  public static void main(String[] args) {
    int[] A = new int[]{1,2,3,4};
    int start = 1, end = 3;
    int ans = new SubarraySumII().subarraySumII(A, start, end) ;
    System.out.println(ans);
  }

  /**
   * O(n^2) normal solution
   * @param A an integer array
   * @param start an integer
   * @param end an integer
   * @return the number of possible answer
   */
  public int subarraySumII(int[] A, int start, int end) {
    // Write your code here
    int[] preSum = new int[A.length+1];
    preSum[0] = 0;
    for (int i = 0; i < A.length; ++i) {
      preSum[i+1] = preSum[i] + A[i];
    }
    int cnt = 0;
    for (int i = 0; i < A.length; ++i) {
      for (int j = i+1; j <= A.length; ++j) {
        int diff = preSum[j] - preSum[i];
        if (start <= diff && diff <= end) {
          cnt++;
        }
      }
    }
    return cnt;
  }
}
