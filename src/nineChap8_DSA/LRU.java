package nineChap8_DSA;

import java.util.*;

/**
 * https://sites.google.com/site/jennyshelloworld/company-blog/chapter-8---data-structure
 * I think this lecture is much better than the one I took in 9Chap!
 * 
 * @author tzhang
 *
 */
public class LRU {
  static class Node {
    int key;
    int value;
    Node pre;
    Node next;

    Node(int k, int v) {
      key = k;
      value = v;
    }
  }

  private int capacity;
  Map<Integer, Node> map = new HashMap<>();
  Node head = new Node(-1, -1);
  Node tail = new Node(-1, -1);

  // @param capacity, an integer
  public LRU(int capacity) {
    // write your code here
    this.capacity = capacity;
    head.next = tail;
    tail.pre = head;
  }

  // @return an integer
  public int get(int key) {
    // write your code here
    if (!map.containsKey(key)) {
      return -1;
    }
    Node recent = map.get(key);
    // move this node to the tail, so the list head always point to the LR node!
    recent.pre.next = recent.next;
    recent.next.pre = recent.pre;

    recent.pre = tail.pre;
    tail.pre = recent;
    recent.pre.next = recent;
    recent.next = tail;

    return recent.value;
  }

  // @param key, an integer
  // @param value, an integer
  // @return nothing
  public void set(int key, int value) {
    // step 1: check if the key is already in LRU
    if (get(key) != -1) { // Scott's favorite: process in conditional statement so cool, also
                          // get(key) is well designed API!
      map.get(key).value = value;
      return;
    }

    /**
     * step 2: check if full, don't worry, no duplicate in LRU, since set is just update duplicate
     * node.
     * In this scenario, just remove LR node, which means the head's next node and insert new node
     * before tail.
     */
    if (map.size() == capacity) {
      map.remove(head.next.key);
      head.next = head.next.next;
      head.next.pre = head;
    }

    // step 3: enough capacity scenario
    Node insert = new Node(key, value);
    map.put(key, insert);
    insert.pre = tail.pre;
    tail.pre = insert;
    insert.pre.next = insert;
    insert.next = tail;
  }

  private int size() {
    return map.size();
  }

  private void print() {
    Node h = head.next;
    Node t = tail.pre;
    while (h != null) {
      System.out.print("[" + h.key + ", " + h.value + "] ");
      h = h.next;
    }
    System.out.println();
    while (t != null) {
      System.out.print("[" + t.key + ", " + t.value + "] ");
      t = t.pre;
    }
    System.out.println();

  }

  public static void main(String[] args) {
    LRU lru = new LRU(3);
    lru.set(1, 101);
    lru.set(2, 102);
    lru.set(3, 103);
    lru.print();
    lru.set(7, 107);
    lru.set(2, 107);
    lru.print();
  }
}
