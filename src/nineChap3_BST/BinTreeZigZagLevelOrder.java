package nineChap3_BST;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import misc.BTtreePrinter;
import misc.TreeNode;

/**
 * http://www.lintcode.com/en/problem/binary-tree-zigzag-level-order-traversal/# Created at 3:07 PM
 * on 11/21/15.
 */
public class BinTreeZigZagLevelOrder {
  public static void main(String[] args) {
    TreeNode root = new TreeNode(0);
    TreeNode yi = new TreeNode(3);
    TreeNode er = new TreeNode(2);
    TreeNode sa = new TreeNode(3);
    TreeNode si = new TreeNode(4);
    TreeNode wu = new TreeNode(15);
    TreeNode li = new TreeNode(6);
    TreeNode qi = new TreeNode(7);
    TreeNode ba = new TreeNode(8);
    TreeNode ji = new TreeNode(9);
    TreeNode sh = new TreeNode(10);

    TreeNode xin = new TreeNode(20);

    root = yi;
    root.left = ji;
    ji.right = si;
    root.right = xin;
    xin.left = wu;
    xin.right = qi;

    BTtreePrinter printer = new BTtreePrinter();
    System.out.println("");
    printer.printNode(root);
    ArrayList<ArrayList<Integer>> ans = new BinTreeZigZagLevelOrder().zigzagLevelOrder(root);
    for (ArrayList<Integer> level : ans) {
      System.out.println(level);
    }
  }

  /**
   * A good understand of BFS, simply use 2 stacks instead of queue for the order issue
   * @param root: The root of binary tree.
   * @return: A list of lists of integer include the zigzag level order traversal of its nodes'
   * values
   */
  public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
    ArrayList<ArrayList<Integer>> result = new ArrayList<>();
    if (root == null) {
      return result;
    }
    Stack<TreeNode> curLevel = new Stack<>();
    Stack<TreeNode> nexLevel = new Stack<>();
    Stack<TreeNode> tmp = new Stack<>();
    curLevel.push(root);
    boolean flag = true; // reverse order

    while (!curLevel.isEmpty()) {
      ArrayList<Integer> level = new ArrayList<>();

      while (!curLevel.isEmpty()) {
        TreeNode cur = curLevel.pop();
        level.add(cur.val);
        if (flag) {
          if (cur.left != null) {
            nexLevel.push(cur.left);
          }
          if (cur.right != null) {
            nexLevel.push(cur.right);
          }
        } else {
          if (cur.right != null) {
            nexLevel.push(cur.right);
          }
          if (cur.left != null) {
            nexLevel.push(cur.left);
          }
        }
      }

      result.add(level);
      tmp = curLevel;
      curLevel = nexLevel;
      nexLevel = tmp;
      flag = !flag;
    }

    return result;
  }
  public ArrayList<ArrayList<Integer>> zigzagLevelOrderWRONG(TreeNode root) {
    // write your code here
    ArrayList<ArrayList<Integer>> result = new ArrayList<>();
    if (root == null) {
      return result;
    }

    //Queue<TreeNode> bfsQ = new LinkedList<>();
    Deque<TreeNode> bfsQ = new ArrayDeque<>();
    bfsQ.offer(root);
    int flag = 0;
    while (!bfsQ.isEmpty()) {
      ArrayList<Integer> level = new ArrayList<>();
      int len = bfsQ.size();
      if (flag == 0) {
        for (int i = 0; i < len; ++i) {
          TreeNode cur = bfsQ.poll();
          level.add(cur.val);
          if (cur.right != null) {
            bfsQ.offer(cur.right);
          }
          if (cur.left != null) {
            bfsQ.offer(cur.left);
          }
        }
        flag = 1;
      } else {
        for (int i = len - 1; i >= 0; i--) {
          TreeNode cur = bfsQ.pollLast();
          level.add(cur.val);
          if (cur.left != null) {
            bfsQ.offer(cur.left);
          }
          if (cur.right != null) {
            bfsQ.offer(cur.right);
          }
        }
        flag = 0;
      }
      result.add(level);
    }
    return result;
  }
}
