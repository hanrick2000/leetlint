package freq3_tony;
import java.util.*;

public class PopulateNextRightPointer {
  private TreeNode root;
  
  /*
   * 注意static inner class和普通inner class的区别. static的类是C++意义上的内类. 而un-static则要
   */
  private static class TreeNode {
    int val;
    TreeNode left, right, next;
    TreeNode(int v) {
      this.val = v;
      this.left = this.right = this.next = null;
    }
  }
  
  /**
   * 使用N00t做的levelOrder的第一个方法写的一个level order
   * @param root
   * @return
   */
  private List<List<Integer>> populateBFS(TreeNode root) {
    if (root == null)
      return null;
    List<List<Integer>> resSets = new ArrayList<List<Integer>>();
    Queue<TreeNode> level = new LinkedList<>();
    level.offer(root);
    level.offer(null);
    while (!level.isEmpty()) {
      List<Integer> res = new ArrayList<>();
      TreeNode cur = level.poll();
      while (cur != null) { // 注意每一层加入一个null作为终止flag
        res.add(cur.val);
        if (cur.left != null)  level.offer(cur.left);
        if (cur.right != null)  level.offer(cur.right);
        cur = level.poll();  // poll to get to the end of a level
      }
      resSets.add(res);
      if (!level.isEmpty())  level.offer(null);
    }
    return resSets;
  }
  
  private TreeNode buildTree() {
    this.root = new TreeNode(1);
    TreeNode a = new TreeNode(2);
    TreeNode b = new TreeNode(3);
    root.left = a; root.right = b;
    TreeNode a1 = new TreeNode(5);
    TreeNode a2 = new TreeNode(6);
    a.left =a1;
    a.right =a2;
    TreeNode b1 = new TreeNode(7);
    TreeNode b2 = new TreeNode(8);
    b.left = b1;
    b.right = b2;
    return root;
  }
  
  public static void main(String[] args) {
    PopulateNextRightPointer bfsTest = new PopulateNextRightPointer();
    bfsTest.buildTree();
    List<List<Integer>> resSets = new ArrayList<List<Integer>>();
    resSets = bfsTest.populateBFS(bfsTest.root);
    for (List<Integer> res : resSets) {
      System.out.println(res);
    }
  }
}
