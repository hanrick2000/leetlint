package nineChap3_BST;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import misc.BTtreePrinter;
import freq1_tony.SerDesBinaryTree;
import freq1_tony.TreeNode;

public class ValidateBST {
  public static void main(String[] args) {
    TreeNode root = new TreeNode(0);
    TreeNode yi = new TreeNode(1);
    TreeNode er = new TreeNode(2);
    TreeNode sa = new TreeNode(3);
    TreeNode si = new TreeNode(4);
    TreeNode wu = new TreeNode(5);
    TreeNode li = new TreeNode(6);
    TreeNode sh = new TreeNode(10);
    TreeNode shwu = new TreeNode(15);
    TreeNode ersh = new TreeNode(20);

    sh.left = wu;
    sh.right = shwu;
    shwu.left = li;
    shwu.right = ersh;

    boolean ans = client(sh);
    BTtreePrinter.printNode(sh);
    System.out.println(ans);

    List<String> ser = new ArrayList<String>();
    SerDesBinaryTree.writeBinTree(yi, ser);
    // String a = serialize(sh);
    String a = SerializeBFS.serializehehejun(sh);
    System.out.println(a);

    String serstr = "10,5,15,#,#,6,20";
    TreeNode desroot = SerializeBFS.deserializehehejun(serstr);
    misc.BTtreePrinter printer = new misc.BTtreePrinter();
    printer.printNode(desroot);
  }

  public static boolean client(TreeNode root) {
    if (root == null)
      return false;
    return isValidBST(root, new TreeNode[1]);
  }

  public static boolean isValidBST(TreeNode root, TreeNode[] pre) {
    if (root == null) {
      return true;
    }
    boolean left = isValidBST(root.left, pre);
    if (pre[0] != null && pre[0].value <= root.value) {
      return false;
    }
    pre[0] = root;
    return left && isValidBST(root.right, pre);
  }

  /**
   * 阌熸枻鎷蜂竴阌熸枻鎷烽敓鏂ゆ嫹, 鐩撮敓鏂ゆ嫹18/19 test case阌熻剼鍑ゆ嫹阌熻杈炬嫹阌熸枻鎷? 阌熸枻鎷蜂负BST阌熸枻鎷烽敓鏂ゆ嫹阌熸枻鎷烽敓鏂ゆ嫹阌熸枻鎷烽敓鏂ゆ嫹阌熸枻鎷? root < 阌熸枻鎷烽敓鏂ゆ嫹阌熸枻鎷? 阌熸枻鎷烽敓? fail
   * 
   * @param root
   * @return
   */
  public static boolean isValidBSTwrong(TreeNode root) {
    if (root == null) {
      return true;
    }
    // "divide"
    boolean l = true, r = true;
    if (root.left != null) {
      if (root.left.value >= root.value) {
        l = false;
      } else {
        l = isValidBSTwrong(root.left);
      }
    }
    if (root.right != null) {
      if (root.right.value <= root.value) {
        r = false;
      } else {
        r = isValidBSTwrong(root.right);
      }
    }

    // Conquer"
    return l && r;

  }
}
