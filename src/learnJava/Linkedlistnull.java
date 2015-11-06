package learnJava;

public class Linkedlistnull {
  static class node {
    int value;
    node next;

    node(int v) {
      value = v;
    }

    static void printlist(node root) {
      while (root != null) {
        System.out.println(root.value + " ");
        root = root.next;
      }
    }
  }

  public static void main(String[] args) {
    node yi = new node(1);
    node er = new node(2);

    node si = new node(4);
    yi.next = er;
    er.next = si;

//    test(er);
//    er = new node(100);
//    test2(er);
    plusdc(yi);
//    System.out.println(er.val);
    node.printlist(yi);
  }

  public static void test1(node root) {
    node tmp = root;
//    tmp.val = 99;
    root = new node(100);
  }
  
  public static void test2(node root) {
    node x = new node(33);
    root.value = x.value;
  }

  public static node plus1(node root) {
    if (root == null)
      return null;
    // root.val = 99;
    node tmp = root;
    root = new node(root.value + 1);
    root.next = tmp.next;
    return root;
    // plus1(root.next);
  }
  
  public static void plus2(node root) {
    if (root == null) {
      root = new node(100);
      return;
    }
    plus2(root.next);
  }
  
  /**
   * 
   * @param root
   * @return
   */
  public static node plusdc(node root) {
    if (root == null) {
      root = new node(100);
      return root;
    }
    root.next = plusdc(root.next);
    return root;
  }

}
