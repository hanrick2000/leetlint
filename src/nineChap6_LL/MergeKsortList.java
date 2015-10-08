package nineChap6_LL;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class MergeKsortList {
  public ListNode mergeKLists(List<ListNode> lists) {
    // write your code here

    int[] arr1 = new int[] {1, 4, 10, 12, 100};
    int[] arr2 = new int[] {0, 2, 3, 5, 88};
    int[] arr3 = new int[] {6, 7, 8, 9};

    ListNode l1 = ListNode.buildList(arr1);
    ListNode l2 = ListNode.buildList(arr2);
    ListNode l3 = ListNode.buildList(arr3);
    
    List<ListNode> heads = new ArrayList<>();
    heads.add(l1);
    heads.add(l2);
    heads.add(l3);
    
    ListNode ans = merge(heads);
    return ans;
  }

  public static ListNode merge(List<ListNode> lists) {
    Comparator<ListNode> listCmptr = new Comparator<ListNode>() {
      public int compare(ListNode lhs, ListNode rhs) {
        if (lhs == null) {
          return -1;
        }
        else if (rhs == null) {
          return 1;
        }
        return lhs.val - rhs.val;
      }
    };
    
    // this is how to instantiate a priority queue: PriorityQueue(int initialCapacity, Comparator<? super E> comparator)
    Queue<ListNode> minPQ = new PriorityQueue<ListNode>(listCmptr);
    for (int i = 0; i < lists.size(); ++i ){  // simple traverse lists
      if (lists.get(i) != null) {
        minPQ.add(lists.get(i));
      }
    }
    
    ListNode dummy = new ListNode(0);
    
//    while (!minPQ.isEmpty()) {
//      ListNode min = minPQ.poll();
//      
//      minPQ.add(min.next);
//      dummy.next = min;
//    }
    
    ListNode tail = dummy;
    while (!minPQ.isEmpty()) {
      ListNode min = minPQ.poll();
      tail.next = min;
      tail = tail.next;
      if (tail.next != null) {
        minPQ.add(tail.next);
      }
    }
    
    return dummy.next;
  }
  
  public MergeKsortList() {
    ListNode test = mergeKLists(null);
    ListNode.printList(test);
  }
  
  public static void main(String[] args) {
    MergeKsortList mksl = new MergeKsortList();
  }
}
