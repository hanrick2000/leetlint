package nineChap3_BST;

import java.util.HashMap;
import java.util.Map;
import misc.BTtreePrinter;
import misc.TreeNode;

/**
 * Created at 10:43 AM on 11/24/15.
 */
public class ConstructBinTreePreIn {
  public static void main(String[] args) {
    new ConstructBinTreePreIn().test();
  }

  public void test() {
    int[] in = new int[] {1, 5, 2, 7, 4, 3};
    int[] pre = new int[] {2,1,5,3,7,4};
    TreeNode root = buildTree(pre, in);
    BTtreePrinter printer = new BTtreePrinter();
    printer.printNode(root);
  }
  /**
   *@param preorder : A list of integers that preorder traversal of a tree
   *@param inorder : A list of integers that inorder traversal of a tree
   *@return : Root of a tree
   */
  public TreeNode buildTree(int[] preorder, int[] inorder) {
    // write your code here
    if (preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0 ||
        preorder.length != inorder.length) {
      return null;
    }
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < inorder.length; ++i) {
      map.put(inorder[i],i);
    }
    TreeNode root = rec(preorder, inorder, 0, preorder.length-1, 0, inorder.length-1, map);
    return root;
  }

  private TreeNode rec(int[] pre, int[] in, int pa, int pb, int ia, int ib, Map<Integer, Integer> map) {
    if (pa > pb || ia > ib) {
      return null;
    }

    int partition = map.get(pre[pa]);
    TreeNode root = new TreeNode(pre[pa]);
    root.left = rec(pre, in, pa+1, pa + (partition - ia), ia, partition-1, map);
    root.right = rec(pre, in, pb - (ib-partition) +1, pb, partition+1, ib, map);
    return root;
  }
}
