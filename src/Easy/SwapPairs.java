package Easy;

import misc.ListNode;

/**
 * http://www.lintcode.com/en/problem/swap-nodes-in-pairs/
 * Created this class in Easy at 11:15 AM, 10/22/2015.
 */
public class SwapPairs {
    public static void main(String[] args) {
        help();
    }

    public static void help() {
        ListNode root = ListNode.buildList(new int[]{});

        ListNode res = new SwapPairs().swapPairs(root);
        ListNode.printList(res);
    }
    /**
     * @param head a ListNode
     * @return a ListNode
     */
    public ListNode swapPairs(ListNode head) {
        // Write your code here
        if (head == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;

        while (head != null && head.next != null && head.next.next != null) {
            ListNode tmp = head.next;
            head.next = head.next.next;
            tmp.next = head.next.next;
            head.next.next = tmp;

            // next starting point
            head = head.next.next;
        }

        return dummy.next;
    }
}
