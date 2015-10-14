package nineChap6_LL;

import misc.ListNode;

/**
 * after I done the partition array, now it comes the partition list (singly list)
 * 
 * @author tzhang
 *
 */
public class PartitionList {
  private ListNode head;

  public PartitionList() {
    int[] a = new int[] {1, 3, 4, 2, 5, 2};
    head = ListNode.buildList(a);
    ListNode.printList(head);
    ListNode res = null;
    res = partitionListb(head, 3);
    ListNode.printList(res);
  }

  /**
   * 
   * @param head
   * @param s
   * @return
   */
  public ListNode partitionLista(ListNode head, int s) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode lp = new ListNode(0);
    ListNode rp = new ListNode(0);
    ListNode l = lp;
    ListNode r = rp;
    ListNode cur = head;
    while (cur != null) {
      if (cur.val < s) {
        lp.next = new ListNode(cur.val); // debug without using my brain
        lp = lp.next;
      }

      else {
        rp.next = new ListNode(cur.val); // the problem doesn't came from here.
        rp = rp.next;
      }

      cur = cur.next;
    }

    // combine lp/rp
    lp.next = r.next; 
    return l.next;
  }

  /**
   * 9chap solution. Reverse is not the key issue, the issue comes from the 
   * @param head
   * @param s
   * @return
   */
  public ListNode partitionListb(ListNode head, int s) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode lp = new ListNode(0);
    ListNode rp = new ListNode(0);
    ListNode l = lp;
    ListNode r = rp;
    ListNode cur = head;
    while (cur != null) {
      if (cur.val < s) {
        lp.next = cur;
//        lp = cur;
        lp = lp.next;
      }

      else {
        rp.next = cur;
//        rp = cur;
        rp = rp.next;
      }

      cur = cur.next;
    }

    // combine lp/rp
//    rp.next = null; // MUST
    lp.next = r.next;
    return l.next;
  }

  public static void main(String[] args) {
    PartitionList pl = new PartitionList();
  }

}
