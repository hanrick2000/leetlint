package nineChap3_BST;

import misc.BTtreePrinter;
import misc.TreeNode;

/**
 * Fully understand the zuo-you-hu-bo's recursion call!
 * http://www.lintcode.com/en/problem/subtree/
 * Created at 6:54 PM on 11/19/15.
 */
public class Subtree {
  public static void main(String[] args) {
    Subtree s = new Subtree();
    s.test();
  }

  public void test() {
    TreeNode yi = new TreeNode(1);
    TreeNode er = new TreeNode(2);
    TreeNode err = new TreeNode(2);
    TreeNode sa = new TreeNode(3);
    TreeNode si = new TreeNode(4);
    TreeNode wu = new TreeNode(5);
    TreeNode sh = new TreeNode(10);

    wu.left = sa;
    wu.right = yi;
    yi.left = er;
    yi.right = err;
    er.right = si;
    //sa.right = wu;

    TreeNode yi2 = new TreeNode(1);
    TreeNode er2 = new TreeNode(2);
    TreeNode er3 = new TreeNode(2);
    TreeNode sa2 = new TreeNode(3);
    TreeNode si2 = new TreeNode(4);
    TreeNode wu2 = new TreeNode(5);

    yi2.left = er2;
    yi2.right = er3;
    //yi2.right = er3;
    er2.right = si2;
    //sa2.right = wu2;

    BTtreePrinter.printNode(wu);
    BTtreePrinter.printNode(yi2);

    boolean ans = isSubtree(wu,yi2);
    System.out.println(ans);
  }

  /**
   * http://algorithm.yuanbin.me/zh-cn/binary_tree/subtree.html
   * @param T1, T2: The roots of binary tree.
   * @return: True if T2 is a subtree of T1, or false.
   */
  public boolean isSubtree(TreeNode T1, TreeNode T2) {
    // write your code here
    if (T2 == null) {
      return true;
    }
    else if (T1 == null) { // T1 == null && T2 != null
      return false;
    }
    return isSameTree(T1, T2) ||
        isSubtree(T1.left, T2) ||
        isSubtree(T1.right, T2);
  }

  /**
   * http://lintcode.peqiu.com/content/lintcode/subtree.html
   * @param T1
   * @param T2
   * @return
   */
  public boolean isSameTree(TreeNode T1, TreeNode T2) {
    if (T1 == null && T2 == null) {
      return true;
    }
    if (T1 != null && T2 != null) {
      return T1.val == T2.val && isSameTree(T1.left, T2.left)
          && isSameTree(T1.right, T2.right);
    }
    return false;
  }
}
