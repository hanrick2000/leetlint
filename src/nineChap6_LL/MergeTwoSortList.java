package nineChap6_LL;

import misc.ListNode;

public class MergeTwoSortList {
  private ListNode alist, blist;
  public MergeTwoSortList(){
    int[] a = new int[]{1,3,5,7,9,11};
    int[] b = new int[]{2,4,6,8,10};
    
    alist = ListNode.buildList(a);
    blist = ListNode.buildList(b);
  }
  
  public static ListNode mergeTwoLista(ListNode al, ListNode bl) {
    if (al == null) {
      return bl;
    }
    if (bl == null) {
      return al;
    }
    
    ListNode dummy = new ListNode(0);
    ListNode head = dummy; // in the end, is it still the original dummy?
    
    while (al != null && bl != null) {
      ListNode cur = null;
      if (al.val <= bl.val) {
        cur = al;
        al = al.next;
      }
      else {
        cur = bl;
        bl = bl.next;
      }
       
      head.next = cur;
      head = head.next;
    }
    
    head.next = (al == null)? bl : al;
    return dummy.next;
  }
  
  public static void main(String[] args) {
    MergeTwoSortList mtsl = new MergeTwoSortList();
    ListNode.printList(mtsl.alist);
    ListNode.printList(mtsl.blist);
    ListNode res = mergeTwoLista(mtsl.alist, mtsl.blist);
    ListNode.printList(res);
  }
}
