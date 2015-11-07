package JiuChap3_DequeStackTrie;

import java.util.Stack;
import misc.BTtreePrinter;
import misc.TreeNode;

/**
 * Expression tree == MinTree
 * So I reviewed maxTree and write 9chap's algs, very good design. Also draw with invariant
 *
 * Created this class in JiuChap3_DequeStackTrie at 9:24 AM, 11/6/2015.
 */
public class MinTree {
  public static void main(String[] args) {
    int[] data = new int[]{2,5,6,0,3,1};
    int maxData = 6;
    for (int i = 0; i < data.length; ++i) {
      data[i] = maxData - data[i];
    }

    TreeNode root = minTree(data);
    BTtreePrinter treePrinter = new BTtreePrinter();
    treePrinter.printNode(root);
  }

  private static TreeNode minTree(int[] data) {
    TreeNode root = null;
    Stack<TreeNode> decStk = new Stack<>();
    for (int i = 0; i <= data.length; ++i) {
      TreeNode right = i == data.length ? new TreeNode(Integer.MIN_VALUE) : new TreeNode(data[i]);
      while (!decStk.isEmpty()) {
        if (decStk.peek().val < right.val) {
          break;
        }
        else {
          TreeNode now = decStk.pop();
          if (decStk.isEmpty()) {
            right.left = now;
          }
          else {
            TreeNode left = decStk.peek();
            if (left.val < right.val) {
              right.left = now;
            }
            else {
              left.right = now;
            }
          }
        }
      }
      decStk.push(right);
    }
    return decStk.peek().left;
  }
}
