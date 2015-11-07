/**
 * 和CC150的第二章一样的题目. 也就是说用linkedlist<Integer>来表示一个数. 给你2个linkedlist. 求和. 并且结果也是用linkedlist表示.
 */

package freq4_tony;

public class AddTwoNumbers {

  public ListNode addTwoNumbers(ListNode inputA, ListNode inputB) {
    ListNode ret = new ListNode(99);  // dummy head.
    ListNode p1 = inputA, p2 = inputB, p3 = ret;

    int carry = 0;
    // 只要有1个以上的object. 总是要考虑到顺序以及哪个是空的. 这时候需要分别判断.
    // 而且只要有一个还存在就要继续加.
    while (p1 != null || p2 != null) {
      if (p1 != null) {
        carry += p1.val;
        p1 = p1.next; // 每次算完要换成下一个node.
      } 
//      else if (p2 != null) {    搞清楚逻辑,这里是分别计算inputA跟inputB. 并不是非此即彼.
      if (p2 != null){
        carry += p2.val;
        p2 = p2.next;
      }
      // 为什么要用一个dummy head给result的链表呢? 这样每次可以接在上一个result.next
      // 否则不好下手啊. 而且Lafore的书也都是2个ADT: linkNode和linklist. linklist的唯一field就是first. 
      // 它指向链表的第一个node.
      p3.next = new ListNode(carry % 10);
      p3 = p3.next;
      carry /= 10;
    }

    if (carry == 1) {
      p3.next = new ListNode(1);
    }
    return ret.next;
  }

  public static void displayListNode(ListNode head) {
    ListNode pre = head, cur = null;
    // cur = head;
    while (pre != null) {
      System.out.print(pre.val + "->");
      pre = pre.next;
    }
    if (pre == null)
      System.out.println("null");
  }

  public static void main(String[] args) {
    ListNode n1 = new ListNode(2);
    ListNode n2 = new ListNode(4);
    ListNode n3 = new ListNode(3);
    ListNode l1 = new ListNode(1);
    ListNode l2 = new ListNode(6);
    ListNode l3 = new ListNode(4);
    ListNode l4 = new ListNode(8);
    n1.next = n2;
    n2.next = n3;
    
    l1.next = l2;
    l2.next = l3;
    l3.next = l4;

//    displayListNode(n1);
//    displayListNode(l1);
    ListNode result = new AddTwoNumbers().addTwoNumbers(n1, l1);
    displayListNode(result);
  }

}


class ListNode {
  int val;
  ListNode next;

  ListNode(int v) {
    val = v;
    next = null;
  }
}

// class ListNode {
// int val;
// int carry;
//
// ListNode(){
// val = 0;
// carry = 0;
// }
//
// ListNode(int v, int c){
// val = v;
// carry = c;
// }
// }
