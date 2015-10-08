package nineChap3_BST;

import freq1_tony.TreeNode;

import java.util.*;

import misc.BTtreePrinter;

public class InsertBST {
  public static void main(String[] args) {
    TreeNode root = new TreeNode(0);
    TreeNode yi = new TreeNode(1);
    TreeNode er = new TreeNode(2);
    TreeNode sa = new TreeNode(3);
    TreeNode si = new TreeNode(4);
    TreeNode wu = new TreeNode(5);
    TreeNode sh = new TreeNode(10);


    TreeNode xin = new TreeNode(3);
    // root.left = yi;
    // root.right = er;
    // er.left = sa;
    // er.right = si;
    // sa.left = wu;
    root = er;
    er.left = yi;
    // er.right = wu;

    BTtreePrinter printer = new BTtreePrinter();
    System.out.println("");
    printer.printNode(root);
    insertBST2(root, xin);
    // TreeNode r = insertBST(root, xin);
    // List output = new ArrayList();
    // freq1_tony.SerDesBinaryTree.writeBinTree(r, output);
    // System.out.print(output);
    System.out.println("");
    printer.printNode(root);
  }

  /**
   * This is wrong, same as linkedlist. Since it only update the empty root, no link to it!!! eg:
   * {2,1} 3. will show up java.lang.NullPointerException
   * 
   * @param root
   * @param xin
   */
  public static void insertBST1(TreeNode root, TreeNode xin) {
    if (root == null) { //
      // root.value = 99;// will show up java.lang.NullPointerException
      root = new TreeNode(99); // no exception, but no update, why? it only update value, not link!
      return;
    }
    if (root.value > xin.value) {
      insertBST1(root.left, xin);
    } else {
      insertBST1(root.right, xin);
    }
  }

  /**
   * 2nd trial for traverse way to do insert, the diff between 1,2 is that here the base case is
   * leaf's parent. Only in this way, I can update the link.
   *
   * @param root
   * @param xin
   */
  public static void insertBST2(TreeNode root, TreeNode xin) {
    if (root.left == null && root.right == null) {
      if (root.value < xin.value) {
        root.right = xin;
      } else
        root.left = xin;
      return;
    }
    if (root.value > xin.value) {
      if (root.left == null) { //
        root.left = xin;
        return;
      }
      insertBST2(root.left, xin);
    } else {
      if (root.right == null) {
        root.right = xin;
        return;
      }
      insertBST2(root.right, xin);
    }
  }

  /**
   * The best way to solve it is by D&C, that the recursion has return value, which used to update
   * the link.
   *
   * @param root
   * @param xin
   * @return
   */
  public static TreeNode insertBST(TreeNode root, TreeNode xin) {
    if (root == null) {
      root = xin; // now I can use the regular base case for recursion. since the link update is in
                  // the recursion call.
      return root;
    }
    if (root.value > xin.value)
      root.left = insertBST(root.left, xin); //
    else
      root.right = insertBST(root.right, xin);
    return root;
  }
}
