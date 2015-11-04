package misc;

import java.util.ArrayList;
import java.util.List;

/**
 * Must master this technique: Priority Queue (this is implementing MinPQ)
 * Inspired by JiuZhang's Hash Heap, it's easier to resize than Algs4's array implement
 * Created this class in misc at 9:10 AM, 11/2/2015.
 */
public class SimplePQ {
  List<Integer> heap;
  int size;

  SimplePQ() {
    heap = new ArrayList<>();
    size = 0;
  }

  int parent(int idx) {
    if (idx == 0) {
      return -1;
    } else {
      return (idx-1) / 2;
    }
  }

  int lson(int idx) {
    return idx * 2 + 1;
  }

  int rson(int idx) {
    return idx * 2 + 2;
  }

  boolean less(int id1, int id2) {
    return heap.get(id1) < heap.get(id2);
  }

  void exch(int id1, int id2) {
    int temp = heap.get(id1);
    heap.set(id1, heap.get(id2));
    heap.set(id2, temp);
  }

  void swim(int k) {
    while (parent(k) > -1) {
      int parentid = parent(k);
      if (less(k, parentid)) {
        exch(k, parentid);
      }
      else {
        break;
      }
      k = parentid;
    }
  }

  void sink(int k) {
    while (lson(k) < heap.size()) {
      int left = lson(k);
      int right = rson(k);
      int son;
      if (right >= heap.size() || less(left, right)) {
        son = left;
      }
      else {
        son = right;
      }
      if (less(k, son)) {
        break;
      }
      else {
        exch(k, son);
      }
      k = son;
    }
  }

  void push(int v) {
    size++;
    heap.add(v);
    swim(heap.size()-1);
  }

  void pop() {
    exch(0, size-1);
    heap.remove(size-1);
    size--;
    sink(0);
  }

  void printPQ() {
    for (int i : heap) {
      System.out.print(i + " ");
    }
    System.out.println();
  }

  void heapify(int[] data) {
    for (int d : data) {
      push(d);
      printPQ();
    }
    int id = 0;
    for (int p : heap) {
      data[id++] = p;
    }
  }

  public static void main(String[] args) {
    int[] data = new int[]{3,2,1,4,5};
    new SimplePQ().heapify(data);
    for (int d : data) {
      System.out.print(d + " ");
    }
  }
}
