package nineChap6_LL;

import misc.ListNode;

public class MergeListTD {
  public ListNode head;
  public MergeListTD() {
    int[] arr = new int[]{2, 6, 3, 5, 1};
    head = ListNode.buildList(arr);

//    dbgMerge();
//    dbgMiddle();
    dbgSort(head);  
  }
  
  /*
   * SOF...
   */
  public static ListNode sortTD1(ListNode l, ListNode end) {
    if (l == null || l.next == null || l == end) {
      return l;
    }
    if (end == null) {
      System.out.println("WTF");
      return l;
    }
    
    ListNode middle = middle(l); // bcuz it always return 3...
    
    ListNode left = sortTD1(l, middle);
    ListNode right = sortTD1(middle.next, end);
    
    return mergeList(left, right);
  }
  
  /*
   * MergeSort way to sort list top-down approach
   */
  public static ListNode sortTD2(ListNode l) {
    if (l == null || l.next == null) {
      return l  ;
    }
    
    ListNode mid = middle(l);
    ListNode right = sortTD2(mid.next);
    mid.next = null;  // smart!
    ListNode left = sortTD2(l);
    
    return mergeList(left, right);
  }
  
  /*
   * Merge sorted list
   */
  public static ListNode mergeList(ListNode a, ListNode b) {
    if (a == null) {
      return b;
    }
    else if (b == null) {
      return a;
    }
    
    ListNode lDummy = new ListNode(0);
    ListNode l = lDummy;
    
    while (a != null && b != null) {
      if (a.val < b.val) {
        l.next = a;
        a = a.next;
      }
      else {
        l.next = b;
        b = b.next;
      }
      l = l.next;
    }
    
    l.next = a == null ? b : a;
    
    return lDummy.next;
  }

  /*
   * Find the middle node from listNode
   */
  public static ListNode middle(ListNode h) {
    if (h == null || h.next == null) {
      return h;
    }
    ListNode slow = h, fast = h.next;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;
  }
  
  public static ListNode findTail(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode h = head;
    while (h.next != null) {
      h = h.next;
    }
    return h;
  }
  
  public static void dbgMiddle() {
    int[] a = new int[]{1,3, 5, 15};
    ListNode al = ListNode.buildList(a);
    ListNode res = middle(al);
    ListNode.printList(res);
  }
  
  public static void dbgMerge() {
    int[] a = new int[]{2, 6};
    int[] b = new int[]{1,3, 5, 15};
    ListNode al = ListNode.buildList(a);
    ListNode bl = ListNode.buildList(b);
    ListNode res = mergeList(al, bl);
    
    ListNode.printList(res);
  }
  
  public static void dbgSort(ListNode head){
//    ListNode dummy = new ListNode(0);
//    dummy.next = head;
//    head = dummy;
    ListNode tail = findTail(head);
    ListNode res = sortTD2(head);
    ListNode.printList(res);
  }
  
  public static void main(String[] args) {
    MergeListTD mltd = new MergeListTD();
  }
}
