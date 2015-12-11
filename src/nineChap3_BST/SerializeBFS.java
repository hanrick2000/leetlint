package nineChap3_BST;

import java.util.*;

import misc.BTtreePrinter;
import misc.TreeNode;

/**
 * 题目�о接: http://www.lintcode.com/en/problem/binary-tree-serialization/ 分析:
 * Lintcode��Leetcode??Serialize��ͬ, 前��是BFS, 后��是Pre-order DFS. �آ�以Lint和Leet�Є题目表���ST�Є方式都不同!
 * 搞的我的valid BST���错�?
 *
 * @author tzhang
 */
public class SerializeBFS {
  public static void main(String[] args) {
    TreeNode root = new TreeNode(3);
    TreeNode ji = new TreeNode(9);
    TreeNode ersh = new TreeNode(20);
    TreeNode shwu = new TreeNode(15);
    TreeNode qi = new TreeNode(7);

    root.left = ji;
    root.right = ersh;
    ersh.left = shwu;
    ersh.right = qi;
    // bfsClient(root);
    String serstr = serializehehejun(root);

    // test
    serstr = "1,#,2"; // "1,2,#,#,3,4,5,#,#,6,7,8,9, #,#";
    System.out.println(serstr);
    TreeNode desroot = deserializehehejun(serstr);
    BTtreePrinter.printNode(desroot);
  }

  /**
   * 学䷶�͵呵君的BFS写法, Կ�且使用了一个flag来判断是否是����后一�? http://hehejun.blogspot.com/2015/01/lintcodeserialization-and.html
   */
  public static String serializehehejun(TreeNode root) {
    if (root == null) {
      return "#";
    }

    Queue<TreeNode> pre = new LinkedList<>();
    pre.offer(root);
    String ans = "";
    boolean end = false;
    while (!pre.isEmpty() && !end) {
      end = true;
      int size = pre.size();
      for (int i = 0; i < size; ++i) {
        TreeNode cur = pre.poll();
        String thisnode = "";
        if (cur == null) { // 体现了null node�Є作�?
          thisnode = "#";
        } else {
          thisnode = cur.val + "";
        }
        ans = ans.length() == 0 ? ans + thisnode : ans + ", " + thisnode;

        if (cur != null) {
          if (cur.left != null && cur.right != null) {
            end = false;
          }
          pre.add(cur.left);
          pre.add(cur.right);
        }
      }
    }
    return ans;
  }

  /**
   * 第二次在lintcode里����?
   */
  public static String serializeLintcode(TreeNode root) {
    if (root == null) {
      return "#";
    }
    String result = "";
    Queue<TreeNode> parents = new LinkedList<TreeNode>();
    parents.offer(root);
    boolean end = false;
    while (!parents.isEmpty() && !end) {
      end = true;
      int size = parents.size();
      for (int i = 0; i < size; ++i) {
        TreeNode head = parents.poll();
        // update the string
        String thisnode = "";
        if (head == null) {
          thisnode = "#";
        } else {
          thisnode = head.val + ""; // simple way to cast int into string
        }
        result =
            result.length() == 0 ? result + thisnode : result + ", " + thisnode;

        // update the queue
        if (head == null) {
          continue;
        }
        if (head.left != null) {
          end = false;
        }
        if (head.right != null) {
          end = false;
        }
        // if (end == false) {
        parents.offer(head.left);
        parents.offer(head.right);
        // }
      }
    }
    return result;
  }

  /**
   * 第一次写. Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException.
   * ա�因在于判断token[idx]�Є时��顤�理��4�? 可见代码�Є结构设计的不好�벯����问�?
   */
  public static TreeNode deserializehehejun(String ser) {
    if (ser == null || ser.length() == 0) {
      return null;
    }
    if (ser.equals("#")) {
      return new TreeNode();
    }
    String[] token = ser.split(",");
    for (int i = 0; i < token.length; ++i) {
      token[i] = token[i].trim();
    }
    Queue<TreeNode> parents = new LinkedList<>();
    TreeNode root = new TreeNode(Integer.parseInt(token[0]));
    parents.offer(root);
    int idx = 1;
    while (idx < token.length) {
      int size = parents.size();
      for (int i = 0; i < size; ++i) {
        TreeNode head = parents.poll();
        TreeNode left =
            token[idx].equals("#") ? null : new TreeNode(
                Integer.parseInt(token[idx]));
        head.left = left;
        idx++;
        TreeNode right =
            token[idx].equals("#") ? null : new TreeNode(
                Integer.parseInt(token[idx]));
        idx++;
        head.right = right;
        if (left != null) {
          parents.offer(left);
        }
        if (right != null) {
          parents.offer(right);
        }
      }
    }
    return root;
  }

