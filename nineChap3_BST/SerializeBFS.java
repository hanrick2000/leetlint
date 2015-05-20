package nineChap3_BST;

import java.util.*;
import freq1_tony.TreeNode;

/**
 * 棰樼洰阈炬帴: http://www.lintcode.com/en/problem/binary-tree-serialization/ 鍒嗘瀽:
 * Lintcode鍜孡eetcode镄凷erialize涓嶅悓, 鍓嶈€呮槸BFS, 鍚庤€呮槸Pre-order DFS. 镓€浠int鍜孡eet镄勯鐩〃绀筑ST镄勬柟寮忛兘涓嶅悓! 鎼炵殑鎴戠殑valid
 * BST锅氶敊浜?
 * 
 * @author tzhang
 *
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
    System.out.println(serstr);
    TreeNode desroot = deserializeTTT(serstr);
    misc.BTtreePrinter printer = new misc.BTtreePrinter();
    printer.printNode(desroot);
  }

  /**
   * 瀛︿范锻靛懙鍚涚殑BFS鍐欐硶, 钥屼笖浣跨敤浜嗕竴涓猣lag鏉ュ垽鏂槸鍚︽槸链€鍚庝竴灞?
   * http://hehejun.blogspot.com/2015/01/lintcodeserialization-and.html
   * 
   * @param root
   * @return
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
        if (cur == null) { // 浣撶幇浜唍ull node镄勪綔鐢?
          thisnode = "#";
        } else {
          thisnode = cur.value + "";
        }
        ans = ans.length() == 0 ? ans + thisnode : ans + ", " + thisnode;

        if (cur != null) {
          if (!cur.isLeaf()) {
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
   * 绗簩娆″湪lintcode閲岄溃鍐?
   * 
   * @param root
   * @return
   */
  public static String serializeLintcode(TreeNode root) {
    if (root == null)
      return "#";
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
          thisnode = head.value + ""; // simple way to cast int into string
        }
        result =
            result.length() == 0 ? result + thisnode : result + ", " + thisnode;

        // update the queue
        if (head == null)
          continue;
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
   * 绗竴娆″啓. Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException.
   * 铡熷洜鍦ㄤ簬鍒ゆ柇token[idx]镄勬椂链椤鐞嗕简4娆? 鍙浠ｇ爜镄勭粨鏋勮璁＄殑涓嶅ソ浼氩镊撮棶棰?
   * 
   * @param ser
   * @return
   */
  public static TreeNode deserializehehejun(String ser) {
    if (ser == null || ser.length() == 0)
      return null;
    if (ser.equals("#"))
      return new TreeNode();
    String[] token = ser.split(",");
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
   * 绗簩娆″啓Lintcode, 杩樻槸链夊緢澶氩皬阌栾
   * 
   * @param data
   * @return
   */
  public static TreeNode deserializeLintcode(String data) {
    if (data.equals("#")) {
      return null;
    }
    if (data == null || data.length() == 0)
      return null;

    String[] token = data.split(",");
    int len = token.length;
    TreeNode root = new TreeNode(Integer.parseInt(token[0]));
    int idx = 1;
    Queue<TreeNode> parents = new LinkedList<>();
    parents.offer(root);
    while (idx < len) {
      int size = parents.size();
      for (int i = 0; i < size; ++i) {
        TreeNode head = parents.poll();
        if (head == null)
          continue;
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
    if (ser == null || ser.length() == 0)
      return null;
    if (ser.equals("#"))
      return new TreeNode();
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
        level.add(head.value);
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
   * 绗竴娆″啓: BFS 妯＄増, 鍦ㄨ繖涓熀纭€涓婃敼浜? 浣嗘瘮璧峰懙锻靛悰镄勬潵璇村お澶嶆潅浜?
   * 
   * @param root
   * @return
   */
  public static List<List<Integer>> BFStemplate(TreeNode root) {
    List result = new ArrayList<>();
    if (root == null)
      return result;
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
        level.add(cur.value);
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
