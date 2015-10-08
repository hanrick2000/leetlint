//package nineChap6_LL;
//
///**
// * A classical problem in improving Divide & Conquer via "order"!
// * @author tzhang
// *
// * @param <T>
// */
//
//public class List2BST<T extends Comparable> {
//  private ListNode<T> current; // the key point !
//
//
//  private static class ListNode<T> {
//    T val;
//    ListNode<T> next;
//    ListNode(T val) {
//      this.val = val;
//      this.next = null;
//    }
//  }
//
//
//  public Node<T> list2BST(ListNode<T> head) {
////    ListNode<T> tail = findTail(head);
////    return helper(head, tail);
//    return helper(head, 6);
//  }
//
//  // divide --------- BUGgy!
//  public Node<T> helper(ListNode<T> head, ListNode<T> tail) {
//    if (head == tail ) { // || tail == null || head == tail.next
//      if (head == null)  return null;
//      return new Node<>(head.val);
//    }
//
//    ListNode<T> midPre = findMid(head, tail);
//    System.out.println("Mid: " + midPre.next.val);
//
//    Node<T> left = helper(head, midPre);
//    Node<T> right = helper(midPre.next.next, tail);
//
//    Node<T> parent = new Node<T>(midPre.next.val);
//    parent.left = left;
//    parent.right = right;
//    return parent;
//  }
//
//  /**
//   * Basic D&C method to build BST, need O(n) to find mid so O(nLgn) in total
//   * @param head
//   * @param size
//   * @return
//   */
//  public Node<T> helper(ListNode<T> head, int size) {
//    if (size == 0 || size == 1) {
//      if (head == null)  return null;
//      return new Node<>(head.val);
//    }
//
//    ListNode<T> mid = findMid(head, size);
//    System.out.println(mid.val);
//    Node<T> left = helper(head, size/2);
//    Node<T> right = helper(mid.next, size-1-size/2);
//
//    Node<T> parent = new Node<>(mid.val);
////    System.out.println(mid.val);
//    parent.left = left;
//    parent.right = right;
//    return parent;
//  }
//
//  // return the val of mid node, using slow/fast pointers
//  public ListNode<T> findMid(ListNode<T> head) {
//    if (head == null || head.next == null) {
//      return head;
//    }
//
//    ListNode<T> slow = head, fast = head.next;
////    if (fast != null && fast.next != null) {  // WTF! why I use if???
////      slow = slow.next;
////      fast = fast.next.next;
////    }
//    ListNode<T> slowPre = slow;
//    while (fast != null && fast.next != null) {
//      fast = fast.next.next;
//      slowPre = slow;
//      slow = slow.next;
//    }
//
//    return slowPre;
//  }
//
//  // not a good control of end!
//  public ListNode<T> findMid(ListNode<T> head, ListNode<T> tail) {
//    if (head == tail || head.next == tail) {
//      return head;
//    }
//
//    ListNode<T> slow = head, fast = head.next;
//    while (fast != tail && fast.next != tail) {
//      slow = slow.next;
//      fast = fast.next.next;
//    }
//
//    return slow;
//  }
//
//  // learn from jiuzhangs
//  public ListNode<T> findMid(ListNode<T> head, int size) {
//    if (size == 0 || size == 1) {
//      return head;
//    }
//
//    int idx = 0;
//    while (head != null) {
//      if (idx == size/2)  break;
//      head = head.next;
//      idx++;
//    }
//
//    return head;
//  }
//
//  /* -------------------------------------------------------------------
//                  jiu zhang's O(n) method!
//  *********************************************************************/
//
//  public int getListLength(ListNode<T> head) {
//    int len = 0;
//    while (head != null) {
//      head = head.next;
//      len++;
//    }
//    return len;
//  }
//
//  public Node<T> list2BSTJiuZhang(ListNode<T> head) {
//    int len = getListLength(head);
//    current = head;
//    return list2BSThelper(len);
////    return helperLC(head, len); // WRONG
//  }
//
//  /**
//   * key method: use in-order to reduce findRoot from O(n) to O(1)
//   * @param size
//   * @return
//   */
//  private Node<T> list2BSThelper(int size) {
//    if (size <= 0) {
//      return null;
//    }
////    if (size == 1) {
////      return new Node(current.val);
////    }
//
//    Node<T> left = list2BSThelper(size/2);
//    Node<T> root = new Node(current.val);
//    current = current.next;
//    Node<T> right = list2BSThelper(size-1- size/2);
//
//    root.left = left;
//    root.right = right;
//    return root;
//  }
//
//  /**
//   * WRONG!
//   * Why I have to use global var: current?
//   * Because the head will change in recursion and recursion call is done by procedure call, so the
//   * @param head
//   * @param size
//   * @return
//   */
//  private  Node<T> helperLC( ListNode<T>  head, int size) {
//    if (size <= 0) {
//        return null;
//    }
//
//    Node<T>  left = helperLC(head, size/2);
//    Node<T>  root = new  Node<T> (head.val);
//    // head = head.next;
//    System.out.println("root: " + root.data);
//    Node<T>  right = helperLC(head.next, size-1-size/2);
//    root.left = left;
//    root.right = right;
//    return root;
//}
//
//  //private static ListNode<T> findTail(ListNode<T> head) { // Cannot make a static reference to the non-static type T
//  private static <K extends Comparable> ListNode<K> findTail(ListNode<K> head) {
//    while (head != null) {
//      head = head.next;
//    }
//    return head;
//  }
//
//  public static ListNode<Integer> test1() {
//    ListNode<Integer> n1 = new ListNode<Integer>(1);
//    ListNode<Integer> n2 = new ListNode<Integer>(2);
//    ListNode<Integer> n3 = new ListNode<Integer>(3);
//    ListNode<Integer> n4 = new ListNode<Integer>(4);
//    ListNode<Integer> n5 = new ListNode<Integer>(5);
//    ListNode<Integer> n6 = new ListNode<Integer>(6);
//
//    n1.next = n2;
//    n2.next = n3;
//    n3.next = n4;
//    n4.next = n5;
//
////    n5.next = n6;
////    n6.next = null;
//
//    return n1;
//  }
//
//  public static void main(String[] args) {
//    List2BST<Integer> l2b = new List2BST<>();
//    ListNode<Integer> ans = l2b.findMid(test1());
//
//    ListNode<Integer> head = test1();
//
////    ListNode<Integer> middle = l2b.findMid(head, 3);
////    System.out.println(middle.val);
//
////    while (head != null) {
////      System.out.print(head.val + " ");
////      head = head.next;
////    }
//
////    ListNode<Integer> ln2 = head.next;
////    ListNode<Integer> ln3 = head.next.next;
////    System.out.println(ln2 == ln3); // false
//
////    System.out.println("\n" + ans.val);
//
////    Node<Integer> res = l2b.list2BST(head);
//    Node<Integer> res = l2b.list2BSTJiuZhang(head);
//    BTreePrinter.printNode(res);
////
////    ListNode<Integer> taill  = l2b.findTail(head);
////    System.out.println(taill == null);  // true
//  }
//}
