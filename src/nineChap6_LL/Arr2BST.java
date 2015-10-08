package nineChap6_LL;

/**
 * Convert sorted array to balanced binary tree
 * 
 * also learn the BTree printer, generic, wildcard PECS, class/method level generic
 * 
 * @link http://www.lintcode.com/en/problem/convert-sorted-array-to-binary-search-tree-with-minimal-
 *       height/
 * @author tzhang
 *
 */
public class Arr2BST<T extends Comparable<?>> {  // public class Arr2BST {} without T to solve: The type parameter T is hiding the type T
//  public T[] sortedArr;

//  static class Node {
//    public int val;
//    public Node left, right;
//
//    public Node(int val) {
//      this.val = val;
//      this.left = this.right = null;
//    }
//  }

  /**
   * @param A: an integer array
   * @return: a tree node
   */
  public <T extends Comparable<?>> Node<T> sortedArrayToBST(T[] A) {  // The type parameter T is hiding the type T, can fix by remove T in class 
      return helper(A, 0, A.length-1);
  }
  
  public <T extends Comparable<?>> Node<T> helper(T[] A, int lo, int hi) { // or can remove both T between modifier and return type to solve the T parameter hiding
    if (lo > hi) {
      return null;
    }
    
    int mid = lo + (hi - lo) / 2;
    Node<T> left = helper(A, lo, mid - 1);
    Node<T> right = helper(A, mid + 1, hi);
    Node<T> head = new Node<T>(A[mid]);
    head.left = left;
    head.right = right;
    return head;
  }

  public Arr2BST() {
//    sortedArr = new Integer[] {1, 2, 3, 4, 5, 6, 7};  // having touch to solidate generic in ctor
//    Node<T> ans = sortedArrayToBST(sortedArr);
//    BTreePrinter.printNode(ans);
  }
  
  public static void main(String[] args) {
    Arr2BST<Integer> a2b = new Arr2BST<>();
    Integer[] sortedArr = new Integer[] {1, 2, 3, 4, 5, 6, 7};
    Node<Integer> ans = a2b.sortedArrayToBST(sortedArr);
    BTreePrinter.printNode(ans);
  }

}
