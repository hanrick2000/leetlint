package freq3_tony;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class BinaryTreeInorder {
  private int N;
  // 只要保存tree的root就行了
  private TreeNode root;

  private class TreeNode {
    private int value;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(int val) {
      this.value = val;
      // 因为我要每新建一个node就自动+N. 所以要access到class
      // BinaryTreeInorder的N, 所以TreeNode就要用Java独有
      // 的直接class, 不要加static, 不然就和C++的一样了
      N++;
    }
  }

  public TreeNode getRoot() {
    System.out.println(root.value);
    return root;
  }

  public void getSize() {
    System.out.println("size = " + N);
  }

  public void buildTree() {
    TreeNode r = new TreeNode(3);
    // Random seed = new Random();
    r.left = new TreeNode(2);
    r.left.left = new TreeNode(1);
    r.left.right = new TreeNode(9);
    r.right = new TreeNode(8);
    r.right.left = new TreeNode(7);
    r.right.right = new TreeNode(6);

    // r.left = new TreeNode(seed.nextInt(10));
    // r.left.left = new TreeNode(seed.nextInt(10));
    // r.left.right = new TreeNode(seed.nextInt(10));
    // r.right = new TreeNode(seed.nextInt(10));
    // r.right.left = new TreeNode(seed.nextInt(10));
    // r.right.right = new TreeNode(seed.nextInt(10));
    // for (int i = 0; i < 5; i++) {
    // Random seed = new Random();
    // TreeNode node = new TreeNode(seed.nextInt(10));
    // }
    //
    this.root = r;
  }

  public static void recPreorder(TreeNode node) {
    // if (node == null) throw new IllegalStateException("Build tree first");
    if (node == null)
      return;
    else if (node != null) {
      System.out.print(node.value + " ");
      recPreorder(node.left);
      recPreorder(node.right);
    }
  }

  public static void recInorder(TreeNode node) {
    if (node == null)
      return;
    recInorder(node.left);
    System.out.print(node.value + " ");
    recInorder(node.right);
  }

  public static void recPostorder(TreeNode node) {
    if (node == null)
      return;
    recPostorder(node.left);
    recPostorder(node.right);
    System.out.print(node.value + " ");
  }

  // Using N00tc0d3r的方法
  public List<Integer> inorderTraveresal(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    if (root == null)
      return res;
    Stack<TreeNode> stack = new Stack<>();
    TreeNode curr = root;
    // find the left most of root, push path into stack
    // after it finished, curr = null;
    while (curr != null) {
      stack.push(curr);
      curr = curr.left;
    }
    // 关键!
    while (!stack.isEmpty()) {
      curr = stack.pop();
      // visit curr: 只在这个地方"print"当前的node
      res.add(curr.value);
      // add right child
      curr = curr.right;
      // find the left most
      while (curr != null) {
        stack.push(curr);
        curr = curr.left;
      }
    }
    return res;
  }

  public static void main(String[] args) {
    BinaryTreeInorder bti = new BinaryTreeInorder();
    bti.buildTree();
    bti.getSize();
    recPostorder(bti.getRoot());
    // recPreorder(bti.getRoot());
    // System.out.println(bti.inorderTraveresal(bti.getRoot()));

  }
}
