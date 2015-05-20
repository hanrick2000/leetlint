/**
 * 在做add two number: plain order linkedlist的时候看到N00tc0d3r的解法时看的头晕了
 * 还是先复习一下recursion吧.
 */

package freq4_tony;

public class testRecursion {

  public static int triangle(int n, ListNode pre) {
    System.out.println("Entering: n=" + n );
    pre.next = new ListNode(0);
    if (n==0){
      System.out.println("returning base condition: 1");
      return 1;
    }
    else {
      int temp = n ;
      temp += n + triangle(n-1, pre.next);
      pre.val += n;
      System.out.println("Returning " + temp);
      return temp;
    }
//    System.out.println("hello @ " + n);
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
  
  private static void testPrePost(int n){
    System.out.println("pre: " + n);
    if (n==0){
//      testPrePost(n);
      System.out.println("base condition: "+ n);
//      return 0;
    }
    else {
      testPrePost(n-1);
    }
    System.out.println("Post: " + n);
  }
  
  public static void main(String[] args) {
    // TODO Auto-generated method stub
//    ListNode dummy = new ListNode(0);
//    triangle(3, dummy);
//    displayListNode(dummy);
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
//    testPrePost(3);
  }
}
