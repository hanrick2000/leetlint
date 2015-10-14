package nineChap3_BST;

import misc.BTtreePrinter;
import misc.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.List;
import java.util.ArrayList;

public class LevelOrder {
  public static void main(String[] args) {
    TreeNode root = new TreeNode(0);
    TreeNode yi = new TreeNode(1);
    TreeNode er = new TreeNode(2);
    TreeNode sa = new TreeNode(3);
    TreeNode si = new TreeNode(4);
    TreeNode wu = new TreeNode(5);
    TreeNode sh = new TreeNode(10);


    TreeNode xin = new TreeNode(3);
    // root.left = yi;
    // root.right = er;
    // er.left = sa;
    // er.right = si;
    // sa.left = wu;
    root = er;
    er.left = yi;
    er.right = wu;

    BTtreePrinter printer = new BTtreePrinter();
    System.out.println("");
    printer.printNode(root);
    ArrayList<ArrayList<Integer>> ans = levelOrder(root);
    // TreeNode r = insertBST(root, xin);
    // List output = new ArrayList();
    // freq1_tony.SerDesBinaryTree.writeBinTree(r, output);
    // System.out.print(output);
    System.out.println("");
    printer.printNode(root);

    for (List l : ans) {
      System.out.println(l);
    }
  }

  public static ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
    // write your code here
    ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
    if (root == null)
      return result;
    Queue<TreeNode> parents = new LinkedList<TreeNode>();
    parents.offer(root);

    while (!parents.isEmpty()) {
      ArrayList<Integer> level = new ArrayList<>();
      int size = parents.size();
      for (int i = 0; i < size; ++i) {
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
}
