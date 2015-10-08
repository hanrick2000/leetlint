package nineChap6_LL;


/**
 * Remove duplicate node from SORTED list I:
 * remove all duplicate node, Save the 1st one. eg:
 * 1-1-1-2-3-3-4 => 1-2-4 or 1-2-2-2-4-4-5-6 => 1-2-4-5-6.
 * 
 * @author tzhang
 *
 */
public class DupNode1 {
  private ListNode head;

  public DupNode1() {
    int[] arr = new int[] {1, 1, 2, 3, 3};
    head = ListNode.buildList(arr);
  }

  /**
   * My design(Not good), dummy node is not a must, cuz the head won't be deleted.
   * 
   * @param head
   * @return
   */
  public static ListNode dupNode1a(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    head = dummy;

    while (head.next != null && head.next.next != null) {
      if (head.next.val == head.next.next.val) {
        head.next = head.next.next;
      } else {
        head = head.next;
      }
    }

    return dummy.next;
  }

  /**
   * NineChap solution, Good, it's a general solution
   * 
   * @param head
   * @return
   */
  public static ListNode dupNode1b(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode pre = head; // need a pointer to traverse the list
    while (pre.next != null) { // use the pre pointer to do searching, rebuild the list
      if (pre.val == pre.next.val) {
        pre.next = pre.next.next;
      } else {
        pre = pre.next;
      }
    }

    return head; // the origin head is inacted
  }

  public static void main(String[] args) {
    DupNode1 dn1 = new DupNode1();
    ListNode.printList(dn1.head);
    ListNode res = dupNode1b(dn1.head); // dupNode1a(dn1.head);

    ListNode.printList(res);
  }
}
