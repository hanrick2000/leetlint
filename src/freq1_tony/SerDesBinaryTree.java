package freq1_tony;

/**
 * serialize的结果可以参考http://fisherlei.blogspot.com/2013/03/interview-serialize-and-de-serialize.html
 */

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import misc.BTtreePrinter;
import misc.TreeNode;

/**
 * http://articles.leetcode.com/2010/09/serializationdeserialization-of-binary.html
 * 
 * @author tzhang
 *
 */

public class SerDesBinaryTree {
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
//    yi.left = wu;
    List<String> ans = new ArrayList<String>();
    writeBinTree(root, ans);
    for (String s : ans) {
      System.out.print(s);
    }
    System.out.println("\ndes----------------------");
    // String des = "0 1 5 # # # 2 3 # # 4 # # ";
    TreeNode res = new TreeNode();
    // int[] index = new int[] {0};
    // readBinTree(res, index, ans);
    readBinTreeTony(res, 0, ans);

    List<String> tree = new ArrayList<String>();
    writeBinTree(root, tree);
    for (String s : tree) {
      System.out.print(s);
    }

    // 测试1337论坛的一个做法, 学习了stringtokenizer的用法. 比split好用.
    // StringTokenizer st = new StringTokenizer("0 1 5 # # # 2 3 # # 4 # # ");
    // TreeNode res = readBinaryTree(st);
    // List<String> ans = new ArrayList<String>();
    // writeBinTree(res, ans);
    // for (String s : ans) {
    // System.out.print(s);
    // }
    BTtreePrinter printer = new BTtreePrinter();
    System.out.println("");
    printer.printNode(root);
  }

  /**
   * 1337的serialize Binary Tree
   * 
   * @param root
   * @param output
   */
  public static void writeBinTree(TreeNode root, List<String> output) {
    if (root == null)
      output.add("# ");
    else {
      output.add(root.value + " ");
      writeBinTree(root.left, output);
      writeBinTree(root.right, output);
    }
  }

  /**
   * 还是水中的鱼的deserialize写的最简洁. 注意这个cnt的加法.
   * 
   * @param root
   * @param cnt
   * @param output
   */
  public static void readBinTree(TreeNode root, int[] cnt, List<String> output) {
    String token = output.get(cnt[0]);
    if (token.equals("# "))
      return;
    token = token.trim(); // 去掉头尾的space
    System.out.println("I got token: [" + token + "]");
    TreeNode p = new TreeNode(Integer.parseInt(token));
    cnt[0] += 1; // 很
    readBinTree(p.left, cnt, output);
    cnt[0] += 1; // 好
    readBinTree(p.right, cnt, output);
  }

  /**
   * 我一开始的写法. 但是cnt自加那个地方我一开始是混乱的. 现在参考的是水中的鱼的写法
   * 
   * @param root
   * @param cnt
   * @param output
   * @return
   */
  public static int readBinTreeTony(TreeNode root, int cnt, List<String> output) {
    String token = output.get(cnt);
    if (token.equals("# "))
      return cnt;
    token = token.trim(); // 去掉头尾的space
    System.out.println("My token: [" + token + "]");
    TreeNode p = new TreeNode(Integer.parseInt(token));
    cnt += 1;
    cnt = readBinTreeTony(p.left, cnt, output);
    cnt += 1;
    cnt = readBinTreeTony(p.right, cnt, output);
    return cnt;
  }

  /**
   * 1337回复中的一个解法
   * 
   * @param root
   * @param left
   * @param reader
   */
  static void readBinaryTreeHelper(TreeNode root, boolean left,
      StringTokenizer reader) {
    String token = reader.nextToken();
    if (token.equals("#"))
      return;
    else {
      TreeNode newNode = new TreeNode(Integer.parseInt(token));
      if (left)
        root.left = newNode;
      else
        root.right = newNode;
      readBinaryTreeHelper(newNode, true, reader);
      readBinaryTreeHelper(newNode, false, reader);
    }
  }

  /**
   * 这个也是可以的.
   * 
   * @param reader
   * @return
   */
  static TreeNode readBinaryTree(StringTokenizer reader) {
    String token = reader.nextToken();
    if (token.equals("#"))
      return null;
    else {
      TreeNode root = new TreeNode(Integer.parseInt(token));
      readBinaryTreeHelper(root, true, reader);
      readBinaryTreeHelper(root, false, reader);
      return root;
    }
  }
}
