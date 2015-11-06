package nineChap3_BST;

import misc.TreeNode;
import java.util.*;

public class Preorder {
  public static void main(String[] args) {
    TreeNode root = new TreeNode(0);
    TreeNode yi = new TreeNode(1);
    TreeNode er = new TreeNode(2);
    TreeNode sa = new TreeNode(3);
    TreeNode si = new TreeNode(4);
    TreeNode wu = new TreeNode(5);
    // root.left = yi;
    // root.right = er;
    // er.left = sa;
    // er.right = si;
    // sa.left = wu;
    root = yi;
    yi.right = er;
    er.right = sa;

    ArrayList<Integer> res = preOrderDC(root);
    System.out.println(res);
  }

  /**
   * The recursion in Traverse has no return val, so I had to make a dfs helper, and a wrapper for
   * it.
   * 
   * @param root
   * @return
   */
  public static ArrayList<Integer> preOrderRec(TreeNode root) {
    ArrayList<Integer> res = new ArrayList<>();
    dfs(root, res);
    return res;
  }

  /**
   * Traverse call dfs()
   * 
   * @param root
   * @param result
   */
  private static void dfs(TreeNode root, ArrayList<Integer> result) {
    if (root == null) {
      return;
    }
    result.add(root.val);
    dfs(root.left, result);
    dfs(root.right, result);
  }

  /**
   * Divide & Conquer. null vs Null List?
   * 
   * @param root
   * @return
   */
  public static ArrayList<Integer> preOrderDC(TreeNode root) {
    ArrayList<Integer> res = new ArrayList<>();
    // Notice here, Null vs Null object. So I need to return a null list, and it prevents the nullpointer exception!
    if (root == null) {
      res.add(null); // <Effective Java> item 41 
      return res;
    }

    // "divide" :
    ArrayList<Integer> lefts = preOrderDC(root.left);
    ArrayList<Integer> rights = preOrderDC(root.right);

    // "conquer" : 
    res.add(root.val);
    res.addAll(lefts);
    res.addAll(rights);
    return res;
  }
}
