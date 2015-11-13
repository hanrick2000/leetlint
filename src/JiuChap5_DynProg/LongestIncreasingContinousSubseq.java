package JiuChap5_DynProg;

/**
 * http://www.lintcode.com/en/problem/longest-increasing-continuous-subsequence/
 * Created at 12:59 PM on 11/11/15.
 */
public class LongestIncreasingContinousSubseq {
  public static void main(String[] args) {
    int[] A = new int[]{5,1,2,1,3};
    int ans;
    LongestIncreasingContinousSubseq lis = new LongestIncreasingContinousSubseq();
    ans = lis.longIncContSubseq(A);
    System.out.println(ans);
  }

  public int longIncContSubseq(int[] A) {
    int ans = 0;
    if (A == null || A.length == 0) {
      return ans;
    }
    int[] LP = new int[A.length];
    int[] RP = new int[A.length];
    LP[0] = 1;
    RP[0] = 1;
    int maxL = 0, maxR = 0;
    for (int i = 1; i < A.length; ++i) {
      if (A[i] > A[i-1]) {
        LP[i] = LP[i-1] + 1;
      }
      else {
        LP[i] = 0;
      }
      maxL = Math.max(maxL, LP[i]);
    }

    for (int i = 1; i < A.length; ++i) {
      if (A[i] < A[i-1]) {
        RP[i] = RP[i-1] + 1;
      }
      else {
        RP[i] = 0;
      }
      maxR = Math.max(maxR, RP[i]);
    }

    return ans = Math.max(maxL, maxR);
  }
}
