package JiuChap2_UnionFind_Heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * http://www.lintcode.com/en/problem/heapify/
 * Created this class in JiuChap2_UnionFind_Heap at 12:12 AM, 11/2/2015.
 */
public class Heapify {
  public static void main(String[] args) {
    int[] data = new int[]{3,2,1,4,5};
    new Heapify().heapifyHeap(data);
    for (int d : data) {
      System.out.print(d + " ");
    }
  }

  public void heapifyHeap(int[] A) {
    Heap pq = new Heap();
    for (int i : A) {

      pq.add(i);
      pq.printPQ();
    }

    int id = 0;
    for (int h : pq.heap) {
      A[id++] = h;
    }
  }

  private class Heap {
    List<Integer> heap;
    int size;

    Heap() {
      heap = new ArrayList<>();
      size = 0;
    }

    int root() {
      return heap.get(0);
    }

    int size() {
      return size;
    }

    int parent(int id) {
      if (id == 0) {
        return -1;
      }
      return (id-1) / 2;
    }

    int lson(int id) {
      return id * 2 + 1;
    }

    int rson(int id) {
      return id * 2 + 2;
    }

    boolean less(int p, int q) {
      return heap.get(p) < heap.get(q);
    }

    void exch(int p, int q) {
      int temp = heap.get(p);
      heap.set(p, heap.get(q));
      heap.set(q, temp);
    }

    void siftup(int id) {
      while (parent(id) > -1) {
        int parentId = parent(id);
        if (less( id, parentId)) {
          exch(id, parentId);
        }
        else {
          break;
        }
        id = parentId;
      }
    }

    void siftdown(int id) {
      while (lson(id) < heap.size()) {
        int leftId = lson(id);
        int rightId = rson(id);
        int son;
        if (rightId >= heap.size() || less(leftId, rightId)) {
          son = leftId;
        }
        else {
          son = rightId;
        }
        if (less(id, son)) {
          break;
        }
        else {
          exch(id, son);
        }
        id = son;
      }
    }

    void add(int now) {
      size++;
      heap.add(now);
      siftup(heap.size() - 1);
    }

    void delete() {
      exch(0, size-1);
      heap.remove(size-1);
      size--;
      siftdown(0);
    }

    void printPQ() {
      for (int i : heap) {
        System.out.print(i + " ");
      }
      System.out.println();
    }
  }

/* ------------------------------------------------------------------------------------------------
 *                    Scott's code separator: below is the old method: BFS
 * ------------------------------------------------------------------------------------------------
 */
  /**
   * @param A: Given an integer array
   * @return: void
   */
  public void heapifyDummy(int[] A) {
    // write your code here
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for (int i : A) {
      pq.add(i);
    }
    int j = 0;
    for (int p : pq) {
      A[j++] = p;
    }
  }
}
