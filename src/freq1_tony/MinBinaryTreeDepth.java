package freq1_tony;

import misc.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class MinBinaryTreeDepth {
  public static void main(String[] args) {
    TreeNode root = new TreeNode(0);
    TreeNode yi = new TreeNode(1);
    TreeNode er = new TreeNode(2);
    TreeNode sa = new TreeNode(3);
    TreeNode si = new TreeNode(4);
    TreeNode wu = new TreeNode(5);
    root.left = yi;
    root.right = er;
    er.left = sa;
    er.right = si;
    sa.left = wu;

    int ans = minDepBFS(root);
    System.out.println(ans);

  }

  /**
   * 我的BFS写法. 
   * @param root
   * @return
   */
  public static int minDepBFS(TreeNode root) {
    if (root == null) {
      return 0;
    }
    Queue<TreeNode> queue = new LinkedList<TreeNode>();
    queue.offer(root);
    int dep = 0;
    while (!queue.isEmpty()) {
      int size = queue.size();
      ++dep;
      for (int i = 0; i < size; ++i) {
        TreeNode cur = queue.poll();
        if (cur.left == null && cur.right == null) {
          return dep;
        }
        if (cur.left != null)  queue.offer(cur.left);
        if (cur.right != null)  queue.offer(cur.right);
      }
    }
    return dep;
  }
  
  
  public static int minDepth(TreeNode root) {  
    if(root == null)  
        return 0;  
    if(root.left == null)  
        return minDepth(root.right)+1;  
    if(root.right == null)  
        return minDepth(root.left)+1;  
    return Math.min(minDepth(root.left),minDepth(root.right))+1;  
}  
}
