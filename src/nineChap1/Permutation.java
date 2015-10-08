package nineChap1;

import java.util.*;

public class Permutation {
  public static void main(String[] args) {
    int[] num = {1, 2, 2};
    List<List<Integer>> ans = permutation(num);
    System.out.println(ans);
  }

  public static List<List<Integer>> permutation(int[] A) {
    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    boolean[] used = new boolean[A.length];
    dfs(A, used, new ArrayList<Integer>(), ans);
    return ans;
  }

  public static void dfs(int[] A, boolean[] used, List<Integer> path,
      List<List<Integer>> res) {
    if (path.size() == A.length) {
      res.add(new ArrayList<Integer>(path));
      return; // 最好写上. 即base case跳出递归.
    }
    for (int i = 0; i < A.length; ++i) {
      // if (i < A.length - 1 && !used[i+1] && A[i] == A[i + 1])
      if (i > 0 && !used[i - 1] && A[i] == A[i - 1]) // key: 只对第一个重复的字符进行递归调用, 或者反过来说就是: 对于重复的字符,
                                                     // 若前一个未被调用, 则忽略当前的. 从而保证只对第一个重复字符递归.
        continue;
      if (used[i])
        continue;
      used[i] = true;
      path.add(A[i]);
      dfs(A, used, path, res);
      path.remove(path.size() - 1);
      used[i] = false;
    }
  }
}
