package nineChap6_LL;

import java.util.HashMap;

public class CopyRandLL {
  private RandomListNode head;

  private static class RandomListNode {
    int label;
    RandomListNode next, random;

    RandomListNode(int x) {
      this.label = x;
    }

    public static void print(RandomListNode head) {
      RandomListNode d1 = head, d2 = head;

      while (d1 != null) {
        System.out.print(d1.label + " ");
        d1 = d1.next;
      }
      System.out.println();
      while (d2 != null) {
        if (d2.random != null) {
          System.out.print(d2.random.label + " ");
          d2 = d2.next;
        }
      }
      System.out.println();
    }
  }

  public void test() {
    RandomListNode r1 = new RandomListNode(-1);
    RandomListNode r2 = new RandomListNode(2);
    RandomListNode r3 = new RandomListNode(3);
    RandomListNode r4 = new RandomListNode(4);

    // r1.next = r2;
    // r2.next = r3;
    // r3.next = r4;
    // r4.next = null;
    //
    // r1.random = r3;
    // r2.random = r1;
    // r3.random = r4;
    // r4.random = r3;

    r1.next = r2;
    r2.next = null;
    
    r1.random = null;
    r2.random = null;

    head = r1;
  }

  /**
   * Method 1: normal way to do copy data structure
   * 
   * @param head
   * @return
   */
  public RandomListNode copyRandomList1(RandomListNode head) {
    HashMap<RandomListNode, RandomListNode> cpyNode = new HashMap<>();

    RandomListNode dummy1 = head, dummy2 = head;
    // phase 1: copy node
    while (dummy1 != null) {
      cpyNode.put(dummy1, new RandomListNode(dummy1.label));
      dummy1 = dummy1.next;
    }

    // phase 2: copy link
    while (dummy2 != null) {
      cpyNode.get(dummy2).next = cpyNode.get(dummy2.next);
      cpyNode.get(dummy2).random = cpyNode.get(dummy2.random);
      dummy2 = dummy2.next;
    }

    return cpyNode.get(head);
  }

  public RandomListNode copyRandomList2(RandomListNode head) {
    RandomListNode dummy1 = head, dummy2 = head, dummy3 = head;

    RandomListNode ori = new RandomListNode(0), cpy = new RandomListNode(0);
    RandomListNode ans = cpy;

    // phase 1: copy tmp + next
    while (dummy1 != null) {
      RandomListNode nex = new RandomListNode(dummy1.label);
      nex.next = dummy1.next;
      dummy1.next = nex;

      dummy1 = dummy1.next.next;
    }

    // phase 2: traverse again and copy Random
    while (dummy2 != null) {
      RandomListNode ran = new RandomListNode(dummy2.label);
      dummy2.next.random = dummy2.random;

      dummy2 = dummy2.next.next;
    }

    // phase 3: split into 2 lists
    while (dummy3 != null) {
      ori.next = dummy3;
      cpy.next = dummy3.next;

      ori = ori.next;
      cpy = cpy.next;

      dummy3 = dummy3.next.next;
    }

    return ans.next;
  }

  public CopyRandLL() {
    test();

    // Henng's way
    /*
     * RandomListNode.print(head);
     * // RandomListNode ans = copyRandomList1(head);
     * RandomListNode ans = copyRandomList2(head);
     * RandomListNode.print(ans);
     */


  }

  /****************************************************
   * solution: NO HashMap version
   ****************************************************/

  /**
   * JiuZhang solution, Very good in call-by-val
   * 
   * @param head
   */
  private void copyNext(RandomListNode head) {
    while (head != null) {
      RandomListNode newNode = new RandomListNode(head.label);
      newNode.next = head.next;
      newNode.random = head.random; // MUST have, used in copyRandom's condition
      head.next = newNode;
      head = head.next.next;
    }
  }

  /**
   * After copyNext, it has a new list and I can simply re-order the random pointer
   * 
   * @param head
   */
  private void copyRandom(RandomListNode head) {
    while (head != null) {
      // if (head.random.next != null) {
      if (head.next.random != null) { // test case; -1-> null, [null]
        head.next.random = head.random.next;
      }
      head = head.next.next;
    }
  }

  private RandomListNode splitList(RandomListNode head) {
    RandomListNode dummy1 = new RandomListNode(0), dummy2 =
        new RandomListNode(0);
    RandomListNode ori = dummy1, cpy = dummy2;
    while (head != null) {
      dummy1.next = head;
      dummy2.next = head.next;

      dummy1 = dummy1.next; // always forgot!!!
      dummy2 = dummy2.next;

      head = head.next.next;
    }

    return cpy.next;
  }

  private RandomListNode copyRandomList(RandomListNode head) {
    if (head == null) {
      return null;
    }
    copyNext(head);
    copyRandom(head);
    return splitList(head);
  }



  /*
   * review the basic Java: call by val!
   * So I can change the head's val, but not reference to a new Node!
   */
  public void getNext(RandomListNode head) {
    System.out.println(head.label);
    head.label = head.next.label; // can access/mutate object's field
    head = head.next; // CAN'T change an object reference to something else
  }

  public void play() {
    getNext(head);
    System.out.println(head.label);
  }

  public void playCpyNext() {
    copyNext(head);
    RandomListNode.print(head);
  }

  public static void main(String[] args) {
    CopyRandLL crll = new CopyRandLL();
    // crll.playCpyNext(); review call-by-val on solutionJiuZhang's copyNext
    RandomListNode.print(crll.copyRandomList(crll.head));
  }
}
