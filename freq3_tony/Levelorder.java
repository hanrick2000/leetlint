package freq3_tony;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import freq3_tony.TreeNode;

/**
 * 题目: 要求output是按照level来分.
 */
public class Levelorder {
  private int N;
  private TreeNode root;

  // public Levelorder(int rootVal) {
  // root = buildTree(rootVal);
  //
  // }

  public Levelorder(TreeNode root) {
    this.root = root;
    // ??? 在class里面的mthod就可以不用加object的使用instance method?
    // System.out.print(levelorderFlag(root));
  }

  // 方法1: 用flag告诉作为end 标志
  public List<List<Integer>> levelorderFlag(TreeNode r) {
    // http://stackoverflow.com/questions/12861726/why-cant-you-have-a-list-of-lists-in-java
    // List<List<Integer>> resSet = new ArrayList<ArrayList<Integer>>;
    List<List<Integer>> resSet = new ArrayList<List<Integer>>();
    if (r == null)
      return resSet;
    // 先判断是否root为空. 如果是有用的root, 才开始new queue.
    Queue<TreeNode> queue = new LinkedList<TreeNode>();
    queue.add(root);
    queue.add(null); // 这个就是flag了:
    while (!queue.isEmpty()) {
      List<Integer> res = new LinkedList<>();
      TreeNode curr = queue.remove();
      while (curr != null) {
        res.add(curr.getvalue());
        if (curr.getleft() != null)
          queue.add(curr.getleft());
        if (curr.getright() != null)
          queue.add(curr.getright());
        curr = queue.remove();
      }
      // when hit null, it means level end.
      resSet.add(res);
      // add NULL flag in the end of current level
      if (!queue.isEmpty())
        queue.add(null);
    }
    return resSet;

  }

  // 方法2: 用两个queue
  public List<List<Integer>> levelorderTwoQ(TreeNode r) {
    List<List<Integer>> resSet = new ArrayList<List<Integer>>();
    Queue<TreeNode> queue = new LinkedList<TreeNode>();
    if (r == null)
      return resSet;
    queue.add(r);
    TreeNode curr = r;
    while (!queue.isEmpty()) {
      List<Integer> res = new LinkedList<>();
      Queue<TreeNode> queueTemp = new LinkedList<>();
      while (!queue.isEmpty()) {
        curr = queue.remove();
        res.add(curr.getvalue());
        if (curr.getleft() != null)
          queueTemp.add(curr.getleft());
        if (curr.getright() != null)
          queueTemp.add(curr.getright());
      }
      // add res of one level
      resSet.add(res);
      // update level to its childrean level
      queue = queueTemp;
    }
    return resSet;
  }

  // private class TreeNode {
  // private int value;
  // private TreeNode left;
  // private TreeNode right;
  //
  // public TreeNode(int val) {
  // this.value = val;
  // N++;
  // }
  // }

  private TreeNode buildTree(int r) {
    root = new TreeNode(r);
    root.setleft(new TreeNode(2));
    // root.setleft(setleft(new TreeNode(1)));
    // root.left.right = new TreeNode(9);
    // root.right = new TreeNode(8);
    // root.right.left = new TreeNode(7);
    // root.right.right = new TreeNode(6);
    return root;
  }

  private TreeNode getRoot() {
    return root;
  }

  public static void main(String[] args) {
    // TreeNode root = new TreeNode(3);
    // Levelorder lo = new Levelorder(root);
    // System.out.println(lo.levelorderFlag(lo.getRoot()));
    // System.out.print(lo.levelorderTwoQ(lo.getRoot()));
  }
}
