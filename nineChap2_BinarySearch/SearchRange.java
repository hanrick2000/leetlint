package nineChap2_BinarySearch;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a sorted array of integers, find the starting and ending position of a given target value.
 * 
 * @author tzhang
 *
 */
public class SearchRange {
  public static void main(String[] args) {
    int[] num =
        new int[] {-1, 0, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 6, 90, 92, 93,
            101}; // {1,2,3,9,10}; // new int[]{5, 7, 7, 8, 8, 10};
    // int[] res = searchRange(num, 9);
    // System.out.println(res[0] + " " + res[1]);
    List<Integer> input = new ArrayList<>();
    for (int i : num)
      input.add(i);
    List<Integer> ans = new ArrayList<>();
    ans = searchRange(input, 2);
    System.out.println(ans);
  }

  public static ArrayList<Integer> searchRange(List<Integer> A, int target) {
    // write your code here
    ArrayList<Integer> ans = new ArrayList<Integer>(2);
    // ans.set(0, -1);
    // ans.set(1, -1);
    ans.add(-1);
    ans.add(-1);
    if (A == null || A.size() == 0)
      return ans;
    int lo = 0, hi = A.size() - 1, mid = 0;
    // left
    while (lo + 1 < hi) {
      mid = (hi + lo) / 2;
      if (A.get(mid) >= target)
        hi = mid;
      else
        lo = mid;
    }
    if (A.get(hi) == target)
      ans.set(0, hi);
    if (A.get(lo) == target)
      ans.set(0, lo);

    lo = ans.get(0);
    hi = A.size() - 1;
    mid = 0;
    while (lo + 1 < hi) {
      mid = (hi + lo) / 2;
      if (A.get(mid) <= target)
        lo = mid;
      else
        hi = mid;
    }
    if (A.get(lo) == target)
      ans.set(1, lo);
    if (A.get(hi) == target)
      ans.set(1, hi);
    return ans;
  }

  public static int[] searchRange(int[] A, int target) {
    int[] ans = new int[] {-1, -1};
    if (A == null || A.length == 0)
      return ans;
    // left border
    int lo = 0, hi = A.length - 1, mid = 0;
    while (lo + 1 < hi) {
      mid = (hi + lo) / 2;
      if (A[mid] <= target) {
        hi = mid;
      } else
        lo = mid;
    }
    if (A[hi] == target) {
      ans[0] = hi;
    }
    if (A[lo] == target) {
      ans[0] = lo;
    }

    // right border
    lo = ans[0];
    hi = A.length - 1;
    mid = 0;
    while (lo + 1 < hi) {
      mid = (hi + lo) / 2;
      if (A[mid] >= target) {
        lo = mid;
      } else
        hi = mid;
    }
    if (A[lo] == target) {
      ans[1] = lo;
    }
    if (A[hi] == target) {
      ans[1] = hi;
    }
    return ans;
  }

  /**
   * http://blog.csdn.net/linhuanmars/article/details/20593391
   * 
   * @param A
   * @param target
   * @return
   */
  public static int[] searchRangeGanker(int[] A, int target) {
    int[] res = new int[] {-1, -1};
    if (A == null || A.length == 0)
      return res;
    // 先用binary search找左边界.
    int len = A.length;
    int lo = 0, hi = len - 1;
    int mid;
    while (lo <= hi) {
      mid = lo + (hi - lo) / 2;
      if (A[mid] < target)
        lo = mid + 1;
      else if (A[mid] > target)
        hi = mid - 1;
      else
        hi = mid - 1; // key : 相等的时候还向左二分查找
    }
    res[0] = lo;
    // 再用binary search找右边界
    lo = 0;
    hi = len - 1;
    while (lo <= hi) {
      mid = lo + (hi - lo) / 2;
      if (A[mid] < target) {
        lo = mid + 1;
      } else if (A[mid] > target) {
        hi = mid - 1;
      } else {
        lo = mid + 1; // key : 找到的时候继续向右二分查找.
      }
    }
    res[1] = hi;

    if (res[0] <= res[1])
      return res;
    else
      return new int[] {-1, -1};
  }
}
