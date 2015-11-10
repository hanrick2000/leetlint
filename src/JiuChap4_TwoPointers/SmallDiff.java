package JiuChap4_TwoPointers;

import java.util.Arrays;

/**
 * http://www.lintcode.com/en/problem/the-smallest-difference/
 * Created this class in JiuChap4_TwoPointers at 9:05 AM, 11/10/2015.
 */
public class SmallDiff {
  public static void main(String[] args) {
    int[] A = new int[]{3,6,7,4};
    int[] B = new int[]{2,8,9,7};
    SmallDiff sd = new SmallDiff();
    int ans = sd.smallestDifference(A, B);
    System.out.println(ans);
  }

  /**
   * 9chap's clean solution
   * @param A
   * @param B
   * @return
   */
  public int smallestDifference(int[] A, int[] B) {
    Arrays.sort(A);
    Arrays.sort(B);
    int ans = Integer.MAX_VALUE;
    int j = 0;
    for (int i = 0; i < A.length; ++i) {
      while (j + 1 < B.length) {
        if (A[i] < B[j + 1]) {
          break;
        }
        j++;
      }
      if (j < B.length) {
        ans = Math.min(ans, A[i] - B[j]);
      }
      if (j + 1 < B.length) {
        ans = Math.min(ans, B[j + 1] - A[i]);
      }
    }
    return ans;
  }

  /**
   * Still not clean
   * @param A
   * @param B
   * @return
   */
  public int smallestDifference1(int[] A, int[] B) {
    Arrays.sort(A);
    Arrays.sort(B);
    int ans = Integer.MAX_VALUE;
    int j = 0;
    for (int i = 0; i < A.length; ++i) {
      while (j < B.length) {
        if (A[i] > B[j]) {
          ans = Math.min(ans, A[i] - B[j]);
          j++;
        }
        else {
          ans = Math.min(ans, B[j] - A[i]);
          //j--;
          break;
        }
      }
    }
    return ans;
  }

  /**
   * @param A, B: Two integer arrays.
   * @return: Their smallest difference.
   */
  public int smallestDifference2(int[] A, int[] B) {
    Arrays.sort(A);
    Arrays.sort(B);
    int ans = Integer.MAX_VALUE;
    int j = 0;
    for (int i = 0; i < A.length; ++i) {
      while (j < B.length) {
        if (ans > Math.abs(A[i] - B[j])) {
          ans = Math.abs(A[i] - B[j]);
          j++;
        }
        else {
          //j--;
          break;
        }
      }
    }
    return ans;
  }

  /**
   */
  public int smallestDifferenceOn2(int[] A, int[] B) {
    // write your code here
    Arrays.sort(A);
    Arrays.sort(B);
    int ans = Integer.MAX_VALUE;
    for (int i = 0; i < A.length; ++i) {
      for (int j = 0; j < B.length; ++j) {
        ans = Math.min(ans, Math.abs(A[i] - B[j]));
      }
    }
    return ans;
  }
}
