package nineChap3_BST;

import misc.BTtreePrinter;
import misc.TreeNode;

/**
 * Created at 12:05 AM on 11/19/15.
 */
public class IdenticalBinTree {
  public static void main(String[] args) {
    IdenticalBinTree ibt = new IdenticalBinTree();
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
    yi.right = sa;
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
    //sa2.right = wu2;

    BTtreePrinter.printNode(yi);
    BTtreePrinter.printNode(yi2);

    boolean ans = isIdentical(yi, yi2);
    System.out.println(ans);
  }
  /**
   * @param a, b, the root of binary trees.
   * @return true if they are identical, or false.
   */
  public boolean isIdentical(TreeNode a, TreeNode b) {
    // Write your code here
    if (a == null && b != null || a != null && b == null) {
      return false;
    }
    if (a == null && b == null) {
      return true;
    }
    return isIdentical(a.left, b.left) && isIdentical(a.right, b.right);

    //boolean ans = false;
    //ans = helper(a, b);
    //return ans;
  }

  private boolean helper(TreeNode a, TreeNode b) {
    if (isLeaf(a) && isLeaf(b)) {
      if (a.val == b.val)
        return true;
      else
        return false;
    }
    //if (a == null && b == null) {
    //  return true;
    //}

    if (a.val != b.val) {
      return false;
    }

    boolean l = true, r = true;
    if (a.left != null && b.left != null) {
      l = helper(a.left, b.left);
    }
    if (a.right != null && b.right != null) {
      r = helper(a.right, b.right);
    }
    return l && r;
  }

  private boolean isLeaf(TreeNode a) {
    if (a.left == null && a.right == null) {
      return true;
    }
    return false;
  }
}
