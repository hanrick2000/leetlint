package nineChap3_BST;

import java.util.ArrayList;

import misc.BTtreePrinter;
import misc.TreeNode;

public class SearchRangeBST {
  private static ArrayList<Integer> result;

  public static void main(String[] args) {

    TreeNode root = new TreeNode(0);
    TreeNode yi = new TreeNode(1);
    TreeNode er = new TreeNode(2);
    TreeNode sa = new TreeNode(3);
    TreeNode si = new TreeNode(4);
    TreeNode wu = new TreeNode(5);
    TreeNode li = new TreeNode(6);
    TreeNode qi = new TreeNode(7);
    TreeNode ba = new TreeNode(8);
    TreeNode ji = new TreeNode(9);
    TreeNode sh = new TreeNode(10);

    root = yi;
    root.left = er;
    er.right = sa;
    sa.left = si;
    sa.right = wu;
    // wu.left = li;
    // wu.right = qi;
    // li.left = ba;
    // li.right = ji;

    BTtreePrinter printer = new BTtreePrinter();
    System.out.println("");
    printer.printNode(root);
    System.out.println(SerializeBFS.serializeLintcode(root));
  }

  /**
   * Nine Chap's solution, the first time I use global var in oa.
   * 
   * @param root
   * @param k1
   * @param k2
   * @return
   */
  public static ArrayList<Integer> searchRange(TreeNode root, int k1, int k2) {
    result = new ArrayList<>();
    helper(root, k1, k2);
    return result;
  }

  public static void helper(TreeNode root, int k1, int k2) {
    if (root == null)
      return;
    if (root.value > k1) {
      helper(root.left, k1, k2);
    }
    if (root.value >= k1 && root.value <= k2) {
      result.add(root.value);
    }
    if (root.value < k2) {
      helper(root.right, k1, k2);
    }
  }
}
