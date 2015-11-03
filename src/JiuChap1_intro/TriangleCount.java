package JiuChap1_intro;

import java.util.Arrays;

/**
 * http://www.lintcode.com/en/problem/triangle-count/
 * Created this class in JiuChap1_intro at 4:07 PM, 11/2/2015.
 */
public class TriangleCount {

  public static void main(String[] args) {
    int[] data = new int[] { 3, 4, 6, 7 };  //{4,4,4,4}; //
    TriangleCount tc = new TriangleCount();
    int ans = tc.triangleCountWrong(data);
    System.out.println(ans);
  }

  /**
   * 9chap's solution
   * @param S
   * @return
   */
  public int triangleCount(int[] S) {
    int l = 0, r = S.length - 1;
    int ans = 0;
    Arrays.sort(S);
    for (int i = 0; i < S.length; ++i) {
      l = 0;
      r = i - 1;
      while (l < r) {
        if (S[l] + S[r] > S[i]) {
          ans += r - l;
          r--;  // This is the key!
        } else {
          l++;
        }
      }
    }
    return ans;
  }

  /**
   * Wrong idea; the triangle number is: 2 smaller number's sum < biggest number
   *
   * @return: An integer
   */
  public int triangleCountWrong(int S[]) {
    // write your code here
    if (S == null || S.length < 3) {
      return 0;
    }
    Arrays.sort(S);
    int res = 0;
    for (int i = 0; i < S.length - 2; ++i) {
      int left = i + 1;
      int right = S.length - 1;
      int sum = S[left] + S[right];
      while (left < right) {
        if (sum > S[i]) {
          res += right - left;
          System.out.println(i + ", " + left + ", " + right);
          //          break;
          right--;
          sum = S[left] + S[right];
        } else {
          left++;
          sum = S[left] + S[right];
        }
      }
      //      if (S[i] < S[left] + S[right]) {
      //        res++;
      //        System.out.println(i + ", " + left + ", " + right);
      //      }
    }

    return res;
  }
}
