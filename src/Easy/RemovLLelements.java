package Easy;

import misc.ListNode;

/**
 * Created by 11:41 PM on 10/20/2015.
 */
public class RemovLLelements {
    public static void main(String[] args) {
        int[] data = new int[]{3,3,2,4,5,3,3,3,3,0};
        ListNode head = ListNode.buildList(data);
        ListNode result = new RemovLLelements().removeElements(head , 3);
        ListNode.printList(result);
    }

    public ListNode removeElements(ListNode head, int val) {
        // Write your code here
        if (head == null) {
            return null;
        }


        ListNode dummy = new ListNode(val - 1);  // safty
        dummy.next = head;
        head = dummy;
        while (head != null && head.next != null) {
            while (head.next != null && head.next.val == val) {
                head.next = head.next.next;
            }
            head = head.next;
        }
        return dummy.next;
    }

}
