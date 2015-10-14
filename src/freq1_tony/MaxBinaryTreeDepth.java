package freq1_tony;

import misc.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.List;

/**
 * 这是一个简单的Tree题目. 就是看对递归和iteration, 以及3个dfs和1个bfs熟不熟练.
 * 
 * @author tzhang
 *
 */
public class MaxBinaryTreeDepth {
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

    int ans = maxDepRec(root);
    System.out.println(ans);
    List<List<Integer>> result = levelOrder(root);
    // for (List l : result) {
    // System.out.println(l);
    // }
    // ans = maxDepth(root);
    // System.out.println(ans);
  }

  /**
   * 很简单的左右recursion解决问题.
   * 
   * @param root
   * @return
   */
  public static int maxDepRec(TreeNode root) {
    if (root == null)
      return 0;
    return Math.max(maxDepRec(root.left), maxDepRec(root.right)) + 1;
  }

  /**
   * N00t的BFS解法. 即多少层就多少高度. 看看这里的BFS并不是九章的BFS写法, 即: 1queue. 他用的是1queue+dummyNode(副班长). 所以只是通过一个while
   * loop来判断这一层结束没有. 而BFS模版则是while loop里面加一个for loop遍历这一层.
   * 
   * @param root
   * @return
   */
  public static int maxDepth(TreeNode root) {
    int len = 0;
    Queue<TreeNode> queue = new LinkedList<>();
    if (root != null) {
      queue.offer(root);
      queue.offer(null); // 作为副班长, 表示该层的结尾.
    }

    while (!queue.isEmpty()) {
      TreeNode cur = queue.poll();
      if (cur == null) { // 这一层结束了.
        ++len;
        if (!queue.isEmpty())
          queue.offer(null); // 给下一层加一个副班长
      } else {
        if (cur.left != null)
          queue.offer(cur.left);
        if (cur.right != null)
          queue.offer(cur.right);
      }
    }
    return len;
  }

  /**
   * 九章的BFS模版, 其实就在while queue的时候update 深度就可以了, 感觉很容易. 但是N00t的代码简洁很多.
   * 
   * @param root
   * @return
   */
  public static List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    // List result = new ArrayList(); //In summary, raw types should NEVER be used in new code. You
    // should always use parameterized types.
    int dep = 0;
    if (root == null)
      return result;

    Queue<TreeNode> queue = new LinkedList<TreeNode>();
    queue.offer(root);

    while (!queue.isEmpty()) {
      List<Integer> level = new LinkedList<Integer>();
      int size = queue.size();
      ++dep;
      for (int i = 0; i < size; ++i) {
        TreeNode head = queue.poll();
        level.add(head.value);
        if (head.left != null) {
          queue.offer(head.left);
        }
        if (head.right != null) {
          queue.offer(head.right);
        }
      }
      result.add(level);
    }
    System.out.println(dep);
    return result;
  }
}
