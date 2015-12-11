package nineChap3_BST;

import misc.BTtreePrinter;
import misc.TreeNode;
import java.util.*;

public class LCA1 {
  public static void main(String[] args) {
    TreeNode root = new TreeNode(0);
    TreeNode yi = new TreeNode(1);
    TreeNode er = new TreeNode(2);
    TreeNode sa = new TreeNode(3);
    TreeNode si = new TreeNode(4);
    TreeNode wu = new TreeNode(5);
    TreeNode sh = new TreeNode(10);
    // root.left = yi;
    // root.right = er;
    // er.left = sa;
    // er.right = si;
    // sa.left = wu;

    yi.right = er;
    er.right = sa;
    sa.right = si;
    si.right = wu;

    BTtreePrinter.printNode(yi);

    TreeNode ans = lcaBU(yi, sa, wu);
    System.out.println(ans.val);

    List<String> ser = new ArrayList<String>();
    //SerDesBinaryTree.writeBinTree(yi, ser);
    System.out.println(ser);
  }

  /*-
   * http://algorithm.yuanbin.me/zh-cn/binary_tree/lowest_common_ancestor.html
   * 1337 and ninechap's solution: bottom-up which is easier than top-down.
   * http://articles.leetcode.com/2011/07/lowest-common-ancestor-of-a-binary-tree-part-i.html
   * 
   * @param root
   * @param A
   * @param B
   * @return
   */
  public static TreeNode lcaBU(TreeNode root, TreeNode A, TreeNode B) {
    if (root == null) { 
      return root;
    }
    if (root.equals(A) || root.equals(B)) {
      return root;
    }
    // "divide"
    TreeNode left = lcaBU(root.left, A, B);
    TreeNode right = lcaBU(root.right, A, B);
    // "conquer"
    // System.out.print(root.val + " ");
    if (left != null && right != null) {
      return root;
    } else
      return left != null ? left : right;
  }
}
