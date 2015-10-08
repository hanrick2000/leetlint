package nineChap6_LL;

/*-
 * Report: this the main thing i learn is:
 * 1. modular: don't repeat yourself, just use the memorized reverse(listNode)
 * 2. revisit counting in while/for loop to get N or (N-1) th element in list
 * 3. always remember: when separating list, need to end each sublist with NULL, or will lead to self loop
 * 
 * @author tzhang
 *
 */

public class ReoLL {
  private ListNode head;

  public ReoLL() {
    int[] a = new int[] {1, 2, 3, 4}; // {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    head = ListNode.buildList(a);
    reorderList(head);
    // ListNode.printList(head);
  }

  public void reorderList(ListNode head) {
    // write your code here
    if (head == null || head.next == null) {
      return;
    }

    ListNode mid = findMid(head);

    ListNode left = head;
    // ListNode right = reverse(mid); // this will lead to loop!
    ListNode right = reverse(mid.next);
    mid.next = null;

    merge(left, right);
    ListNode.printList(head);

  }

  private static ListNode findMid(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode slow = head, fast = head.next;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }

    return slow;
  }

  private static ListNode reverse(ListNode head) {
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

    return pre; // note: when while loop terminate, it means head != null is false, so head is null
  }

  // WRONG code
  private static void mergeWRONG(ListNode left, ListNode right) {
    ListNode dummyL = left, dummyR = right, ans = left; // dummyL/R are same things as L/R
    int idx = 0;
    while (dummyL != null && dummyR != null) {
      if ((idx & 0x01) == 0) {
        ans.next = dummyL.next;
        dummyL = dummyL.next;
      } else {
        ans.next = dummyR.next;
        dummyR = dummyR.next;
      }
      ans = ans.next;
      idx ^= 0x01;
    }
  }

  private static void merge(ListNode left, ListNode right) {
    ListNode ans = new ListNode(0);

    int i = 0;
    while (left != null && right != null) {
      if (i % 2 == 0) {
        ans.next = left;
        left = left.next;
      } else {
        ans.next = right;
        right = right.next;
      }
      ans = ans.next;
      i++;
    }

    // must add:
    if (left != null) {
      ans.next = left;
    }
    if (right != null) {
      ans.next = right;
    }
  }

  public static void main(String[] args) {
    ReoLL rll = new ReoLL();
  }

}
