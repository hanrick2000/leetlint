package nineChap3_BST;

import misc.BTtreePrinter;
import misc.TreeNode;

/**
 * http://www.lintcode.com/en/problem/tweaked-identical-binary-tree/
 * Created at 7:07 PM on 12/10/15.
 */
public class TweakedIdenticalBinaryTree {
  public static void main(String[] args) {
    String serstr1 = "1,2,3,4,#,#,#"; // "1,2,#,#,3,4,5,#,#,6,7,8,9, #,#";
    String serstr2 = "1,3,2,#,#,#,4";
    TreeNode desroot1 = new SerializeBFS().deserializehehejun(serstr1);
    TreeNode desroot2 = new SerializeBFS().deserializehehejun(serstr2);
    //BTtreePrinter.printNode(desroot1);
    //BTtreePrinter.printNode(desroot2);

    TweakedIdenticalBinaryTree tibt = new TweakedIdenticalBinaryTree();
    boolean ans = tibt.isTweakedIdentical(desroot1, desroot2);
    System.out.println(ans);
  }

  /**
   * @param a, b, the root of binary trees.
   * @return true if they are tweaked identical, or false.
   */
  public boolean isTweakedIdentical(TreeNode a, TreeNode b) {
    // Write your code here
    if (a == null && b == null)  return true;
    if (a == null || b == null)  return false;

    boolean vv = a.val == b.val;
    boolean ll = isTweakedIdentical(a.left, b.left);
    boolean lr = isTweakedIdentical(a.left, b.right);
    boolean rl = isTweakedIdentical(a.right, b.left);
    boolean rr = isTweakedIdentical(a.right, b.right);

    boolean res = vv && (ll && rr || lr && rl);
    return res;
  }
}
