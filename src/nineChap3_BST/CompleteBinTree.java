package nineChap3_BST;

import java.util.LinkedList;
import java.util.Queue;
import misc.BTtreePrinter;
import misc.TreeNode;

/**
 * http://www.lintcode.com/en/problem/complete-binary-tree/
 * Created at 10:32 PM on 11/13/15.
 */
public class CompleteBinTree {
  public static void main(String[] args) throws java.lang.Exception {
    TreeNode root = new TreeNode(5);
    root.left = new TreeNode(10);
    root.right = new TreeNode(15);
    root.left.left = new TreeNode(20);
    //root.left.right = new TreeNode(25);
    root.right.left = new TreeNode(30);
    root.right.right = new TreeNode(35);

    CompleteBinTree i = new CompleteBinTree();
    System.out.println("Breadth First Search : ");
    BTtreePrinter printer = new BTtreePrinter();
    printer.printNode(root);
    boolean ans = i.isComplete(root);
    System.out.println(ans);
  }
  /**
   * @param root, the root of binary tree.
   * @return true if it is a complete binary tree, or false.
   */
  public boolean isComplete(TreeNode root) {
    // Write your code here
    if (root == null) {
      return false;
    }
    Queue<TreeNode> bfsQ = new LinkedList<>();
    bfsQ.offer(root);
    int flag = -1;
    while (!bfsQ.isEmpty()) {
      int len = bfsQ.size();
      for (int i = 0; i < len; ++i) {
        TreeNode cur = bfsQ.poll();
        if (cur == null) {
          if (flag == -1) {
            flag = 0;
          }
          continue;
        }
        else {
          if (flag == 0) {
            return false;
          }
        }

        bfsQ.offer(cur.left);
        bfsQ.offer(cur.right);
      }
    }
    return true;
  }
}
