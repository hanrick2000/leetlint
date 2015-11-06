package freq3_tony;

import misc.TreeNode;

/**
 * http://www.ninechapter.com/solutions/flatten-binary-tree-to-linked-list/ 这里是使用九章的recursion,
 * 相比于n00t更简单.
 * 但是注意虽然没有return root的问题, 从而不像n00t那样第一个rec的结果会影响第二个rec的的作用点. 但是只要是2个rec,
 * 而每个rec都有可能改变影响下一个rec中要使用的var(这里就是lastNode). 那么就要注意第一个rec会不会不执行的edge case
 * @author tzhang
 *
 */
public class FlattenBinaryTreeNineChapter {
  // 这个是每次
  private TreeNode lastNode = null;

  /**
   * 好好理解这个recursion, 其实就是一个模版, recursion就那么几种变来变去.
   * 
   * @param root
   */
  public void flatten(TreeNode root) {
    if (root == null)
      return;
    if (lastNode != null) {
      lastNode.left = null;
      lastNode.right = root;
    }

    lastNode = root;
    TreeNode right = root.right;
    flatten(root.left);
    // flatten(root.right); // Stack overflow!
    flatten(right); // 太好了, 学到了recursion中很容易会出现Stack overflow的一个原因是死循环! 因为flatten(root.left)没有update
                    // lastNode, 这样lastNode.right又被update为root.right. 因为lastNode == root. 所以一直循环赋值.
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

    return root;
  }

  public FlattenBinaryTreeNineChapter() {
    TreeNode root = BuildTree();
    flatten(root);
    System.out.println(root.val + " " + lastNode.val);
    TreeNode node = root;
    while (node != null) {
      System.out.print(node.val + " ");
      node = node.right;
    }
  }

  public static void main(String[] args) {
    FlattenBinaryTreeNineChapter fn = new FlattenBinaryTreeNineChapter();

  }
}
