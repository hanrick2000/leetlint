package freq3_tony;

import misc.TreeNode;

import java.util.Stack;

/**
 * 熟练了recursion的目的是理解直观的解法. 最终还是要转化为stack来做. 不然都会stack overflow
 * 同理, 我的stanford的Dijkstra也要用Stack写. 改stack size是不scalable的.
 * 
 * http://n00tc0d3r.blogspot.com/2013/03/flatten-binary-tree-to-linked-list-in.html
 * @author tzhang
 *
 */
public class FlattenTreebyStack {
  public void flatten(TreeNode root) {
    // 一进来就要先保存root?
    TreeNode cur = root;
    Stack<TreeNode> rtrees = new Stack<>();
    while (cur != null) {
      // finishing flatten left subtree 
      while (cur.left != null) {
        if (cur.right != null)  rtrees.push(cur.right);
        cur.right = cur.left;
        cur.left = null;
        cur = cur.right;
      }
      
      // flatten stored right subtree
      // 为什么要判断cur.right是否为空呢?
      if (cur != null && cur.right == null && rtrees.isEmpty()) {
        cur.right = rtrees.pop();
      }
      // 注意要update cur node
      cur = cur.right;
    }
  }
}
