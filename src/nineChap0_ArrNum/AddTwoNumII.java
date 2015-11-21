package nineChap0_ArrNum;

import misc.ListNode;

/**
 * http://www.lintcode.com/en/problem/add-two-numbers-ii/ Created at 4:57 PM on 11/20/15.
 */
public class AddTwoNumII {
  public static void main(String[] args) {
    ListNode sa = new ListNode(3);
    ListNode li = new ListNode(6);
    ListNode yi = new ListNode(1);
    ListNode yi1 = new ListNode(1);
    ListNode qi = new ListNode(7);
    ListNode er = new ListNode(2);
    ListNode ji = new ListNode(9);
    ListNode wu = new ListNode(5);
    ListNode wu1 = new ListNode(5);

    //li.next = yi;
    //yi.next = qi;
    //er.next = ji;
    //ji.next = wu;

    sa.next = yi;
    yi.next = er;

    wu.next = ji;
    ji.next = wu1;
    wu1.next = yi1;


    ListNode.printList(sa);
    ListNode.printList(wu);
    ListNode ans = new AddTwoNumII().addLists2(sa, wu);
    ListNode.printList(ans);
  }

  public ListNode addLists2(ListNode l1, ListNode l2) {
    // write your code here
    ListNode dummy = new ListNode(0);
    ListNode tail = dummy;
    int carry = 0;
    l1 = reverse(l1);  // I forgot to return as l1 in the first time...
    l2 = reverse(l2);
      ListNode.printList(l1);
      ListNode.printList(l2);

    while (l1 != null && l2 != null) {
      int sum = l1.val + l2.val + carry;
      tail.next = new ListNode(sum % 10);
      carry = sum / 10;
      tail = tail.next;

      l1 = l1.next;
      l2 = l2.next;
    }
    while (l1 != null) {
      int sum = l1.val + carry;
      tail.next = new ListNode(sum % 10);
      carry = sum / 10;
      tail = tail.next;

      l1 = l1.next;
    }

    while ( l2 != null) {
      int sum = l2.val + carry;
      tail.next = new ListNode(sum % 10);
      carry = sum / 10;
      tail = tail.next;

      l2 = l2.next;
    }

    if (carry != 0) {
      tail.next = new ListNode(carry);

      tail = tail.next;
    }

    return reverse(dummy.next);
  }

  /**
   * Well designed, clean version of reverse of list
   * @param root
   * @return
   */
  private ListNode reverse(ListNode root) {
    ListNode prev = null;

    while (root != null) {
      ListNode post = root.next;
      root.next = prev;
      prev = root;
      root = post;
    }

    return prev;
  }

  private ListNode reverseTTT(ListNode root) {
    if (root == null) {
      return null;
    }

    ListNode pre = root;
    root = root.next;
    pre.next = null;

    while (root != null) {// && root.next != null) {
      ListNode post = root.next;
      root.next = pre;
      pre = root;
      root = post;
    }

    return pre;
  }

/* ------------------------------------------------------------------------------------------------
 *                        Scott's code separator: below is the old implement
 * ------------------------------------------------------------------------------------------------
 */

  /**
   * I ran the failed case in here is fine, but it failed in lintcode...
   * @param l1: the first list
   * @param l2: the second list
   * @return: the sum list of l1 and l2
   */
  public ListNode addLists2NOTPASS(ListNode l1, ListNode l2) {
    // write your code here
    ListNode dummy = new ListNode(0);
    if (l1 == null || l2 == null) {
      return dummy;
    }
    int[] tmp = new int[] {0,0};
    ListNode[] aligned = align(l1, l2);

    l1 = aligned[0];
    l2 = aligned[1];
    ListNode.printList(l1);
    ListNode.printList(l2);
    ListNode res = new ListNode(-1);
    int ans = rec(l1, l2, res, tmp);
    if (res.next != null && tmp[1] == 1) {
      ListNode yi = new ListNode(1);
      yi.next = res.next;
      res.next = yi;
    }

    int result = simple(l1, l2, new int[]{-1});
    System.out.println(result);

    return res.next;
  }

  public int rec(ListNode l1, ListNode l2, ListNode ans, int[] tmp) {
    if (l1 == null && l2 == null) {
      ans.next = null;
      return 0;
    }
    int a = rec(l1.next, l2.next, ans, tmp);
    int sum = l1.val + l2.val;
    sum += tmp[1];
    tmp[0] = sum % 10;
    tmp[1] = sum / 10;
    //ans.next = new ListNode(tmp[0]);
    ListNode rest = ans.next;
    ans.next = new ListNode(tmp[0]);
    ans.next.next = rest;
    return 0;
  }

  public int simple(ListNode l1, ListNode l2, int[] level) {
    if (l1 == null && l2 == null) {
      level[0] = 0;
      return 0;
    }
    int a = simple(l1.next, l2.next, level);
    int sum = (int) ((l1.val + l2.val)*(Math.pow(10,level[0])));  // '^' is now power in Java!!!
    level[0]++;
    sum += a;
    return sum;
  }

  public ListNode[] align(ListNode l1, ListNode l2) {
    int leng1 = leng(l1);
    int leng2 = leng(l2);
    int diff = leng1 - leng2;
    if (diff > 0) {
      ListNode root = new ListNode(-1);
      ListNode dummy = root;
      while(diff-->0) {
        root.next = new ListNode(0);
        root = root.next;
      }
      root.next = l2;
      l2 = dummy.next;
    }
    else if (diff < 0) {
      ListNode root = new ListNode(-1);
      ListNode dummy = root;
      diff = -diff;
      while(diff-->0) {
        root.next = new ListNode(0);
        root = root.next;
      }
      root.next = l1;
      l1 = dummy.next;
    }
    ListNode[] res = new ListNode[2];
    res[0] = l1;
    res[1] = l2;
    return res;
  }

  private int leng(ListNode root) {
    int leng = 0;
    while (root != null) {
      root = root.next;
      leng++;
    }
    return leng;
  }
}
