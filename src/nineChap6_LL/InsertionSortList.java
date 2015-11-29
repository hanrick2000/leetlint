package nineChap6_LL;

import misc.ListNode;

/**
 * Created at 5:31 PM on 11/28/15.
 */
public class InsertionSortList {
  public static void main(String[] args) {
    InsertionSortList isl = new InsertionSortList();
    isl.test();
  }

  private void test() {
    int[] A = new int[]{4,3,1,2};
    ListNode root = ListNode.buildList(A);
    root = insertionSort(root);
    ListNode.printList(root);
    //insertion(A);
    //for (int i : A) {
    //  System.out.print(i+" ");
    //}

  }

  /**
   * To understand Yuanbin's code, re-learn the use of dummy!
   * The dummy is a node with no connection!
   * Not the commented out: I was always connected it to the head. WRONG!
   * @param head
   * @return
   */
  public ListNode insertionSort(ListNode head) {
    if (head == null) {
      return head;
    }
    ListNode dummy = new ListNode(0);
    //dummy.next = head;
    //head = dummy;
    ListNode cur = head;
    while (cur != null) {
      ListNode pre = dummy;
      while (pre.next != null && pre.next.val < cur.val) {
        pre = pre.next;
      }
      ListNode temp = cur.next;
      cur.next = pre.next;
      pre.next = cur;
      cur = temp;
    }

    return dummy.next;
  }

  /**
   * http://algorithm.yuanbin.me/zh-cn/linked_list/insertion_sort_list.html
   * @param head: The first node of linked list.
   * @return: The head of linked list.
   */
  public ListNode insertionSortListYuanBin(ListNode head) {
    // write your code here
    ListNode dummy = new ListNode(0);
    ListNode cur = head;
    while (cur != null) {
      ListNode pre = dummy;
      while (pre.next != null && pre.next.val < cur.val) {
        pre = pre.next;
      }
      ListNode temp = cur.next;
      cur.next = pre.next;
      pre.next = cur;
      cur = temp;
    }

    return dummy.next;
  }

  /**
   * WIki algs
   * @param A
   */
  public void insertion(int[] A) {
    for (int i = 1; i < A.length; ++i) {
      int tmp = A[i];
      int j;
      for (j = i-1; j >= 0 && tmp < A[j]; j--) {
        A[j+1] = A[j];
      }
      A[j+1] = tmp;
    }
  }
}
