package freq4_tony;


// http://n00tc0d3r.blogspot.com/2013/01/add-two-binary-numbers.html?q=remove+element
/**
 * 这道题跟AddTwoNumbers类似,不过链表的顺序很正常的数的顺序一样. 上一题的是reverse order. 这题是plain order
 * 
 * @author tzhang
 * 
 */
public class AddTwoNumbersPlainOrder {

  // public ListNode addTwoNUmbersPlainOrder(ListNode A, ListNode B) {
  // ListNode res;
  //
  // return res;
  // }
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

  /**
   * 默写了一遍,居然还出现WTF的错误. 还是搞不懂她这个recursion怎么想出来的.
   * 
   * @param l1
   * @param diff
   * @param l2
   * @param result
   * @return
   */
  private int addTwoNumberRec(ListNode l1, int diff, ListNode l2, ListNode result) {
    // int sum = l1.val;
    if (l1 == null && l2 == null) {
      return 0;
    }
    // else { // 不要写else 否则就是dead code了.
    int sum = l1.val;
    result.next = new ListNode(0);
    if (diff <= 0) {
      sum += l2.val;
      // sum += addTwoNumberRec(l1.next, 0, l2, result.next); /////////////wtf
      sum += addTwoNumberRec(l1.next, 0, l2.next, result.next);
    } else {
      // sum += addTwoNumberRec(l1.next, diff - 1, l2.next, result.next); /////////////wtf
      sum += addTwoNumberRec(l1.next, diff - 1, l2, result.next);
    }
    // }
    result.next.val = sum % 10;
    return sum / 10;
  }

  private int length(ListNode ll) {
    int len = 0;
    while (ll != null) {
      ++len;
      ll = ll.next;
    }
    return len;
  }

  // l1 >= l2
  // public ListNode addTwoNumber(ListNode l1, ListNode l2) {
  // ListNode ans = new ListNode(0);
  // int l1l = length(l1);
  // int l2l = length(l2);
  // ans.val = addTwoNumberRec(l1, l2l - l1l, l2, ans);
  // return ans;
  // }
  private ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    int n1 = length(l1), n2 = length(l2);
    ListNode dummy = new ListNode(0);
    if (n1 >= n2) {
      dummy.val = addTwoNumberRec(l1, n1 - n2, l2, dummy);
    } else {
      dummy.val = addTwoNumberRec(l2, n2 - n1, l1, dummy);
    }
    return (dummy.val > 0) ? dummy : dummy.next;
  }

  // ////////////
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    ListNode n1 = new ListNode(1);
    ListNode n2 = new ListNode(2);
    ListNode n3 = new ListNode(0);
    ListNode l1 = new ListNode(1);
    ListNode l2 = new ListNode(0);
    ListNode l3 = new ListNode(8);
    ListNode l4 = new ListNode(3);
    n1.next = n2;
    n2.next = n3;

    l1.next = l2;
    l2.next = l3;
    l3.next = l4;

    displayListNode(n1);
    displayListNode(l1);
    ListNode result = new AddTwoNumbersPlainOrder().addTwoNumbers(n1, l1);
    displayListNode(result);
  }

}
