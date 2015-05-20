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
    er.right = wu;

    BTtreePrinter printer = new BTtreePrinter();
    System.out.println("");
    printer.printNode(root);
    insertBST1(root, xin);
    // TreeNode r = insertBST(root, xin);
    // List output = new ArrayList();
    // freq1_tony.SerDesBinaryTree.writeBinTree(r, output);
    // System.out.print(output);
    System.out.println("");
    printer.printNode(root);
  }

  /**
   * 杩樻槸链夊緢澶氭纺娲? 渚嫔{2,1}锷犲叆3.
   * 
   * @param root
   * @param xin
   */
  public static void insertBST1(TreeNode root, TreeNode xin) {
    if (root == null) { // 绗竴娆″啓娉? 涓轰粈涔堜笉瀵瑰憿: 锲犱负姝ゆ椂鏄痩eaf镄勫瓙鑺傜偣, 骞舵病链塴ink. 镓€浠ユ槸涓€涓绔嬬殑鑺傜偣.
      root.value = 99;//xin;
      return;
    }
    if (root.left == null && root.right == null) {
      if (root.value < xin.value) {
        root.right = xin;
      } else
        root.left = xin;
      return;
    }
    if (root.value > xin.value) {
      insertBST1(root.left, xin);
    } else {
      insertBST1(root.right, xin);
    }
  }
  
  /**
   * 杩樻槸链夊緢澶氭纺娲? 渚嫔{2,1}锷犲叆3.
   * 
   * @param root
   * @param xin
   */
  public static void insertBST3(TreeNode root, TreeNode xin) {
    if (root.left == null && root.right == null) {
      if (root.value < xin.value) {
        root.right = xin;
      } else
        root.left = xin;
      return;
    }
    if (root.value > xin.value) {
      if (root.left == null) { // 鐪嬫潵recursion call node.left/right涔嫔墠閮借鍒ゆ柇鏄惁瀛桦湪镓嶈兘杩涘叆
        root.left = xin;
        return;
      }
      insertBST1(root.left, xin);
    } else {
      if (root.right == null) {
        root.right = xin;
        return;
 }
      insertBST1(root.right, xin);
    }
  }

  /**
   * 姝ｇ‘镄勫啓娉? 涓轰粈涔圱raverse涓嶈, 闱炶鐢―C镄剅ecursion? 鍙傝Algs4镄凯400. 鍏跺疄灏辨槸涔濈珷镄凞FS镄凞C妯＄増.
   * 
   * @param root
   * @param xin
   * @return
   */
  public static TreeNode insertBST(TreeNode root, TreeNode xin) {
    if (root == null) {
      root = xin;
      return root;
    }
    if (root.value > xin.value)
      root.left = insertBST(root.left, xin); // 杩欓噷镄剅eturn鏄叧阌? 鍗冲缓绔嬩简left/right link to the parent
    else
      root.right = insertBST(root.right, xin);
    return root;
  }
}
