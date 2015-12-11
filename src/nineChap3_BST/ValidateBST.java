package nineChap3_BST;

import java.util.ArrayList;
import java.util.List;

import misc.BTtreePrinter;
import misc.TreeNode;

public class ValidateBST {
  public static void main(String[] args) {
    TreeNode root = new TreeNode(0);
    TreeNode yi = new TreeNode(1);
    TreeNode er = new TreeNode(2);
    TreeNode sa = new TreeNode(3);
    TreeNode si = new TreeNode(4);
    TreeNode wu = new TreeNode(5);
    TreeNode li = new TreeNode(6);
    TreeNode sh = new TreeNode(10);
    TreeNode shwu = new TreeNode(15);
    TreeNode ersh = new TreeNode(20);

    // sh.left = wu;
    // sh.right = shwu;
    // shwu.left = li;
    // shwu.right = ersh;

    boolean ans = client(sh);
    BTtreePrinter.printNode(sh);
    System.out.println(ans);

    List<String> ser = new ArrayList<String>();
    //SerDesBinaryTree.writeBinTree(yi, ser);
    // String a = serialize(sh);
    String a = SerializeBFS.serializehehejun(sh);
    System.out.println(a);

    String serstr = "10,5,15,#,#,6,20";
    TreeNode desroot = SerializeBFS.deserializehehejun(serstr);
    misc.BTtreePrinter printer = new misc.BTtreePrinter();
    printer.printNode(desroot);
  }

  /**
   * clent of the isvalidBST since the api has only one param-->root.
   *
   * @param root
   * @return
   */
  public static boolean client(TreeNode root) {
    if (root == null)
      return false;
    return isValidBST(root, new TreeNode[1]);
  }

  /**
   * In-order DFS to validate, idea from ganker
   *
   * @param root
   * @param pre
   * @return
   */
  public static boolean isValidBST(TreeNode root, TreeNode[] pre) {
    if (root == null) {
      return true;
    }
    boolean left = isValidBST(root.left, pre);
    if (pre[0] != null && pre[0].val <= root.val) { // pre[0] != null is not pre != null
      return false;
    }
    pre[0] = root;
    return left && isValidBST(root.right, pre);
  }

  /**
   * Fails at 18/19 test case in Lintcode. This is the wrong way to do. Since
   * left-subtree<root<right-subtree.
   *
   * @param root
   * @return
   */
  public static boolean isValidBSTwrong(TreeNode root) {
    if (root == null) {
      return true;
    }
    // "divide"
    boolean l = true, r = true;

    if (root.left != null) {
      if (root.left.val >= root.val) {
        l = false;
      } else {
        l = isValidBSTwrong(root.left);
      }
    }

    if (root.right != null) {
      if (root.right.val <= root.val) {
        r = false;
      } else {
        r = isValidBSTwrong(root.right);
      }
    }

    // Conquer"
    return l && r;
  }
}
