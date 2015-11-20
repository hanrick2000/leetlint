package nineChap3_BST;

import misc.BTtreePrinter;
import misc.TreeNode;

/**
 * Created at 12:43 AM on 11/19/15.
 */
public class SymmetricBinTree {
  public static void main(String[] args) {
    SymmetricBinTree ibt = new SymmetricBinTree();
    ibt.test();
  }

  public void test() {
    TreeNode root = new TreeNode(0);
    TreeNode yi = new TreeNode(1);
    TreeNode er = new TreeNode(2);
    TreeNode sa = new TreeNode(3);
    TreeNode si = new TreeNode(4);
    TreeNode wu = new TreeNode(5);
    TreeNode sh = new TreeNode(10);

    yi.left = er;
    yi.right = er;
    //er.right = si;
    //sa.right = wu;

    TreeNode yi2 = new TreeNode(1);
    TreeNode er2 = new TreeNode(2);
    TreeNode sa2 = new TreeNode(3);
    TreeNode si2 = new TreeNode(4);
    TreeNode wu2 = new TreeNode(5);

    yi2.left = er2;
    yi2.right = sa2;
    er2.right = si2;
    sa2.right = wu2;

    BTtreePrinter.printNode(yi);
    BTtreePrinter.printNode(yi2);

    boolean ans = isSymmetric(yi2);
    System.out.println(ans);
  }

  /**
   * @param root, the root of binary tree.
   * @return true if it is a mirror of itself, or false.
   */
  public boolean isSymmetric(TreeNode root) {
    // Write your code here
    if (root == null) {
      return true;
    }
    return helper(root.left, root.right);
  }

  private boolean helper(TreeNode a, TreeNode b) {
    if (a == null && b == null) {
      return true;
    }
    if (a != null && b == null || a == null && b != null) {
      return false;
    }
    if (a.val != b.val) {
      return false;
    }
    return helper(a.left, b.right) && helper(a.right, b.left);
  }
  /**
   * @param root, the root of binary tree.
   * @return true if it is a mirror of itself, or false.
   */
  public boolean isSymmetricWRONG(TreeNode root) {
    // Write your code here
    if (root == null) {
      return true;
    }
    if (root.left == null && root.right == null) {
      return true;
    }
    if (root.left == null && root.right != null || root.left != null && root.right == null) {
      return false;
    }
    if (root.left.val != root.right.val) {
      return false;
    }
    return isSymmetricWRONG(root.left) && isSymmetricWRONG(root.right);
  }
}
