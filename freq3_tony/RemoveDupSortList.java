package freq3_tony;

import java.util.*;

/**
 * 
 * @author tzhang
 *
 */
public class RemoveDupSortList {
  private ListNode head;
  public ListNode removeDupSortList(ListNode head) {
    ListNode cur = head;
    while(cur != null && cur.next != null){
      if (cur.value == cur.next.value) {
        cur.next = cur.next.next;
      } else {
        cur = cur.next;
      }      
    }
    return head;
  }
  
  public RemoveDupSortList() {
    // 因为定义了aux为field, 就要在ctor里面初始化. 不然其他任何method要用的时候都会报nullpointerException
    // aux = new int[] {}; 还是不行. 记住: int[] array是static, 必须给大小.
    head = new ListNode(1);
    ListNode n1 = new ListNode(1);
    ListNode n2 = new ListNode(3);
    ListNode n3 = new ListNode(4);
    ListNode n4 = new ListNode(4);
    ListNode n5 = new ListNode(5);
    head.next = n1;
    n1.next = n2;
    n2.next = n3;
    n3.next = n4;
    n4.next = n5;
  }
  
  public void printList(ListNode head) {
    while (head != null) {
      System.out.print(head.value + " ");
      head = head.next;
    }
  }
  public static void main(String[] args) {
    RemoveDupSortList rdsl = new RemoveDupSortList();
    rdsl.removeDupSortList(rdsl.head);
    rdsl.printList(rdsl.head);
  }
}
