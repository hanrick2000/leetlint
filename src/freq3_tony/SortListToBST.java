package freq3_tony;

import java.util.LinkedList;
import java.util.List;

/**
 * 刚做了Sorted Array to BST. 因为BST就是recursive结构. 所以直接每次选mid, 然后recursive左右array 但是LinkedList不能random
 * access, 怎么办?
 * 
 * @author tzhang
 *
 */
public class SortListToBST {
  private ListNode h;
  // Listnode inner class
  private class ListNode {
    int value;
    ListNode next;
    
    ListNode(int v) {
      this.value = v;
      this.next = null;
    }
    
//    public void add(int nodeV) {
//      ListNode node = new ListNode(nodeV);
//    }
  }
  // 直接套用的SortArrayToBST的方法. 也是用recursion. 但是LinkedList.get()是O(n)操作.
  //    所以时间复杂度是从SortArrToBST的O(1 * lgN) 到了 O(N * lgN)
  public TreeNode DUMMYsortListBST(List<Integer> list, int lo, int hi) {
    // 忘记加了base condiion, 导致stack overflow...
    if (lo > hi)  return null;
    int mid = lo + (hi - lo) / 2;
    TreeNode node = new TreeNode(list.get(mid));
    node.setleft(DUMMYsortListBST(list, lo, mid - 1));
    node.setright(DUMMYsortListBST(list, mid + 1, hi));
    return node;
  }

  /*
   * 想好这个buttom-up的做法, 以及理解这里的recursion!!!
   * http://www.programcreek.com/2013/01/leetcode-convert-sorted-list-to-binary-search-tree-java/
   */
  public TreeNode sortListBST( int lo, int hi) {
    if (lo > hi)  return null;
    int mid = lo + (hi-lo)/2;
    // pre的recursion
    TreeNode left = sortListBST(lo, mid-1);
    // ------------------------------------
    TreeNode root = new TreeNode(h.value);
    System.out.println(h.value);
    h = h.next;    
    // ------------------------------------
    // post的recursion
    TreeNode right = sortListBST(mid+1, hi);
    // after 2 个 recursion
    root.setleft(left);
    root.setright(right);
    return root;
  }
  
  // get length given the head list node
  public int getLength(ListNode ln) {
    int len = 0;
    ListNode p = ln;
    while (p != null) {
      p = p.next;
      len++;
    }
    return len;
  }
  
  public TreeNode sortedListToBST(ListNode ln) {
    if (ln == null)  return null;
//    this.h = ln;
    int len = getLength(ln);
    return sortListBST(0, len-1);
  }
  
  public SortListToBST(){
    this.h = new ListNode(1);
    ListNode s1 = new ListNode(2);
    ListNode s2 = new ListNode(3);
    ListNode s3 = new ListNode(5);
    ListNode s4 = new ListNode(7);
    ListNode s5 = new ListNode(8);
    ListNode s6 = new ListNode(9);
    h.next = s1;
    s1.next = s2;
    s2.next = s3;
    s3.next = s4;
    s4.next = s5;
    s5.next = s6;
  }
  
  public static void main(String[] args) {
    
    SortListToBST ltb = new SortListToBST();
//    TreeNode Gruut = ltb.DUMMYsortListBST(sortL, 0, 5);
//    Gruut.printTree(Gruut);
    TreeNode r = ltb.sortedListToBST(ltb.h);
//    Levelorder lord = new Levelorder(r);
//    System.out.print(lord.levelorderTwoQ(r));
  }
}
