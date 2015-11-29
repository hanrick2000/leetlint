package nineChap3_BST;

import java.util.ArrayList;
import java.util.List;
import misc.BTtreePrinter;
import misc.TreeNode;

/**
 * http://www.lintcode.com/en/problem/binary-tree-path-sum/
 * Created at 5:01 PM on 11/28/15.
 */
public class BinTreePathSum {
  public static void main(String[] args) {
    BinTreePathSum btp = new BinTreePathSum();
    btp.test();
  }

  public void test() {
    TreeNode yi = new TreeNode(1);
    TreeNode er1 = new TreeNode(2);
    TreeNode er2 = new TreeNode(2);
    TreeNode sa = new TreeNode(3);
    TreeNode si = new TreeNode(4);

    yi.left = er1;
    yi.right = si;
    er1.left = er2;
    er1.right = sa;

    BTtreePrinter.printNode(yi);

    List<List<Integer>> result = binaryTreePathSum(yi, 5);
    for (List<Integer> list : result) {
      System.out.println(list);
    }
  }
  /**
   * @param root the root of binary tree
   * @param target an integer
   * @return all valid paths
   */
  public List<List<Integer>> binaryTreePathSum(TreeNode root, int target) {
    // Write your code here
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) {
      return result;
    }
    List<Integer> path = new ArrayList<>();
    path.add(root.val);
    //rec(root, target, result, path);
    rec9chap(root, target - root.val, result, path);
    return result;
  }

  private void rec(TreeNode root, int target, List<List<Integer>> result, List<Integer> path) {
    if (root == null) {
      return;
    }
    if (target == root.val) {
      path.add(root.val);
      result.add(new ArrayList<>(path));
      path.remove(path.size()-1);
      return;
    }
    if (root.left != null) {
      path.add(root.val);
      rec(root.left, target - root.val, result, path);
      path.remove(path.size()-1);
    }
    if (root.right != null) {
      path.add(root.val);
      rec(root.right, target - root.val, result, path);
      path.remove(path.size()-1);
    }
  }

  private void rec9chap(TreeNode root, int target, List<List<Integer>> result, List<Integer> path) {
    if (root.left == null && root.right == null) {
      if (target == 0) {
        result.add(new ArrayList<>(path));
      }
      return;
    }
    if (root.left != null) {
      path.add(root.left.val);
      //target -= root.left.val;
      rec9chap(root.left, target - root.left.val, result, path);
      //target += root.left.val;
      path.remove(path.size()-1);
    }
    if (root.right != null) {
      path.add(root.right.val);
      //target -= root.right.val;
      rec9chap(root.right, target - root.right.val, result, path);
      //target += root.right.val;
      path.remove(path.size()-1);
    }
  }
}
