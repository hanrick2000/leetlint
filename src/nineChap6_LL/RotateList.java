package nineChap6_LL;

import misc.ListNode;

/**
 * http://www.lintcode.com/en/problem/rotate-list/
 * Created at 7:58 PM on 11/24/15.
 */
public class RotateList {
  public static void main(String[] args) {
    int[] test = new int[]{1,2,3,4,5};
    ListNode root = ListNode.buildList(test);
    ListNode res = new RotateList().rotateRight(root, 4);
    ListNode.printList(res);
  }
  /**
   * @param head: the List
   * @param n: rotate to the right k places
   * @return: the list after rotation
   */
  public ListNode rotateRight(ListNode head, int n) {
    if (head == null || n == 0) {
      return head;
    }
    int len = 0;
    ListNode root = head;
    while (head.next != null) {
      head = head.next;
      len++;
    }
    len = len+1;
    n = n % len;
    head.next = root;

    ListNode dummy = root;
    for (int i = 1; i < len - n; ++i) {
      dummy = dummy.next;
    }
    ListNode res = dummy.next;
    dummy.next = null;
    return res;
  }

  public ListNode rotateRightWRONG(ListNode head, int k) {
    // write your code here
    if (k == 0) {
      return head;
    }
    int diff = k;
    ListNode oriHead = head;
    ListNode brk = head;
    while (diff != 0) {
      brk = brk.next;
      if (brk == null) {
        return head;
      }
      diff--;
    }

    if (brk == null) {
      return head;
    }

    while (brk.next != null) {
      head = head.next;
      brk = brk.next;
    }
    ListNode newHead = head.next;
    brk.next = oriHead;
    head.next = null;
    return newHead;
  }
}
