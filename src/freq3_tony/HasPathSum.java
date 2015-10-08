package freq3_tony;

import java.util.LinkedList;
import java.util.List;

/**
 * 读懂题意: 找到root-to-leaf的path的sum = given number. 这里参考的是programcreek.com的解法 法1: 记录所有的path, 以及对应的sum.
 * 法2: 左右子节点的recursion. 因为只求结果, 所以return rec(左) || rec(右)
 * 
 * 法三: 是九章的DFS模版, 见freq3_solution的code
 * 
 * 现在是可以完全理解相对复杂的recursion, 也可以快速分解recursion的path.
 * 但是怎么想到如何写出来呢? 还要多锻炼.
 * 
 * @author tzhang
 *
 */
public class HasPathSum {
  private class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;

    TreeNode(int v) {
      this.value = v;
    }
  }

  /**
   * BFS 的解法, 即保存每个path, 但不是dfs, 而是bfs. 这样一层一层的保存所有的path 于是用queue. 这样就要保存path和sum. 并同时判断有没有解
   * 
   * @param root
   * @param sum
   * @return
   */
  public boolean hasPathSum(TreeNode root, int sum) {
    if (root == null)
      return false;
    List<TreeNode> paths = new LinkedList<>();
    List<Integer> sums = new LinkedList<>();

    paths.add(root);
    sums.add(root.value);

    while (!paths.isEmpty()) {
      TreeNode curr = ((LinkedList<TreeNode>) paths).poll();
      int sumValue = ((LinkedList<Integer>) sums).poll();

      if (curr.left == null && curr.right == null && sumValue == sum) {
        // System.out.println(sums);
        return true;
      }
      if (curr.left != null) {
        paths.add(curr.left);
        sums.add(sumValue + curr.left.value);
      }
      if (curr.right != null) {
        paths.add(curr.right);
        sums.add(sumValue + curr.right.value);
      }
    }

    return false;
  }

  // dfs解法. 即recursion, 方便快捷简单. 其实思路是一样
  public boolean hasPathSumDFS(TreeNode root, int sum) {
    if (root == null)
      return false;
    if (root.value == sum && root.left == null && root.right == null) // 为什不是判断sum==0?
      return true;

    return hasPathSumDFS(root.left, sum - root.value)
        || hasPathSumDFS(root.right, sum - root.value);
  }

  // 九章算法DFS模版. 为什么不像dfs解法那样sum-root.value? 当然可以. 改了之后如下.
  public boolean hasPathSumJiuZhang(TreeNode root, int sum) {
    if (root == null)
      return false;
    // sum = sum-root.value;
    if (root.left == null && root.right == null) {
      System.out.println(root.value + " " + sum);
      if (sum == root.value)  // 注意recursion到这里的时候, 
        return true;
    }
    if (hasPathSumJiuZhang(root.left, sum - root.value))
      return true;
    if (hasPathSumJiuZhang(root.right, sum - root.value))
      return true;
    return false;
  }

  public TreeNode buildTree() {
    TreeNode root = new TreeNode(5);
    TreeNode l1 = new TreeNode(4);
    TreeNode r1 = new TreeNode(8);
    TreeNode a = new TreeNode(11);
    TreeNode b = new TreeNode(13);
    TreeNode c = new TreeNode(4);
    TreeNode e = new TreeNode(7);
    TreeNode d = new TreeNode(2);
    TreeNode f = new TreeNode(1);

    root.left = l1;
    root.right = r1;
    l1.left = a;
    r1.left = b;
    r1.right = c;
    a.left = e;
    a.right = d;
    c.right = f;

    return root;
  }

  public HasPathSum() {
    TreeNode root = buildTree();
    // boolean result = hasPathSum(root, 22);
    // boolean result = hasPathSumDFS(root, 22);
    boolean result = hasPathSumJiuZhang(root, 22);
    System.out.println(result);
  }

  public static void main(String[] args) {
    HasPathSum hps = new HasPathSum();
  }
}
