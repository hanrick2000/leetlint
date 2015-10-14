package nineChap6_LL;

import misc.ListNode;

public class RevLLII {
  private ListNode head;

  public RevLLII() {
    int[] a = new int[] {1, 2, 3, 4, 5};
    head = ListNode.buildList(a);
    
    
  }

  public ListNode reverseBetween(ListNode head, int m, int n) {
    // if m == n, then no reverse
    if (m >= n || head == null) {
      return head;
    }

    ListNode dummy = new ListNode(0);
    dummy.next = head;
    head = dummy;

    // find preM
    for (int i = 1; i < m; ++i) {
      if (head == null) {
        return null;
      }
      head = head.next;
    }

    ListNode preM = head;
    ListNode mNode = head.next;
    ListNode nNode = mNode, postN = nNode.next;

    for (int i = m; i < n; ++i) {
      if (postN == null) {
        return null;
      }
      
      ListNode tmp = postN.next;
      postN.next = nNode;
      nNode = postN;
      postN = tmp;
    }
    
    preM.next = nNode;
    mNode.next = postN;

    return dummy.next;
  }
}
