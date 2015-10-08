package learnJava;

import java.util.*;

/**
 * http://www.codeproject.com/Articles/794448/Is-it-Really-Better-to-Return-an-Empty-List-Instea
 * 
 * @author tzhang
 *
 */
public class NullList {

  public static void main(String[] args) {
    List<TreeNode> list = new ArrayList<>(0);
    // list.add(null);
    list.add(new TreeNode(1));
    list.add(new TreeNode(2));
    list.add(new TreeNode(3));
    System.out.println(list.size());
    
    TreeNode[] arr = new TreeNode[1];
    boolean a = arr == null;
    boolean b = arr[0]==null;
    System.out.println(a + ", " + b);
  }

  static class TreeNode {
    int value;
    TreeNode left, right;

    TreeNode(int v) {
      value = v;
    }

    TreeNode() {}

    public String toString() {
      int s = value;
      return Integer.toString(s);
    }
  }
}
