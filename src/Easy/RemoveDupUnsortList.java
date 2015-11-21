package Easy;

import java.util.HashMap;
import java.util.Map;
import misc.ListNode;

/**
 * http://www.lintcode.com/en/problem/remove-duplicates-from-unsorted-list/
 * Created at 12:04 AM on 11/20/15.
 */
public class RemoveDupUnsortList {
  public static void main(String[] args) {
    int[] A = new int[]{1,3,2,1,4};
    ListNode root = ListNode.buildList(A);
    RemoveDupUnsortList rdul = new RemoveDupUnsortList();
    ListNode ans = rdul.removeDuplicates(root);
    ListNode.printList(ans);
  }
  /**
   * @param head: The first node of linked list.
   * @return: head node
   */
  public ListNode removeDuplicates(ListNode head) {
    Map<Integer, Integer> map = new HashMap<>();
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    head = dummy;

    while (head.next != null) {
      if (map.containsKey(head.next.val)) {   //eg: 1->2-> 1 - 1 -1 -1 -4.
        head.next = head.next.next;  // Smart, NOT changing head, but update next.
      }
      else {
        map.put(head.next.val, 0);
        head = head.next;
      }
    }
    return dummy.next;
  }

  public ListNode removeDuplicatesSOSO(ListNode head) {
    // Write your code here
    Map<Integer, Integer> map = new HashMap<>();
    while (head != null) {
      if (map.containsKey(head.val)) {
        //
      }
      map.put(head.val, 1);
      head = head.next;
    }
    ListNode dummy = new ListNode(0);
    ListNode res = dummy;
    for (int v : map.keySet()) {
      dummy.next = new ListNode(v);
      dummy = dummy.next;
    }
    return res.next;
  }
}
