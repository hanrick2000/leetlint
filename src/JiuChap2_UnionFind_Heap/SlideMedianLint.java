package JiuChap2_UnionFind_Heap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created this class in JiuChap2_UnionFind_Heap at 8:51 AM, 11/5/2015.
 */
public class SlideMedianLint {

  public static void main(String[] args) {
    int[] data = new int[]{1,2,7,8,5};
    int k = 1;
    ArrayList<Integer> ans = new SlideMedianLint().medianSlidingWindow(data, k);
    System.out.println(ans);
  }
  /**
   * @param nums: A list of integers.
   * @return: The median of the element inside the window at each moving.
   */
  public ArrayList<Integer> medianSlidingWindow(int[] nums, int k) {
    // write your code here
    if (nums == null || nums.length == 0) {
      return null;
    }
    ArrayList<Integer> result = new ArrayList<>();
    int median = nums[0];
    HashHeap lmaxq = new HashHeap("max");
    HashHeap rminq = new HashHeap("min");

    for (int i = 1; i < nums.length; ++i) {
      if (lmaxq.size() + 1 + rminq.size() == k) {
        lmaxq.poll();
      }

      if (nums[i] <= median) {
        lmaxq.add(nums[i]);
      }
      else {
        rminq.add(nums[i]);
      }

      if (lmaxq.size() > rminq.size()) {
        rminq.add(median);
        median = lmaxq.poll();
      }
      else if (lmaxq.size()+1 < rminq.size()) {
        lmaxq.add(median);
        median = rminq.poll();
      }

      if (lmaxq.size() + 1 + rminq.size() == k) {
        result.add(median);
      }
    }

    return result;
  }


  class HashHeap {
    int size;
    String mode;
    List<Integer> heap;
    Map<Integer, Node> hash;

    class Node {
      public int id;
      public int num;

      Node(int id, int num) {
        this.id = id;
        this.num = num;
      }
    }

    HashHeap(String MODE) {
      size = 0;
      mode = MODE;
      heap = new ArrayList<>();
      hash = new HashMap<>();
    }

    int size() {
      return size;
    }

    int parent(int i) {
      if (i == 0) {
        return -1;
      }
      else  return (i-1)/2;
    }

    int lson(int i) {
      return 2*i+1;
    }

    int rson(int i) {
      return 2*i+2;
    }

    /**
     * Less != compare. So less return boolean, compare return int!
     * @param p
     * @param q
     * @return
     */
    boolean less(int p, int q) {
      if (mode == "min") {
        return p<=q;
      }
      else {
        return q<=p;
      }
    }

    void exch(int i, int j) {
      int Vi = heap.get(i);
      int Vj = heap.get(j);
      Node Ni = hash.get(Vi);
      Node Nj = hash.get(Vj);
      heap.set(i, Vj);
      heap.set(j, Vi);
      hash.put(Vi, new Node(j, Ni.num));
      hash.put(Vj, new Node(i, Nj.num));
    }

    void swim(int i) {
      while (parent(i) > -1) {
        int parentid = parent(i);
        if (less(heap.get(i), heap.get(parentid))) {
          exch(i, parentid);
        }
        i = parentid;
      }
    }

    void sink(int i) {
      while (lson(i) < heap.size()) {
        int left = lson(i);
        int right = rson(i);
        int son = -1;
        if (right >= heap.size() || less(heap.get(left), heap.get(right))) {  // the less delegate the min/max.
          son = left;
        }
        else {
          son = right;
        }
        if (less(heap.get(son), heap.get(i))) {
          exch(son, i);
        }
        else {
          break;
        }
        i = son;
      }
    }

    void add(int now) {
      size++;
      if (hash.containsKey(now)) {
        int nowId = hash.get(now).id;
        int nowNum = hash.get(now).num;
        Node node = new Node(nowId, nowNum+1);
        hash.put(now, node);
      }
      else {
        heap.add(now);
        hash.put(now, new Node(heap.size()-1, 0));
        swim(heap.size()-1);
      }
    }

    void delete(int now) {
      size--;
      if (hash.get(now).num > 1) {
        hash.put(now, new Node(hash.get(now).id, hash.get(now).num-1));
      }
      else { // unique now to be removed
        int nowId = hash.get(now).id;
        exch(nowId, heap.size()-1);
        hash.remove(now);
        heap.remove(heap.size()-1);
        if (heap.size() > nowId) {
          if (mode == "min")  sink(nowId);
          else  swim(nowId);
        }
      }
    }

    int poll() {
      int Vi = heap.get(0);
      delete(Vi);
      return Vi;
    }

    int peak() {
      return heap.get(0);
    }
  }

}
