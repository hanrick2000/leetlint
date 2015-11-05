package testingAns;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created this class in testingAns at 1:46 PM, 11/5/2015.
 */
public class SlideMedian9chap {

  public static void main(String[] args) {
    int[] data = new int[] {142,38,100,53,22,84,168,50,194,136,111,13,47,45,151,164,126,47,106,124,183,8,87,38,91,121,102,46,82,195,53,18,11,165,61}; //{ 1, 2, 7, 7, 2, 10, 3, 4, 5 };
    int k = 35;
    ArrayList<Integer> ans = new SlideMedian9chap().medianSlidingWindow(data, k);
    System.out.println(ans);
  }

  /*
   * @param nums
   *            : A list of integers.
   * @return: The median of the element inside the window at each moving.
   */
  public ArrayList<Integer> medianSlidingWindow(int[] nums, int k) {
    // write your code here

    ArrayList<Integer> ans = new ArrayList<Integer>();
    if (nums.length == 0)
      return ans;
    int median = nums[0];
    HashHeap minheap = new HashHeap("min");
    HashHeap maxheap = new HashHeap("max");
    for (int i = 0; i < nums.length; i++) {
      if (i != 0) {
        if (nums[i] > median) {
          minheap.add(nums[i]);
        } else {
          maxheap.add(nums[i]);
        }
      }

      if (i >= k) {
        if (median == nums[i - k]) {
          if (maxheap.size() > 0) {
            median = maxheap.poll();
          } else if (minheap.size() > 0) {
            median = minheap.poll();
          }

        } else if (median < nums[i - k]) {
          minheap.delete(nums[i - k]);
        } else {
          maxheap.delete(nums[i - k]);
        }
      }

      while (maxheap.size() > minheap.size()) {
        minheap.add(median);
        median = maxheap.poll();
      }
      while (minheap.size() > maxheap.size() + 1) {
        maxheap.add(median);
        median = minheap.poll();
      }

      if (i + 1 >= k) {
        ans.add(median);
      }
    }
    return ans;
  }

  class HashHeap {
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

    public HashHeap(String mod) {
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

    void delete(int now) {
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
  }
}


