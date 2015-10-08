package freq3_tony;
/**
 * 还是Path sum的变种题目. 这个是找最大值. 咦: 最大->maxPQ
 * 而且这里的要求是任意一条path, 不必从root, 因为root可能为负数.
 * 
 * 这道题最主要是搞懂题意, 分析好: 每一个节点的时候的最大值有4种情况:
 *  1. root
 *  2/3. root+max(left, right)
 *  4. root+left+right;
 * 自然是要计算到leaf才知道每个node情况. 所以要
 * http://n00tc0d3r.blogspot.com/2013/01/tree-path-sum.html
 * 
 * https://leetcodenotes.wordpress.com/2013/11/04/leetcode-binary-tree-maximum-path-sum-%E6%A0%91%E4%B8%AD%E4%BB%BB%E6%84%8F%E4%B8%A4%E7%82%B9%E9%97%B4%E6%9C%80%E5%A4%A7path-sum/
 * @author tzhang
 *
 */
public class BinaryTreeMaxPathSum {
  // 解法1: 使用一个新的object记录当前的Max partial path sum跟current max path sum. 注意分清楚这2个的区别.
  private class Data {
    int path = 0;
    int sum = Integer.MIN_VALUE;
  }
  
  public int maxPathSum(TreeNode root) {
    Data data = maxSubPath(root);
    return data.sum;
  }
  
  private Data maxSubPath(TreeNode root) {
    // code before recursion call happening Down the tree
    Data data = new Data();
    if (root == null)  return data;
    
    Data l = maxSubPath(root.left);
    Data r = maxSubPath(root.right);
    
    // code after recursion call happening UP the tree
    data.path = Math.max(0, Math.max(l.path, r.path));  // Max Partial path sum有三种可能: 左, 右, 不左不右
    data.sum = Math.max(Math.max(l.sum, r.sum), l.path+root.value+r.path); // current max path sum有三种可能: arch, 左, 右.
    return data;
  }
}
