package Easy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 12:34 AM on 10/14/2015.
 */
public class MajorityNumber {
    public static void main(String[] args) {
        int[] test = new int[]{1,1,1,1,2,2,2}; //{1, 1, 1, 1, 2, 2, 2, 2, 2};
        ArrayList<Integer> input = new ArrayList<>();
        for (int a : test) {
            input.add(a);
        }
        System.out.println(majority9chap(input));
    }

    public static int majority9chap(ArrayList<Integer> nums) {
        if (nums == null || nums.size() == 0) {
            return 0;
        }
        int res = 99; // = nums.remove(0);
        int cnt = 0;
        for (int i : nums) {
            if (cnt == 0) {
                res = i;
                cnt = 1;
                continue;
            }
            if (res != i) {
                cnt--;
            } else {
                cnt++;
            }
        }
        return res;
    }

    /**
     * I was thinking in the right way from high level: remove diff pair, but how to traverse whole array?
     *
     * @param A
     * @return
     */
    public static int majorityWrong(int[] A) {
        if (A == null || A.length == 0) {
            return -1;
        }

        if (A.length == 1) {
            return A[0];
        }
        int visit = -1;
        for (int a : A) {
            visit = Math.min(a, visit);
        }

        int i = 0, j = A.length - 1;
        while (i < j) {
            while (A[i] == A[j]) {
                j--;
            }
            while (A[i] != A[j]) {
                A[i] = visit;
                A[j] = visit;
                i++;
                j--;
            }
        }

        for (int k = 0; k < A.length; ++k) {
            if (A[k] != visit) {
                // how to do???
            }
        }
        return 999;
    }
}
