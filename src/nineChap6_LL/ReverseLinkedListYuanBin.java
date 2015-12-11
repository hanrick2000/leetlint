package nineChap6_LL;

import misc.ListNode;

/**
 * http://algorithm.yuanbin.me/zh-cn/linked_list/reverse_linked_list.html
 * Created at 4:51 PM on 11/29/15.
 */
public class ReverseLinkedListYuanBin {
  public static void main(String[] args) {
    int[] A = new int[]{1,2,3,4,5,6};
    ListNode root1 = ListNode.buildList(A);
    ListNode rev1 = iterRev(root1);
    ListNode.printList(rev1);

    ListNode root2 = ListNode.buildList(new int[]{4,5,6,7,8,9});
    ListNode rev2 = iterRev(root2);
    ListNode.printList(rev2);

  }

  public static ListNode iterRev(ListNode root) {
    if (root == null) {
      return root;
    }

    ListNode pre = null;
    ListNode cur = root;
    while (cur != null) {
      ListNode post = cur.next;
      cur.next = pre;
      pre = cur;
      cur = post;
    }
    return pre.next;
  }

  /**
   * http://www.cnblogs.com/kubixuesheng/p/4394509.html
   * @param root
   * @return
   */
  public static ListNode rec(ListNode root) {
    if (root == null) {
      return root;
    }
    if (root.next == null) {
      return root;
    }
    ListNode newHead = rec(root.next);
    root.next.next = root;
    root.next = null;
    return newHead;
  }
}
