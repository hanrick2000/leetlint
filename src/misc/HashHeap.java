package misc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Very fruitful and learn the design of data structure and meaning of it.
 * http://www.jiuzhang.com/solutions/hash-heap/
 * Created this class in misc at 12:09 AM, 11/4/2015.
 */
public class HashHeap {
  String mode;
  List<Integer> heap;
  Map<Integer, Node> hash;  // learn how to use HashMap! the key is the number, and the value is the pointer(aka idx)
  int size;  // to solve duplicate value, so size is the real size while heap.size is distinct val's number

  class Node {
    Integer id;  // the idx
    Integer num; // the count, solve the duplicate issue

    Node(Node now) {
      id = now.id;
      num = now.num;
    }

    Node(Integer id, Integer num) {
      this.id = id;
      this.num = num;
    }
  }

  public HashHeap(String MODE) {
    mode = MODE;
    heap = new ArrayList<>();
    hash = new HashMap<>();
    size = 0;
  }

  public int peak() {
    return heap.get(0);
  }

  public boolean isEmpty() {
    return heap.size() == 0;
  }

  public int size() {return size; }

  int parent(int id) {
    if (id == 0) {
      return -1; // why set -1, not other number?
    }
    else  return (id-1)/2; // notice not id/2
  }

  // why no check for valid
  int lson(int id) {
    return id * 2 + 1;
  }

  // do eval when use it
  int rson(int id) {
    return id * 2 + 2;
  }

  boolean less(int p, int q) {
    if (mode == "min") {
      return p <= q;
    }
    else {
      return q <= p;
    }
  }

  void exch(int i, int j) {
    int Vi = heap.get(i);
    int Vj = heap.get(j);
    Node ni = hash.get(Vi);
    Node nj = hash.get(Vj);
    heap.set(j, Vi);
    heap.set(i, Vj);
    hash.put(Vi, new Node(j, ni.num));
    hash.put(Vj, new Node(i, nj.num));
  }

  void swim(int i) {
    while (parent(i) > -1) {
      int parentId = parent(i);
      if (less(heap.get(i), heap.get(parentId))) {
        exch(i, parentId);
      }
      i = parentId;
    }
  }

  void sink(int i) {
    while (lson(i) < heap.size()) {
      int left = lson(i);
      int right = rson(i);
      int son = -1;
      if (right >= heap.size() || less(heap.get(left), heap.get(right))) {
        son = left;
      }
      else {
        son = right;
      }
      if (less(heap.get(son), heap.get(i))) {
        exch(i, son);
      }
      else {
        break;
      }
      i = son;
    }
  }

  public void add(int now) {
    size++;
    if (hash.containsKey(now)) {
      Node hashnow = hash.get(now);
      hash.put(now, new Node(hashnow.id, hashnow.num+1));
    }
    else {
      heap.add(now);
      hash.put(now, new Node(heap.size()-1, 1));
      swim(heap.size()-1);
    }
  }

  /**
   * Always double check if it's the last one to remove!
   *
   * @param now
   */
  public void remove(int now) {
    size--;
    Node hashnow = hash.get(now);
    int id = hashnow.id;
    int num = hashnow.num;
    if (num > 1) {  // I was wrongly put id here last time...
      hash.put(now, new Node(id, num-1));
    }
    else {
      exch(id, heap.size()-1);
      heap.remove(heap.size()-1);
      hash.remove(now);
      if (heap.size() > id) {  // need to check if valid: say, now the heap is {4}, but 4's id is still 1, can't do heap.get(0)!
        if (mode == "max") swim(id);  // Now I know why 9chap called swim, it's for maxPQ case.
        else sink(id);
      }
    }
  }

  public int pop() {
    int root = peak();
    remove(root);
    return root;
  }

  void printPQ() {
    if (heap.size() == 0) {
      System.out.println("HashHeap is empty!");
    }
    for (int v : heap) {
      System.out.print(v + " ");
    }
    System.out.println();
  }

  public void heapify(int[] A) {
    //HashHeap hh = new HashHeap();     NOT fully understand OOP!
    for (int a : A) {
      //hh.add(a);
      this.add(a);
    }
    int id = 0;
    for (int v : this.heap) { //hh.heap) {
      A[id++] = v;
    }
    this.printPQ();
    //hh.printPQ(); // FT, I forgot to add "hh." before printPQ, therefore it's printing nothing since class value not object!!!
  }

  public static void main(String[] args) {
    int[] data = new int[] {1, 1, 3}; // {10,17,13,20,23,23};
    HashHeap test = new HashHeap("min");
    test.heapify(data);
    //test.remove(17);
    test.remove(3);
    test.remove(3);
    test.remove(1);
    test.printPQ();
  }
}

/* ------------------------------------------------------------------------------------------------
 *                        Scott's code separator: below is the old implement
 * ------------------------------------------------------------------------------------------------
 */

//public class HashHeap {
//  List<Integer> heap;
//  Map<Integer, Integer> map;
//  int size;
//
//  HashHeap() {
//    heap = new ArrayList<>();
//    map = new HashMap<>();
//    size = 0;
//  }
//
//  int parent(int i) {
//    if (i == 0) {
//      return -1;
//    }
//    else {
//      return (i-1) / 2;
//    }
//  }
//
//  int lson(int i) {
//    return 2 * i + 1;
//  }
//
//  int rson(int i) {
//    return 2 * i + 2;
//  }
//
//  void swim(int i) {
//    while (parent(i) > -1) {
//      int parentId = parent(i);
//      if (less(i, parentId)) {
//        exch(i, parentId);
//      }
//      i = parentId;
//    }
//  }
//
//  void sink(int i) {
//    while (lson(i) < size) {
//      int left = lson(i);
//      int right = rson(i);
//      int son = -1;
//      if (right >= size-1 || less(left, right)) {
//        son = left;
//      }
//      else {
//        son = right;
//      }
//      if (less(son, i)) {
//        exch(son, i);
//      }
//      else {
//        break; // no do nothing!
//      }
//      i = son;
//    }
//  }
//
//  boolean less(int i, int j) {
//    return heap.get(i) < heap.get(j);
//  }
//
//  void exch(int i, int j) {
//    int Vi = map.get(i);
//    int Vj = map.get(j);
//    map.put(i, Vj);
//    map.put(j, Vi);
//  }
//
//  void push(int x) {
//    heap.add(x);
//    size++;
//    map.put(size, x);
//    swim(size-1);
//  }
//
//  int pop() {
//    int min = map.get(0);
//    exch(0, size-1);
//    heap.remove(size-1);
//    map.remove(size-1);
//    size--;
//    sink(0);
//    return min;
//  }
//
//  void heapify(int[] A) {
//    HashHeap hpq = new HashHeap();
//    for (int a : A) {
//      hpq.push(a);
//    }
//    printPQ();
//  }
//
//  void printPQ() {
//    for (int i = 0; i < size; ++i) {
//      System.out.println(heap.get(i) + " " + map.get(i));
//    }
//  }
//}
