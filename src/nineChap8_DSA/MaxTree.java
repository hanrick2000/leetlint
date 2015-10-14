package nineChap8_DSA;

import misc.TreeNode;
import java.util.Stack;

/**
 * Created by 1:59 AM on 10/13/2015.
 */
public class MaxTree {
    public TreeNode maxTree(int[] A) {
        // write your code here
        if (A == null || A.length == 0) {
            return null;
        }

        Stack<TreeNode> decStk = new Stack<>();
        TreeNode curNode;
        for (int i = 0; i < A.length; ++i) {
            if (i < A.length) {
                curNode = new TreeNode(A[i]);
            }
            else {
                curNode = new TreeNode(-1);
            }

            while (!decStk.isEmpty() && decStk.peek().value < curNode.value) {
                TreeNode tmp = new TreeNode(decStk.pop().value);
                curNode.left = tmp;
                decStk.push(curNode);
            }
            decStk.push(new TreeNode(A[i]));
        }
        return null;
    }
}
