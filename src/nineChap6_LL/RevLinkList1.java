package nineChap6_LL;

/**
 * The basic reverse linked list, no dummy, so need to keep track of pre, head
 * @author tzhang
 *
 */
public class RevLinkList1 {
  private ListNode head;
  
  RevLinkList1(){
    int[] a = new int[]{1,2,3,4,5};
    head = ListNode.buildList(a);
  }
  
  /**
   * My first try, the edge brings the bug
   * @param head
   * @return
   */
  public static ListNode revLLa(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    
    ListNode pre = null;
    
    while (head.next != null) {
      ListNode temp = head.next;
      head.next = pre;
      pre = head;
      head = temp;
    }
    
    head.next = pre; // Must add this since the while loop will stop in the last node
    
    return head;
  }
  
  /**
   * 2nd try, this time I loop to the null node, then need to return pre!
   * It is the same as ninechap's solution
   * @param head
   * @return
   */
  public static ListNode revLLb(ListNode head) {
    if (head == null || head.next == null) {
      return head;     
    }
    
    ListNode pre = null;
    while (head != null) {
      ListNode temp = head.next;
      head.next = pre;
      pre = head;
      head = temp;
    }
    
    return pre; // this time need to return pre, since head is null!
  }
  public static void main(String[] args) {
    RevLinkList1 rll = new RevLinkList1();
    ListNode.printList(rll.head);
    ListNode res = revLLb(rll.head);
    ListNode.printList(res);
  }
}
