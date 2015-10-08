package nineChap3_BST;

import java.util.*;

import misc.BTtreePrinter;
import freq1_tony.TreeNode;

public class TraverseDFS {
  public static void main(String[] args) {
    TreeNode root = new TreeNode(0);
    TreeNode yi = new TreeNode(1);
    TreeNode er = new TreeNode(2);
    TreeNode sa = new TreeNode(3);
    TreeNode si = new TreeNode(4);
    TreeNode wu = new TreeNode(5);
    TreeNode li = new TreeNode(6);
    TreeNode qi = new TreeNode(7);
    TreeNode ba = new TreeNode(8);
    TreeNode ji = new TreeNode(9);
    TreeNode sh = new TreeNode(10);

    root = yi;
    root.left = er;
    er.right = sa;
    sa.left = si;
    sa.right = wu;
//    BTtreePrinter printer = new BTtreePrinter();
    System.out.println("");
    BTtreePrinter.printNode(root);
    System.out.println(SerializeBFS.serializeLintcode(root));

    System.out.println("\npre order : ");
    pre(root);
    System.out.println("\nin order : ");
    inorder(root);
    System.out.println("\npost order : ");
    post(root);
    System.out.println("\n level order : ");
    List<List<Integer>> levelorder = new ArrayList<List<Integer>>();
    levelorder = levelDFSClient(root);
    for (List l : levelorder) {
      System.out.println(l);
    }
  }

  /**
   * pre-order DFS
   * 
   * @param root
   */
  public static void pre(TreeNode root) {
    if (root == null)
      return;
    System.out.print(root.value + " ");
    pre(root.left);
    pre(root.right);
  }

  public static void inorder(TreeNode root) {
    if (root == null)
      return;
    inorder(root.left);
    System.out.print(root.value + " ");
    inorder(root.right);
  }

  public static void post(TreeNode root) {
    if (root == null)
      return;
    post(root.left);
    post(root.right);
    System.out.print(root.value + " ");
  }

  /**
   * bfs template fron ninechap
   * 
   * @param root
   * @return
   */
  public static List<List<Integer>> bfs(TreeNode root) {
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    if (root == null)
      return result;

    Queue<TreeNode> parents = new LinkedList<>();
    parents.add(root);
    while (!parents.isEmpty()) {
      List<Integer> level = new ArrayList<>();
      int size = parents.size();
      for (int i = 0; i < size; i++) {
        TreeNode head = parents.poll();
        level.add(head.value);
        if (head.left != null)
          parents.offer(head.left);
        if (head.right != null)
          parents.offer(head.right);
      }
      result.add(level);
    }
    return result;
  }

  /**
   * 
   * @param root
   */
  public static List<List<Integer>> levelDFSClient(TreeNode root) {
    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    levelDFS(root, 1, ans);
    return ans;
  }
  
  /**
   * hehejun's recursion method for level order
   * 
   * @param root
   * @param level
   * @param path
   */
  public static void levelDFS(TreeNode root, int level, List<List<Integer>> result) {
    if (root == null) 
      return;
    if (result.size() < level)
      result.add(new ArrayList<Integer>());
    result.get(level-1).add(root.value);
    levelDFS(root.left, level+1, result);
    levelDFS(root.right, level+1, result);
  }
  
  /**
   * Try to use recursion level order to do serialization, but how to deal with the end level?
   * 
   * @param root
   * @param level
   * @param path
   */
  public static void levelDFSserialize(TreeNode root, int level, List<List<Integer>> result) {
//    if (root == null) 
//      return;
    if (result.size() < level)
      result.add(new ArrayList<Integer>());
    if (root == null) {
      result.get(level-1).add(null);
      return;
    }
    else 
      result.get(level-1).add(root.value);
    levelDFSserialize(root.left, level+1, result);
    levelDFSserialize(root.right, level+1, result);
  }
}
