package JiuChap6_FollowUpA;

import java.util.ArrayList;

/**
 * O(n) solution: Quick Select
 * http://www.lintcode.com/en/problem/kth-largest-element/
 * Created at 10:41 PM on 11/15/15.
 */
public class KthLargest {
  public static void main(String[] args) {
    int[] A = new int[]{1,1,1}; // {1,2,3,4,5,6,8,9,10,7};
    KthLargest kl = new KthLargest();
    ArrayList<Integer> Alist = new ArrayList<>();
    for (int a : A) {
      Alist.add(a);
    }
    int ans;
    for (int i = 1; i <= A.length; ++i) {
      ans = kl.kthLargestElement(A.length-i+1, Alist);
      System.out.println(ans);
    }
  }

  //param k : description of k
  //param numbers : array of numbers
  //return: description of return
  public int kthLargestElement(int k, ArrayList<Integer> numbers) {
    // write your code here
    if (numbers == null || numbers.size() == 0) {
      return 0;
    }
    if (k <= 0) {
      return 0;
    }

    return getKth(numbers, 0, numbers.size()-1, k); // numbers.size() - k + 1);
    //return getK(k, numbers, 0, numbers.size()-1);
  }

  public int getKth(ArrayList<Integer> nums, int l, int r, int k) {
    if (l == r) {
      return nums.get(l);
    }
    int pivot = nums.get((l+r)/2);
    int par = partition(nums, l, r, pivot);
    if (par + 1 == k) {
      return nums.get(par);
    }
    else if (par + 1 > k) {
      return getKth(nums, l, par-1, k);
    }
    else {
      //return getKth(nums, par+1, r, k - par - 1);
      return getKth(nums, par+1, r, k);
    }
  }

  /**
   * Default implementation of MIT's partition, choosing last element as pivot
   * @param nums
   * @param p
   * @param r
   * @return
   */
  public int partitionMIT(ArrayList<Integer> nums, int p, int r) {
    int i = p-1;
    int x = nums.get(r);
    for (int j = p; j < r; ++j) {
      if (nums.get(j) <= x) {
        i++;
        exch(nums, i, j);
      }
    }
    exch(nums, r, i+1);
    return i+1;
  }

  public int getK(int k, ArrayList<Integer> numbers, int l, int r) {
    if (l == r) {
      return numbers.get(l);
    }
    int pivot = numbers.get((l+r)/2);
    int par = partition(numbers, l, r, pivot);
    if (par + 1 == k) {
      return numbers.get(par);
    }
    else if (par + 1 < k) {
      return getK(k, numbers, par+1, r);
    }
    else {
      return getK(k, numbers, l, par - 1);
    }
  }

  public int partition(ArrayList<Integer> nums, int p, int r, int pivot) {
    int i = p;
    for (int j = p+1; j <= r; ++j) {
      if (nums.get(j) < pivot) {
        ++i;
        exch(nums, i, j);
      }
      else if (nums.get(j) == pivot) {
        exch(nums, p, j);
        j--;
      }
    }
    exch(nums, p, i);
    return i;
  }

  public int paritionRand(ArrayList<Integer> nums, int p, int r) {
    int i = p;
    int x = nums.get((p+r)/2);  // randomly pick pivot
    for (int j = p+1; j <= r; ++j) {
      if (nums.get(j) < x) {
        i++;
        exch(nums, i, j);
      }
      else if (nums.get(j) == x) {
        exch(nums, p, j);
        j--;
      }
    }
    exch(nums, p, i);
    return i;
  }

  private void exch(ArrayList<Integer> nums, int i, int j) {
    int tmp = nums.get(i);
    nums.set(i, nums.get(j));
    nums.set(j, tmp);
  }
}
