package nineChap6_LL;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class MergeKList {
  private ListNode[] heads;

  public MergeKList() {
    int[] arr1 = new int[] {1, 4, 10, 12, 100};
    int[] arr2 = new int[] {0, 2, 3, 5, 88};
    int[] arr3 = new int[] {6, 7, 8, 9};

    ListNode l1 = ListNode.buildList(arr1);
    ListNode l2 = ListNode.buildList(arr2);
    ListNode l3 = ListNode.buildList(arr3);
    
    ArrayList<ListNode> heads = new ArrayList<>();
    heads.add(l1);
    heads.add(l2);
    heads.add(l3);
    
    ListNode res = mergeKLists(heads);
    ListNode.printList(res);
  }

  /*
   * What syntax is it? new class{function};
   */
  private static Comparator<ListNode> ListNodeComp = new Comparator<ListNode>() {
    public int compare(ListNode left, ListNode right) {
      if (left == null) {
        return 1;
      } else if (right == null) {
        return -1;
      }
      return left.val - right.val;
    }
  };

  /*
   * Jiuzhang's solution, learn priorityQueue, Comparator, and PQ's ctor
   */
  public static ListNode mergeKLists(ArrayList<ListNode> heads) {
    /*-
     * This is PQ's 4th ctor: 
     * @param1: cap
     * @param2: comparator
     * http://www.tutorialspoint.com/java/util/java_util_priorityqueue.htm
     * */
    Queue<ListNode> minHeap = new PriorityQueue<ListNode>(heads.size(), ListNodeComp);
    for (ListNode head : heads) {
      minHeap.offer(head);
    }
    
    ListNode dummy = new ListNode(0);
    ListNode tail = dummy;
    
    // BFS
    while (!minHeap.isEmpty()) {
      ListNode hea = minHeap.poll();
      tail.next = hea;
      tail = hea;
      if (hea.next != null) {
        minHeap.offer(hea.next);
      }
    }
    
    return dummy.next;
      
  }
  
  public static void main(String[] args) {
    MergeKList mkl = new MergeKList();
  }
}
