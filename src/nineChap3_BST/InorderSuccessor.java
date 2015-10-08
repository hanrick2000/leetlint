package nineChap3_BST;

import misc.BTtreePrinter;
import freq1_tony.TreeNode;

public class InorderSuccessor {
  /**
   * https://www.youtube.com/watch?v=5cPbNCrdotA
   * 
   * @param root
   * @param p
   * @return
   */
  public static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
    if (root == null || p == null) {
      return null;
    }

    // case 1: p has right subtree
    if (p.right != null) {
      return findMin(p.right);
    }

    // case 2: p has no right subtree, find closet ancestor that p is in its left subtree
    TreeNode successor = null;
    TreeNode ancestor = root;
    while (ancestor != p) {
      if (ancestor.value > p.value) {
        successor = ancestor;
        ancestor = ancestor.left;
      } else {
        ancestor = ancestor.right;
      }
    }
    return successor;

  }

  private static TreeNode findMin(TreeNode root) {
    while (root.left != null) {
      root = root.left;
    }
    return root;
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(10);
    TreeNode yi = new TreeNode(1);
    TreeNode er = new TreeNode(2);
    TreeNode sa = new TreeNode(3);
    TreeNode li = new TreeNode(6);
    TreeNode qi = new TreeNode(7);
    TreeNode ba = new TreeNode(8);
    TreeNode ji = new TreeNode(9);
    TreeNode sl = new TreeNode(16);
    TreeNode sq = new TreeNode(17);
    TreeNode es = new TreeNode(20);
    TreeNode ew = new TreeNode(25);
    TreeNode eq = new TreeNode(27);

    // root.left = li;
    // root.right = es;
    // li.left = sa;
    // li.right = ji;
    // sa.left = yi;
    // ji.left = qi;
    // qi.right = ba;
    // es.left = sq;
    // es.right = ew;
    // sq.left = sl;
    // ew.right = eq;

    root = yi;
    root.right = er;

    BTtreePrinter.printNode(root);

    TreeNode ans = inorderSuccessor(root, yi);
    System.out.println(ans.value);
  }
}
