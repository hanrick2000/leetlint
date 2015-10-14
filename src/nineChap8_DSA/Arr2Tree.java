package nineChap8_DSA;

import misc.BTtreePrinter;
import misc.TreeNode;
public class Arr2Tree {
//  private static class TreeNode {
//    int label;
//    TreeNode left, right;
//
//    TreeNode(int l) {
//      label = l;
//    }
//  }

  public static void main(String[] args) {
    int[] arr = new int[] {2, 5, 6, 0, 3, 1};
    TreeNode root = arr2tree(arr, 0, arr.length-1);
//    misc.BTtreePrinter printer = new misc.BTtreePrinter();
    BTtreePrinter.printNode(root);
  }

  public static TreeNode arr2tree(int[] arr, int lhs, int rhs) {
    // return
    if (lhs > rhs) {
      return null;
    }
    // rec
    int mid = (lhs + rhs) / 2;
    TreeNode root = new TreeNode(arr[mid]);
    TreeNode left = arr2tree(arr, lhs, mid-1);
    TreeNode right = arr2tree(arr, mid + 1, rhs);
    root.left = left;
    root.right = right;
    return root; // MUST
  }
}
