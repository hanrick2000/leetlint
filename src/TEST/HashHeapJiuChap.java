package TEST;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 9 chap hashheap, but still have issue when removing from 2 element heap!
 * http://www.jiuzhang.com/solutions/hash-heap/
 * Created this class in TEST at 11:50 PM, 11/4/2015.
 */
public class HashHeapJiuChap {
  ArrayList<Integer> heap;
  String mode;
  int size_t;
  HashMap<Integer, Node> hash;

  class Node {
    public Integer id;
    public Integer num;

    Node(Node now) {
      id = now.id;
      num = now.num;
    }

    Node(Integer first, Integer second) {

      this.id = first;
      this.num = second;
    }
  }

  public HashHeapJiuChap(String mod) { // 传入min 表示最小堆，max 表示最大堆
    // TODO Auto-generated constructor stub
    heap = new ArrayList<Integer>();
    mode = mod;
    hash = new HashMap<Integer, Node>();
    size_t = 0;
  }

  int peak() {
    return heap.get(0);
  }

  int size() {
    return size_t;
  }

  Boolean empty() {
    return (heap.size() == 0);
  }

  int parent(int id) {
    if (id == 0) {
      return -1;
    }
    return (id - 1) / 2;
  }

  int lson(int id) {
    return id * 2 + 1;
  }

  int rson(int id) {
    return id * 2 + 2;
  }

  boolean comparesmall(int a, int b) {
    if (a <= b) {
      if (mode == "min")
        return true;
      else
        return false;
    } else {
      if (mode == "min")
        return false;
      else
        return true;
    }

  }

  void swap(int idA, int idB) {
    int valA = heap.get(idA);
    int valB = heap.get(idB);

    int numA = hash.get(valA).num;
    int numB = hash.get(valB).num;
    hash.put(valB, new Node(idA, numB));
    hash.put(valA, new Node(idB, numA));
    heap.set(idA, valB);
    heap.set(idB, valA);
  }

  Integer poll() {
    size_t--;
    Integer now = heap.get(0);
    Node hashnow = hash.get(now);
    if (hashnow.num == 1) {
      swap(0, heap.size() - 1);
      hash.remove(now);
      heap.remove(heap.size() - 1);
      if (heap.size() > 0) {
        siftdown(0);
      }
    } else {
      hash.put(now, new Node(0, hashnow.num - 1));
    }
    return now;
  }

  void add(int now) {
    size_t++;
    if (hash.containsKey(now)) {
      Node hashnow = hash.get(now);
      hash.put(now, new Node(hashnow.id, hashnow.num + 1));

    } else {
      heap.add(now);
      hash.put(now, new Node(heap.size() - 1, 1));
    }

    siftup(heap.size() - 1);
  }

  void remove(int now) {
    size_t--;
    ;
    Node hashnow = hash.get(now);
    int id = hashnow.id;
    int num = hashnow.num;
    if (hashnow.num == 1) {

      swap(id, heap.size() - 1);
      hash.remove(now);
      heap.remove(heap.size() - 1);
      if (heap.size() > id) {
        siftup(id);
        siftdown(id);
      }
    } else {
      hash.put(now, new Node(id, num - 1));
    }
  }

  void siftup(int id) {
    while (parent(id) > -1) {
      int parentId = parent(id);
      if (comparesmall(heap.get(parentId), heap.get(id)) == true) {
        break;
      } else {
        swap(id, parentId);
      }
      id = parentId;
    }
  }

  void siftdown(int id) {
    while (lson(id) < heap.size()) {
      int leftId = lson(id);
      int rightId = rson(id);
      int son;
      if (rightId >= heap.size() || (comparesmall(heap.get(leftId), heap.get(rightId)) == true)) {
        son = leftId;
      } else {
        son = rightId;
      }
      if (comparesmall(heap.get(id), heap.get(son)) == true) {
        break;
      } else {
        swap(id, son);
      }
      id = son;
    }
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
    //hh.printPQ(); // FT, I forgot to add "hh." before printPQ, therefore it's printing nothing since class val not object!!!
  }

  public static void main(String[] args) {
    int[] data = new int[] {1, 1, 3}; // {10,17,13,20,23,23};
    HashHeapJiuChap test = new HashHeapJiuChap("min");
    test.heapify(data);
    //test.remove(17);
    test.remove(3);
    test.remove(3);
    test.remove(1);
    test.printPQ();
  }
}
