package nineChap6_LL;

public class ReorderList {
  private ListNode head;
  public ReorderList(){
    int[] a = new int[] { 1,2,3, 4,5,6,7,8,9,10,11, 12};
    head = ListNode.buildList(a);
    ListNode res = findMiddle(head);
    res = reorder(head);
    ListNode.printList(res);
  }
  
  public static ListNode reorder(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    
    // find middle
    ListNode mid = findMiddle(head);
    ListNode headRt = mid.next;
    mid.next = null;
    
    // reverse right half
    ListNode headR = reverse(headRt);
    
    // merge left/right list
    ListNode res = new ListNode(0);
    ListNode resDummy = res;
    int ptr = 0;
    while (head != null || headR != null) {
      ListNode cur = null;
      if (ptr % 2 == 0) {
        cur = head;
        head = head.next;
      }
      else {
        cur = headR;
        headR = headR.next;
      }
      ptr++;
      
      res.next = cur;
      res = cur;
    }
    
    res.next = head == null? headR : head;
    
    return resDummy.next;
  }
  
  public static ListNode findMiddle(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    
    ListNode slow = head, fast = head.next;
    while (fast != null && fast.next != null) {
      fast = fast.next.next;
      slow = slow.next;
    }
    
    return slow;
  }
  
  public static ListNode reverse(ListNode head) {
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
    
    return pre;
  }

  public static void main(String[] args) {
    ReorderList rl = new ReorderList();
  }
}
