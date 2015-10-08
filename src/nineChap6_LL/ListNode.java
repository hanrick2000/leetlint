package nineChap6_LL;

public class ListNode {
  int val;
  ListNode next;

  ListNode(int v) {
    val = v;
    next = null;
  }

  public static void printList(ListNode head) {
    if (head == null) {
      return;
    }
    while (head != null) {
      System.out.print(head.val + " ");
      head = head.next;
    }
    System.out.println();
  }
  
  /**
   * Simple method to convert array to list
   * @param a
   * @return
   */
  public static ListNode buildList(int a[]) {
//    int a[] = new int[]{1,1,2,3,3}; // {1, 2, 3, 3, 4, 4, 5};
    ListNode l[] = new ListNode[a.length];
    for (int i = 0; i < a.length; ++i) {
      l[i] = new ListNode(a[i]);
//      if (i < a.length-1)  {
//        l[i].next = l[i+1]; // too stupid, l[i+1] has not been init yet!
//      }
    }
    for (int i = 0; i < a.length-1; ++i) {
      l[i].next = l[i+1];
    }
//    l[0].next = l[1];
//    l[1].next = l[2];
//    l[2].next = l[3];
//    l[3].next = l[4];
//  l[4].next = l[5];
//  l[5].next = l[6];
    return l[0];
  }
}
