package freq3_tony;

import java.util.LinkedList;
import java.util.Queue;

public class BSTOrderRecursive {
  // node STATIC inner class
  private static class Node<T> {
    private T data;
    private Node<T> left, right;

    // need a public ctor
    public Node(T data) {
      this.data = data;
    }

    public void setLeft(Node<T> left) {
      this.left = left;
    }

    public void setRight(Node<T> right) {
      this.right = right;
    }

    public Node<T> getLeft() {
      return this.left;
    }

    public Node<T> getRight() {
      return this.right;
    }
  }
  // 为什么这里用wildcard???
  public static void preorder(Node<?> n) {
    if (n != null) {
      System.out.print(n.data + " ");
      preorder(n.getLeft());
      preorder(n.getRight());
    }
  }

  public static void inorder(Node<?> n) {
    if (n != null) {
      inorder(n.getLeft());
      System.out.print(n.data + " ");
      inorder(n.getRight());
    }
  }

  public static void postorder(Node<?> n) {
    if (n != null) {
      postorder(n.getLeft());
      postorder(n.getRight());
      System.out.print(n.data + " ");
    }
  }

  // level order 就是BFS
  public static void levelorder(Node<?> n) {
    // Queue和LinkedList什么关系?
    Queue<Node<?>> nodequeue = new LinkedList<Node<?>>();
    if (n != null) {
      nodequeue.add(n);
    }
    while (!nodequeue.isEmpty()) {
      Node<?> next = nodequeue.remove();
      System.out.print(next.data + " ");
      if (next.getLeft() != null) {
        nodequeue.add(next.getLeft());
      }
      if (next.getRight() != null) {
        nodequeue.add(next.getRight());
      }
    }
  }

  public static void main(final String[] args) {
    Node<Integer> one = new Node<Integer>(1);
    Node<Integer> two = new Node<Integer>(2);
    Node<Integer> three = new Node<Integer>(3);
    Node<Integer> four = new Node<Integer>(4);
    Node<Integer> five = new Node<Integer>(5);
    Node<Integer> six = new Node<Integer>(6);
    Node<Integer> seven = new Node<Integer>(7);
    Node<Integer> eight = new Node<Integer>(8);
    Node<Integer> nine = new Node<Integer>(9);
    one.setLeft(two);
    one.setRight(three);
    two.setLeft(four);
    two.setRight(five);
    three.setLeft(six);
    four.setLeft(seven);
    six.setLeft(eight);
    six.setRight(nine);
    preorder(one);
    System.out.println();
    inorder(one);
    System.out.println();
    postorder(one);
    System.out.println();
    levelorder(one);
    System.out.println();
  }

}
