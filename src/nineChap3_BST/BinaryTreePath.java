package nineChap3_BST;

import java.util.ArrayList;
import java.util.List;
import misc.BTtreePrinter;
import misc.TreeNode;

/**
 * Created at 10:53 AM on 11/24/15.
 */
public class BinaryTreePath {
  public static void main(String[] args) {
    TreeNode root = new TreeNode(0);
    TreeNode yi = new TreeNode(3);
    TreeNode er = new TreeNode(2);
    TreeNode sa = new TreeNode(3);
    TreeNode si = new TreeNode(4);
    TreeNode wu = new TreeNode(15);
    TreeNode li = new TreeNode(6);
    TreeNode qi = new TreeNode(7);
    TreeNode ba = new TreeNode(8);
    TreeNode ji = new TreeNode(9);
    TreeNode sh = new TreeNode(10);

    TreeNode xin = new TreeNode(20);

    root = yi;
    root.left = ji;
    ji.right = si;
    root.right = xin;
    xin.left = wu;
    xin.right = qi;

    BTtreePrinter printer = new BTtreePrinter();
    System.out.println("");
    printer.printNode(root);
    BinaryTreePath btp = new BinaryTreePath();
    List<String> ans = btp.binaryTreePaths(root);
    System.out.println(ans);
  }
  /**
   * @param root the root of the binary tree
   * @return all root-to-leaf paths
   */
  public List<String> binaryTreePaths(TreeNode root) {
    // Write your code here
    List<String> result = new ArrayList<>();
    if (root == null) {
      return result;
    }
    rec(root, result, new StringBuilder());
    return result;
  }

  /**
   * https://leetcode.com/discuss/52250/binary-tree-paths-dfs-java-solution
   * @param root
   * @param result
   * @param sb
   */
  private void rec(TreeNode root, List<String> result, StringBuilder sb) {
    if (root.left == null || root.right == null) {
      sb.append(root.val);
      result.add(sb.toString());
      return;
    }
    sb.append(root.val+"->");
    if (root.left != null) {
      rec(root.left, result, new StringBuilder(sb));
    }
    if (root.right != null) {
      rec(root.right, result, new StringBuilder(sb));
    }
  }

  private void recMESSY(TreeNode root, List<String> result, StringBuilder sb) {
    int leng = 0;
    if (root.left == null && root.right == null) {
      result.add(sb.toString() + "->" + root.val);
      leng = ("->"+root.val).length();
      sb.delete(sb.length()-leng+1, sb.length()-1);
      return;
    }
    if (sb.length() == 0) {
      sb.append(root.val);
      leng = (root.val + "").length();
    }
    else {
      sb.append("->" + root.val);
      leng = ("->" + root.val).length();
    }
    if (root.left != null) {
      recMESSY(root.left, result, sb);
    }
    if (root.right != null) {
      recMESSY(root.right, result, sb);
    }
    sb.delete(sb.length()-leng+1, sb.length()-1);
  }
}
