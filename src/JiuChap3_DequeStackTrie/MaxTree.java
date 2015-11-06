package JiuChap3_DequeStackTrie;

import java.util.Stack;
import misc.BTtreePrinter;
import misc.TreeNode;

/**
 * http://www.lintcode.com/en/problem/max-tree/
 * Created this class in JiuChap3_DequeStackTrie at 10:16 PM, 11/5/2015.
 */
public class MaxTree {
  public static void main(String[] args) {
    int[] data = new int[]{6,4,20}; //{2,5,6,0,3,1};
    MaxTree mt = new MaxTree();
    TreeNode root = mt.maxTree(data);
    System.out.println(root.toString());
    BTtreePrinter tp = new BTtreePrinter();
    tp.printNode(root);
  }

/* ------------------------------------------------------------------------------------------------
 *                        Scott's code separator
 * ------------------------------------------------------------------------------------------------
 */

  /**
   * JiuChap's code is clear and crystal!
   * @param A
   * @return
   */
  public TreeNode maxTree(int[] A) {
    TreeNode root = null;
    Stack<TreeNode> stack = new Stack<>();
    for (int i = 0; i <= A.length; ++i) {
      TreeNode right = i == A.length ? new TreeNode(Integer.MAX_VALUE) : new TreeNode(A[i]);
      while (!stack.isEmpty()) {
        if (stack.peek().val > right.val) {
          break;
        }
        else {
          TreeNode nodeNow = stack.pop();
          if (stack.isEmpty()) {
            right.left = nodeNow;
          }
          else {
            TreeNode left = stack.peek();
            if (left.val < right.val) {
              left.right = nodeNow;
            }
            else {
              right.left = nodeNow;
            }
          }
        }
      }
      stack.push(right);
    }
    return  stack.peek().left;
  }

/* ------------------------------------------------------------------------------------------------
 *                        I got the idea, howcome the implement is so chaos!
 * ------------------------------------------------------------------------------------------------
 */
  /**
   * @param A: Given an integer array with no duplicates.
   * @return: The root of max tree.
   */
  public TreeNode maxTreeWRONG(int[] A) {
    // write your code here
    TreeNode root = new TreeNode();
    if (A == null || A.length == 0) {
      return root;
    }
    Stack<TreeNode> stack = new Stack<>();
    for (int i = 0; i <= A.length; ++i) {
      if (stack.isEmpty()) {
        stack.push(new TreeNode(A[i]));
        continue;
      }
      if (i == A.length) {
        while (!stack.isEmpty()) {
          TreeNode last = stack.pop();
          if (!stack.isEmpty()) {
            root = stack.peek();
            root.right = last;
          }
          else {
            root = last;

          }
        }
        break;
      }
      if (stack.peek().val > A[i]) {
        stack.push(new TreeNode(A[i]));
      }
      else {
        TreeNode newNode = new TreeNode(A[i]);
        TreeNode node = stack.pop();
        if (!stack.isEmpty()) {
          if (stack.peek().val < newNode.val) {
            stack.peek().right = node;
          }
        }
        else {
          newNode.left = node;
        }
        stack.push(newNode);
      }
    }
    return root;
  }
}
