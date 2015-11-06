package freq3_tony;

import java.util.*;

/**
 * 再做remove duplicates from list之前先做remove Nth-to-end Element of list
 * 
 * @author tzhang
 *
 */
public class RemoveNthElemList {
  // private int[] aux;
  // http://stackoverflow.com/questions/11990514/creating-array-without-declaring-the-size-java
  private ArrayList<Integer> aux;
  private ListNode head; // 现在终于只到为什么这个

  // dummy method to find size and copy list into Arraylist. O(n)
  public int findSize(ListNode head, int n) {
    int size = 0;
    ListNode node = head;
    if (node == null)
      return 1;
    // aux = new int[] {};
    while (node != null) {
      aux.add(node.value);
      // System.out.println(node.val + ", " + size);
      node = node.next;
      size++;
    }
    return size;
  }

  // 用shell sort的方法: 2个相隔n+1个node的pointer!
  public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode pilot, pre;
    pilot = head;
    pre = head;
    while (pilot != null) {
      if (n >= 0) {
        --n;
      } else {
        pre = pre.next;
      }
      pilot = pilot.next;
    }

    if (n > 0) {
      // too long to fit into this input.
    } else if (n == 0) {
      head = head.next; // 说明刚好n=0的时候pilot到尾了. 说明长度正好n
    } else {
      pre.next = pre.next.next; // 正常情况. 注意list的删除node, 要给出node.pre. 然后如此update.
    }

    return head;
  }

  public void printList(ListNode head) {
    while (head != null) {
      System.out.print(head.value + " ");
      head = head.next;
    }
  }

  // ctor to build a list
  public RemoveNthElemList() {
    // 因为定义了aux为field, 就要在ctor里面初始化. 不然其他任何method要用的时候都会报nullpointerException
    // aux = new int[] {}; 还是不行. 记住: int[] array是static, 必须给大小.
    aux = new ArrayList<>();
    head = new ListNode(3);
    this.head = head;
    ListNode n1 = new ListNode(1);
    ListNode n2 = new ListNode(5);
    ListNode n3 = new ListNode(4);
    ListNode n4 = new ListNode(7);
    ListNode n5 = new ListNode(2);
    head.next = n1;
    n1.next = n2;
    n2.next = n3;
    n3.next = n4;
    n4.next = n5;
  }

  public static void main(String[] args) {
    RemoveNthElemList rnel = new RemoveNthElemList();
    System.out.println("size of list: " + rnel.findSize(rnel.head, 0));
    System.out.println(rnel.aux);
    rnel.removeNthFromEnd(rnel.head, 2);
    System.out.println(rnel.findSize(rnel.head, 0));
    rnel.printList(rnel.head);
  }
}


class ListNode {
  int value;
  ListNode next;

  ListNode(int v) {
    this.value = v;
    this.next = null;
  }
}
