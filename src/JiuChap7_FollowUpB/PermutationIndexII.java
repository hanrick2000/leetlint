package JiuChap7_FollowUpB;

import java.util.*;

/**
 * http://www.lintcode.com/en/problem/permutation-index-ii/
 * Created at 12:43 PM on 11/24/15.
 */
public class PermutationIndexII {
  public static void main(String[] args) {
    int[] A = new int[]{1,4,2,2};
    PermutationIndexII pi2 = new PermutationIndexII();
    //long ans = pi2.permutationIndexII(A);
    //System.out.println(ans);
    pi2.getAll(A);
  }
  /**
   * @param A an integer array
   * @return a long integer
   */
  public long permutationIndexII(int[] A) {
    // Write your code here
    return 0;
  }

  private void getAll(int[] A) {
    Arrays.sort(A);
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> tmp = new ArrayList<>();
    dfs(A, result, tmp, 0, new ArrayList<>());

    for (List<Integer> list : result) {
      System.out.println(list);
    }
  }

  private void dfs(int[] A, List<List<Integer>> result, List<Integer> tmp, int step, List<Integer> used) {
    if (tmp.size() == A.length) {
      result.add(new ArrayList<>(tmp));
      return;
    }

    for (int i = 0; i < A.length; ++i) {
      // used.contains(i-1) is important: say we have {2_a, 2_b, 2_c}, so three 2, only need 1 permutation
      // so [2_a, 2_b, 2_c] is ok, and should ignore something like [2_a, 2_c, 2_b], so we check if 2_b is
      // used when adding 2_c.
      if (i-1 >= 0 && A[i] == A[i-1] && used.contains(i-1)) {
        continue;
      }
      if (used.contains(i)) {
        continue;
      }
      tmp.add(A[i]);
      used.add(i);
      dfs(A, result, tmp, step+1, used);
      used.remove(used.size()-1);
      tmp.remove(tmp.size()-1);
    }
  }
}
