package nineChap6_LL;

public class FindCycle {
  public FindCycle() {
    test();
  }
  
  private static void test() {
    int[] arr = new int[] {1, 2 ,3 ,4 ,5, 6, 7};
    ListNode head = ListNode.buildList(arr);
    ListNode si = head.next.next.next;
    ListNode qi = si.next.next.next;
    qi.next = si;
    
    ListNode ans = findCycle(head);
    System.out.println(ans.val);
    
  }
  
  private static ListNode findCycle(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    
    ListNode slow = head, fast = head.next;
    while (fast != null && fast.next != null) {
      if (slow.equals(fast)) {
        slow = head;
        break;
      }
      slow = slow.next;
      fast = fast.next.next;
      
    }
    
    while (true) {
      if (slow.equals(fast.next)) {  // NOTE: the algs is find slow == fast.next ! not Fast.
        break;
      }
      slow = slow.next;
      fast = fast.next;
    }
    
    return slow;
  }
  
  public static void main(String[] args) {
    FindCycle fc = new FindCycle();
  }
}
