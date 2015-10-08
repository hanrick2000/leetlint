package nineChap3_BST;

import java.util.Stack;

import misc.BTtreePrinter;
import freq1_tony.TreeNode;

public class IteratorBST {
  public static void main(String[] args) {
    TreeNode lin = new TreeNode(0);
    TreeNode yi = new TreeNode(1);
    TreeNode er = new TreeNode(2);
    TreeNode sa = new TreeNode(3);
    TreeNode si = new TreeNode(4);
    TreeNode wu = new TreeNode(5);

    TreeNode root = sa;
    sa.left = yi;
    sa.right = wu;
    yi.left = lin;
    yi.right = er;
    wu.left = si;

    BTtreePrinter printer = new BTtreePrinter();
    System.out.println("");
    printer.printNode(root);
//    System.out.println(SerializeBFS.serializeLintcode(root));
    
    IteratorBST ibst = new IteratorBST(root);
  }
  
  public IteratorBST(TreeNode root){
    Solution iterator = new Solution(root);
    while (iterator.hasNext()) {
      TreeNode node = iterator.next();
      System.out.println(node.value);
    }
  }

  class Solution {
    private Stack<TreeNode> stack = new Stack<TreeNode>(); // http://stackoverflow.com/questions/5552117/implementing-stack-using-linked-lists
    private TreeNode curt;

    // @param root: The root of binary tree.
    public Solution(TreeNode root) {
      // write your code here
      curt = root;
    }

    // @return: True if there has next node, or false
    public boolean hasNext() {
      // write your code here
      return (curt != null || !stack.isEmpty());
    }

    // @return: return next node
    public TreeNode next() {
      // write your code here
      while (curt != null) {
        stack.push(curt);
        curt = curt.left;
      }
      curt = stack.pop();
      TreeNode node = curt;
      curt = curt.right;
      return node;
    }
  }
}
