package Easy;

/*-
 * In this package, I practice the basic while/for loop, to remember how to get 
 * the N-th element in array, list, etc.
 * 
 * I always confused if I got the N-th or (N-1)-th during loops, eg: reverse 
 * linked list II.
 * 
 * @author tzhang
 *
 */
public class GetMth {
  
  private static class ListNode {
    private int val;
    private ListNode next;
    
    ListNode(int v) {
      this.val = v;
      this.next = null;
    }
    
    public static ListNode buildList(int[] arr) {
      ListNode dummy = new ListNode(0), head;
      head = dummy;
      for (int i = 0; i < arr.length; ++i) {
        head.next = new ListNode(arr[i]);
        head = head.next;
      }
      return dummy.next;
    }
    
    public static void printList(ListNode head) {
      while (head != null) {
        System.out.print(head.val);
        head = head.next;
        if (head != null) {
          System.out.print("->");
        }
        else {
          System.out.println();
        }
      }
    }
  }

  /**
   * http://stackoverflow.com/questions/25538672/post-increment-in-while-loop-in-c
   * 
   * @param arr
   */
  public static void testPrePostIncrementWhile(int[] arr) {

    // test pre-increment in while expression
    System.out.println("test pre-increment in while expression");
    int cntPre = 0, cntPost = 0;
    while (++cntPre < 5) {
      System.out.println(cntPre);
    }

    System.out.println();
    while (cntPost++ < 5) {
      System.out.println(cntPost);
    }

    // test increment in while loop
    System.out.println("test increment in while loop");
    cntPre = 0;
    cntPost = 0;

    while (cntPre < 5) {
      System.out.println(cntPre);
      ++cntPre;
    }

    System.out.println();
    while (cntPost < 5) {
      System.out.println(cntPost);
      cntPost++;
    }

    System.out.println("test increment in for loop");
    for (cntPre = 0; cntPre < 5; ++cntPre) {
      System.out.println(cntPre);
    }

    System.out.println();
    for (cntPost = 0; cntPost < 5; cntPost++) {
      System.out.println(cntPost);
    }

    System.out.println("\nLoop to while equation");
    // for is compiled to while loop
    for (int i = 0; i < 3; ++i) {
      System.out.println("I'm for loop");
    }
    System.out.println();
    int j = 0;
    while (j < 3) {
      System.out.println("I'm the equivalent while loop");
      ++j;
    }
  }

  public static int findNthArray(int[] arr, int N) {
    int cnt = 0, Nth = -1;
    while (cnt++ < N) {
      Nth = -1;
      if (cnt == N) {
        Nth = arr[cnt];
        break;
      }
    }

    return Nth;
  }

  public GetMth() {
    int[] A = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    // int res = findNthArray(A, 5);
    // System.out.println(res);

//    testPrePostIncrementWhile(A);
    
    ListNode root = ListNode.buildList(A);
    ListNode.printList(root);
    
    int idx = 0;
    ListNode preM = root;
    while (idx++ < 5) {
      preM = preM.next;
    }
    
    /* 
    while (idx < 5) {
      idx++;
      preM = preM.next;
    }
    */
    
    System.out.println(root.val);

  }

  public static void main(String[] args) {
    GetMth gm = new GetMth();
  }
}
