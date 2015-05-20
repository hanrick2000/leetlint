package freq3_tony;

import java.util.*;

/**
 * path sum的变形, 也就是recursion的时候多个记录path的container. 
 * 关键又是一个理解recursion的好题: recursion call之前的代码可以理解为happening on the way DOWN the tree,
 * 而recursion call之后的代码理解为为happening on the way UP the tree. 见Algs4 : p401
 * http://n00tc0d3r.blogspot.com/2013/01/tree-path-sum.html
 * @author tzhang
 *
 */
public class FindAllPathSum {
  // 相当于algs client.
  public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
    ArrayList<ArrayList<Integer>> resSet = new ArrayList<>();
    findPathSum(root, sum, new ArrayList<Integer>(), resSet);
    return resSet;
  }

  // dfs 模版: 标准的汉堡包: pre, post. 不过不像inorder那样有个between recursion.
  private void findPathSum(TreeNode root, int sum,
      ArrayList<Integer> path, ArrayList<ArrayList<Integer>> resSet) {
    if (root == null) {
      return;
    }
    path.add(root.value);  // 很好理解, 花了图就知道, 是happening down the tree.
    if (root.left == null && root.right == null && sum == root.value) {
      // 因为Java都是pass by reference. 所以任何对于path的改动都会影响到resSet
      ArrayList<Integer> currPath = new ArrayList<Integer>(path);
      resSet.add(currPath);
    }
    findPathSum(root.left, sum-root.value, path, resSet);
    findPathSum(root.right, sum-root.value, path, resSet);
    path.remove(path.size()-1);   // 很好理解, 即画了图之后就只到, 这时候就真的happen up the tree, 吐出来. 
  }
}
