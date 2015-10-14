package freq3_tony;

import misc.TreeNode;

/**
 * 晕. 要读懂题意. http://n00tc0d3r.blogspot.com/2013/06/sum-root-to-leaf-numbers.html 简单的Sum Path的变形:
 * 求所有root-to-leaf path的值的和. 例如 : 1 / \ 2 3
 * 
 * 那么左右分别组成: 12, 13. 那么应该sum的值 12+13 = 25.
 * 
 * @author tzhang
 *
 */
public class SumRootLeafNum {
  private TreeNode node;

  // 错误理解题意的tttttt解法
  public boolean sumRLNttttttt(TreeNode node, int sum) {
    if (node == null)
      return false;
    if (node.left == null && node.right == null) {
      if (node.value == sum)
        return true;
    }
    if (sumRLNttttttt(node.left, sum - node.value * 10))
      return true;
    if (sumRLNttttttt(node.right, sum - node.value * 10))
      return true;
    return false;
  }

  // n00t的解法, 就是DFS, 不过不要老想着DFS就2个参数: root和sum. 可以加上很多啊, 例如backtracking模版
  public int sumNumHelper(TreeNode node, int path, int sum) {
    if (node.left == null && node.right == null) {
      return sum + path;
    }
    if (node.left != null) {
      sum = sumNumHelper(node.left, path * 10 + node.left.value, sum);
    }
    if (node.right != null) {
      sum = sumNumHelper(node.right, path * 10 + node.right.value, sum);
    }
    return sum;
  }
  
  public int sumNumbers(TreeNode root) {
    if (root == null)  return 0;
    return sumNumHelper(root, root.value, 0);
  }

  public TreeNode buildTree() {
    node = new TreeNode(1);
    TreeNode l1 = new TreeNode(2);
    TreeNode r1 = new TreeNode(3);
    node.left = l1;
    node.right = r1;
//    l1.left = l1.right = null;
//    r1.left = r1.right = null;
    return node;
  }

  public SumRootLeafNum() {
    buildTree();
//    boolean result = sumRLNttttttt(root, 13);
    int result = sumNumbers(node); //sumNumHelper(root, root.value, 0);
    System.out.println(result);
  }

  public static void main(String[] args) {
    SumRootLeafNum srln = new SumRootLeafNum();

  }
}
