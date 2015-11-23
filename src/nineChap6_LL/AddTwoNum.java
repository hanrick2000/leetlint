package nineChap6_LL;

import misc.ListNode;

/**
 * Created at 4:04 PM on 11/21/15.
 */
public class AddTwoNum {
  public static void main(String[] args) {
    ListNode sa = new ListNode(3);
    ListNode li = new ListNode(6);
    ListNode yi = new ListNode(1);
    ListNode yi1 = new ListNode(1);
    ListNode qi = new ListNode(7);
    ListNode er = new ListNode(2);
    ListNode ji = new ListNode(9);
    ListNode wu = new ListNode(5);
    ListNode wu1 = new ListNode(5);

    //li.next = yi;
    //yi.next = qi;
    //er.next = ji;
    //ji.next = wu;

    sa.next = yi;
    yi.next = er;

    wu.next = ji;
    ji.next = wu1;
    wu1.next = yi1;


    ListNode.printList(sa);
    ListNode.printList(wu);
    ListNode ans = new AddTwoNum().addLists(sa, wu);
    ListNode.printList(ans);
  }
  /**
   * @param l1: the first list
   * @param l2: the second list
   * @return: the sum list of l1 and l2
   */
  public ListNode addLists(ListNode l1, ListNode l2) {
    // write your code here
    ListNode dummy = new ListNode(0);
    ListNode tail = dummy;
    int carry = 0;

    while (l1 != null && l2 != null) {
      int sum = l1.val + l2.val + carry;
      tail.next = new ListNode(sum % 10);
      carry = sum / 10;
      tail = tail.next;

      l1 = l1.next;
      l2 = l2.next;
    }
    while (l1 != null) {
      int sum = l1.val + carry;
      tail.next = new ListNode(sum % 10);
      carry = sum / 10;
      tail = tail.next;

      l1 = l1.next;
    }

    while ( l2 != null) {
      int sum = l2.val + carry;
      tail.next = new ListNode(sum % 10);
      carry = sum / 10;
      tail = tail.next;

      l2 = l2.next;
    }

    if (carry != 0) {
      tail.next = new ListNode(carry);

      tail = tail.next;
    }

    return reverse(dummy.next);
  }

  private ListNode reverse(ListNode root) {
    if  (root == null) {
      return root;
    }
    ListNode prev = null;
    while (root != null) {
      ListNode post = root.next;
      root.next = prev;
      prev = root;
      root = post;
    }
    return prev;
  }
}
