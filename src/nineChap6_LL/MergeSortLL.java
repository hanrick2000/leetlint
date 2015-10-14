package nineChap6_LL;

import misc.ListNode;

public class MergeSortLL {
  
  public MergeSortLL(ListNode head) {
    int len = findLen(head);
    ListNode ans = sortWRONG(head, len);
    ListNode.printList(ans);
  }
  
  /**
   * this causes SOF, since when size == 0, it never return
   * @param head
   * @param size
   * @return
   */
  public static ListNode sortWRONG(ListNode head, int size) {
    if (head == null || head.next == null || size == 0) {
      return head;
    }
    
    ListNode midPre = findMid(head, size);
    ListNode headL = sortWRONG(head, size/2);
    ListNode mid = midPre.next;
    midPre.next = null;
    ListNode headR = sortWRONG(mid, size-1-size/2);
    
    ListNode res = merge(headL, headR);
    return res;
  }
  
  private static int findLen(ListNode head) {
    int len = 0;
    while (head != null) {
      head = head.next;
      len++;
    }
    return len;
  }
  
  public static ListNode findMid(ListNode head, int size) {
    if (head == null) {
      return head;
    }
    
    for (int i = 0; i < size/2; ++i) {
      if (head.next == null) {
        return null;
      }
      head = head.next;
    }
    
    return head;
  }
  
  public static ListNode merge(ListNode lhs, ListNode rhs) {
    ListNode dummy = new ListNode(0);
    ListNode tail = dummy;
    
    while (lhs != null && rhs != null) {
      if (lhs.val < rhs.val) {
        tail.next = lhs;
        lhs = lhs.next;
      }
      else if (lhs.val >= rhs.val) {
        tail.next = rhs;
        rhs = rhs.next;
      }
      tail = tail.next;
    }
    
    if (lhs != null) {
      tail.next = lhs;
    }
    else if (rhs != null) {
      tail.next = rhs;
    }
    
    return dummy.next;
  }
  
  public static void main(String[] args) {
    int[] arr  = new int[] {2, 3, 1, 6, 5};
    ListNode head = ListNode.buildList(arr);
    MergeSortLL msll = new MergeSortLL(head);
  }
}
