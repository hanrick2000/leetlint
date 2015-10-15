package Easy;

import misc.ListNode;

/**
 * Created by 1:02 PM on 10/14/2015.
 */
public class DelMidNode {
    public static void main(String[] args) {

    }
    public void deleteNode(ListNode node) {
        // write your code here
        if (node == null || node.next == null) {
            return;
        }

        node.val = node.next.val;
        node.next = node.next.next;
    }
}
