package freq3_tony;

import misc.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 用Construct Binary Tree from preOrd and inOrder思路就能解决了
 * 
 * @author tzhang
 *
 */
public class ConstructBTPostIn {
  private int[] postOrd;
  private int[] inOrd;
  private Map<Integer, Integer> inOrdMap;

  // private TreeNode root;

  public ConstructBTPostIn(int[] inorder, int[] postorder) {
    postOrd = postorder; // new int[] {7, 10, 4, 3, 1, 2, 8, 11};
    inOrd = inorder; // new int[] {4, 10, 3, 1, 7, 11, 8, 2};
    if (inorder.length < 1)
      return;
    inOrdMap = new HashMap<>();
    for (int i = 0; i < inorder.length; i++) {
      inOrdMap.put(inOrd[i], i);
    }
    // root = new TreeNode(preOrd[0]);
  }
  
  /**
   * 直接从contruct BT from inorder and preorder 改改就可以了. 
   * @param cur : position of this recursion's root place in post-order
   * @param start : left most place of this recur's range in post-order
   * @param end : rightt most place of this recur's range in post-order
   * @return
   */
  private TreeNode buildSubTreePost(int cur, int start, int end) {
    if (start > end)  return null;
    TreeNode root = new TreeNode(postOrd[cur]);
    int mid = inOrdMap.get(root.val);
    if (mid < end) { // 想象这里为什么不能是mid < start.
      root.right = (buildSubTreePost(cur-1, mid+1, end));
    }
    if (mid > start) {
      root.left = (buildSubTreePost(cur-(end-mid+1), start, mid-1));
    }
    return root;
  }
  
  public static void main(String[] args) {
    int[] post = new int[] {4, 1, 3, 10, 11, 8, 2, 7};
    int[] in = new int[] {4, 10, 3, 1, 7, 11, 8, 2};
    ConstructBTPostIn cbpi = new ConstructBTPostIn(in, post);
    TreeNode head = cbpi.buildSubTreePost(post.length-1, 0, post.length-1);
//    head.printTree(head);
    Levelorder lord = new Levelorder(head);
    System.out.print(lord.levelorderTwoQ(head));
  }
}
