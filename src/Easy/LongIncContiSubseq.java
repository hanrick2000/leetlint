package Easy;

/**
 * http://www.lintcode.com/en/problem/longest-increasing-continuous-subsequence/
 * Created by 12:06 AM on 10/14/2015.
 */
public class LongIncContiSubseq {
    public static void main(String[] args) {
        int[] test = new int[]{5,4,3,1, 5, 6,7,8,9};
        System.out.println(longIncContSubseq(test));
    }

    public static int longIncContSubseq(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }

        int cnt = 1, ans = 1;
        for (int i = 0; i < A.length; i++) {
            while (i+1 < A.length && A[i] < A[i+1]) {
                i++;
                cnt++;
            }
            ans = Math.max(ans, cnt);
            cnt = 1;
        }
        cnt = 1;
        for (int j = 0; j < A.length; ++j) {
            while (j+1 < A.length && A[j] > A[j+1]) {
                j++;
                cnt++;
            }
            ans = Math.max(ans, cnt);
            cnt = 1;
        }
        return ans;
    }
}