  /**
   * 第二次写Lintcode, 还是���很�벰����ﯯ
   */
  public static TreeNode deserializeLintcode(String data) {
    if (data.equals("#")) {
      return null;
    }
    if (data == null || data.length() == 0) {
      return null;
    }

    String[] token = data.split(",");
    for (int i = 0; i < token.length; ++i) {
      token[i] = token[i].trim();
    }
    int len = token.length;
    TreeNode root = new TreeNode(Integer.parseInt(token[0]));
    int idx = 1;
    Queue<TreeNode> parents = new LinkedList<>();
    parents.offer(root);
    while (idx < len) {
      int size = parents.size();
      for (int i = 0; i < size; ++i) {
        TreeNode head = parents.poll();
        if (head == null) {
          continue;
        }
        head.left =
            token[idx].equals("#") ? null : new TreeNode(
                Integer.parseInt(token[idx]));
        idx++;
        head.right =
            token[idx].equals("#") ? null : new TreeNode(
                Integer.parseInt(token[idx]));
        idx++;

        // update parents
        parents.offer(head.left);
        parents.offer(head.right);
      }
    }
    return root;
  }

  /**
   *
   * @param ser
   * @return
   */
  public static TreeNode deserializeTTT(String ser) {
    if (ser == null || ser.length() == 0) {
      return null;
    }
    if (ser.equals("#")) {
      return new TreeNode();
    }
    String[] token = ser.split(", ");
    // for (String s : token)
    // System.out.println(s);
    Queue<TreeNode> parents = new LinkedList<>();
    TreeNode root = new TreeNode(Integer.parseInt(token[0]));
    parents.offer(root);
    int idx = 1;
    while (idx < token.length) {
      List<Integer> level = new ArrayList<>();
      int size = parents.size();
      for (int i = 0; i < size; ++i) {
        TreeNode head = parents.poll();
        level.add(head.val);
        if (!token[idx].equals("#")) {
          head.left = new TreeNode(Integer.parseInt(token[idx]));
          parents.offer(head.left);
          idx++;
        } else {
          idx++;
        }
        if (!token[idx].equals("#")) {
          head.right = new TreeNode(Integer.parseInt(token[idx]));
          parents.offer(head.right);
          idx++;
        } else {
          idx++;
        }
        // if (token[idx].equals("#")) {
        // // parents.offer(null);
        // idx++;
        // }
      }
    }
    return root;
  }

  /**
   * 第一次写: BFS 模版, 在这个基硢�上改�? 但比起呵�͵君�Є来说太复杂�?
   */
  public static List<List<Integer>> BFStemplate(TreeNode root) {
    List result = new ArrayList<>();
    if (root == null) {
      return result;
    }
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    TreeNode sentinalNode = new TreeNode(-99);
    boolean end = false;
    while (!queue.isEmpty() && !end) {
      end = true;
      // TreeNode cur = queue.poll();
      List<Integer> level = new ArrayList<>();
      int size = queue.size();
      for (int i = 0; i < size; ++i) {
        TreeNode cur = queue.poll();
        level.add(cur.val);
        // if (cur == sentinalNode) { // isLeaf(cur)
        // continue;
        // }
        if (cur.left != null) {
          queue.offer(cur.left);
          end = false;
        } else {
          queue.offer(sentinalNode);
        }
        if (cur.right != null) {
          queue.offer(cur.right);
          end = false;
        } else {
          queue.offer(sentinalNode);
        }
      }
      result.add(level);
    }
    return result;
  }

  public static void bfsClient(TreeNode root) {
    List<List<Integer>> ans = new ArrayList();
    ans = BFStemplate(root);
    System.out.println(ans);
    StringBuilder str = new StringBuilder();
    if (ans == null) {
      str = null;
    } else {
      for (List<Integer> l : ans) {
        StringBuilder sub = new StringBuilder();
        for (int i : l) {
          if (i == -99) {
            sub.append(", #");
          } else {
            sub.append(", " + i);
          }
          // System.out.println(i + " ");
        }
        str.append(sub);
      }
    }
    System.out.println(str.toString());
  }
}
