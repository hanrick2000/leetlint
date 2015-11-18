package nineChap3_BST;

import misc.BTtreePrinter;
import misc.TreeNode;

/**
 * http://www.lintcode.com/en/problem/binary-tree-maximum-path-sum-ii
 * Created at 11:43 PM on 11/16/15.
 */
public class BinTreeMaxPathSumII {
  public static void main(String[] args) {
    TreeNode root = new TreeNode(0);
    TreeNode a = new TreeNode(-1);
    TreeNode b = new TreeNode(12);
    TreeNode c = new TreeNode(3);
    TreeNode d = new TreeNode(-9);
    TreeNode e = new TreeNode(2);
    TreeNode f = new TreeNode(100);
    TreeNode g = new TreeNode(-1000);

    root = a;
    a.left = b;
    a.right = c;
    b.left = d;
    c.right = e;
    d.left = f;
    f.right = g;

    BTtreePrinter printer = new BTtreePrinter();
    System.out.println("");
    printer.printNode(root);
    System.out.println(SerializeBFS.serializeLintcode(root));
    BinTreeMaxPathSumII btmsps = new BinTreeMaxPathSumII();
    int ans = btmsps.maxPathSum2(root);
    System.out.println(ans);
  }
  /**
   * @param root the root of binary tree.
   * @return an integer
   */
  public int maxPathSum2(TreeNode root) {
    // Write your code here
    if (root == null) {
      return 0;
    }
    return dfs(root);
  }

  public int dfs(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int left = dfs(root.left);
    int right =dfs(root.right);

    return Math.max(left, right) + root.val;
  }
}
