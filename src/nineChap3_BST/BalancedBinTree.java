package nineChap3_BST;

import misc.BTtreePrinter;
import misc.TreeNode;

public class BalancedBinTree {
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


    TreeNode xin = new TreeNode(3);
    // root.left = yi;
    // root.right = er;
    // er.left = sa;
    // er.right = si;
    // sa.left = wu;
//    root = er;
//    er.left = yi;
//    yi.right = wu;
    
    root = yi;
    root.left = er;
    er.right = sa;
    sa.left = si;
    sa.right = wu;
//    wu.left = li;
//    wu.right = qi;
//    li.left = ba;
//    li.right = ji;

    BTtreePrinter printer = new BTtreePrinter();
    System.out.println("");
    printer.printNode(root);
    System.out.println(SerializeBFS.serializeLintcode(root));
    // boolean ans = isBalancedWrong(root);
    int dif = getDiff(root);
    System.out.println(dif);
  }

  /**
   * Inspired via hehejun! Smart axx, he only return int but it means 2 things: if valid, simply the
   * depth, if not valid, then return -1 which denote invalid recursion.
   * 
   * review post-order DFS or say, Bottom up.
   * @return
   */
  public static int getDiff(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int ldiff = getDiff(root.left);
    int rdiff = getDiff(root.right);
//    if (ldiff == -1 || rdiff == -1)
//      return -1; // if don't prune, then {1,2,#,#,3,4,5,#,#,6,7,8,9} still wrong!
    int diff = Math.abs(ldiff - rdiff);
    return diff = diff > 1 ? -1 : Math.max(ldiff, rdiff) + 1;
  }

  /**
   * Wrong way to do, since this only check the 1st left, right tree. simply recurse to find
   * left/right subtree's height and measure the diff.
   * 
   * @param root
   * @return
   */
  public static boolean isBalancedWrong(TreeNode root) {
    // TODO Auto-generated method stub
    if (root == null)
      return true;
    // if (root.left == null || root.right == null)
    // return true;

    int lh = root.left == null ? 0 : Height(root.left);
    int rh = root.right == null ? 0 : Height(root.right);
    System.err.println(lh + ", " + rh);
    boolean ans = 1 >= Math.abs(lh - rh);
    return ans;
  }

  public static int Height(TreeNode root) {
    if (root == null)
      return 0;
    int lh = Height(root.left);
    int rh = Height(root.right);
    return Math.max(lh, rh) + 1;
  }
}
