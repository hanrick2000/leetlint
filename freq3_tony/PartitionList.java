package freq3_tony;

/**
 * 给一个singly linkedlist, 以及一个int x. 重新Stable的排列出x之前的小于x, 之后的大于x. 遇到linkedlist就是指针. 特别是双指针很好用.
 * 这里参考Ganker的答案. 其实就是CC里面给出pointer, 如何删除这个node的应用: 如何在不知道pre的情况下把A->B->...变成B->A->... 重新回顾了Java的基础,
 * obj1 = obj2是copy的handle, 即指向obj2的指针.
 * 
 * @author tzhang
 *
 */
public class PartitionList {
  private class ListNode {
    int val;
    ListNode next;

    ListNode(int v) {
      val = v;
      next = null;
    }

    int getValue() {
      return val;
    }

    ListNode getNext() {
      return next;
    }
  }

  /**
   * 双指针大法解决linkedlist问题. 这里walker指向小于x的最后一个node, runner就一直向前跑. 作为loop的sential
   * @param head
   * @param x
   * @return
   */
  private ListNode partition(ListNode head, int x) {
    ListNode helper = new ListNode(0);
    helper.next = head;
    ListNode walker, runner;
    walker = helper;
    runner = helper;
    while (runner.next != null) {
      if (runner.next.val < x) {
        if (runner != walker) {
          ListNode next = runner.next.next;
          runner.next.next = walker.next;
          walker.next = runner.next;
          runner.next = next;
        } else {
          runner = runner.next;
        }
        walker = walker.next;
      } else {
        runner = runner.next;
      }
    }
    return helper.next;
  }

  private void printList(ListNode head) {
    ListNode temp;
    temp = head;
    while (temp != null) {
      System.out.print(temp.val + " ");
      temp = temp.getNext();
    }
    System.out.println("-");
  }

  /**
   * ctor as a client
   */
  public PartitionList() {
    ListNode a = new ListNode(1);
    ListNode b = new ListNode(3);
    ListNode c = new ListNode(1);
    ListNode d = new ListNode(5);
    ListNode e = new ListNode(2);
    ListNode f = new ListNode(1);
    a.next = b;
    b.next = c;
    c.next = d;
    d.next = e;
    e.next = f;
    printList(a);
    ListNode res = partition(a, 3);
    printList(res);
  }

  public static void main(String[] args) {
    PartitionList pl = new PartitionList();

  }
}
