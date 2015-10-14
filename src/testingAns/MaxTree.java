package testingAns;

import java.util.Stack;

import misc.BTtreePrinter;
import misc.TreeNode;

public class MaxTree {
  public static void main(String[] args) {
    int[] arr = new int[] {5,4,3,7,0,2,1}; //{2, 5, 6, 0, 3, 1};
    TreeNode root = maxTree(arr);
    BTtreePrinter.printNode(root);
  }

  // 9 chap solution, need to fully understand it!
  public static TreeNode maxTree(int[] arr) {
    if (arr == null || arr.length == 0) {
      return null;
    }

    Stack<TreeNode> stk = new Stack<>();
    TreeNode curNode = null;
    for (int i = 0; i <= arr.length; ++i) {
      if (i < arr.length) {
        curNode = new TreeNode(arr[i]);
      }
      if (i == arr.length) {
        curNode = new TreeNode(Integer.MAX_VALUE);
      }

      while (!stk.isEmpty() && curNode.value > stk.peek().value) {

        TreeNode nodeNow = stk.pop();
        if (!stk.isEmpty()) {
          TreeNode leftNow = stk.peek();
          if (leftNow.value > curNode.value) {
            curNode.left = nodeNow;
          } else {
            leftNow.right = nodeNow;
          }
        } else { // stack is empty
          curNode.left = nodeNow;
        }
      }
      stk.push(curNode);

    }
    return stk.peek().left;
  }
}
