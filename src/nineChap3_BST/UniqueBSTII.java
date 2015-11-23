package nineChap3_BST;

import java.util.*;
import misc.BTtreePrinter;
import misc.TreeNode;

/**
 * http://www.lintcode.com/en/problem/unique-binary-search-trees-ii/
 * Created at 6:09 PM on 11/22/15.
 */
public class UniqueBSTII {
  public static void main(String[] args) {
    UniqueBSTII ub2 = new UniqueBSTII();
    List<TreeNode> ans = ub2.generateTrees(3);
    BTtreePrinter printer = new BTtreePrinter();
    for (TreeNode a : ans) {
      printer.printNode(a);
    }
  }
  /**
   * @paramn n: An integer
   * @return: A list of root
   */
  public List<TreeNode> generateTrees(int n) {
    // write your code here
    List<TreeNode> ans = new ArrayList<>();
    if (n < 0) {
      return ans;
    }

    ans = dfs(1,n);
    //for (int i = 1; i <= n; ++i) {
    //  Set<Integer> pool = new HashSet<>();
    //  TreeNode root = new TreeNode(i);
    //  ans.add(root);
    //  pool.add(i);
    //  dfs(root, n, pool);
    //  pool.remove(i);
    //}
    return ans;
  }

  /**
   * 9chaps solution
   * @param left
   * @param right
   * @return
   */
  private List<TreeNode> dfs(int left, int right) {
    List<TreeNode> res = new ArrayList<>();
    if (left > right) {
      res.add(null);
      return res;
    }

    for (int i = left; i <= right; ++i) {
      List<TreeNode> lroots = dfs(left, i-1);
      List<TreeNode> rroots = dfs(i+1, right);
      for (TreeNode lroot : lroots) {
        for (TreeNode rroot : rroots) {
          TreeNode root = new TreeNode(i);
          root.left = lroot;
          root.right = rroot;
          res.add(root);
        }
      }
    }
    return res;
  }

  /**
   * DFS in a wrong idea
   * @param root
   * @param n
   * @param pool
   */
  private void dfsWRONG(TreeNode root, int n, Set<Integer> pool) {
    if (root == null || pool.size() == n) {
      return;
    }
    for (int i = 1; i < root.val; ++i) {
      if(pool.contains(i)) {
        continue;
      }
      root.left = new TreeNode(i);
      pool.add(i);
      dfsWRONG(root.left, n, pool);
      //pool.remove(i);
    }
    for (int j = root.val+1; j <= n; ++j) {
      if(pool.contains(j)) {
        continue;
      }
      root.right = new TreeNode(j);
      pool.add(j);
      dfsWRONG(root.right, n, pool);
      //pool.remove(j);
    }
  }
}
