package nineChap3_BST;

import misc.BTtreePrinter;
import misc.TreeNode;

/**
 * http://www.lintcode.com/en/problem/remove-node-in-binary-search-tree/
 * Created at 4:14 PM on 11/28/15.
 */
public class RemoveNodeBST {
  public static void main(String[] args) {
    new RemoveNodeBST().test();
  }

  public void test() {
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

    wu.left = sa;
    wu.right = ba;
    sa.left = er;
    sa.right = si;
    ba.left = li;
    ba.right = sh;
    li.right = qi;
    sh.left = ji;


    BTtreePrinter.printNode(wu);
    TreeNode min = deleteMin(ba);
    System.out.println(min.val);

    BTtreePrinter.printNode(wu);
    //TreeNode ans = removeNode(wu, 3);
    //BTtreePrinter.printNode(ans);
  }
  /**
   * Algs4's algorithm
   * http://hehejun.blogspot.com/2015/01/lintcoderemove-node-in-binary-search.html
   * @param root: The root of the binary search tree.
   * @param value: Remove the node with given value.
   * @return: The root of the binary search tree after removal.
   */
  public TreeNode removeNode(TreeNode root, int value) {
    // write your code here
    if (root == null)
      return null;
    if (value < root.val)
      root.left = removeNode(root.left, value);
    else if (value > root.val)
      root.right = removeNode(root.right, value);
    else {
      if (root.left == null)
        return root.right;
      if (root.right == null)
        return root.left;
      TreeNode x = root;
      root = findMin(root.right);
      root.right = deleteMin(x.right);
      root.left = x.left;
    }
    return root;
  }

  private TreeNode deleteMin(TreeNode x) {
    if (x.left == null) return x.right;
    x.left = deleteMin(x.left);
    //x.N = size(x.left) + size(x.right) + 1;
    return x;
  }

  private TreeNode findMin(TreeNode node)
  {
    if (node.left == null)
      return node;
    else
      return findMin(node.left);
  }
}
