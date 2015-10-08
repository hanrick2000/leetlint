package interview;

/*-
 * Uber interview/ Leetcode: find the maximum sum of array:
 * 1. brute force - O(n3) 
 * 2. optimized brute force - O(n2)
 * 3. Divide and Conquer - O(nlgn)
 * 4. Dynamic Programming - O(n)
 *  
 * @author tzhang
 *
 */
public class MaxSubarray {
  public MaxSubarray() {
    int[] arr = new int[] {3, -2, 5, -1};
    int ans;
    // ans = DandC(arr);
    // ans = O3(arr);
//    ans = O2(arr);
    ans = DP(arr);
    System.out.println(ans);
  }

  // Method 1: O(N^3) dummy 3 loops
  public static int O3(int[] arr) {
    int max = Integer.MIN_VALUE;
    // int sumij = 0; Wrong! Because I need to calculate for each sumij so need a new one right
    // before the calculation loop which is the k-loop
    for (int i = 0; i < arr.length; ++i) {
      for (int j = i; j < arr.length; ++j) {
        int sumij = 0;
        for (int k = i; k <= j; ++k) {
          sumij += arr[k];
        }
        max = Math.max(sumij, max);
      }
    }
    return max;
  }

  // Method 2: O(N^2) improved to use pre calculated part!
  public static int O2(int[] arr) {
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < arr.length; ++i) {
      int sumij = 0;
      for (int j = i; j < arr.length; ++j) {
        sumij += arr[j];
        max = Math.max(sumij, max);
      }
    }
    return max;
  }

  // Divide and Conquer solution: O(nlgn)
  public static int DandC(int[] arr) {
    return DChelper(arr, 0, arr.length - 1);
  }

  private static int DChelper(int[] arr, int lhs, int rhs) {
    if (rhs <= lhs) {
      return arr[lhs];
    }
    int mid = (rhs + lhs) / 2;
    int maxL = DChelper(arr, lhs, mid);
    int maxR = DChelper(arr, mid + 1, rhs);

    int sumL = Integer.MIN_VALUE, sumR = Integer.MIN_VALUE;
    int sum = 0;
    for (int i = mid + 1; i <= rhs; ++i) {
      sum += arr[i];
      sumR = Math.max(sumR, sum);
    }
    sum = 0;
    // for (int i = lhs; i <= m; ++i) {
    for (int i = mid - 1; i >= lhs; i--) {
      sum += arr[i];
      sumL = Math.max(sumL, sum);
    }
    int ans = Math.max(maxL, maxR);
    return Math.max(ans, sumL + sumR + arr[mid]);
  }
  
  // The best: O(n)   -> to find the optimal solution of a given set, go for DP
  public static int DP(int[] arr) {
    int[] opt = new int[arr.length];
    opt[0] = arr[0] >= 0 ? arr[0] : 0;
    int max = opt[0];
    for (int i = 1; i < arr.length; ++i) {
      if ((opt[i-1] + arr[i]) >= 0) {
        opt[i] = opt[i-1] + arr[i];
      }
      else {
        opt[i] = arr[i];
      }
      max = Math.max(opt[i], max);
    }
//    return opt[arr.length-1];
    return max;
  }

  public static void main(String[] args) {
    MaxSubarray ms = new MaxSubarray();
  }
}
