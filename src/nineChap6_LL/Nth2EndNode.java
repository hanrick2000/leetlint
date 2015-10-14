package nineChap6_LL;

import misc.ListNode;

/**
 * Remove the N-th node from the end.
 * Using slow/fast pointer, or precisely speaking, just two-pointers method
 * @author tzhang
 *
 */
public class Nth2EndNode {
  private ListNode head;
  public Nth2EndNode(){
    int[] a = new int[]{1,2,3,4,5,6,7,8};
    head = ListNode.buildList(a);
    ListNode res = findNth2End(head,3);
    ListNode.printList(res);
  }
  
  /*
   * My solution, Notice in this case, the final head is undetermined if n is large, so use dummy node
   */
  public static ListNode findNth2End(ListNode head, int n) {
    if (head == null) {
      return head;
    }
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    head = dummy;
    
    ListNode slow = head, fast = head;
    int index = 0;
    while (fast.next != null) {
      if (index >= n) {
        slow = slow.next;
      }
      fast = fast.next;
      index++;
    }
    
    slow.next = slow.next.next;
    return dummy.next;
  }
  
  public static void main(String[] args) {
    Nth2EndNode n2e = new Nth2EndNode();
  }
}
