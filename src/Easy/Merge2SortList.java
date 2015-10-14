package Easy;

import misc.ListNode;

/**
 * Created by 1:42 AM on 10/14/2015.
 */
public class Merge2SortList {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        int[] data1 = new int[]{1,3,8,11,15};
        ListNode root1 = ListNode.buildList(data1);
        int[] data2 = new int[]{2};
        ListNode root2 = ListNode.buildList(data2);
//        ListNode.printList(root1);
        ListNode.printList(merge2(root1, root2));
    }

    public static ListNode merge2(ListNode root1, ListNode root2) {
        if (root1 == null || root2 == null) {
            return root1 == null? root2 : root1;
        }

//        ListNode dummy1 = new ListNode(-1);
//        ListNode dummy2 = new ListNode(-2);
//        dummy1.next = root1;
//        dummy1 = dummy1.next;
//        dummy2.next = root2;
//        dummy2 = dummy2.next;

        ListNode dummy = new ListNode(-1);
        ListNode res = dummy;

        while (root1 != null && root2 != null) {
            if (root1.val < root2.val) {
                dummy.next = new ListNode(root1.val);
                root1 = root1.next;
            }
            else {
                dummy.next = new ListNode(root2.val);
                root2 = root2.next;
            }
            dummy = dummy.next;
        }

        dummy.next = root1 != null ? root1 : root2;
        return res.next;
    }
}
