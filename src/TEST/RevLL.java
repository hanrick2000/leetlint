package TEST;

/**
 * Created this class in TEST at 12:47 PM, 10/31/2015.
 */
public class RevLL {
  private static class Node{
    int value;
    Node next;
    Node(int v) {
      this.value = v;
      this.next = null;
    }

  }

  /**
   * Better than my original solution.
   *
   * @param cur
   * @return
   */
  public Node rev(Node cur) {
    Node pre = null;

    while (cur != null) {
      Node post = cur.next;
      cur.next = pre;
      pre = cur;
      cur = post;
    }

    return pre;
  }

  public Node rev2(Node cur) {
    if (cur == null) {
      return cur;
    }

    Node pre = null;

    while (cur.next != null) {
      Node post = cur.next;
      cur.next = pre;
      pre = cur;
      cur = post;
    }

    cur.next = pre;

    return cur;
  }

  public void test() {
    Node er = new Node(2);
    Node sa = new Node(3);
    Node wu = new Node(5);
    Node li = new Node(6);
    er.next = sa;
    sa.next = wu;
    wu.next = li;

    Node res = rev(er);
    while (res != null) {
      System.out.print(res.value + " ");
      res = res.next;
    }
  }
  public static void main(String[] args) {
    RevLL rl = new RevLL();
    rl.test();
  }
}
