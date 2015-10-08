package freq3_tony;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreePreorder {
  private int N;
  private TreeNode root;

  public TreeNode getRoot() {
    return root;
  }
  
  public BinaryTreePreorder(int rootVal) {
    TreeNode r = new TreeNode(rootVal);
    buildTree(r);
    System.out.print(preOrderTraversal(r));
    
  }
  // stack based pre-order traversal
  public List<Integer> preOrderTraversal(TreeNode r) {
    List<Integer> res = new ArrayList<>();
    if (r == null)  return res;
    Stack<TreeNode> stack = new Stack<>();
    TreeNode curr = getRoot();
    while (curr != null) {
      // 因为preorder是先visit 当前的node
      res.add(curr.value);
      stack.push(curr);
      curr = curr.left;
    }
    while (!stack.isEmpty()) {
      curr = stack.pop();
      // goto the right subtree
      curr = curr.right;
      while (curr!=null) {
        // visit当亲的node, 重复第一个while
        res.add(curr.value);
        stack.push(curr);
        curr = curr.left;
      }
    }
    return res;
  }
  
  private class TreeNode {
    private int value;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(int val) {
      this.value = val;
      N++;
    }
  }

  // 如果这里用了static, 那么会报错.
  private void buildTree(TreeNode r) {
    r.left = new TreeNode(2);
    r.left.left = new TreeNode(1);
    r.left.right = new TreeNode(9);
    r.right = new TreeNode(8);
    r.right.left = new TreeNode(7);
    r.right.right = new TreeNode(6);
//    return r;
    root = r;
  }
  
  public static void main(String[] args) {
    BinaryTreePreorder btp = new BinaryTreePreorder(3);
    
  }
}
