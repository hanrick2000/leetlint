// You are given two linked lists representing two non-negative numbers. The digits are stored in
// reverse order and each of their nodes contain a single digit. Add the two numbers and return it
// as a linked list.
//
// Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
// Output: 7 -> 0 -> 8
// ��CC150 2_4��ȫһ��
package frequency_4;

import java.util.LinkedList;

public class AddTwoNumbers {
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    return addTwoNumbers(l1, l2, 0);
  }

  public ListNode addTwoNumbers(ListNode l1, ListNode l2, int carry) {
    // carry �ǽ�����һλ������ val�Ǳ�λ
    if (l1 == null && l2 == null && carry == 0) {
      return null;
    }
    ListNode result = new ListNode(0);
    int value = carry;
    if (l1 != null) {
      value = value + l1.val;
    }
    if (l2 != null) {
      value = value + l2.val;
    }
    // ����Ϊ0 Ȼ�����val���ڵ���10 carry��Ϊ1
    carry = 0;
    if (value > 9) {
      carry = 1;
      value = value % 10;
    }
    // ������ȷ��result��val
    result.val = value; // ������ok�� ��Ϊ ����n1 n2����null val=carry; Ҳִ�й���
    ListNode following =
        addTwoNumbers(l1 == null ? null : l1.next, l2 == null ? null : l2.next, carry);
    result.next = following;
    return result;
  }
  
  public static void printll(ListNode head){
    while (head != null){
      System.out.print(head.val + " ");
      head = head.next;
    }
    if (head == null){
      System.out.println(" EOF");
    }
  }

  public static void main(String[] args) {
    ListNode l1 = new ListNode(3);
    ListNode l2 = new ListNode(4);
    ListNode l3 = new ListNode(7);
    l1.next = l2;
//    l2 = l1.next;
    l2.next = l3;
//    l3 = l2.next;
    ListNode h1 = new ListNode(7);
    ListNode h2 = new ListNode(9);
    ListNode h3 = new ListNode(6);
    h1.next = h2;
//    h2 = h1.next;
    h2.next = h3;
//    h3 = h2.next;
    ListNode ret = (new AddTwoNumbers()).addTwoNumbers(l1, h1);
    
    printll(ret);
  }
}


class ListNode {
  int val;
  ListNode next;

  ListNode(int x) {
    val = x;
    next = null;
  }
}
