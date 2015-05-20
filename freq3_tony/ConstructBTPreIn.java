package freq3_tony;

import java.util.*;

/**
 * Construct a Binary tree from inorder and pre-order 小前提: 不能有重复的node 加深理解recursion. 以及解决问题的思考方式.
 * 
 * @author tzhang
 *
 *         还是和以前一样, 参考LC创始人的blog:
 *         http://articles.leetcode.com/2011/04/construct-binary-tree-from-inorder
 *         -and-preorder-postorder-traversal.html
 * 
 */
public class ConstructBTPreIn {
  private int[] preOrd;
  private int[] inOrd;
  private Map<Integer, Integer> inOrdMap;

  // private TreeNode root;

  public ConstructBTPreIn(int[] inorder, int[] preorder) {
    preOrd = preorder; // new int[] {7, 10, 4, 3, 1, 2, 8, 11};
    inOrd = inorder; // new int[] {4, 10, 3, 1, 7, 11, 8, 2};
    if (inorder.length < 1)
      return;
    inOrdMap = new HashMap<>();
    for (int i = 0; i < inorder.length; i++) {
      inOrdMap.put(inOrd[i], i);
    }
//     root = new TreeNode(preOrd[0]);
  }

  private TreeNode BuildTree(int curRoot, int start, int end) {
    TreeNode root = new TreeNode(preOrd[curRoot]);
    if (start < end) {
      int mid = inOrdMap.get(preOrd[curRoot]);
      if (mid > start) {
        root.setleft(BuildTree(curRoot + 1, start, mid - 1));
      }
      if (mid < end) {
        root.setright(BuildTree(curRoot + (mid - start + 1), mid + 1, end));
      }
    }
    return root;
  }

  public static void main(String[] args) {
    int[] pre = new int[] {7, 10, 4, 3, 1, 2, 8, 11};
    int[] in = new int[] {4, 10, 3, 1, 7, 11, 8, 2};
    ConstructBTPreIn cbpi = new ConstructBTPreIn(in, pre);
    TreeNode head = cbpi.BuildTree(0, 0, pre.length-1);
//    head.printTree(head);
    Levelorder lord = new Levelorder(head);
    System.out.print(lord.levelorderTwoQ(head));
  }
}
