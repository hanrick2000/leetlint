package nineChap3_BST;

import freq1_tony.TreeNode;
import java.util.*;

public class Preorder {
  public static void main(String[] args) {
    TreeNode root = new TreeNode(0);
    TreeNode yi = new TreeNode(1);
    TreeNode er = new TreeNode(2);
    TreeNode sa = new TreeNode(3);
    TreeNode si = new TreeNode(4);
    TreeNode wu = new TreeNode(5);
    // root.left = yi;
    // root.right = er;
    // er.left = sa;
    // er.right = si;
    // sa.left = wu;
    root = yi;
    yi.right = er;
    er.right = sa;

    ArrayList<Integer> res = preOrderDC(root);
    System.out.println(res);
  }

  /**
   * Traverse镄勫仛娉? 鍗硆ecursion娌℃湁杩斿洖链?
   * 
   * @param root
   * @return
   */
  public static ArrayList<Integer> preOrderRec(TreeNode root) {
    ArrayList<Integer> res = new ArrayList<>();
    dfs(root, res);
    return res;
  }

  /**
   * Traverse涓€寮€濮嬫兂涓嶅埌镐庝箞鐩存帴鍐? 锲犱负娌℃湁杩斿洖链煎晩, 绐佺劧鎯冲埌鍙互call dfs(), 铹跺悗dfs鏉pdate杩欎釜杩斿洖链煎氨鍙互浜?
   * 
   * @param root
   * @param result
   */
  private static void dfs(TreeNode root, ArrayList<Integer> result) {
    if (root == null) {
      return;
    }
    result.add(root.value);
    dfs(root.left, result);
    dfs(root.right, result);
  }

  /**
   * Divide & Conquer镄勫仛娉? 娣卞叆鐞呜Вrecursion. 浠ュ强瀵逛簬null vs Null List镄勫尯鍒?
   * 
   * @param root
   * @return
   */
  public static ArrayList<Integer> preOrderDC(TreeNode root) {
    ArrayList<Integer> res = new ArrayList<>();
    // 褰搑ecursion鍒颁竴涓┖鑺傜偣镄勬椂链椤氨杩斿洖?
    if (root == null) {
      res.add(null); // 娉ㄦ剰杩欓噷鍜宻erialize, 鎴栬€?Effective Java> item 41涓嶅悓, 涓岖敤锷犲叆null node!
      return res;
    }

    // "divide" : 寰楀埌宸﹀彸瀛愭爲镄刾re_order
    ArrayList<Integer> lefts = preOrderDC(root.left);
    ArrayList<Integer> rights = preOrderDC(root.right);

    // "conquer" : 鎸夌収pre-order镄勯『搴忓缑鍒板綋鍓峳oot镄勭粨鏋?
    res.add(root.value);
    res.addAll(lefts);
    res.addAll(rights);
    return res;
  }
}
