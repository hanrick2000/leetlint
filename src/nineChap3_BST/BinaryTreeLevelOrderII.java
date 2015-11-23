package nineChap3_BST;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import misc.BTtreePrinter;
import misc.TreeNode;

/**
 * http://www.lintcode.com/en/problem/binary-tree-level-order-traversal-ii/
 * Created at 5:19 PM on 11/22/15.
 */
public class BinaryTreeLevelOrderII {
  public static void main(String[] args) {
    new BinaryTreeLevelOrderII().test();
  }

  public void test() {
    TreeNode yi = new TreeNode(1);
    TreeNode er = new TreeNode(2);
    TreeNode sa = new TreeNode(3);
    TreeNode si = new TreeNode(4);
    TreeNode wu = new TreeNode(5);

    yi.left = er;
    yi.right = sa;
    sa.left = si;
    sa.right = wu;

    new BTtreePrinter().printNode(yi);
    ArrayList<ArrayList<Integer>> result = levelOrderBottom(yi);
    for (ArrayList<Integer> list : result) {
      System.out.println(list);
    }
  }
  /**
   * @param root: The root of binary tree.
   * @return: buttom-up level order a list of lists of integer
   */
  public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root) {
    // write your code here
    ArrayList<ArrayList<Integer>> result = new ArrayList<>();
    if (root == null) {
      return result;
    }
    Queue<TreeNode> bfsQ = new LinkedList<>();
    bfsQ.offer(root);

    while (!bfsQ.isEmpty()) {
      int len = bfsQ.size();
      ArrayList<Integer> curLevel = new ArrayList<>();
      for (int i = 0; i < len; ++i) {
        TreeNode cur = bfsQ.poll();
        curLevel.add(cur.val);
        if (cur.left != null) {
          bfsQ.offer(cur.left);
        }
        if (cur.right != null) {
          bfsQ.offer(cur.right);
        }
      }
      // http://stackoverflow.com/questions/12949690/java-arrays-how-to-add-elements-at-the-beginning
      result.add(0, curLevel);
    }
    return result;
  }
}
