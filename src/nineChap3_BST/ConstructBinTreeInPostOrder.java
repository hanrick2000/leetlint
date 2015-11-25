package nineChap3_BST;

import java.util.HashMap;
import java.util.Map;
import misc.BTtreePrinter;
import misc.TreeNode;

/**
 * http://www.lintcode.com/en/problem/construct-binary-tree-from-inorder-and-postorder-traversal/
 * Created at 5:29 PM on 11/22/15.
 */
public class ConstructBinTreeInPostOrder {
  public static void main(String[] args) {
    new ConstructBinTreeInPostOrder().test();
  }

  public void test() {
    int[] in = new int[] {1, 5, 2, 7, 4, 3};
    int[] post = new int[] {5, 1, 4, 7, 3, 2};
    TreeNode root = buildTree(in, post);
  }

  /**
   * @param inorder : A list of integers that inorder traversal of a tree
   * @param postorder : A list of integers that postorder traversal of a tree
   * @return : Root of a tree
   */
  public TreeNode buildTree(int[] inorder, int[] postorder) {
    // write your code here
    if (inorder == null || postorder == null || inorder.length != postorder.length) {
      return null;
    }
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < inorder.length; ++i) {
      map.put(inorder[i],i);
    }
    TreeNode root = rec(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1, map);
    BTtreePrinter printer = new BTtreePrinter();
    printer.printNode(root);
    return root;
  }

  /**
   * http://articles.leetcode.com/2011/04/construct-binary-tree-from-inorder-and-preorder-postorder-traversal.html
   * Improved from O(n^2) to O(N) by using hashmap, so O(1) search.
   * O(N) here is: need to traverse all the nodes, so O(N)
   * @param in
   * @param post
   * @param ia
   * @param ib
   * @param pa
   * @param pb
   * @param map
   * @return
   */
  public TreeNode rec(int[] in, int[] post, int ia, int ib, int pa, int pb, Map<Integer, Integer> map) {
    if (ia > ib || pa > pb) {
      return null;
    }
    if (pa == pb) {
      return new TreeNode(post[pb]);
    }
    //int partition = find(in, post[pb]);
    int partition = map.get(post[pb]);
    TreeNode root = new TreeNode(post[pb]);
    root.left = rec(in, post, ia, partition-1, pa, pa + partition - ia -1, map);
    root.right = rec(in, post, partition+1, ib, pb - (ib - partition), pb -1, map);
    return root;
  }

  public TreeNode recMESSY(int[] in, int[] post, int a, int b) {
    if (a <= -1 || b >= post.length) {
      return null;
    }
    if (a >= b) {
      return new TreeNode(post[b]);
    }
    TreeNode root = new TreeNode(post[b]);
    int partition = find(in, post[b]);
    int leftSize = partition - a;
    int rightSize = b - partition;
    TreeNode left = recMESSY(in, post, a, leftSize - 1);
    TreeNode right = recMESSY(in, post, b - rightSize, b - 1);
    root.left = left;
    root.right = right;
    return root;
  }

  private int find(int[] in, int root) {
    for (int i = 0; i < in.length; ++i) {
      if (in[i] == root) {
        return i;
      }
    }
    return -1;
  }
}
