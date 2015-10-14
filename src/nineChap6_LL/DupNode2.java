package nineChap6_LL;

import misc.ListNode;

/**
 * Remove duplicate node from SORTED list II:
 * remove all duplicate node, include the 1st one. eg:
 * 1-1-1-2-3-3-4 => 2-4 or 1-2-2-2-4-4-5-6 => 1-5-6.
 * 
 * @author tzhang
 *
 */
public class DupNode2 {
  private ListNode head;

  public DupNode2() {
    int[] a = new int[] {1, 1, 1, 1, 1, 2, 3, 3, 4};
    // int[] a = new int[] {1, 2, 2, 4, 4, 5};
    head = ListNode.buildList(a);
  }

  /**
   * First try, my searching for same val loop is bad design.
   * 
   * @param head
   * @return
   */
  public static ListNode dupNode2a(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode dummy = new ListNode(0);
    dummy.next = head;
    head = dummy;

    while (head.next != null && head.next.next != null) {
      if (head.next.val == head.next.next.val) {
        int curVal = head.next.val;
        while (curVal == head.next.next.val) {
          head.next = head.next.next;
        }
        head.next = head.next.next;
      } else {
        head = head.next;
      }
    }

    return dummy.next;
  }

  /**
   * NineChap's solution, use general way to search and delete node
   * 
   * @param head
   * @return
   */
  public static ListNode dupNode2b(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode dummy = new ListNode(0);
    dummy.next = head;
    head = dummy;

    while (head.next != null && head.next.next != null) {
      if (head.next.val == head.next.next.val) {
        int curVal = head.next.val;
        while (head.next != null && head.next.val == curVal) { // this is the key best part
          head.next = head.next.next;
        }
      }

      else {
        head = head.next;
      }
    }

    return dummy.next;
  }

  public static void main(String[] args) {
    DupNode2 dn2 = new DupNode2();
    ListNode.printList(dn2.head);

    ListNode res = dupNode2b(dn2.head);
    ListNode.printList(res);
  }
}
