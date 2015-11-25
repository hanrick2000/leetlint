package nineChap6_LL;

import misc.ListNode;

/**
 * http://www.lintcode.com/en/problem/palindrome-linked-list/#
 * Created this class in nineChap6_LL at 1:57 PM, 10/24/2015.
 */
public class PalindromeLL {
  public static void main(String[] args) {
    int[] test = new int[]{1,2}; //{1,2,3,2,1}; //
    ListNode root = ListNode.buildList(test);
    PalindromeLL pll = new PalindromeLL();
    ListNode slow = pll.findMid(root);
    System.out.println(slow.val);
    boolean isPali = pll.isPalindrome(root);
    System.out.println(isPali);
  }

  public boolean isPalindrome(ListNode head) {
    if (head == null || head.next == null) {
      return true;
    }

    ListNode brk = findMid(head);
    ListNode head2 = reverse(brk);


    //ListNode head2 = reverse(head).next;
    //reverse(head).next = null;

    while (head != null) {
      if (head.val != head2.val) {
        return false;
      }
      head = head.next;
      head2 = head2.next;
    }

    return true;
  }

  public ListNode findMid(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode slow = head, fast = head.next;
    while (fast != null && fast.next != null) {//|| fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }

    return slow;
  }

  private ListNode reverse(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode pre = null;

    // 1->2->3->... => 1<-2 3<-...
    while (head != null) {
      ListNode tmp = head.next;
      head.next = pre;
      pre = head;
      head = tmp;
    }

    return pre;
  }
}

/*
*  1-2-2-1
*  so 4-> mid= 0+3/2 = 1
*  1-2-3-2-1
*  so 5 -> mid = 0+4/2 = 2
*
*  find mid, reverse the mid+1->end.
*  compare head with end till leng1 == 0/1
*
* */
