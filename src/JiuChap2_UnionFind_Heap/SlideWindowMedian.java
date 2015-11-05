package JiuChap2_UnionFind_Heap;

import java.util.ArrayList;
import misc.HashHeap;

/**
 * Created this class in JiuChap2_UnionFind_Heap at 10:57 PM, 11/4/2015.
 */
public class SlideWindowMedian {

  public static void main(String[] args) {
    //int[] data = new int[] { 1, 2, 7, 7, 2, 10, 3, 4, 5 };
    //int k = 1;
    int[] data = new int[] {142,38,100,53,22,84,168,50,194,136,111,13,47,45,151,164,126,47,106,124,183,8,87,38,91,121,102,46,82,195,53,18,11,165,61}; //{ 1, 2, 7, 7, 2, 10, 3, 4, 5 };
    int k = 35;
    ArrayList<Integer> ans = new SlideWindowMedian().medianSlidingWindow(data, k);
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
    HashHeap lmax = new HashHeap("max");
    HashHeap rmin = new HashHeap("min");

    int median = nums[0];
    for (int i = 0; i < nums.length; ++i) {
      if (i!=0) {
        if (nums[i] < median) {
          lmax.add(nums[i]);
        } else {
          rmin.add(nums[i]);
        }
      }

      if (i >= k) {
        if (median == nums[i-k]) {
          if (lmax.size()>0) {
            median = lmax.pop();
          }
          else if (rmin.size()>0) {
            median = rmin.pop();
          }
        }
        else if (median < nums[i - k]) {
          rmin.remove(nums[i - k]);
        } else {
          lmax.remove(nums[i - k]);
        }
      }

      if (lmax.size() > rmin.size()) {
        rmin.add(median);
        median = lmax.pop();
      } else if (lmax.size() + 1 < rmin.size()) {
        lmax.add(median);
        median = rmin.pop();
      }

      if (i + 1 >= k) {
        result.add(median);
      }
    }

    return result;
  }
}
