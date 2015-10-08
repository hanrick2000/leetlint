package nineChap3_BST;

import misc.BTtreePrinter;
import freq1_tony.TreeNode;

public class BinTreeMaxPathSum {
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
    yi.left = er;
    yi.right = sa;
    
    BTtreePrinter printer = new BTtreePrinter();
    System.out.println("");
    printer.printNode(root);
    System.out.println(SerializeBFS.serializeLintcode(root));
    // boolean ans = isBalancedWrong(root);
    int[] ans = maxPathSum(root);
    System.out.println(ans[0] + ", " + ans[1]);
  }

  /**
   * 
   * @param root
   * @return
   */
  public static int[] maxPathSum(TreeNode root) {
    if (root == null) {
      return new int[] {0, Integer.MIN_VALUE};
    }
    int[] left = maxPathSum(root.left);
    int[] right = maxPathSum(root.right);

    int singlePath = Math.max(left[0], right[0]) + root.value;
    singlePath = Math.max(singlePath, 0);

    int arcPath = Math.max(left[1], right[1]);
    arcPath = Math.max(arcPath, left[0] + right[0] + root.value);

    return new int[] {singlePath, arcPath};
  }
}
