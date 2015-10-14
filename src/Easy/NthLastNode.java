package Easy;

import misc.ListNode;

/**
 * Created by 2:12 AM on 10/14/2015.
 */
public class NthLastNode {
    public static void main(String[] args) {

        ListNode list = ListNode.buildList(new int[]{3,2,1,5});
        System.out.println(nthLastNode(list, 4).val);
    }

    public static ListNode nthLastNode(ListNode root, int n) {
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
