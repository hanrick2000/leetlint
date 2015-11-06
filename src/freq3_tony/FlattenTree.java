package freq3_tony;

/**
 * 做提前现看懂题意. 特别是题目给的例子. 这个看的是N00t的2个解法 很好的学习recursion的题目
 * 
 * @author tzhang
 *
 */
public class FlattenTree {
  private class TreeNode {
    private int value;
    private TreeNode left;
    private TreeNode right;

    TreeNode(int v) {
      this.value = v;
      // this.left = null;
      // this.right = null;
    }
  }

  public TreeNode BuildTree() {
    TreeNode root = new TreeNode(1);
    TreeNode l1 = new TreeNode(2);
    TreeNode r1 = new TreeNode(5);
    TreeNode a = new TreeNode(3);
    TreeNode b = new TreeNode(4);
    TreeNode c = new TreeNode(6);

    root.left = l1;
    root.right = r1;
    l1.left = a;
    l1.right = b;
    r1.right = c;
    // l1 = root.left;
    // r1 = root.right;
    // a = l1.left;
    // b = l1.right;
    // c = r1.right;

    return root;
  }

  /**
   * 标准的recursion解法.
   * 
   * @param root
   * @return
   */
  public TreeNode flattenEasyRecursion(TreeNode root) {
    if (root == null)
      return null;
    TreeNode rtree = root.right;
    if (root.left != null) {
      root.right = root.left;
      root.left = null;
      root = flattenEasyRecursion(root.right); // cool
    }
    if (rtree != null) {
      root.right = rtree;
      root = flattenEasyRecursion(root.right); // cool
    }
    return root;
  }

  /**
   * 原来还有这么简洁的iteration解法:
   * http://yishafang.blogspot.com/2015/02/flatten-binary-tree-to-linked-list.html
   * 
   * @param root
   */
  public void flatten(TreeNode root) {
    while (root != null) {
      if (root.left != null) {
        TreeNode p = root.left;
        while (p.right != null) {
          p = p.right;
        }
        p.right = root.right;
        root.right = root.left;
        root.left = null;
      }
      root = root.right;
    }
  }

  public FlattenTree() {
    TreeNode root = BuildTree();
    // TreeNode resroot = flattenEasyRecursion(root);
    // System.out.println(root.val + " " + resroot.val);
    flatten(root);
    TreeNode node = root;
    while (node != null) {
      System.out.print(node.value + " ");
      node = node.right;
    }
  }


  public static void main(String[] args) {
    FlattenTree ft = new FlattenTree();
  }
}
