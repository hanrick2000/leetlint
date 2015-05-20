package freq3_tony;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreePostorder {

  private int N;
  private TreeNode root;

  private class TreeNode {
    private int val;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(int val) {
      this.val = val;
      N++;
    }
  }

  private TreeNode getRoot() {
    return root;
  }

  private void buildTree(TreeNode r) {
    r.left = new TreeNode(2);
    r.left.left = new TreeNode(1);
    r.left.right = new TreeNode(9);
    r.right = new TreeNode(8);
    r.right.left = new TreeNode(7);
    r.right.right = new TreeNode(6);
    // return r;
    root = r;
  }

  public BinaryTreePostorder(int r) {
    this.root = new TreeNode(r);
    buildTree(root);
    System.out.print(postOrderTraversal(root));
  }

  // N00tc0d3r的方法
  private List<Integer> postOrderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    if (root == null)  return null;
    Stack<TreeNode> stack = new Stack<>();
    Stack<Integer> mirror = new Stack<>();
    TreeNode curr = root;
    while (curr != null) {
//      res.add(curr.val);
      stack.push(curr);
      mirror.push(curr.val);
      curr = curr.right;
    }
    while (!stack.isEmpty()) {
      curr = stack.pop();
      curr = curr.left;
      
      while (curr != null) {
        stack.push(curr);
        mirror.push(curr.val);
        curr = curr.right;
      }
    }
    
    while (!mirror.isEmpty()) {
      res.add(mirror.pop());
    }
    return res;
  }

  public static void main(String[] args) {
    BinaryTreePostorder post = new BinaryTreePostorder(3);
  }
}
