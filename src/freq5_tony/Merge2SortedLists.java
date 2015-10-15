package freq5_tony;

import misc.ListNode;

public class Merge2SortedLists {

  public ListNode merge2SortedLists(ListNode l1, ListNode l2){
    ListNode p1 = l1;   // why?
    ListNode p2 = l2;;
    
    ListNode fakeHead = new ListNode(0);
    ListNode ret = fakeHead;
    
    while (p1!=null && p2!=null){
      if (p1.val <= p2.val){
        ret.next = l1;
        p1 = p1.next;
      }
      else{
        ret.next = p2;
        p2 = p2.next;
      }
      ret = ret.next;  // why? need to build a linked-list.
    }
    
    if (p1 != null){
      ret.next = p1;
    }
    else
      ret.next = p2;
    
    return fakeHead.next;   // why?
  }
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
