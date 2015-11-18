package nineChap6_LL;

import misc.ListNode;

/**
 * http://www.lintcode.com/en/problem/intersection-of-two-linked-lists/
 * Created at 11:59 PM on 11/16/15.
 */
public class IntersectionLL {
  public static void main(String[] args) {
    int[] arr1 = new int[] {1, 4, 10, 12, 100};
    int[] arr2 = new int[] {0, 2, 3, 88};
    int[] arr3 = new int[] {6,7};

    ListNode l1 = ListNode.buildList(arr1);
    ListNode l2 = ListNode.buildList(arr2);
    ListNode l3 = ListNode.buildList(arr3);

    findLast(l1).next = l3;
    findLast(l2).next = l3;

    IntersectionLL ill = new IntersectionLL();
    ListNode ans = ill.getIntersectionNode1(l1, l2);
    if (ans == null)  System.out.println("Null node");
    else  System.out.println(ans.val);
  }

  /**
   * Easiest way to do
   * http://www.cnblogs.com/yuzhangcmu/p/4128794.html
   * @param headA: the first list
   * @param headB: the second list
   * @return: a ListNode
   */
  public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
    // Write your code here
    ListNode dummyA = headA;
    ListNode dummyB = headB;
    int lenA = 0, lenB = 0;
    while (dummyA!=null) {
      lenA++;
      dummyA = dummyA.next;
    }
    while (dummyB!= null) {
      lenB++;
      dummyB = dummyB.next;
    }
    dummyA = headA;
    dummyB = headB;
    if (lenA > lenB) {
      int diff = lenA-lenB;
      while (diff != 0) {
        diff--;
        dummyA = dummyA.next;
      }
      //while (dummyA != null) {
      //  if (dummyA == dummyB) {
      //    return dummyA;
      //  }
      //  dummyA = dummyA.next;
      //}
      //return null;
    }
    else if (lenA < lenB){
      int diff = lenB-lenA;
      while (diff != 0) {
        diff--;
        dummyB = dummyB.next;
      }
      //while (dummyB != null) {
      //  if (dummyA == dummyB) {
      //    return dummyA;
      //  }
      //  dummyB = dummyB.next;
      //}
    }
    //else {
    //  while (dummyA != null) {
    //    if (dummyA == dummyB) {
    //      return dummyA;
    //    }
    //    dummyA = dummyA.next;
    //    dummyB = dummyB.next;
    //  }
    //}
    while (dummyA != null && dummyB != null) {
      if (dummyA == dummyB) {
        return dummyA;
      }
      dummyA = dummyA.next;
      dummyB = dummyB.next;
    }
    return null; // why need this?
  }

  private static ListNode findLast(ListNode root) {
    while (root.next != null) {
      root = root.next;
    }
    return root;
  }
}
