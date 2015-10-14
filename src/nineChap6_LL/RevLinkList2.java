package nineChap6_LL;

import misc.ListNode;

/*-
 * reverse the LL(m-th to n-th) inside the whole list
 * eg: 1-2-3-4-5, m = 1, n = 3 => 1-4-3-2-5.
 * @author tzhang
 *
 */
public class RevLinkList2 {
  private ListNode head;

  public RevLinkList2() {
    int[] a = new int[] {1, 2, 3, 4, 5};
    head = ListNode.buildList(a);
  }

  /**
   * first try
   * @param head
   * @param m
   * @param n
   * @return
   */
  public static ListNode revLL2a(ListNode head, int m, int n) {
    if (head == null || head.next == null || m > n) {
      return head;
    }

    ListNode dummy = head;
    ListNode test = head;
    int lenLL = 0;
    while (test != null) {
      lenLL++;
      test = test.next;
    }

    if (n > lenLL) {
      return head;
    }

    ListNode pre = null;
    int cnt = 0;
    while (head != null) {
      cnt++;
      if (cnt == m) {
        pre = head;
        break;
      }
      head = head.next;
    }
    
    ListNode preM = pre;
    head = head.next;

    while (head != null && cnt != n) {
      cnt++;
      ListNode temp = head.next;
      head.next = pre;
      pre.next = null;
      pre = head;
    }
    
    return head;
  }
  
  public static ListNode revLL2b(ListNode head, int m, int n) {
    if (m >= n || head == null) {
      return head;
    }
    
    // since m might be 0, so need a pre holder
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
    
    /* My way, didn't validate m!
    ListNode preM = null;
    for (int cnt = 1; cnt < m; ++cnt) {
      head = head.next;
      preM = head;
    }*/

    // remember mnode, nnode also reverse while traversing
    ListNode mNode = head.next;
    ListNode nNode = mNode, postN = mNode.next;
    for (int i = m; i < n; ++i) {
      if (postN == null) {
        return null;
      }
      ListNode temp = postN.next;
      postN.next = nNode;
      nNode = postN;
      postN = temp;
    }
    
    // deal with preM and postN
    preM.next = nNode;
    mNode.next = postN;
    
    return dummy.next;
  }
  
  public static void main(String[] args) {
    RevLinkList2 rll2 = new RevLinkList2();
    ListNode.printList(rll2.head);
    ListNode res = revLL2b(rll2.head, 3,4);
    ListNode.printList(res);
  }
}
