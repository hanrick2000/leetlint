package nineChap6_LL;

public class ListCycleII {
  private ListNode head;
  
  public ListCycleII(){
    int[] l1 = new int[]{1,2,3,4};
    int[] cyc = new int[]{5,6,7,8,9};
    
    ListNode head = ListNode.buildList(l1);
    ListNode cycEntry = ListNode.buildList(cyc);
    ListNode l1Tail = findTail(head);
    ListNode cycTail = findTail(cycEntry);
    l1Tail.next = cycEntry;
    cycTail.next = cycEntry;
    
//    ListNode yi = new ListNode(1);
//    ListNode er = new ListNode(2);
//    ListNode sa = new ListNode(3);
//    ListNode si = new ListNode(4);
//    ListNode wu = new ListNode(5);
//    ListNode li = new ListNode(6);
//    ListNode qi = new ListNode(7);
//    ListNode ba = new ListNode(8);
////    ListNode shy = new ListNode(11);
////    ListNode she = new ListNode(12);
//    
//    head = yi;
//    yi.next = er;
//    er.next = sa;
//    sa.next = si;
//    si.next = wu;
//    wu.next = li;
//    li.next = qi;
//    qi.next = ba;
//    ba.next = wu;
//    
    
    System.out.println(listCycle(head));
    
    ListNode res = findCycleEntry2(head);
    System.out.println(res.val);
  }
  
  public static int listCycle(ListNode head) {
    if (head == null || head.next == null) {
      return 0;
    }
    
    ListNode slow = head, fast = head.next;
    
    while (fast != null) {
      if (slow == fast) {
        return 1;
      }
      slow = slow.next;
      fast = fast.next.next;
    }
    
    return 0;
  }
  
  /*
   * Just remember this solution, note the 2nd while break when fast.next == slow
   */
  public static ListNode findCycleEntry(ListNode head) {
    ListNode slow = head, fast = head.next;
    
    while (true) {
      if (slow == fast) { // too dumb!
        break;
      }
      slow = slow.next;
      fast = fast.next.next;
    }
    
    slow = head;
    while (true) {
      if (fast.next == slow) {
        return slow;
      }
      slow = slow.next;
      fast = fast.next;
    }
    
//    return head;
  }
  
  /*
   * jiuzhang's method
   */
  public static ListNode findCycleEntry2(ListNode head) {
    ListNode slow = head, fast = head.next;
    while (slow != fast) {
      if (fast != slow)
      slow = slow.next;
      fast = fast.next.next;
    }
    
    slow = head;
    while (fast.next != slow) {
      slow = slow.next;
      fast = fast.next;
    }
    
    return slow;
  }
  
  private static ListNode findTail(ListNode h) {
    ListNode head = h;
    while (head.next != null) {
      head = head.next;
    }
    
    return head;
  }
  
  public static void main(String[] args) {
    ListCycleII lci = new ListCycleII();
  }
}
