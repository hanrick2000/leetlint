package nineChap3_BST;

import misc.BTtreePrinter;
import misc.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * http://www.lintcode.com/en/problem/flatten-binary-tree-to-linked-list/ Created by 12:07 AM on
 * 10/21/2015.
 */
public class flattenTreeLL {
  public static void main(String[] args) {
    new flattenTreeLL().test();
  }

  public void test() {
    TreeNode yi = new TreeNode(1);
    TreeNode er = new TreeNode(2);
    TreeNode sa = new TreeNode(3);
    TreeNode si = new TreeNode(4);
    TreeNode wu = new TreeNode(5);
    TreeNode li = new TreeNode(6);

    yi.left = er;
    yi.right = wu;
    er.left = sa;
    er.right = si;
    wu.right = li;

    flatten(yi);
  }

  /**
   * Ganker's solution, so good
   * @param root
   */
  public void flatten(TreeNode root) {
    // write your code here
    if (root == null) {
      return;
    }

    BTtreePrinter printer = new BTtreePrinter();
    printer.printNode(root);

    List<TreeNode> temp = new ArrayList<>(1);
    temp.add(null); // a must.
    helper(root, temp);
    printer.printNode(root);
  }

  /**
   * http://blog.csdn.net/linhuanmars/article/details/23717703
   * @param root
   * @param pre
   */
  private void helper(TreeNode root, List<TreeNode> pre) {
    if (root == null) {
      return;
    }

    if (pre.get(0) != null) {  // if (pre != null)
      pre.get(0).left = null;
      pre.get(0).right = root;
    }

    TreeNode right = root.right;
    pre.set(0, root);
    //TreeNode right = root.right;
    helper(root.left, pre);
    helper(right, pre);
  }
}
