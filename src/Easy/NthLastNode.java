package Easy;

import misc.ListNode;

/**
 * Created by 2:12 AM on 10/14/2015.
 */
public class NthLastNode {
    public static void main(String[] args) {

        ListNode list = ListNode.buildList(new int[]{3,2,1,5});
        System.out.println(new NthLastNode().nthToLast(list, 2).val);
    }

    ListNode nthToLast(ListNode head, int n) {
        // write your code here
        if (head == null) {
            return null;
        }

        ListNode nth = head;
        while (nth != null && --n != 0) {
            nth = nth.next;
        }

        ListNode ans = head;
        while (nth.next != null) {
            head = head.next;
            nth = nth.next;
        }
        return head;
    }

    /**
     * 1st try: Wrong in 2 part:
     * 1. when use Double pointer? --> Sliding window
     * 2. when use while in for?   --> Max Tree
     * @param root
     * @param n
     * @return
     */
    public static ListNode nthLastNodeBad(ListNode root, int n) {
        if (root == null) {
            return null;
        }

        ListNode slow, fast;
        for (slow = root, fast = root; fast.next != null; slow = slow.next, fast = fast.next) {
//            while (--n != 0) {
            while (--n > 0 && fast != null) {
                fast = fast.next;
            }
            if (fast == null) {
                return null;
            }
            if (fast.next == null) {
                return slow;
            }
        }
        return slow;
    }
}
