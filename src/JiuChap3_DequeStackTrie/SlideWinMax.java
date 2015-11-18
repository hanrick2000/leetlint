package JiuChap3_DequeStackTrie;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import misc.HashHeap;

/**
 * http://www.lintcode.com/en/problem/sliding-window-maximum/
 * Created this class in JiuChap3_DequeStackTrie at 10:23 PM, 11/2/2015.
 */
public class SlideWinMax {

  public static void main(String[] args) {
    SlideWinMax swm = new SlideWinMax();
    int[] data = new int[] {7,3,2,8, 5}; // { 1, 2, 7, 7, 8 };
    ArrayList<Integer> res = swm.maxSlidingWindow(data, 3);
    System.out.println(res);
  }

  /**
   * @param nums: A list of integers.
   * @return: The maximum number inside the window at each moving.
   */
  public ArrayList<Integer> maxSlidingWindow(int[] nums, int k) {
    // write your code here
    ArrayList<Integer> result = new ArrayList<>();
    //result = fornk(nums, k);
    //result = useHashHeap(nums, k);
    result = useDeque(nums, k);
    return result;
  }


/* ------------------------------------------------------------------------------------------------
 *                        Scott's code separator
 * ------------------------------------------------------------------------------------------------
 */

  /**
   * O(n) solution learned from jiuChap, it's better to have another array to save the idx.
   * for Sliding window problem: add first , then remove.
   * @param nums
   * @param k
   * @return
   */
  private ArrayList<Integer> useDeque(int[] nums, int k) {
    ArrayList<Integer> result = new ArrayList<>();
    if (nums == null || nums.length == 0) {
      return result;
    }
    Deque<Integer> deque = new ArrayDeque<>();

    for (int i = 0; i < nums.length; ++i) {
      while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
        deque.pollLast();
      }
      deque.offer(nums[i]);
      if (i + 1 > k && deque.peekFirst() == nums[i-k]) {
        deque.pollFirst();
      }
      if (i + 1 >= k) {
        result.add(deque.peekFirst());
      }
    }

    return result;
  }


/* ------------------------------------------------------------------------------------------------
 *                        Scott's code separator
 * ------------------------------------------------------------------------------------------------
 */

  /**
   * Use hashheap, learned from sliding window median. This is much easier.
   * However, the time complexity is still: O(nlgk)
   * @param nums
   * @param k
   * @return
   */
  private ArrayList<Integer> useHashHeap(int[] nums, int k) {
    ArrayList<Integer> result = new ArrayList<>();
    if (nums == null || nums.length == 0) {
      return result;
    }
    HashHeap maxPQ = new HashHeap("max");

    for (int i = 0; i < nums.length; ++i) {
      maxPQ.add(nums[i]);
      if (i + 1 >= k) {
        if (i+1 > k) {
          maxPQ.remove(nums[i - k]);
        }
        result.add(maxPQ.peek());
      }
    }
    return result;
  }


  /***************************************************************************
   *                      O(nk)
   ***************************************************************************/

  /**
   * Dummy O(nk) method
   * @param nums
   * @param k
   * @return
   */
  private ArrayList<Integer> fornk(int[] nums, int k) {
    ArrayList<Integer> result = new ArrayList<>();

    for (int i = 0; i + k <= nums.length; ++i) {
      int maxi = Integer.MIN_VALUE;
      for (int j = i; j < nums.length && j < i + k; ++j) {
        maxi = Math.max(maxi, nums[j]);
      }
      result.add(maxi);
    }

    return result;
  }

  /**
   * Using Hash Heap to able remove(idx) and add(v)
   *
   * @param nums
   * @param k
   * @return
   */
  private ArrayList<Integer> forNlgK(int[] nums, int k) {
    ArrayList<Integer> result = new ArrayList<>();
    return result;
  }
}
